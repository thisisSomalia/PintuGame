package com.example.ctgu.login_test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ctgu.login_test.pintu.PintuActivity;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBManager(PintuActivity context){
        dbHelper = new DBHelper(context, "UserData.db", context, 2);
//        获取实例化对象
        db = dbHelper.getWritableDatabase();
    }
    public void add(List<UsersInfo> usersInfos){
        db.beginTransaction();//开始事务
        for (UsersInfo usersInfo : usersInfos){
            db.execSQL("insert into usertable values(null,?,?)",new Object[]{
                    usersInfo.name,usersInfo.password
            });
        }
        db.setTransactionSuccessful();//设置事务完成
        db.endTransaction();//结束事务
    }

    public void add(String username,String userpassword,double score){
        db.beginTransaction();
        db.execSQL("insert into usertable values(null,?,?,?)",new Object[]{
                username,userpassword,score
        });
    }

    public void update(){

    }


    public void delete(UsersInfo usersInfo){
        db.delete("usertable","age >= ?",new String[]{String.valueOf(usersInfo.age)});
    }


    public List<UsersInfo> query(){
        ArrayList<UsersInfo> usersInfos = new ArrayList<>();
        Cursor c  = queryTheCursor();
        while (c.moveToNext()){
            UsersInfo usersInfo = new UsersInfo();
            usersInfo._id = c.getInt(c.getColumnIndex("_id"));
            usersInfo.name = c.getString(c.getColumnIndex("name"));
            usersInfo.password = c.getString(c.getColumnIndex("password"));
            usersInfo.age = c.getInt(c.getColumnIndex("age"));
            usersInfo.info = c.getString(c.getColumnIndex("info"));
            usersInfos.add(usersInfo);
        }
        c.close();
        return usersInfos;
    }

    public Cursor queryTheCursor(){
        Cursor c = db.rawQuery("SELECT * FROM usertable", null);
        return c;
    }

    public Cursor queryTheCursorByName(String name){
        Cursor c = db.rawQuery("SELECT * FROM usertable", null);
        return c;
    }

    public void closeDB(){
        db.close();
    }
}