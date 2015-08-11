package com.rama.multipledeletelistview;

import java.util.List;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<WorldPopulation> {
	Context context;
	LayoutInflater inflater;
	List<WorldPopulation> worldPopulationlist;
	private SparseBooleanArray mSelectedItemsIds;

	public ListViewAdapter(Context context,
			List<WorldPopulation> worldPopulationlist) {
		super(context, R.layout.single_item, worldPopulationlist);
		mSelectedItemsIds = new SparseBooleanArray();
		this.context = context;
		this.worldPopulationlist = worldPopulationlist;
		inflater = LayoutInflater.from(context);
	}

	private class ViewHolder {
		TextView rank;
		TextView country;
		TextView population;
		ImageView flag;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.single_item, null);

			holder.rank = (TextView) convertView.findViewById(R.id.rank);
			holder.country = (TextView) convertView.findViewById(R.id.country);
			holder.population = (TextView) convertView
					.findViewById(R.id.population);

			holder.flag = (ImageView) convertView.findViewById(R.id.flag);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.rank.setText(worldPopulationlist.get(position).getRank());
		holder.country.setText(worldPopulationlist.get(position).getCountry());
		holder.population.setText(worldPopulationlist.get(position)
				.getPopulation());
		holder.flag.setImageResource(worldPopulationlist.get(position)
				.getFlag());

		return convertView;
	}

	@Override
	public void remove(WorldPopulation object) {
		worldPopulationlist.remove(object);
		notifyDataSetChanged();
	}

	List<WorldPopulation> getWorldPopulations() {
		return worldPopulationlist;
	}

	public void toggleSelection(int position) {
		selectView(position, !mSelectedItemsIds.get(position));
	}

	public void removeSelection() {
		mSelectedItemsIds = new SparseBooleanArray();
		notifyDataSetChanged();
	}

	public void selectView(int position, boolean value) {
		if (value) {
			mSelectedItemsIds.put(position, value);
		} else {
			mSelectedItemsIds.delete(position);
		}
		notifyDataSetChanged();
	}

	public int getSelectCount() {
		return mSelectedItemsIds.size();
	}

	public SparseBooleanArray getSelectIds() {
		return mSelectedItemsIds;
	}
}
