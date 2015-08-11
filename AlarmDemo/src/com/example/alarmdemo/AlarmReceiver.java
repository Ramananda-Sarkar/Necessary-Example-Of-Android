package com.example.alarmdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class AlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		MediaPlayer mp = MediaPlayer.create(context, R.raw.minion_ring_ring);
		mp.start();
	}

}
