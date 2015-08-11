package com.database.helper;

import android.database.sqlite.SQLiteDatabase;

public class PlayListTable {
	public static final String PLAYLIST_TABLE_NAME = "playlist";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";

	private static final String CREATE_PLAYLIST_TABLE = "CREATE TABLE IF NOT EXISTS"
			+ PLAYLIST_TABLE_NAME
			+ "("
			+ COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_NAME
			+ " TEXT NOT NULL" + ");";

	public static void onCreateTable(SQLiteDatabase db) {
		db.execSQL(CREATE_PLAYLIST_TABLE);
	}

	public static void onUpgradeTable(SQLiteDatabase db, int oldVersion,
			int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + PLAYLIST_TABLE_NAME);
		onCreateTable(db);
	}

}
