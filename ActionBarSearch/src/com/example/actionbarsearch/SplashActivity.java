package com.example.actionbarsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashActivity extends Activity {
	private myCounter mCounter;
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent i = new Intent(this, SearchActivity.class);
		startActivity(i);

		mCounter = new myCounter(1000, 1000);
		mCounter.start();
	}

	

	private class myCounter extends CountDownTimer {

		public myCounter(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onFinish() {
			mIntent = new Intent(SplashActivity.this, SearchActivity.class);
			startActivity(mIntent);
			fileList();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub

		}

	}

}
