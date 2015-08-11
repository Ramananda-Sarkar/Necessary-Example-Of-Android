package com.code.androiddatabase;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etName, etEducat, etEmail, etMobile;

	Button btnSave, btnAllContact;

	String name, educat, email, mobile;

	Contacts contacts;

	DatabaseInsert dbinsert;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dbinsert = new DatabaseInsert(this);

		etName = (EditText) findViewById(R.id.etName);

		etEducat = (EditText) findViewById(R.id.etEducat);

		etEmail = (EditText) findViewById(R.id.etEmail);

		etMobile = (EditText) findViewById(R.id.etMobile);

	}

	public void save(View v) {

		name = etName.getText().toString();

		educat = etEducat.getText().toString();

		email = etEmail.getText().toString();

		mobile = etMobile.getText().toString();

		dbinsert.open();

		dbinsert.insertContact(name, educat, email, mobile);

		dbinsert.close();

		Toast.makeText(MainActivity.this, "Saved Successfully",
				Toast.LENGTH_LONG).show();

	}

	public void showAll(View v) {

		Intent i = new Intent(MainActivity.this, ContactList.class);

		startActivity(i);

	}

}
