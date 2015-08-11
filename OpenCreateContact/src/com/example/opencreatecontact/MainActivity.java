package com.example.opencreatecontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void create(View v) {
		Intent i = new Intent(Intent.ACTION_INSERT);
		i.setType(Contacts.CONTENT_TYPE);
		if (Integer.valueOf(Build.VERSION.SDK_INT) > 14)
			i.putExtra("finishActivityOnSaveCompleted", true);
		startActivityForResult(i, 2);
	}

}
