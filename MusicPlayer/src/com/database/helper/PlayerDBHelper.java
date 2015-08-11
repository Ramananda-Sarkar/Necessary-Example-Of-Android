package com.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerDBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "songDatabase.db";
	private static final int DATABASE_VERSION = 2;

	public PlayerDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		SongTable.onCreateTable(db);
		PlayListTable.onCreateTable(db);
		SongInPlayListTable.onCreateTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
			SongTable.onUpgradeTable(db, oldVersion, newVersion);
			PlayListTable.onUpgradeTable(db, oldVersion, newVersion);
			SongInPlayListTable.onUpgradeTable(db, oldVersion, newVersion);
		}
	}

}
