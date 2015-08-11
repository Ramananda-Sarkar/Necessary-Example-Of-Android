package com.rama.expandableview;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> listData;
	private HashMap<String, List<String>> listDataChild;
	boolean isChecked;

	public ExpandableListAdapter(Context con, List<String> listData, HashMap<String, List<String>> childData) {
		_context = con;
		this.listData = listData;
		listDataChild = childData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.listDataChild.get(this.listData.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		final String childText = (String) getChild(groupPosition, childPosition);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item, null);
		}
		TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
		txtListChild.setText(childText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.listDataChild.get(this.listData.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.listData.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.listData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_group, null);
		}
		TextView lbListData = (TextView) convertView.findViewById(R.id.lblListHeader);
		ImageView img = (ImageView) convertView.findViewById(R.id.imageView1);
		lbListData.setTypeface(null, Typeface.BOLD);
		lbListData.setText(headerTitle);
		if (isExpanded) {
			img.setImageResource(R.drawable.bottom);
		}
		if (!isExpanded) {
			img.setImageResource(R.drawable.side);
		}
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
