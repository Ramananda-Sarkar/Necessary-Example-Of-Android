package com.example.notificationwithsound;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button showNoti, cancelNoti;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		showNoti = (Button) findViewById(R.id.btnShow);
		cancelNoti = (Button) findViewById(R.id.btnCancel);

		showNoti.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Uri soundUri = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				Intent intent = new Intent(MainActivity.this,
						NotificationReceiver.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(
						MainActivity.this, 0, intent, 0);

				Notification notification = new Notification.Builder(
						MainActivity.this)
						.setSound(soundUri)
						.setContentIntent(pendingIntent)
						.setSmallIcon(R.drawable.index)
						.setContentTitle("Hello User......")
						.setContentText("Hi, welcome to use this app........")
						.addAction(R.drawable.ic_launcher, "Open",
								pendingIntent)
						.addAction(0, "Remain", pendingIntent).build();

				NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

				notificationManager.notify(0, notification);

			}
		});

		cancelNoti.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Context.NOTIFICATION_SERVICE != null) {
					String as = Context.NOTIFICATION_SERVICE;
					NotificationManager manager = (NotificationManager) getApplicationContext()
							.getSystemService(as);
					manager.cancel(0);
				}
			}
		});

	}

}
