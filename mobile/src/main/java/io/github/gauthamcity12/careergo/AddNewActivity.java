package io.github.gauthamcity12.careergo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class AddNewActivity extends Activity {

    private TextView name;
    private TextView pos;
    private TextView pos2;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.editName);
        name.setFocusable(true);
        name.requestFocus();
        pos = (TextView) findViewById(R.id.editPos);
        pos2 = (TextView) findViewById(R.id.editPos2);
        desc = (TextView) findViewById(R.id.editDesc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getValues(View view){
        Random rand = new Random();
        String val = name.getText().toString();
        String val2 = pos.getText().toString();
        String val3 = desc.getText().toString();

        if((val.equals("") )|| (val2.equals("")) || (val3.equals(""))){
            Toast.makeText(this, "Please fill in all the necesssary fields", Toast.LENGTH_SHORT).show();
        }
        else{
            SQLiteDatabase db = CompanyInfoStore.getInstance(this).getWritableDatabase();

            ContentValues newValue = new ContentValues();
            newValue.put(CompanyInfoStore.KEY_ID, rand.nextInt());
            newValue.put(CompanyInfoStore.KEY_NAME, val);
            newValue.put(CompanyInfoStore.KEY_POS, val2);
            newValue.put(CompanyInfoStore.KEY_DETAILS, val3);
            if(!(pos2.getText().toString()).equals("")){
                newValue.put(CompanyInfoStore.KEY_POS2, ", "+(pos2.getText().toString()));
            }
            else{
                newValue.put(CompanyInfoStore.KEY_POS2, "");
            }
            db.insert(CompanyInfoStore.TABLE_NAME, null, newValue);
            db.close();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }


}
