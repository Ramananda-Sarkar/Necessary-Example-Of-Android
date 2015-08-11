package com.rama.boundserviceusingmessanger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Messenger mMessenger = null;
	boolean status = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void startService(View v) {
		if (status) {
			Toast.makeText(getApplicationContext(),
					"Service All ready Running", Toast.LENGTH_LONG).show();
		} else {
			Intent intent = new Intent(this, BoundService.class);
			bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
			status = true;
			Toast.makeText(getApplicationContext(),
					"Service Successfully Started", Toast.LENGTH_LONG).show();
		}

	}

	public void stopService(View v) {
		if (status) {
			Toast.makeText(getApplicationContext(),
					"Service Stop Successfully...", Toast.LENGTH_LONG).show();
			status = false;
			mMessenger = null;
		} else {
			Toast.makeText(getApplicationContext(),
					"Service All ready Stop...", Toast.LENGTH_LONG).show();
		}
	}

	public void invoke(View v) {
		Message message = Message.obtain(null, 1, 0.0);
		String s = "This is the message from activity";
		Bundle bn = new Bundle();
		bn.putString("my_string", s);
		message.setData(bn);

		try {
			mMessenger.send(message);
		} catch (RemoteException e) {
		}

	}

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mMessenger = null;
			status = false;
		}

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder service) {
			mMessenger = new Messenger(service);
			status = true;

		}
	};

	protected void onStop() {
		unbindService(mConnection);
		mMessenger = null;
		status = false;
	};
}
