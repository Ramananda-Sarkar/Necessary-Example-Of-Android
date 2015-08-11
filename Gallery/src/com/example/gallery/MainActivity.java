package com.example.gallery;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	GridView gv;
	ArrayList<File> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gv = (GridView) findViewById(R.id.gridView);

		list = imageReader(Environment.getExternalStorageDirectory());

		gv.setAdapter(new GridAdapter());
	}

	class GridAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(R.layout.single_view,
					parent, false);
			ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
			iv.setImageURI(Uri.parse(getItem(position).toString()));
			return convertView;
		}

	}

	ArrayList<File> imageReader(File root) {
		ArrayList<File> aList = new ArrayList<File>();

		File[] files = root.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				aList.addAll(imageReader(files[i]));
			} else {
				if (files[i].getName().endsWith(".jpg")) {
					aList.add(files[i]);
				}
			}
		}
		return aList;
	}

}
