package com.rama.multipledeletelistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView lv;
	ListViewAdapter listViewAdapter;
	List<WorldPopulation> worldPopulationlist = new ArrayList<WorldPopulation>();
	String[] rank;
	String[] country;
	String[] population;
	int[] flag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rank = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", };
		country = new String[] { "China", "India", "United States",
				"Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
				"Russia", "Japan", };
		population = new String[] { "1,370,350,000", "1,272,560,000",
				"321,188,000", "255,461,700", "204,446,000", "190,046,000",
				"183,523,000", "158,493,000", "146,267,288", "126,880,000", };

		flag = new int[] { R.drawable.china, R.drawable.india, R.drawable.usa,
				R.drawable.indonesia, R.drawable.brazill, R.drawable.pakistan,
				R.drawable.nijeria, R.drawable.bangladesh, R.drawable.rusian,
				R.drawable.japan };
		for (int i = 0; i < rank.length; i++) {
			WorldPopulation worldPopulation = new WorldPopulation(rank[i],
					country[i], population[i], flag[i]);
			worldPopulationlist.add(worldPopulation);
		}

		lv = (ListView) findViewById(R.id.listView);
		listViewAdapter = new ListViewAdapter(getApplicationContext(),
				worldPopulationlist);
		lv.setAdapter(listViewAdapter);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		lv.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				listViewAdapter.removeSelection();
			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				mode.getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
				case R.id.delete:
					SparseBooleanArray selected = listViewAdapter
							.getSelectIds();

					for (int i = (selected.size() - 1); i >= 0; i--) {
						if (selected.valueAt(i)) {
							WorldPopulation selectedItem = listViewAdapter
									.getItem(selected.keyAt(i));
							listViewAdapter.remove(selectedItem);
						}
					}
					mode.finish();
					return true;

				default:
					return false;
				}
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				final int checkedCount = lv.getCheckedItemCount();
				mode.setTitle(checkedCount + " Selected");
				listViewAdapter.toggleSelection(position);
			}
		});
	}

	
}
