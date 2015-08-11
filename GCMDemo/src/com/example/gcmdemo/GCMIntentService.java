package com.example.gcmdemo;

import static com.example.gcmdemo.CommonUtilities.SENDER_ID;
import static com.example.gcmdemo.CommonUtilities.displayMessage;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";

	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onError(Context context, String errorId) {
		 Log.i(TAG, "Received error: " + errorId);
	        displayMessage(context, getString(R.string.gcm_error, errorId));
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		String message = intent.getExtras().getString("price");
		displayMessage(context, message);
		generateNotification(context, message);
	}

	private void generateNotification(Context context, String message) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, message, when);

		String title = context.getString(R.string.app_name);
		Intent notificationIntent = new Intent(context, MainActivity.class);

		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);

		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// play default notification
		notification.defaults |= Notification.DEFAULT_SOUND;

		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notificationManager.notify(0, notification);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		displayMessage(context, "Your Device Register with GCM");
		Log.d("Name", MainActivity.name);
		ServerUtilities.register(context, MainActivity.name,
				MainActivity.email, registrationId);
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {
		Log.i(TAG, "Device Unregistered");
		displayMessage(context, getString(R.string.gcm_unregistered));
		ServerUtilities.unregister(context, registrationId);
	}

	
	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		 // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
	}
	
	@Override
	protected void onDeletedMessages(Context context, int total) {
		 Log.i(TAG, "Received deleted messages notification");
	        String message = getString(R.string.gcm_deleted, total);
	        displayMessage(context, message);
	        // notifies user
	        generateNotification(context, message);
	}
}
