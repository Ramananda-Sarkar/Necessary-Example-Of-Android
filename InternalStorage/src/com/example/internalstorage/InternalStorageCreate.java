package com.example.internalstorage;

import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InternalStorageCreate extends Activity {
	EditText filename, file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_storage_create);
		filename = (EditText) findViewById(R.id.etFile);
		file = (EditText) findViewById(R.id.etData);
	}

	public void save(View v) {
		FileOutputStream outputStream = null;
		try {
			String fileName = filename.getText().toString();
			String fileData = file.getText().toString();
			byte[] bs = fileData.getBytes();
			outputStream = openFileOutput(fileName, MODE_PRIVATE);
			outputStream.write(bs);
			outputStream.flush();

			// ================================

			Toast.makeText(getApplicationContext(), "Create : " + fileName, Toast.LENGTH_LONG)
					.show();

			Intent in = new Intent(InternalStorageCreate.this, MainActivity.class);
			startActivity(in);

		} catch (Exception e) {

		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	}
}
