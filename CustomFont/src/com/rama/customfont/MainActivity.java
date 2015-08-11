package com.rama.customfont;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final String FONT1 = "ITCBLKAD.TTF";
	public static final String FONT2 = "BRUSHSCI.TTF";

	Button btnConvertFont;
	TextView txt1, txt2;
	EditText etGetName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnConvertFont = (Button) findViewById(R.id.button);
		txt1 = (TextView) findViewById(R.id.text1);
		txt2 = (TextView) findViewById(R.id.text2);
		etGetName = (EditText) findViewById(R.id.etText);
	}

	public void convert(View v) {
		String name = etGetName.getText().toString();
		txt1.setText(name);
		Typeface typeface1 = Typeface.createFromAsset(getAssets(), FONT1);
		txt1.setTypeface(typeface1);

		txt2.setText(name);
		Typeface typeface2 = Typeface.createFromAsset(getAssets(), FONT2);
		txt2.setTypeface(typeface2);

	}
}
