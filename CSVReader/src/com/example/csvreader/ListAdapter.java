package com.example.csvreader;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String[]> {

	List<String[]> scoreList = new ArrayList<String[]>();

	static class ViewHolder {
		TextView name, score;
	}

	@Override
	public String[] getItem(int position) {
		return scoreList.get(position);
	}

	@Override
	public int getCount() {
		return this.scoreList.size();
	}

	@Override
	public void add(String[] object) {
		scoreList.add(object);
		super.add(object);
	}

	public ListAdapter(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.single_list_view, parent, false);
			holder = new ViewHolder();
			holder.name = (TextView) row.findViewById(R.id.nameText);
			holder.score = (TextView) row.findViewById(R.id.scoreText);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		String[] stats = getItem(position);
		holder.name.setText(stats[0]);
		holder.score.setText(stats[1]);
		return row;
	}
}
