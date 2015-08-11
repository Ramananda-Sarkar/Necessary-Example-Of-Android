package com.rama.shareactionexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {
	EditText etTtext;
	ShareActionProvider mShare;
	String text="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etTtext = (EditText) findViewById(R.id.etText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		getMenuInflater().inflate(R.menu.main, menu);

		MenuItem shareItem = (MenuItem) menu.findItem(R.id.action_share);
		mShare = (ShareActionProvider) shareItem.getActionProvider();
		shareAction();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_share:
			
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void shareAction() {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		text = etTtext.getText().toString();
		shareIntent.putExtra(Intent.EXTRA_TEXT, text);
		mShare.setShareIntent(shareIntent);
	}
}
