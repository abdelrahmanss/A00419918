package com.example.turbo.bmicalc;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


public class BMICALCDATABASE extends SQLiteOpenHelper {

    private static final String DB_NAME = "BMIDBCLASS";  //name of DB
    private static final int DB_VERSION = 1;  //Version of DB
    public static final String TABLE_NAME = "PERSON"; //name of the table
    public static final String TABLE_NAME2 = "BMI"; //name of the table
    public static final String ID = "_id";
    public static final String Height = "height";
    public static final String Weight = "weight";
    public static final String BMI = "bmi";
    public static final String Dates = "date";
    public static final String Pass = "Password";
    public static final String UserID = "uid";



    public static final String UID = "uid";



    public BMICALCDATABASE(Context context) {
        super(context,DB_NAME,null,DB_VERSION); //null is for cursor
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + "_uid INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT, "
                + "EMAIL TEXT UNIQUE, "
                + "PASSWORD TEXT, " //NEVER STORE PASSWORDS IN CLEAR TEXT IN REAL APPS
                + "HEALTH_CARD_NUMB TEXT, "
                + "DOB TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_NAME2 + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "height DOUBLE NOT NULL, "
                + "weight DOUBLE NOT NULL, "
                + "bmi DOUBLE NOT NULL, " //NEVER STORE PASSWORDS IN CLEAR TEXT IN REAL APPS
                + "DATE TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertUserDetails(String name,String password, String health_card_numb, String date, String email ) {  // Insertion for Person Details
        Date today = new Date();  //we want to start with some initial data
        ContentValues personValues = new ContentValues();
        personValues.put("NAME", name);
        personValues.put("DOB", date);
        personValues.put("PASSWORD", password);
        personValues.put("HEALTH_CARD_NUMB", health_card_numb);
        personValues.put("EMAIL", email);

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NAME, null, personValues);
    }

    public boolean InsertBMI(String height, String weight, String bmi, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues personValues = new ContentValues();
        personValues.put(Height, height);
        personValues.put(Weight, weight);
        personValues.put(BMI, bmi);
        personValues.put(Dates, date);
        long result = db.insert(TABLE_NAME2,null,personValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String id,String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(UserID,id);
        contentValues.put(Pass,pass);
        String strf = "_uid= " + id;
        db.update(TABLE_NAME, contentValues, strf,null);
        return true;
    }

public Cursor getalldata() {
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor res = db.rawQuery("select * FROM "+TABLE_NAME2,null);
    return  res;

}

    public Cursor getallusers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2 = db.rawQuery("select * FROM "+TABLE_NAME,null);
        return  res2;

    }


    public Cursor clearallrecords() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res3= db.rawQuery("delete FROM "+TABLE_NAME2,null);
        db.execSQL("delete from "+ TABLE_NAME2);

return res3;
    }

    public Cursor clearallusers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res4= db.rawQuery("delete FROM "+TABLE_NAME,null);
        db.execSQL("delete from "+ TABLE_NAME);

        return res4;
    }




}
