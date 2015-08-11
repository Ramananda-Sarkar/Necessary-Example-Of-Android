package com.example.ipcservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class IpcService extends Service {

	private final int job_1 = 1;
	private final int job_2 = 2;
	private final int job_1_process = 3;
	private final int job_2_process = 4;

	Messenger messenger = new Messenger(new IcommingHandler());

	@Override
	public IBinder onBind(Intent intent) {
		return messenger.getBinder();
	}

	class IcommingHandler extends Handler {
		Message MSB;
		String message;
		Bundle bundle = new Bundle();

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case job_1:
				message = "This is the first message from service...";
				MSB = Message.obtain(null, job_1_process);
				bundle.putString("response_message", message);
				MSB.setData(bundle);
				try {
					msg.replyTo.send(MSB);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case job_2:
				message = "This is the Second message from service...";
				MSB = Message.obtain(null, job_2_process);
				bundle.putString("response_message", message);
				MSB.setData(bundle);
				try {
					msg.replyTo.send(MSB);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				super.handleMessage(msg);
				;
			}

		}
	}
}
