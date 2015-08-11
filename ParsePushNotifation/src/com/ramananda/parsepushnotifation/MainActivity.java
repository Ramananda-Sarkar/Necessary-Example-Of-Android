package com.ramananda.parsepushnotifation;

import android.app.Activity;
import android.os.Bundle;

import com.parse.ParseInstallation;
import com.parse.PushService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();

	}

}
