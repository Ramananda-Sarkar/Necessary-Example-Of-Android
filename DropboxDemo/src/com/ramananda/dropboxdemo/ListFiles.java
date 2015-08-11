package com.ramananda.dropboxdemo;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;

public class ListFiles extends AsyncTask<Void, Void, ArrayList> {

	private DropboxAPI dropboxApi;
	private String path;
	private Handler handler;

	public ListFiles(DropboxAPI dropboxAPI, String path, Handler handler)
	{
		super();
		this.dropboxApi = dropboxAPI;
		this.path = path;
		this.handler = handler;
	}

	@Override
	protected ArrayList doInBackground(Void... params) {
		ArrayList files = new ArrayList();
		try {
			Entry directory = dropboxApi.metadata(path, 1000, null, true, null);
			for (Entry entry : directory.contents) {
				files.add(entry.fileName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return files;
	}

	@Override
	protected void onPostExecute(ArrayList result) {
		Message message = handler.obtainMessage();
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("data", result);
		message.setData(bundle);
		handler.sendMessage(message);
	}
}
