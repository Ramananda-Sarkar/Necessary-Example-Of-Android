package com.rama.storage;

import Prefarences.GlobalObj;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PreferanceActivity extends Activity {

	SharedPreferences preferences = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferance);
		try {
			preferences = getSharedPreferences(GlobalObj.CONTS_MYPREFERENCES, 0);
			String name = preferences.getString("user", "no user data");
			String pass = preferences.getString("psw", "no user data");
			TextView view = (TextView) findViewById(R.id.tvPreferance);
			view.setText(name + "   " + pass);

			Button logout = (Button) findViewById(R.id.btnLogout);
			logout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SharedPreferences.Editor editor = preferences.edit();
					editor.remove("user");
					editor.remove("psw");
					editor.commit();

					// =================================

					Intent intent = new Intent(PreferanceActivity.this, LoginActivity.class);
					startActivity(intent);

					Toast.makeText(getApplicationContext(), "user Logged out ! ", Toast.LENGTH_LONG)
							.show();
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
