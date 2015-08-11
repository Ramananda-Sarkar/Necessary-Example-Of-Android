package com.code.androiddatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseContents extends SQLiteOpenHelper {

	public static String DB_NAME = "Contact_Details";
	public static String CONTACT = "contact";
	public static String CONTACT_NAME = "name";
	public static String CONTACT_ID = "id";
	public static String CONTACT_EDUCAT = "educat";
	public static String CONTACT_EMAIL = "email";
	public static String CONTACT_MOBILE = "mobile";
	
	
public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
			
					+ CONTACT
					+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " name TEXT, "
					+ " educat TEXT, "
					+ " email TEXT, "
					+ " mobile TEXT);";

	

	public DatabaseContents(Context context) {
	
		super(context, DB_NAME, null, 1);
	
	}

	
	@Override
	public void onCreate(SQLiteDatabase database) {
		
		database.execSQL(CREATE_TABLE_SQL);
		
		}
	
	

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) 
	{
	
		
	}

	
}
