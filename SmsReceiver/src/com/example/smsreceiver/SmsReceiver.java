package com.example.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Receive Sms : " + intent.getAction(),
				Toast.LENGTH_LONG).show();

		if (intent.getAction().equalsIgnoreCase(
				"android.provider.Telephony.SMS_Received")) {
			Bundle bundle = intent.getExtras();
			SmsMessage[] chunks = null;
			String body = "";
			String number = "";

			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				chunks = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					chunks[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
					body += chunks[i].getMessageBody().toString();
					number = chunks[i].getOriginatingAddress();
				}

				if (body.equals("tract")) {
					MediaPlayer mp = MediaPlayer.create(context,
							R.raw.minion_ring_ring);

					mp.start();

				} else {
					Toast.makeText(context,
							"number " + number + " body " + body,
							Toast.LENGTH_LONG).show();
					Intent i = new Intent(context, ReplayActivity.class);
					i.putExtra("number", number);
					i.putExtra("body", body);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(i);
				}

			}
		}
	}

}
