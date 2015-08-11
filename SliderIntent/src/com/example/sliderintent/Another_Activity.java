package com.example.sliderintent;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Another_Activity extends Activity {

	Button btnPrevious;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another_);
		btnPrevious = (Button) findViewById(R.id.btnPre);
	}

	public void previous(View v) {
		Intent intent = new Intent(Another_Activity.this, MainActivity.class);
		Bundle banAnimation = ActivityOptions.makeCustomAnimation(
				getApplicationContext(), R.anim.animaton_pre,
				R.anim.animation_next).toBundle();
		startActivity(intent, banAnimation);
		
	}

}
