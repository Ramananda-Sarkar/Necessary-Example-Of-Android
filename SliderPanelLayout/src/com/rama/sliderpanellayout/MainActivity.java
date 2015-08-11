package com.rama.sliderpanellayout;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v4.widget.SlidingPaneLayout.PanelSlideListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	SlidingPaneLayout mSlidingPanel;
	ListView mMenuList;
	ImageView appImage;
	TextView TitleText;
	ArrayAdapter<String> adapter;
	String[] MenuTitles = new String[] { "First Item", "Second Item",
			"Third Item", "Fourth Item" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
		mMenuList = (ListView) findViewById(R.id.MenuList);
		appImage = (ImageView) findViewById(android.R.id.home);

		TitleText = (TextView) findViewById(android.R.id.title);

		adapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, MenuTitles);
		mMenuList.setAdapter(adapter);

		mSlidingPanel.setPanelSlideListener(panelListener);
		mSlidingPanel.setParallaxDistance(200);

		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#00968c")));
		getActionBar().setIcon(R.drawable.ic_action_overflow);
	}

	PanelSlideListener panelListener = new PanelSlideListener() {

		@Override
		public void onPanelClosed(View v) {
			// TODO Auto-genxxerated method stub
			// getActionBar().setTitle(getString(R.string.app_name));
			appImage.animate().rotation(0);
		}

		@Override
		public void onPanelOpened(View v) {
			// TODO Auto-generated method stub
			getActionBar().setTitle("Menu Titles");
			appImage.animate().rotation(90);
		}

		@Override
		public void onPanelSlide(View arg0, float arg1) {
			// TODO Auto-generated method stub

		}

	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			if (mSlidingPanel.isOpen()) {
				appImage.animate().rotation(0);
				mSlidingPanel.closePane();
				getActionBar().setTitle(getString(R.string.app_name));
			} else {
				appImage.animate().rotation(90);
				mSlidingPanel.openPane();
				getActionBar().setTitle("Menu Titles");
			}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
