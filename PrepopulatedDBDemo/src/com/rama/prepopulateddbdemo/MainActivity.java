package com.rama.prepopulateddbdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private PrepopulatedDBOpenHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		dbHelper = PrepopulatedDBOpenHelper
				.getPrepopulatedDBObject(getApplicationContext());
		dbHelper = PrepopulatedDBOpenHelper
				.getPrepopulatedDBObject(getApplicationContext());
		dbHelper = PrepopulatedDBOpenHelper
				.getPrepopulatedDBObject(getApplicationContext());

	}

	public void show(View v) {
		ArrayList<StudentInfo> allsudeList = dbHelper.getAllStudent();
		for (StudentInfo stu : allsudeList) {
			Toast.makeText(getApplicationContext(), stu.toString(),
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		dbHelper.close();
	}
}
