package com.ramananda.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class Myservice extends IntentService {

	public Myservice() {
		super("MY_THREAD");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(getApplicationContext(), "start service.....",
				Toast.LENGTH_LONG).show();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(), "stop service.....",
				Toast.LENGTH_LONG).show();
		super.onDestroy();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		synchronized (this) {
			int count = 0;
			while (count < 10) {
				try {
					wait(1500);
					count++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
