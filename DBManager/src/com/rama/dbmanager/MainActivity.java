package com.rama.dbmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText etName, etEmail, etPhone, etAddress;

	DatabaseOpenHelper dbhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etName = (EditText) findViewById(R.id.etName);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etPhone = (EditText) findViewById(R.id.etPhone);
		etAddress = (EditText) findViewById(R.id.etAddress);
		dbhelper = DatabaseOpenHelper.getDbHelperInstance(this);
	}

	public void save(View v) {
		String Name = etName.getText().toString();
		String Email = etEmail.getText().toString();
		String Phone = etPhone.getText().toString();
		String Address = etAddress.getText().toString();

		StudentInfo stinfo = new StudentInfo(Name, Email, Phone, Address);
		Toast.makeText(getApplicationContext(), stinfo.toString(),
				Toast.LENGTH_LONG).show();

		long inserted = dbhelper.insertStudent(stinfo);
		if (inserted > 0) {
			Toast.makeText(getApplicationContext(), "data inserted",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), "data inserted failed",
					Toast.LENGTH_LONG).show();
		}
	}

	public void view(View v) {
		ArrayList<StudentInfo> getAll = dbhelper.getAllStudent();
		if (getAll != null && getAll.size() > 0) {
			for (StudentInfo studentInfo : getAll) {
				Toast.makeText(getApplicationContext(), studentInfo.toString(),
						Toast.LENGTH_LONG).show();
			}
		} else if (getAll.size() == 0) {
				Toast.makeText(getApplicationContext(), "data not found", Toast.LENGTH_LONG).show();
		}
	}
}
