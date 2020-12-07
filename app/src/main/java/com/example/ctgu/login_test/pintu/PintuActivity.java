package com.example.ctgu.login_test.pintu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ctgu.login_test.DBHelper;
import com.example.ctgu.login_test.R;
import com.example.ctgu.login_test.pintu.view.GamePintuLayout;
import  com.example.ctgu.login_test.pintu.view.GamePintuLayout.GamePintuListener;

public class PintuActivity extends Activity
{

	private GamePintuLayout mGamePintuLayout;
	private TextView mLevel ;
	private TextView mTime;
	private String username;
	private String userpassword;
	private int sco;
	private DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pintu);

		mTime = (TextView) findViewById(R.id.id_time);
		mLevel = (TextView) findViewById(R.id.id_level);
		
		mGamePintuLayout = (GamePintuLayout) findViewById(R.id.id_gamepintu);
		mGamePintuLayout.setTimeEnabled(true);

		dbHelper = new DBHelper(this, "UserData.db", null, 3);

		mGamePintuLayout.setOnGamePintuListener(new GamePintuListener()
		{
			@Override
			public void timechanged(int currentTime)
			{
				mTime.setText(""+currentTime);
			}

			@Override
			public void nextLevel(final int nextLevel)
			{
				new AlertDialog.Builder(PintuActivity.this)
						.setTitle("游戏详情").setMessage("恭喜你，升级啦！")
						.setPositiveButton("下一级", new OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog,
                                                int which)
							{
								mGamePintuLayout.nextLevel();
								mLevel.setText(""+nextLevel);
								sco = nextLevel;
							}
						}).show();
			}

			@Override
			public void gameover()
			{
				SharedPreferences sharedPreferences = getSharedPreferences("UsersInfo",MODE_PRIVATE);
				username = sharedPreferences.getString("username","");
				userpassword = sharedPreferences.getString("password","");
				double score = Math.pow(2, sco)*100;
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("username", username);
				values.put("password", userpassword);
				values.put("score",score);
				db.insert("usertable", null, values);
				db.close();

				new AlertDialog.Builder(PintuActivity.this)
				.setTitle("游戏详情").setMessage("游戏结束！")
				.setPositiveButton("重新开始", new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog,
                                        int which)
					{
						mGamePintuLayout.restart();
					}
				}).setNegativeButton("退出",new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						finish();
					}
				}).show();
			}
		});

	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		
		mGamePintuLayout.pause();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		mGamePintuLayout.resume();
	}

}
