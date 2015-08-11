package com.rama.customizedlistview;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "my_dbmanager";
	private static final int DB_VERSION = 1;

	private static final String TABLE_NAME = "student_data";
	private static final String ID_FIELD = "_id";
	private static final String NAME_FIELD = "name";
	private static final String EMAIL_FIELD = "email";
	private static final String PHONE_FIELD = "phone";
	private static final String ADDRESS_FIELD = "address";

	public static final String STUDENT_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " (" + ID_FIELD
			+ " INTEGER PRIMARY KEY, " + NAME_FIELD + " TEXT, " + EMAIL_FIELD + " TEXT, "
			+ PHONE_FIELD + " TEXT, " + ADDRESS_FIELD + " TEXT)";

	public DatabaseOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(STUDENT_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// insert Item table

	public long insertStudent(StudentInfo stu) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NAME_FIELD, stu.getName());
		values.put(EMAIL_FIELD, stu.getEmail());
		values.put(PHONE_FIELD, stu.getPhone());
		values.put(ADDRESS_FIELD, stu.getAddress());
		long inserted = db.insert(TABLE_NAME, null, values);
		db.close();
		return inserted;
	}

	// data fatch table

	public ArrayList<StudentInfo> getAllStudent() {
		ArrayList<StudentInfo> allStudent = new ArrayList<StudentInfo>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor.getColumnIndex(ID_FIELD));
				String name = cursor.getString(cursor.getColumnIndex(NAME_FIELD));
				String email = cursor.getString(cursor.getColumnIndex(EMAIL_FIELD));
				String phone = cursor.getString(cursor.getColumnIndex(PHONE_FIELD));
				String address = cursor.getString(cursor.getColumnIndex(ADDRESS_FIELD));
				StudentInfo e = new StudentInfo(id, name, email, phone, address);
				allStudent.add(e);
				cursor.moveToNext();

			}
		}
		cursor.close();
		db.close();
		return allStudent;

	}
}
