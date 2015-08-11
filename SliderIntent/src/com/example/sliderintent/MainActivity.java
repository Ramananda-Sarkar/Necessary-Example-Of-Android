package com.example.sliderintent;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnNext = (Button) findViewById(R.id.btnNext);
	}

	public void next(View v) {
		Intent intent = new Intent(MainActivity.this, Another_Activity.class);
		Bundle banAnimation = ActivityOptions.makeCustomAnimation(
				getApplicationContext(), R.anim.animation_next,
				R.anim.animaton_pre).toBundle();
		startActivity(intent, banAnimation);
		
		
	}

}
