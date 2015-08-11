package com.rama.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MediaPlayerService extends Service {

	MediaBinder mBinder = new MediaBinder();

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("MediaPlayerService", "----------------onBind--------------");
		return mBinder;
	}

	public class MediaBinder extends Binder {
		MediaPlayerService getService() {
			Log.e("MainActivity", "--------------getService-----------");
			
			return MediaPlayerService.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Log.e("MediaPlayerService", "-------------onCreate--------------");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("MediaPlayerService", "-------------onStartCommand--------------");
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("MediaPlayerService", "-------------onDestroy--------------");
	}

	public void play() {
		Log.e("MediaPlayerService",
				"---------------Playing a song--------------");
	}

	public void pause() {
		Log.e("MediaPlayerService",
				"---------------Player is paused--------------");

	}

	public void stop() {
		Log.e("MediaPlayerService",
				"---------------Player is stopped--------------");
	}
}
