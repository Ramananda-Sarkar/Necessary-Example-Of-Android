package com.code.androiddatabase;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactList extends Activity {

	ListView listView;

	DatabaseInsert dbinsert;

	String name, educat, email, mobile;

	ArrayList<Contacts> contacts;

	CustomList clist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_contact_list);

		dbinsert = new DatabaseInsert(this);

		dbinsert.open();

		contacts = dbinsert.getContacts();

		dbinsert.close();

		listView = (ListView) findViewById(R.id.listView);

		clist = new CustomList(ContactList.this, R.layout.listshow,
				R.id.tvName, contacts);

		listView.setAdapter(clist);

	}

	
	class CustomList extends ArrayAdapter<Contacts> {

		ArrayList<Contacts> lists;

		Context context;

		public CustomList(Context context, int resource,
				int textViewResourceId, ArrayList<Contacts> objects) {

			super(context, resource, textViewResourceId, objects);

			this.lists = objects;

			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View v = convertView;

			if (convertView == null) {

				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(LAYOUT_INFLATER_SERVICE);

				v = inflater.inflate(R.layout.listshow, parent, false);
			}

			TextView tvName = (TextView) v.findViewById(R.id.tvName);

			TextView tvEmail = (TextView) v.findViewById(R.id.tvEmail);

			Contacts c = (Contacts) this.lists.get(position);

			tvName.setText(c.getName());

			tvEmail.setText(c.getEmail());

			return v;
		}
	}
}
