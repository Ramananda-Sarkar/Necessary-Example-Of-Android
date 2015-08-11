package com.example.ipcservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private final int job_1 = 1;
	private final int job_2 = 2;
	private final int job_1_process = 3;
	private final int job_2_process = 4;

	Messenger messanger = null;
	boolean isBind = false;
	TextView tvOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvOutput = (TextView) findViewById(R.id.textView1);
		Intent intent = new Intent(this, IpcService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	public void getMessage(View v) {
		String button_text = (String) ((Button) v).getText();
		Message msg;
		if (button_text.equals("GET First Message")) {
			msg = Message.obtain(null, job_1);
			msg.replyTo = new Messenger(new ResponseHendler());
			try {
				messanger.send(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (button_text.equals("Get Second Message")) {
			msg = Message.obtain(null, job_2);
			msg.replyTo = new Messenger(new ResponseHendler());
			try {
				messanger.send(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			messanger = null;
			messanger = null;
			isBind = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			messanger = new Messenger(service);
			isBind = true;
		}
	};

	protected void onStop() {
		unbindService(mConnection);
		isBind = false;
		messanger = null;

	};

	class ResponseHendler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			String message;
			switch (msg.what) {
			case job_1_process:
				message = msg.getData().getString("response_message");
				tvOutput.setText(message);
				break;
			case job_2_process:
				message = msg.getData().getString("response_message");
				tvOutput.setText(message);
				break;

			default:
				break;
			}
		}
	}

}
