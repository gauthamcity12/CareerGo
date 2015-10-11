package io.github.gauthamcity12.careergo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CompanyInfoStore extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CompanyInfoStore.db";
    public static final String TABLE_NAME = "CompanyInfo";
    public static final String KEY_ID = "RowID";
    public static final String KEY_NAME = "CompanyName";
    public static final String KEY_POS = "Position1";
    public static final String KEY_POS2 = "Position2";
    public static final String KEY_DETAILS = "Details";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL, " + KEY_NAME + " VARCHAR(30) NOT NULL, " +
            KEY_POS + " VARCHAR(20) NOT NULL, " + KEY_POS2 + " VARCHAR(10) NOT NULL, " + KEY_DETAILS + " VARCHAR(120) NOT NULL);";

    private static CompanyInfoStore helperInstance;

    public static synchronized CompanyInfoStore getInstance(Context c) {
        if (helperInstance == null) {
            helperInstance = new CompanyInfoStore(c.getApplicationContext());
        }
        return helperInstance;
    }

    private CompanyInfoStore(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
        Log.i("CompanyInfo", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // no version changes for now
        db.execSQL("DROP TABLE IF EXISTS CompanyInfoStore");
        db.execSQL(CREATE_TABLE);
        onCreate(db);
    }
}

