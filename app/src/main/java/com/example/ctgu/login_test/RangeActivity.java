package com.example.ctgu.login_test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RangeActivity extends AppCompatActivity {

    private List<UsersInfo> userList = new ArrayList<>();
    private UserAdapter adapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);

        dbHelper = new DBHelper(this,"UserData.db",null,4);
        dbHelper.getWritableDatabase();

        initUsers();
        adapter = new UserAdapter(RangeActivity.this,R.layout.user_layout,userList);
        ListView listView = (ListView) findViewById(R.id.lv_01);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UsersInfo userTemp = userList.get(i);
                Toast.makeText(RangeActivity.this,userTemp.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initUsers() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("usertable",null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                //遍历Cursor对象，取出数据并打印
                String username = cursor.getString(cursor.getColumnIndex("username"));
                double score = cursor.getDouble(cursor.getColumnIndex("score"));
                UsersInfo user = new UsersInfo();
                user.setScore(score);
                user.setName(username);
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
