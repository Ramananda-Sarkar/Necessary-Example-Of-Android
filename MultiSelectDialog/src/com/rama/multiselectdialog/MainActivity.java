package com.rama.multiselectdialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void multicolor(View v) {
		MultiChoiceDialog choiceDialog = new MultiChoiceDialog();
		choiceDialog.show(getSupportFragmentManager(), "my choose");
	}

}
