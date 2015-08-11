package com.raman.musicplayer;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.database.helper.PlayListTable;
import com.database.helper.PlayerDBHelper;
import com.database.helper.SongInPlayListTable;
import com.database.helper.SongTable;

public class MusicPlayerContent extends ContentProvider {

	private PlayerDBHelper playerDBHelper;
	private SQLiteDatabase database;
	private static Context context;
	private static String packageName = context.getPackageName();

	private static final class Paths {
		private static final String SONG_PATH = "song_uri";
		private static final String PLAYLIST_PATH = "playlist_uri";
		private static final String SONG_IN_PLAYLIST_PATH = "song_in_playlist_uri";

	}

	// content Uri

	public static final Uri SONG_CONTENT_URI = Uri.parse("content://"
			+ packageName + "/" + Paths.SONG_PATH);
	public static final Uri PLAYLIST_CONTENT_URI = Uri.parse("content://"
			+ packageName + "/" + Paths.PLAYLIST_PATH);
	public static final Uri SONGIN_CONTENT_URI = Uri.parse("content://"
			+ packageName + "/" + Paths.SONG_IN_PLAYLIST_PATH);

	// content types

	public static final String SONG_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/vnd.nearalias.musicplayer.song";
	public static final String PLAYLIST_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/vnd.nearalias.musicplayer.playlist";
	public static final String SONG_IN_PLAYLIST_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/vnd.nearalias.musicplayer.song_in_playlist";

	// content item types
	public static final String SONG_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/vnd.nearalias.musicplayer.song";
	public static final String PLAYLIST_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/vnd.nearalias.musicplayer.playlist";
	public static final String SONG_IN_PLAYLIST_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/vnd.nearalias.musicplayer.song_in_playlist";

	// URI matches
	private static final class UriMatches {
		private static final int SONG = 0;
		private static final int SONG_ID = 1;
		private static final int PLAYLIST = 2;
		private static final int PLAYLIST_ID = 3;
		private static final int SONG_IN_PLAYLIST = 4;
		private static final int SONG_IN_PLAYLIST_ID = 5;
	}

	private static final UriMatcher sUriMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);

	static {
		sUriMatcher.addURI(packageName, Paths.SONG_PATH, UriMatches.SONG);
		sUriMatcher.addURI(packageName, Paths.SONG_PATH + "/#",
				UriMatches.SONG_ID);
		sUriMatcher.addURI(packageName, Paths.PLAYLIST_PATH,
				UriMatches.PLAYLIST);
		sUriMatcher.addURI(packageName, Paths.PLAYLIST_PATH + "/#",
				UriMatches.PLAYLIST_ID);
		sUriMatcher.addURI(packageName, Paths.SONG_IN_PLAYLIST_PATH,
				UriMatches.SONG_IN_PLAYLIST);
		sUriMatcher.addURI(packageName, Paths.SONG_IN_PLAYLIST_PATH + "/#",
				UriMatches.SONG_IN_PLAYLIST_ID);
	}

	private final SQLiteDatabase getDatabase() {
		if (database == null) {
			if (playerDBHelper == null) {
				playerDBHelper = new PlayerDBHelper(getContext());

			}
			database = playerDBHelper.getWritableDatabase();
		}
		return database;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int rowsDeleted = 0;
		String id;
		switch (sUriMatcher.match(uri)) {
		case UriMatches.SONG:
			rowsDeleted = getDatabase().delete(SongTable.SONG_TABLE_NAME,
					selection, selectionArgs);
			break;
		case UriMatches.SONG_ID:
			id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsDeleted = getDatabase().delete(SongTable.SONG_TABLE_NAME,
						SongTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsDeleted = getDatabase().delete(SongTable.SONG_TABLE_NAME,
						SongTable.COLUMN_ID + "=" + id + " and " + selection,
						selectionArgs);
			}
			break;
		case UriMatches.PLAYLIST:
			rowsDeleted = getDatabase()
					.delete(PlayListTable.PLAYLIST_TABLE_NAME, selection,
							selectionArgs);
			break;
		case UriMatches.PLAYLIST_ID:
			id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsDeleted = getDatabase().delete(
						PlayListTable.PLAYLIST_TABLE_NAME,
						PlayListTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsDeleted = getDatabase().delete(
						PlayListTable.PLAYLIST_TABLE_NAME,
						PlayListTable.COLUMN_ID + "=" + id + " and "
								+ selection, selectionArgs);
			}
			break;
		case UriMatches.SONG_IN_PLAYLIST:
			rowsDeleted = getDatabase().delete(
					SongInPlayListTable.SONG_IN_PLAYLIST_TABLE_NAME, selection,
					selectionArgs);
			break;
		case UriMatches.SONG_IN_PLAYLIST_ID:
			id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsDeleted = getDatabase().delete(
						SongInPlayListTable.SONG_IN_PLAYLIST_TABLE_NAME,
						SongInPlayListTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsDeleted = getDatabase().delete(
						SongInPlayListTable.SONG_IN_PLAYLIST_TABLE_NAME,
						SongInPlayListTable.COLUMN_ID + "=" + id + " and "
								+ selection, selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case UriMatches.SONG:
			return SONG_CONTENT_TYPE;
		case UriMatches.SONG_ID:
			return SONG_CONTENT_ITEM_TYPE;
		case UriMatches.PLAYLIST:
			return PLAYLIST_CONTENT_TYPE;
		case UriMatches.PLAYLIST_ID:
			return PLAYLIST_CONTENT_ITEM_TYPE;
		case UriMatches.SONG_IN_PLAYLIST:
			return SONG_IN_PLAYLIST_CONTENT_TYPE;
		case UriMatches.SONG_IN_PLAYLIST_ID:
			return SONG_IN_PLAYLIST_CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Uri resultUri = null;
		long id = 0;
		switch (sUriMatcher.match(uri)) {
		case UriMatches.SONG:
			id = getDatabase().insert(SongTable.SONG_TABLE_NAME, null, values);
			if (id > 0) {
				resultUri = ContentUris.withAppendedId(SONG_CONTENT_URI, id);
			}
			break;
		case UriMatches.PLAYLIST:
			id = getDatabase().insert(PlayListTable.PLAYLIST_TABLE_NAME, null,
					values);
			if (id > 0) {
				resultUri = ContentUris
						.withAppendedId(PLAYLIST_CONTENT_URI, id);
			}
			break;

		case UriMatches.SONG_IN_PLAYLIST:
			id = getDatabase().insert(
					SongInPlayListTable.SONG_IN_PLAYLIST_TABLE_NAME, null,
					values);
			if (id > 0) {
				resultUri = ContentUris.withAppendedId(SONGIN_CONTENT_URI, id);
			}
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		if (id > 0) {
			getContext().getContentResolver().notifyChange(resultUri, null);
			return resultUri;
		}
		return null;

	}

	@Override
	public boolean onCreate() {
		playerDBHelper = new PlayerDBHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		switch (sUriMatcher.match(uri)) {
		case UriMatches.SONG:
			break;
		case UriMatches.SONG_ID:
			queryBuilder.setTables(SongTable.SONG_TABLE_NAME);
			queryBuilder.appendWhere(SongTable.COLUMN_ID + "="
					+ uri.getLastPathSegment());
			break;
		case UriMatches.PLAYLIST:
			break;
		case UriMatches.PLAYLIST_ID:
			queryBuilder.setTables(PlayListTable.PLAYLIST_TABLE_NAME);
			queryBuilder.appendWhere(PlayListTable.COLUMN_ID + "="
					+ uri.getLastPathSegment());
			break;
		case UriMatches.SONG_IN_PLAYLIST:
			break;
		case UriMatches.SONG_IN_PLAYLIST_ID:
			queryBuilder
					.setTables(SongInPlayListTable.SONG_IN_PLAYLIST_TABLE_NAME);
			queryBuilder.appendWhere(SongInPlayListTable.COLUMN_ID + "="
					+ uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		Cursor cursor = queryBuilder.query(getDatabase(), projection,
				selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int rowsUpdated = 0;
		String id;
		switch (sUriMatcher.match(uri)) {
		case UriMatches.SONG:
			rowsUpdated = getDatabase().update(SongTable.SONG_TABLE_NAME,
					values, selection, selectionArgs);
			break;
		case UriMatches.SONG_ID:
			id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsUpdated = getDatabase().update(SongTable.SONG_TABLE_NAME,
						values, SongTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsUpdated = getDatabase().update(SongTable.SONG_TABLE_NAME,
						values,
						SongTable.COLUMN_ID + "=" + id + " and " + selection,
						selectionArgs);
			}
			break;
		case UriMatches.PLAYLIST:
			rowsUpdated = getDatabase().update(
					PlayListTable.PLAYLIST_TABLE_NAME, values, selection,
					selectionArgs);
			break;
		case UriMatches.PLAYLIST_ID:
			id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsUpdated = getDatabase().update(
						PlayListTable.PLAYLIST_TABLE_NAME, values,
						PlayListTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsUpdated = getDatabase().update(
						PlayListTable.PLAYLIST_TABLE_NAME,
						values,
						PlayListTable.COLUMN_ID + "=" + id + " and "
								+ selection, selectionArgs);
			}
			break;
		case UriMatches.SONG_IN_PLAYLIST:
			rowsUpdated = getDatabase().update(
					SongInPlayListTable.SONG_IN_PLAYLIST_TABLE_NAME, values,
					selection, selectionArgs);
			break;
		case UriMatches.SONG_IN_PLAYLIST_ID:
			id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsUpdated = getDatabase().update(
						SongInPlayListTable.SONG_IN_PLAYLIST_TABLE_NAME,
						values, SongInPlayListTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsUpdated = getDatabase().update(
						SongInPlayListTable.SONG_IN_PLAYLIST_TABLE_NAME,
						values,
						SongInPlayListTable.COLUMN_ID + "=" + id + " and "
								+ selection, selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsUpdated;
	}

}
