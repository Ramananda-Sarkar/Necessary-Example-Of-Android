package com.rama.storage;

import Prefarences.GlobalObj;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	Button save;
	EditText etname, etpass;
	CheckBox chbox;

	SharedPreferences preferences = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		try {

			etname = (EditText) findViewById(R.id.etName);
			etpass = (EditText) findViewById(R.id.etPass);
			chbox = (CheckBox) findViewById(R.id.btnCheck);
			save = (Button) findViewById(R.id.btnButton);
			save.setOnClickListener(this);
			preferences = this.getSharedPreferences(GlobalObj.CONTS_MYPREFERENCES, 0);
			if (preferences.contains("user") && preferences.contains("psw")) {
				goSecond();
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void onClick(View v) {
		try {

			preferences = this.getSharedPreferences(GlobalObj.CONTS_MYPREFERENCES, MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			
			if (chbox.isChecked()) {
				String name = etname.getText().toString();
				String pass = etpass.getText().toString();
				editor.putString("user", name.toString());
				editor.putString("psw", pass.toString());
				editor.commit();
			} else {
				editor.remove("user");
				editor.remove("psw");
				editor.commit();
			}

			goSecond();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void goSecond() {
		Toast.makeText(getApplicationContext(), "user loging sucessfully !", Toast.LENGTH_LONG)
				.show();
		Intent intent = new Intent(LoginActivity.this, ShareActivity.class);
		startActivity(intent);
	}
}
