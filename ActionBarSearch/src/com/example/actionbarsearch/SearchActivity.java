package com.example.actionbarsearch;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class SearchActivity extends Activity {

	ListView lv;
	ArrayAdapter<String> myAdapter;
	String[] dataArray = new String[] { "Bangladesh", "India", "Dhaka",
			"Bhudan", "Androidhub4you", "Pakistan", "Srilanka", "Nepal",
			"Japan" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		lv = (ListView) findViewById(R.id.listView);
		myAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataArray);
		lv.setAdapter(myAdapter);
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false);

		SearchView.OnQueryTextListener textListener = new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				myAdapter.getFilter().filter(query);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				myAdapter.getFilter().filter(newText);
				return true;
			}
		};
		searchView.setOnQueryTextListener(textListener);
		return super.onCreateOptionsMenu(menu);
	}

}
