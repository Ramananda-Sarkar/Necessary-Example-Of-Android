package com.rama.singlechoicedialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void setChoice(View v) {
		SingleChoiceDialog dialog = new SingleChoiceDialog();
		dialog.show(getSupportFragmentManager(), "my dialog");
	}
}
