package com.rama.boundserviceusingmessanger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class BoundService extends Service {

	static final int SAY_HELLO = 1;

	Messenger messenger = new Messenger(new MessageHandler());

	@Override
	public IBinder onBind(Intent arg0) {
		Toast.makeText(getApplicationContext(), "Service binding...",
				Toast.LENGTH_LONG).show();
		return messenger.getBinder();
	}

	public class MessageHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case SAY_HELLO:
				Toast.makeText(getApplicationContext(),
						"SAY Hello From Service", Toast.LENGTH_LONG).show();
				Bundle bundle = msg.getData();
				String message = bundle.getString("my_string");
				Toast.makeText(getApplicationContext(), "Message " + message,
						Toast.LENGTH_LONG).show();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	}
}
