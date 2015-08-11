package com.example.smsreceiver;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReplayActivity extends Activity {
	private EditText etNumber, etMessage, etReplay;
	private String number, body;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_replay);
		etNumber = (EditText) findViewById(R.id.etNumber);
		etMessage = (EditText) findViewById(R.id.etMessage);
		etReplay = (EditText) findViewById(R.id.etReply);

		number = getIntent().getStringExtra("number");
		body = getIntent().getStringExtra("body");
		etNumber.setText(number);
		etMessage.setText(body);
	}

	public void sendSMS(View v) {
		String replayStr = etReplay.getText().toString();
		if (replayStr.equals("")) {
			Toast.makeText(getApplicationContext(), "Write some message",
					Toast.LENGTH_LONG).show();
		} else {
			SmsManager manager = SmsManager.getDefault();
			manager.sendTextMessage(number, null, replayStr, null, null);
			Toast.makeText(getApplicationContext(), "Reply sent",
					Toast.LENGTH_LONG).show();
		}
	}
}
