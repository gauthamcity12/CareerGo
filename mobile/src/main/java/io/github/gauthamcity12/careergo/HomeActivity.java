package io.github.gauthamcity12.careergo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class HomeActivity extends Activity {

    private RecyclerView recycler;
    private LinearLayoutManager rLayoutManager;
    private RecyclerAdapter rAdapter;
    private boolean connected;
    private final static UUID PEBBLE_APP_UUID = UUID.fromString("ce529649-6b83-4f97-8ff4-eacb9d8b74da");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        CompanyApp myApp = new CompanyApp();

        recycler.setHasFixedSize(true);
        rLayoutManager = new LinearLayoutManager(this);
        rLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rAdapter = new RecyclerAdapter(getCompanies());

        recycler.setLayoutManager(rLayoutManager);
        recycler.setAdapter(rAdapter);
        recycler.setItemAnimator(new DefaultItemAnimator());

        connected = PebbleKit.isWatchConnected(getApplicationContext());

        PebbleKit.registerReceivedAckHandler(getApplicationContext(), new PebbleKit.PebbleAckReceiver(PEBBLE_APP_UUID) {
            @Override
            public void receiveAck(Context context, int transactionId) {
                Log.i(getLocalClassName(), "Received ack for transaction " + transactionId);
            }
        });

        PebbleKit.registerReceivedNackHandler(getApplicationContext(), new PebbleKit.PebbleNackReceiver(PEBBLE_APP_UUID) {
            @Override
            public void receiveNack(Context context, int transactionId) {
                Log.i(getLocalClassName(), "Received nack for transaction " + transactionId);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_new) {
            Intent intent = new Intent(this, AddNewActivity.class);
            startActivity(intent);
        }

        if (id == R.id.delete) {
            SQLiteDatabase db = CompanyInfoStore.getInstance(this).getWritableDatabase();
            db.delete(CompanyInfoStore.TABLE_NAME, null, null);
            db.close();
            recycler.setAdapter(new RecyclerAdapter(getCompanies()));
        }

        return super.onOptionsItemSelected(item);
    }

    public List<CompanyInfo> getCompanies(){
        ArrayList<CompanyInfo> list = new ArrayList<>();

        String query = "SELECT * FROM "+CompanyInfoStore.TABLE_NAME;
        SQLiteDatabase db = CompanyInfoStore.getInstance(this).getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PebbleDictionary dict = new PebbleDictionary();


        CompanyInfo ci;
        int counter = 0;
        if(cursor.moveToFirst()){ // checks the first row
            ci = new CompanyInfo(cursor.getString(1), cursor.getString(2)+cursor.getString(3), cursor.getString(4));

            list.add(ci);
        }
        while (cursor.moveToNext()){ // checks the rest of the rows
            counter += 5;
            ci = new CompanyInfo(cursor.getString(1), cursor.getString(2)+cursor.getString(3), cursor.getString(4));
            dict.addString(counter, cursor.getString(1));
            dict.addString(counter+1, cursor.getString(2)+cursor.getString(3));
            dict.addString(counter+2, cursor.getString(4));
            list.add(ci);
        }
        db.close();

        if(list.isEmpty()){
            list.add(new CompanyInfo("No Companies Yet!", "Add some", "information"));
            return list;
        }
        PebbleKit.sendDataToPebble(getApplicationContext(), PEBBLE_APP_UUID,dict);
        return list;
    }


}
