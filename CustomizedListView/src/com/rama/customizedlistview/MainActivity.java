package com.rama.customizedlistview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText etName, etEmail, etPhone, etAddress;

	DatabaseOpenHelper dbhelper;
	// declear list view
	ListView listView;
	// declear Adapter
	CustomizedView adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etName = (EditText) findViewById(R.id.etName);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etPhone = (EditText) findViewById(R.id.etPhone);
		etAddress = (EditText) findViewById(R.id.etAddress);
		dbhelper = new DatabaseOpenHelper(this);
		listView = (ListView) findViewById(R.id.listView);
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
			Toast.makeText(getApplicationContext(), "data inserted faield",
					Toast.LENGTH_LONG).show();
		}
	}

	public void view(View v) {
		ArrayList<StudentInfo> allStudents = dbhelper.getAllStudent();
		if (allStudents != null && allStudents.size() > 0) {
			adapter  = new CustomizedView(this, allStudents);
			listView.setAdapter(adapter);
		}
	}
}
