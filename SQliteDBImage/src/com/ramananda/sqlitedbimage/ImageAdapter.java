package com.ramananda.sqlitedbimage;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	Context context;
	ArrayList<Contact> dataList;
	Bitmap circleBitmap;

	public ImageAdapter(Context context, ArrayList<Contact> imageList) {
		this.context = context;
		this.dataList = imageList;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	public class ViewHolder {
		TextView name;
		ImageView imageView;
	}

	@Override
	public long getItemId(int position) {
		return dataList.indexOf(getItem(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.screen_list, null);
			holder.name = (TextView) convertView.findViewById(R.id.txtTitle);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.imgIcon);
			// String str = ((TextView) convertView.findViewById(R.id.txt_eng))
			// .getText().toString();

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(dataList.get(position).get_name());
		byte[] bs = dataList.get(position).get_image();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bs);

		Bitmap imagesBitmap = BitmapFactory.decodeStream(byteArrayInputStream);
		circleBitmap = ImageConverter.getRoundedCornerBitmap(imagesBitmap, 75);
		holder.imageView.setImageBitmap(circleBitmap);
		return convertView;
	}

}
