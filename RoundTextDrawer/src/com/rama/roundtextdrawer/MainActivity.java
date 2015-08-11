package com.rama.roundtextdrawer;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int HIGHLIGHT_COLOR = 0x999be6ff;

	String[] nameList = new String[] { "Ramananda Sarkar", "Ashutosh Roy",
			"Dolar Sharma", "Porimal Kumar Roy", "Gibon Roy", "Zyx", "Sariful","Mirpur","Abu Bakar" };

	ArrayList<ListData> dataList;

	private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
	private TextDrawable.IBuilder mDrawableBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dataList = new ArrayList<MainActivity.ListData>();
		for (int i = 0; i < nameList.length; i++) {
			ListData ls = new ListData(nameList[i]);
			dataList.add(ls);
		}

		mDrawableBuilder = TextDrawable.builder().round();

		ListView lv = (ListView) findViewById(R.id.listView);
		lv.setAdapter(new SampleAdapter());
	}

	private class SampleAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public ListData getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(MainActivity.this,
						R.layout.list_patern_layout, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			ListData item = getItem(position);

			// provide support for selected state
			updateCheckedState(holder, item);
			holder.imageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// when the image is clicked, update the selected state
					ListData data = getItem(position);
					data.setChecked(!data.isChecked);
					updateCheckedState(holder, data);
				}
			});
			holder.textView.setText(item.data);

			return convertView;
		}

		private void updateCheckedState(ViewHolder holder, ListData item) {
			if (item.isChecked) {
				holder.imageView.setImageDrawable(mDrawableBuilder.build(" ",
						0xff616161));
				holder.view.setBackgroundColor(HIGHLIGHT_COLOR);
				holder.checkIcon.setVisibility(View.VISIBLE);
			} else {

				String fullName = String.valueOf(item.data);
				String firstName = String.valueOf(item.data.charAt(0));
				String lastName = null;
				String finalText = null;
				int size = fullName.length();
				for (int i = 0; i < size; i++) {
					if (fullName.charAt(i) == ' ') {
						lastName = String.valueOf(fullName.charAt(i + 1));
						finalText = firstName.concat(lastName);
						break;
					} else {
						finalText = String.valueOf(item.data.charAt(0));
					}
				}

				TextDrawable drawable = mDrawableBuilder.build(finalText,
						mColorGenerator.getColor(item.data));
				holder.imageView.setImageDrawable(drawable);
				holder.view.setBackgroundColor(Color.TRANSPARENT);
				holder.checkIcon.setVisibility(View.GONE);
			}
		}
	}

	private static class ViewHolder {

		private View view;

		private ImageView imageView;

		private TextView textView;

		private ImageView checkIcon;

		private ViewHolder(View view) {
			this.view = view;
			imageView = (ImageView) view.findViewById(R.id.imageView);
			textView = (TextView) view.findViewById(R.id.textView);
			checkIcon = (ImageView) view.findViewById(R.id.check_icon);
		}
	}

	private static class ListData {

		private String data;

		private boolean isChecked;

		public ListData(String data) {
			this.data = data;
		}

		public void setChecked(boolean isChecked) {
			this.isChecked = isChecked;
		}
	}
}
