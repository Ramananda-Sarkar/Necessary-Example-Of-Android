package com.example.xmlpullparser;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.listView1);
		List<Employee> emp = null;
		try {
			XmlPullParserHandler parser = new XmlPullParserHandler();
			InputStream inp = getAssets().open("item.xml");
			emp = parser.parse(inp);

			ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this,
					android.R.layout.simple_list_item_1, emp);
			lv.setAdapter(adapter);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
