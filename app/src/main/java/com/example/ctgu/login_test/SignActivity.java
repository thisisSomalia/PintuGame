package com.example.ctgu.login_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(SignActivity.this,"onCreate",Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_sign);
    }
}
