package com.example.internalstorage;

import java.io.FileInputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InternalStorageRead extends Activity {
	String currentFileName = " ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_storage_read);
		try {
			fillFile();
			readFile();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void fillFile() {
		try {
			String[] strings = getApplicationContext().fileList();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_spinner_item, strings);
			final Spinner spinner = (Spinner) findViewById(R.id.etSpanner);

			spinner.setAdapter(adapter);

			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					currentFileName = spinner.getAdapter().getItem(arg2).toString();
					TextView tv = (TextView) findViewById(R.id.etOutput);
					tv.setText(currentFileName);

					long totalSpace = getApplicationContext().getFilesDir().getTotalSpace();
					Toast.makeText(getApplicationContext(), "Size : " + totalSpace,
							Toast.LENGTH_LONG).show();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void readFile() {
		try {
			Button btn = (Button) findViewById(R.id.btnShow);
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					try {
						FileInputStream fileInputStream = openFileInput(currentFileName);
						byte[] byteInput = new byte[fileInputStream.available()];
						String finallData = "";
						while (fileInputStream.read(byteInput) != -1) {
							String currentData = new String(byteInput);
							finallData = finallData + currentData;
						}
						TextView rs = (TextView) findViewById(R.id.result);
						rs.setText(finallData);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
