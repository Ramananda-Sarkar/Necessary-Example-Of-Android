package com.code.androiddatabase;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseInsert {

	SQLiteDatabase db;

	DatabaseContents datacontent;

	public DatabaseInsert(Context context)

	{
		datacontent = new DatabaseContents(context);
	}

	public void open() {
		db = datacontent.getWritableDatabase();
	}

	public void close()

	{
		db.close();
	}

	public long insertContact(String name, String educat, String email,
			String mobile) {

		ContentValues values = new ContentValues();

		values.put(DatabaseContents.CONTACT_NAME, name);

		values.put(DatabaseContents.CONTACT_EDUCAT, educat);

		values.put(DatabaseContents.CONTACT_EMAIL, email);

		values.put(DatabaseContents.CONTACT_MOBILE, mobile);

		long inserted = db.insert(DatabaseContents.CONTACT, null, values);

		return inserted;
	}

	public ArrayList<Contacts> getContacts() {

		ArrayList<Contacts> contacts = new ArrayList<Contacts>();

		String[] columns =

		{

		DatabaseContents.CONTACT_ID,

		DatabaseContents.CONTACT_NAME,

		DatabaseContents.CONTACT_EDUCAT,

		DatabaseContents.CONTACT_EMAIL,

		DatabaseContents.CONTACT_MOBILE

		};

		Cursor cursor = db.query(DatabaseContents.CONTACT, columns, null, null,
				null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			for (int i = 0; i < cursor.getCount(); i++) {

				String id = cursor.getString(cursor
						.getColumnIndex(DatabaseContents.CONTACT_ID));

				String name = cursor.getString(cursor
						.getColumnIndex(DatabaseContents.CONTACT_NAME));

				String educat = cursor.getString(cursor
						.getColumnIndex(DatabaseContents.CONTACT_EDUCAT));

				String email = cursor.getString(cursor
						.getColumnIndex(DatabaseContents.CONTACT_EMAIL));

				String mobile = cursor.getString(cursor
						.getColumnIndex(DatabaseContents.CONTACT_MOBILE));

				Contacts c = new Contacts(name, educat, email, mobile);

				contacts.add(c);

				cursor.moveToNext();

			}

		}

		cursor.close();

		return contacts;
	}

}
