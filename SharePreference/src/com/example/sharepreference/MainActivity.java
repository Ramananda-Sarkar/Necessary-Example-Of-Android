package com.example.sharepreference;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	Button login;
	EditText etName, etPass;
	CheckBox checkMark;
	SharedPreferences preferences;
	private static final String myPreference = "Mypreference";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		login = (Button) findViewById(R.id.btnLogin);
		etName = (EditText) findViewById(R.id.etUser);
		etPass = (EditText) findViewById(R.id.etPass);
		checkMark = (CheckBox) findViewById(R.id.checkBox);

		preferences = getSharedPreferences(myPreference, MODE_PRIVATE);

		String userName = preferences.getString("user", "");
		String userPass = preferences.getString("pass", "");
		etName.setText(userName);
		etPass.setText(userPass);
	}

	public void login(View v) {

		SharedPreferences.Editor editor = preferences.edit();
		String user = etName.getText().toString();
		String pass = etPass.getText().toString();

		if (checkMark.isChecked()) {
			editor.putString("user", user);
			editor.putString("pass", pass);
			editor.commit();
		} else {
			editor.remove("user");
			editor.remove("pass");
			editor.commit();
		}
		goAnotherAcivity();
	}

	public void goAnotherAcivity() {
		Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
		startActivity(intent);
	}
}
