package com.ramananda.callhistory;

import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView txtCall = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtCall = (TextView) findViewById(R.id.textCallView);
		getCallDetails();
	}

	private void getCallDetails() {
		StringBuffer sb = new StringBuffer();
		@SuppressWarnings("deprecation")
		Cursor managerCursor = managedQuery(CallLog.Calls.CONTENT_URI, null,
				null, null, null);
		int number = managerCursor.getColumnIndex(CallLog.Calls.NUMBER);
		int type = managerCursor.getColumnIndex(CallLog.Calls.TYPE);
		int date = managerCursor.getColumnIndex(CallLog.Calls.DATE);
		Date callDayTime = new Date(Long.valueOf(date));
		int duration = managerCursor.getColumnIndex(CallLog.Calls.DURATION);
		sb.append("call log:");
		while (managerCursor.moveToNext()) {
			String phNumber = managerCursor.getString(number);
			String callType = managerCursor.getString(type);
			String callDate = managerCursor.getString(date);
			String callDuration = managerCursor.getString(duration);

			Log.i("Data :", phNumber + "|" + callType + "|" + callDate + " |"
					+ callDuration);
			String dir = null;
			int dirCode = Integer.parseInt(callType);
			switch (dirCode) {
			case CallLog.Calls.OUTGOING_TYPE:
				dir = "OUTGOING";
				break;
			case CallLog.Calls.INCOMING_TYPE:
				dir = "INCOMING";
				break;
			case CallLog.Calls.MISSED_TYPE:
				dir = "MISSED";
				break;

			}
			sb.append("\n phone number :--" + phNumber + "\nCall Type:--" + dir
					+ "\nCall Date :---" + callDayTime
					+ " \nCall Duration in sec----: " + callDuration);

			sb.append("\n-----------------");
		}
		txtCall.setText(sb);
	}

}
