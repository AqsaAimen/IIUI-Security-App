package com.example.iiuisecurityapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static com.example.iiuisecurityapp.DatabaseContract.Users.TABLE_NAME;
import static com.example.iiuisecurityapp.DatabaseContract.login.table2;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    DatabaseHelper dbhelper;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "IIUIUser.db";
    private static final String Table2="table2";
    private static final String CREATE_TBL_USERS = "CREATE TABLE "
            + TABLE_NAME + " ("
            + DatabaseContract.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Users.COL_FULLNAME + " TEXT NOT NULL, "
            + DatabaseContract.Users.COL_Email + " TEXT NOT NULL, "
            + DatabaseContract.Users.COL_Password + " TEXT NOT NULL, "
            + DatabaseContract.Users.COL_confirmPass + " TEXT)";
    private static final  String CREATE_TABLE2 = "CREATE TABLE "
            + DatabaseContract.login.table2 + " ("
            + DatabaseContract.login._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +DatabaseContract.login.COL_Email + "TEXT NOT NULL,"
            + DatabaseContract.login.COL_Password + " TEXT)";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_USERS);
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /*public boolean InsertData(String name,String email,String password,String confirmpassword) {
        //dbhelper=new DatabaseHelper(this);
//        SQLiteDatabase db=dbhelper.getWritableDatabase();
       db=dbhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Users.COL_FULLNAME, name);
        values.put(DatabaseContract.Users.COL_Email, email);
        values.put(DatabaseContract.Users.COL_Password,password);
        values.put(DatabaseContract.Users.COL_confirmPass, confirmpassword);
        long RESULT = db.insert(TABLE_NAME, null, values);
        if (RESULT ==-1)
            //Toast.makeText(this, "you are registered: " + newRowId, Toast.LENGTH_SHORT).show();
            return false;

        else
            return true;

        //db.close();
    }*/
}
