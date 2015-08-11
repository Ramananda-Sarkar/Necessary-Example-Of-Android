package com.example.alarmdemo;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText text;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (EditText) findViewById(R.id.etTime);
		btn = (Button) findViewById(R.id.btnAlam);
	}

	public void setAlarm(View v) {
		String timeStr = text.getText().toString();
		int interval = Integer.parseInt(timeStr);
		long time = new GregorianCalendar().getTimeInMillis()
				+ (interval * 1000);

		Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent
				.getBroadcast(this, 1, alarmIntent,
						PendingIntent.FLAG_UPDATE_CURRENT

				));

	}
}
