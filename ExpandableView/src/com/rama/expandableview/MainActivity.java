package com.rama.expandableview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class MainActivity extends Activity {
	ExpandableListAdapter listAdapter;
	ExpandableListView expandableListView;
	String[] all_district_list;
	List<String> distictList;
	HashMap<String, List<String>> listChildData;
	boolean isChecked;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);

		expandableListView = (ExpandableListView) findViewById(R.id.lvExp);
		all_district_list = getResources().getStringArray(R.array.distict_list);

		prepareDataList();
		listAdapter = new ExpandableListAdapter(this, distictList,
				listChildData);

		expandableListView.setAdapter(listAdapter);
		expandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {
					int previousGroup = -1;

					@Override
					public void onGroupExpand(int groupPosition) {
						if (groupPosition != previousGroup) {
							expandableListView.collapseGroup(previousGroup);
							previousGroup = groupPosition;
						}
					}
				});
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(
						getApplicationContext(),
						listChildData.get(distictList.get(groupPosition)).get(
								childPosition), Toast.LENGTH_LONG).show();
				
				return false;
			}

		});
	}

	public void prepareDataList() {
		distictList = new ArrayList<String>();
		listChildData = new HashMap<String, List<String>>();

		for (int i = 0; i < all_district_list.length; i++) {
			distictList.add(all_district_list[i]);
		}

		// adding child data
		List<String> joypurhat = new ArrayList<String>();

		for (int i = 0; i < getResources().getStringArray(
				R.array.joypurhat_thana_list).length; i++) {
			joypurhat.add(getResources().getStringArray(
					R.array.joypurhat_thana_list)[i]);
		}

		List<String> bogura = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.bogura_thana_list).length; i++) {
			bogura.add(getResources().getStringArray(R.array.bogura_thana_list)[i]);
		}

		// Adding child data
		List<String> dinajpur = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.dinajpur_thana_list).length; i++) {
			dinajpur.add(getResources().getStringArray(
					R.array.dinajpur_thana_list)[i]);
		}

		List<String> naogaon = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.naogaon_thana_list).length; i++) {
			naogaon.add(getResources().getStringArray(
					R.array.naogaon_thana_list)[i]);
		}

		List<String> natore = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.natore_thana_list).length; i++) {
			natore.add(getResources().getStringArray(R.array.natore_thana_list)[i]);
		}
		List<String> nababgonj = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.nababgonj_thana_list).length; i++) {
			nababgonj.add(getResources().getStringArray(
					R.array.nababgonj_thana_list)[i]);
		}
		List<String> pabna = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.pabna_thana_list).length; i++) {
			pabna.add(getResources().getStringArray(R.array.pabna_thana_list)[i]);
		}
		List<String> sirajgonj = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.sirajgonj_thana_list).length; i++) {
			sirajgonj.add(getResources().getStringArray(
					R.array.sirajgonj_thana_list)[i]);
		}
		
		List<String> thakurgaon = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.thakurgaon_thana_list).length; i++) {
			thakurgaon.add(getResources().getStringArray(
					R.array.thakurgaon_thana_list)[i]);
		}
		List<String> panchagrah = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.panchagrah_thana_list).length; i++) {
			panchagrah.add(getResources().getStringArray(
					R.array.panchagrah_thana_list)[i]);
		}
		List<String> dhaka = new ArrayList<String>();
		for (int i = 0; i < getResources().getStringArray(
				R.array.sirajgonj_thana_list).length; i++) {
			dhaka.add(getResources().getStringArray(
					R.array.sirajgonj_thana_list)[i]);
		}
		
		
		listChildData.put(distictList.get(0), joypurhat);
		listChildData.put(distictList.get(1), bogura);
		listChildData.put(distictList.get(2), dinajpur);
		listChildData.put(distictList.get(3), naogaon);
		listChildData.put(distictList.get(4), natore);
		listChildData.put(distictList.get(5), nababgonj);
		listChildData.put(distictList.get(6), pabna);
		listChildData.put(distictList.get(7), sirajgonj);
		listChildData.put(distictList.get(8), thakurgaon);
		listChildData.put(distictList.get(9), panchagrah);
		listChildData.put(distictList.get(10), dhaka);

	}

}
