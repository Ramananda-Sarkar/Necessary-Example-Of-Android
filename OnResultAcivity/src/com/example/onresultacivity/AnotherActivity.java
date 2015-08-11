package com.example.onresultacivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AnotherActivity extends Activity {
	EditText etNumber;
	Button getNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another);

		etNumber = (EditText) findViewById(R.id.etNumber);
		getNumber = (Button) findViewById(R.id.btnPre);

		getNumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = etNumber.getText().toString();
				Intent i = new Intent(AnotherActivity.this, MainActivity.class);
				i.putExtra("number", number);
				setResult(Activity.RESULT_OK, i);
				finish();
			}
		});
	}

}
