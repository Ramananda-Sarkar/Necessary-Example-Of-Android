package com.example.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	CheckBox cb;
	EditText et;
	Button bsave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cb = (CheckBox) findViewById(R.id.check);
		et = (EditText) findViewById(R.id.etText);
		bsave = (Button) findViewById(R.id.btnSave);
		bsave.setOnClickListener(this);
		LoadPref();
	}

	public void LoadPref() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean ckValue = sp.getBoolean("checkbox", false);
		String name = sp.getString("name", "Your text here");
		if (ckValue) {
			cb.setChecked(true);
		} else {
			cb.setChecked(false);
		}
		et.setText(name);
	}

	public void SavePref(String key, boolean value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public void SavePref(String key, String value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();

	}

	@Override
	public void onClick(View v) {
		SavePref("checkbox", cb.isChecked());
		if (cb.isChecked()) {
			SavePref("name", et.getText().toString());
			finish();
		}
	}

}
