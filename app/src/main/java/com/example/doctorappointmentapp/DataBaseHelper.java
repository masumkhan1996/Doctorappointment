package com.example.doctorappointmentapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {

        super(context, "userdetails.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table user(UserName primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");

    }

    //inserting im database
    public boolean insert(String UserName, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName", UserName);
        contentValues.put("Password", password);
        long ins = sqLiteDatabase.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }

    //checking if name exists
    public Boolean chkUserName(String UserName) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user where UserName=?", new String[]{UserName});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }
    //checking the username and password
    public Boolean chkUserNamePassword(String UserName,String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user where UserName=? and password=?", new String[]{UserName,password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }



}


