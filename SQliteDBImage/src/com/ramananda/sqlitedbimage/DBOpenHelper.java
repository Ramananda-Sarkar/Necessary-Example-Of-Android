package com.ramananda.sqlitedbimage;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	private static DBOpenHelper instance;

	private static final String DB_NAME = "db_name";
	private static final int DB_VERSION = 1;

	// Contacts table name
	private static final String TABLE_CONTACTS = "contacts";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_IMAGE = "image";

	private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE "
			+ TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_NAME + " TEXT," + KEY_IMAGE + " BLOB" + ")";

	private DBOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public static DBOpenHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DBOpenHelper(context);
		}
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		onCreate(db);

	}

	public long addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.get_name());
		values.put(KEY_IMAGE, contact.get_image());

		long insert = db.insert(TABLE_CONTACTS, null, values);
		db.close();
		return insert;

	}

	public Contact getSingleContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Contact contact;
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				KEY_NAME, KEY_IMAGE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		contact = new Contact(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getBlob(1));

		return contact;

	}

	// Getting All Contacts
	public ArrayList<Contact> getAllContacts() {
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT * FROM contacts ORDER BY name";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {

				String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
				byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE));

				Contact contacts = new Contact(name, image);

				// Adding contact to list
				contactList.add(contacts);
			} while (cursor.moveToNext());
		}
		// close inserting data from database
		db.close();
		// return contact list
		return contactList;

	}

	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.get_name());
		values.put(KEY_IMAGE, contact.get_image());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.get_id()) });

	}

	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.get_id()) });
		db.close();
	}

	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
