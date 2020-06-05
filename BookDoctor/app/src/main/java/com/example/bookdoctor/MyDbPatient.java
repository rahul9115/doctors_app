package com.example.bookdoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import patient.patient;


public class MyDbPatient extends SQLiteOpenHelper {
    public static final int DB_VERSION =1;
    public static final String DB_NAME="BookDoctor.db";
    public static final String TABLE_NAME="patients";
    public static final String KEY_ID="patients_Id";
    public static final String KEY_f_name="f_name";
    public static final String KEY_l_name="l_name";
    public static final String KEY_email="email";
    public static final String KEY_password="password";
    public static final String KEY_Phn_no="Phn_no";

    public MyDbPatient(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = " CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_f_name + " TEXT,"
                + KEY_l_name + " TEXT," + KEY_email + " TEXT," + KEY_password + " TEXT," + KEY_Phn_no + " TEXT" + ");";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert1(patient patient){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_f_name,patient.getKEY_f_name());
        values.put(KEY_l_name,patient.getKEY_l_name());
        values.put(KEY_email,patient.getKEY_email());
        values.put(KEY_password,patient.getKEY_password());
        values.put(KEY_Phn_no,patient.getKEY_Phn_no());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }


}
