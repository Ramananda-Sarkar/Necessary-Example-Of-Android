package com.rama.customizedlistview;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomizedView extends ArrayAdapter<StudentInfo> {
	Activity con;
	ArrayList<StudentInfo> listview;

	public CustomizedView(Context context, ArrayList<StudentInfo> students) {
		super(context, R.layout.list_view, students);
		this.con = (Activity) context;
		this.listview = students;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		if (convertView == null) {
			LayoutInflater inflater = con.getLayoutInflater();
			v = inflater.inflate(R.layout.list_view, null);
			TextView name = (TextView)v.findViewById(R.id.txtName);
			TextView email = (TextView)v.findViewById(R.id.txtEmail);
			TextView phone = (TextView)v.findViewById(R.id.txtPhone);
			TextView address = (TextView)v.findViewById(R.id.txtAddress);
			StudentInfo e = listview.get(position);
			name.setText(e.getName());
			email.setText(e.getEmail());
			phone.setText(e.getPhone());
			address.setText(e.getAddress());
		} else {
			v = convertView;
		}
		return v;
	}

}