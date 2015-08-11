package com.rama.slidermenudemo.adapter;

import java.util.ArrayList;

import model.NewDrawerItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rama.slidermenudemo.R;

public class NavDrawerListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<NewDrawerItem> navDrawerItems = new ArrayList<NewDrawerItem>();

	public NavDrawerListAdapter(Context context, ArrayList<NewDrawerItem> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;

	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.drawer_list_item, null);

		}
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
		imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
		txtTitle.setText(navDrawerItems.get(position).getTitle());
		if (navDrawerItems.get(position).isCounterVisible()) {
			txtCount.setText(navDrawerItems.get(position).getTitle());
		} else {
			txtCount.setVisibility(View.GONE);
		}
		return convertView;
	}

}
