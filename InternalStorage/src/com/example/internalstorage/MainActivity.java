package com.example.internalstorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button Create = (Button) findViewById(R.id.btnCreate);
		Create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, InternalStorageCreate.class);
				startActivity(intent);
			}
		});

		Button Read = (Button) findViewById(R.id.btnRead);
		Read.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, InternalStorageRead.class);
				startActivity(intent);
			}
		});
	}

}
