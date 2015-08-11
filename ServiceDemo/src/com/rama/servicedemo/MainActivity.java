package com.rama.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	MediaPlayerService mService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void start(View v) {
		Intent startService = new Intent(this, MediaPlayerService.class);
		bindService(startService, connection, Context.BIND_AUTO_CREATE);
	}

	public void play(View v) {
		if (mService != null) {
			mService.play();
		}
	}

	public void pause(View v) {
		if (mService != null) {
			mService.pause();
		}
	}

	public void stop(View v) {
		if (mService != null) {
			mService.stop();
		}
		unbindService(connection);
	}

	ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.e("MainActivity", "--------------onServiceDisconnected-----------");
			
			mService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			Log.e("MainActivity", "--------------onServiceconnected-----------");
			
			mService = ((MediaPlayerService.MediaBinder) binder).getService();
		}
	};
}
