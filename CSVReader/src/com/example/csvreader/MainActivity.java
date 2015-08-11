package com.example.csvreader;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lv;
	private ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.listView);
		adapter = new ListAdapter(getApplicationContext(),
				R.layout.single_list_view);
		Parcelable state = lv.onSaveInstanceState();
		lv.setAdapter(adapter);
		lv.onRestoreInstanceState(state);

		InputStream inputStream = getResources().openRawResource(R.raw.stats);
		CSVReadFile readFile = new CSVReadFile(inputStream);
		List<String[]> scoreList = readFile.read();
		for (String[] scoreData : scoreList) {
			adapter.add(scoreData);
		}
	}

}
