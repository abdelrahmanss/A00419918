package com.example.turbo.bmicalc;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


public class BMICALCDATABASE extends SQLiteOpenHelper {

    private static final String DB_NAME = "inclass";  //name of DB
    private static final int DB_VERSION = 1;  //Version of DB
    public static final String TABLE_NAME = "PERSON"; //name of the table

    public BMICALCDATABASE(Context context) {
        super(context, DB_NAME, null, DB_VERSION); //null is for cursor
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT, "
                + "PASSWORD TEXT, " //NEVER STORE PASSWORDS IN CLEAR TEXT IN REAL APPS
                + "HEALTH_CARD_NUMB TEXT, DATE INTEGER);");

        Date today = new Date();  //we want to start with some initia data
        ContentValues personValues = new ContentValues();
        personValues.put("NAME", "HAMADA ElBolaggy");
        personValues.put("PASSWORD", "SUPER SECRET");
        personValues.put("HEALTH_CARD_NUMB", "123456789101");
        personValues.put("DATE", today.getTime());
        db.insert(TABLE_NAME, null, personValues);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
