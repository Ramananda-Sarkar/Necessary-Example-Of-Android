package com.example.customdialoglayout;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void login(View v){
		MyDialog dialog = new MyDialog();
		dialog.show(getSupportFragmentManager(), "my dialog");
		
	}
}
