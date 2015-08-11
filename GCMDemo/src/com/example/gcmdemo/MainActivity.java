package com.example.gcmdemo;

import static com.example.gcmdemo.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.example.gcmdemo.CommonUtilities.EXTRA_MESSAGE;
import static com.example.gcmdemo.CommonUtilities.SENDER_ID;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity {

	TextView lbMessage;

	// Asyntask
	AsyncTask<Void, Void, Void> mRegisterTask;

	// Alert Dialog manager

	AleartDialogManager alert = new AleartDialogManager();

	// connection detector

	ConnectionDetector cd;
	public static String name;
	public static String email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cd = new ConnectionDetector(getApplicationContext());

		if (!cd.isConnecting()) {
			alert.showAlertDialog(MainActivity.this,
					"Internet Connection Error",
					"Please Connect to working Internet connection", false);
			return;
		}

		Intent i = getIntent();
		name = i.getStringExtra("name");
		email = i.getStringExtra("email");

		// make sure the device has the proper dependences.

		GCMRegistrar.checkDevice(this);

		lbMessage = (TextView) findViewById(R.id.lblMessage);

		registerReceiver(mHandleMessage, new IntentFilter(
				DISPLAY_MESSAGE_ACTION));

		final String regId = GCMRegistrar.getRegistrationId(this);

		if (regId.equals("")) {
			GCMRegistrar.register(this, SENDER_ID);
		} else {
			if (GCMRegistrar.isRegisteredOnServer(this)) {
				Toast.makeText(getApplicationContext(),
						"Already registered with GCM", Toast.LENGTH_LONG)
						.show();
			} else {
				final Context context = this;

				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						ServerUtilities.register(context, name, email, regId);
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {

						mRegisterTask = null;
					}
				};
				mRegisterTask.execute(null, null, null);
			}
		}
	}

	private final BroadcastReceiver mHandleMessage = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
			WakeLocker.acquire(getApplicationContext());

			lbMessage.append(newMessage + "\n");
			Toast.makeText(getApplicationContext(),
					"New Message: " + newMessage, Toast.LENGTH_LONG).show();
			WakeLocker.release();
		}
	};

	protected void onDestroy() {

		if (mRegisterTask != null) {
			mRegisterTask.cancel(true);
		}
		try {
			unregisterReceiver(mHandleMessage);
			GCMRegistrar.onDestroy(this);
		} catch (Exception e) {
			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
		}
		super.onDestroy();
	};

}
