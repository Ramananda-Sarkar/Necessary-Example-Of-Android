package com.ramananda.dropboxdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.TokenPair;

public class MainActivity extends Activity implements OnClickListener {

	private LinearLayout contaner;
	private DropboxAPI dropboxApi;
	private boolean isUserLoggedIn;
	private Button btnLogin, btnUpload, btnList;

	private final static String DROPBOX_FILE_DIR = "/DropboxDemo/";
	private final static String DROPBOX_NAME = "dropbox_prefs";
	private final static String ACCESS_KEY = "mgap8t2shi0uub1";
	private final static String ACCESS_SECRET = "jmdeno10101a9fm";
	private final static AccessType ACCESS_TYPE = AccessType.DROPBOX;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contaner = (LinearLayout) findViewById(R.id.containver_files);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);
		btnUpload = (Button) findViewById(R.id.btnUplod);
		btnUpload.setOnClickListener(this);
		btnList = (Button) findViewById(R.id.btnListFile);
		btnList.setOnClickListener(this);

		loggedIn(false);

		AppKeyPair appKeyPair = new AppKeyPair(ACCESS_KEY, ACCESS_SECRET);
		AndroidAuthSession session;
		SharedPreferences preferences = getSharedPreferences(DROPBOX_NAME, 0);
		String key = preferences.getString(ACCESS_KEY, null);
		String secret = preferences.getString(ACCESS_SECRET, null);

		if (key != null && secret != null) {
			AccessTokenPair token = new AccessTokenPair(key, secret);
			session = new AndroidAuthSession(appKeyPair, ACCESS_TYPE, token);

		} else {
			session = new AndroidAuthSession(appKeyPair, ACCESS_TYPE);

		}
		dropboxApi = new DropboxAPI(session);
	}

	@Override
	protected void onResume() {
		super.onResume();
		AndroidAuthSession session = (AndroidAuthSession) dropboxApi
				.getSession();
		if (session.authenticationSuccessful()) {
			try {
				session.finishAuthentication();
				TokenPair tokens = session.getAccessTokenPair();
				SharedPreferences prefs = getSharedPreferences(DROPBOX_NAME, 0);
				Editor editor = prefs.edit();
				editor.putString(ACCESS_KEY, tokens.key);
				editor.putString(ACCESS_SECRET, tokens.secret);
				editor.commit();
				loggedIn(true);
			} catch (IllegalStateException e) {
				Toast.makeText(getApplicationContext(),
						"Error During dorpbox auth", Toast.LENGTH_LONG).show();
			}
		}
	}

	private final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			ArrayList<String> result = msg.getData().getStringArrayList("data");
			for (String fileName : result) {
				TextView textView = new TextView(MainActivity.this);
				textView.setText(fileName);
				contaner.addView(textView);
			}
		};
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			if (isUserLoggedIn) {
				dropboxApi.getSession().unlink();
				loggedIn(false);
			} else {
				((AndroidAuthSession) dropboxApi.getSession())
						.startAuthentication(MainActivity.this);
			}
			break;
		case R.id.btnUplod:
			UploadFile uploadFile = new UploadFile(this, dropboxApi,
					DROPBOX_FILE_DIR);
			uploadFile.execute();
			break;
		case R.id.btnListFile:
			ListFiles listFiles = new ListFiles(dropboxApi, DROPBOX_FILE_DIR,
					handler);
			listFiles.execute();
			break;

		}
	}

	public void loggedIn(boolean userLoggenIn) {
		isUserLoggedIn = userLoggenIn;
		btnUpload.setEnabled(userLoggenIn);
		btnUpload.setBackgroundColor(userLoggenIn ? Color.BLUE : Color.GRAY);
		btnList.setEnabled(userLoggenIn);
		btnList.setBackgroundColor(userLoggenIn ? Color.BLUE : Color.GRAY);
		btnLogin.setText(userLoggenIn ? "Logout" : "Login");

	}
}
