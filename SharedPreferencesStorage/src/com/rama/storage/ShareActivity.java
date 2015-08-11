package com.rama.storage;

import Prefarences.GlobalObj;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShareActivity extends Activity {

	SharedPreferences preferences = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);

		try {
			preferences = getSharedPreferences(GlobalObj.CONTS_MYPREFERENCES, MODE_PRIVATE);
			String name = preferences.getString("user", "no user data");
			String pass = preferences.getString("psw", "no user data");
			TextView view = (TextView) findViewById(R.id.outText);
			view.setText(" Wellcome " + name + "  " + pass);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void send(View v) {
		Intent n = new Intent(ShareActivity.this, PreferanceActivity.class);
		startActivity(n);
	}
}
