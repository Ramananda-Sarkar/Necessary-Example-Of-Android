package com.rama.prepopulateddbdemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PrepopulatedDBOpenHelper extends SQLiteOpenHelper {
	private static PrepopulatedDBOpenHelper PrepopulateDBHelperInstance;
	private static final String DB_NAME = "my_dbmanager";

	private static final String TABLE_NAME = "student_data";
	private static final String ID_FIELD = "_id";
	private static final String NAME_FIELD = "name";
	private static final String EMAIL_FIELD = "email";
	private static final String PHONE_FIELD = "phone";
	private static final String ADDRESS_FIELD = "address";
	public static String DB_PATH;
	private SQLiteDatabase database;
	private Context context;

	private PrepopulatedDBOpenHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.context = context;

		Log.e("In Constructor", "Contructor of DatabaseHelper");
		// database path /data/data/pkg-name/databases/
		String packageName = context.getPackageName();
		DB_PATH = "/data/data/" + packageName + "/databases/";
		this.database = openDatabase();

	}
	// single pattern object
	public static PrepopulatedDBOpenHelper getPrepopulatedDBObject(Context context){
		if(PrepopulateDBHelperInstance == null){
			PrepopulateDBHelperInstance = new PrepopulatedDBOpenHelper(context);
		}
		return PrepopulateDBHelperInstance;
	}
	
	public SQLiteDatabase getDatabase(){
		return this.database;
	}
	public SQLiteDatabase openDatabase() {
		String path = DB_PATH + DB_NAME;
		if (database == null) {
			createDatabase();
			database = SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.OPEN_READWRITE);
		}
		return database;
	}

	private void createDatabase() {
		boolean dbExists = checkDB();
		if (!dbExists) {
			this.getReadableDatabase();
			Log.e(getClass().getName(),
					"Database doesn't exist. Copying database from assets...");
			copyDatabase();
		} else {
			Log.e(getClass().getName(), "Database already exists");
		}
	}
	// get method
	
	
	public ArrayList<StudentInfo> getAllStudent(){
		ArrayList<StudentInfo> allStudent = new ArrayList<StudentInfo>();
		
		Cursor cursor = this.database.query(TABLE_NAME, null, null, null, null, null, null, null);
		if(cursor!=null && cursor.getCount()>0){
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
		this.database.close();
		return allStudent;
		
	}
	
	// copy database 
	private void copyDatabase() {
		try {
			InputStream dbInputStream = context.getAssets().open(DB_NAME);
			String path = DB_PATH + DB_NAME;
			OutputStream dbOutputStream = new FileOutputStream(path);
			byte[] buffer = new byte[4096];
			int readCount = 0;
			while ((readCount = dbInputStream.read(buffer)) > 0) {
				dbOutputStream.write(buffer, 0, readCount);
			}

			dbInputStream.close();
			dbOutputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkDB() {
		String path = DB_PATH + DB_NAME;
		File file = new File(path);
		if (file.exists()) {
			Log.e(getClass().getName(), "Database already exists");
			return true;
		}
		Log.e(getClass().getName(), "Database does not exists");
		return false;
	}
	
	public synchronized void close() {
		if (this.database != null) {
			this.database.close();
		}
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
