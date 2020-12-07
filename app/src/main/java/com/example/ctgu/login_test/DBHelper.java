package com.example.ctgu.login_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ctgu.login_test.pintu.PintuActivity;


public class DBHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "UserData.db";
    public DBHelper(Login_Activity context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super((Context) context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Register_Activity register_activity, String name, Object factory, int version) {
        super((Context) register_activity, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(PintuActivity pintuActivity, String name, Object factory, int version) {
        super((Context) pintuActivity, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(RangeActivity rangeActivity, String s, Object o, int i) {
        super((Context) rangeActivity, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        String usersInfo_table = "create table usertable" +
                "(id integer primary key autoincrement, username text," +
                "password text,"+"score double)";
        db.execSQL(usersInfo_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("alter table usertable add column other string");
        db.execSQL("drop table if exists usertable");
        onCreate(db);
    }
}
