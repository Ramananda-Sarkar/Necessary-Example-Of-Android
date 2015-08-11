package com.example.sharepreference;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.TextView;

public class AnotherActivity extends Activity {
	TextView tv1, tv2;
	private static final String myPreference = "Mypreference";
	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);

		preferences = getSharedPreferences(myPreference, MODE_PRIVATE);
		tv1.setText(preferences.getString("user", ""));
		tv2.setText(preferences.getString("pass", ""));
	}

}
