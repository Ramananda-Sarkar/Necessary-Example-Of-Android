package com.rama.contentprovider;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView lv;
	ArrayAdapter<String> adapter;
	ArrayList<String> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		data = new ArrayList<String>();
		addContacts();
		lv = (ListView) findViewById(R.id.lv);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		lv.setAdapter(adapter);

	}

	private ArrayList<String> addContacts() {

		Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;

		// Querying the table ContactsContract.Contacts to retrieve all the
		// contacts
		Cursor contactsCursor = getContentResolver().query(contactsUri, null,
				null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC ");

		if (contactsCursor.moveToFirst()) {
			do {
				long contactId = contactsCursor.getLong(contactsCursor
						.getColumnIndex("_ID"));

				Uri dataUri = ContactsContract.Data.CONTENT_URI;

				Cursor dataCursor = getContentResolver().query(dataUri, null,
						ContactsContract.Data.CONTACT_ID + "=" + contactId,
						null, null);

				String displayName = "";

				if (dataCursor.moveToFirst()) {
					// Getting Display Name
					displayName = dataCursor
							.getString(dataCursor
									.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));

					data.add(displayName);
				}

			} while (contactsCursor.moveToNext());
		}
		return data;

	}

}
