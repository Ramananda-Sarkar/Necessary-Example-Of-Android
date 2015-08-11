package com.example.onresultacivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etPhone, etName, etEmail;
	Button btnGetNumber, btnCall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etPhone = (EditText) findViewById(R.id.etPhone);
		etName = (EditText) findViewById(R.id.etUser);
		etEmail = (EditText) findViewById(R.id.etEmail);

		btnGetNumber = (Button) findViewById(R.id.btnGetNumber);
		btnCall = (Button) findViewById(R.id.btnCall);

		// etPhone.setText(getIntent().getStringExtra("number"));
	}

	public void getcall(View v) {
		String name = etName.getText().toString();
		String email = etEmail.getText().toString();
		String phone = etPhone.getText().toString();

		Toast.makeText(getApplicationContext(),
				"Name : " + name + "  email :" + email + " phone :" + phone,
				Toast.LENGTH_LONG).show();

	}

	public void getNumber(View v) {
		Intent i = new Intent(MainActivity.this, AnotherActivity.class);
		startActivityForResult(i, 1000);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1000) {
			if (resultCode == Activity.RESULT_OK) {
				String number = data.getStringExtra("number");
				etPhone.setText(number);
			} else {
				Log.e("MainActivity", " result not found");
			}
		}
	}
}
