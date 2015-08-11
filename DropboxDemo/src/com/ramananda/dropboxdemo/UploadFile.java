package com.ramananda.dropboxdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;

public class UploadFile extends AsyncTask<Void, Void, Boolean> {

	private DropboxAPI dropboxApi;
	private String path;
	private Handler handler;
	Context _context;

	public UploadFile(Context context, DropboxAPI dropboxAPI, String path)
	{
		super();
		this.dropboxApi = dropboxAPI;
		this.path = path;
		this._context = context;
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		final File tempDropboxDirectory = _context.getCacheDir();
		File tempFileToUpload;
		FileWriter fileWriter = null;
		try {
			tempFileToUpload = File.createTempFile("file", ".txt",
					tempDropboxDirectory);
			fileWriter = new FileWriter(tempFileToUpload);
			fileWriter.write("test file");
			fileWriter.close();
			FileInputStream fileInputStream = new FileInputStream(
					tempFileToUpload);
			dropboxApi.putFile(path + "test_file", fileInputStream,
					tempFileToUpload.length(), null, null);
			tempFileToUpload.delete();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		if (result) {
			myToast("File has been uploaded!");
		} else {
			myToast("Error occured while processing the upload request");
		}
	}

	public void myToast(String text) {
		Toast.makeText(_context, text, Toast.LENGTH_LONG).show();
	}
}
