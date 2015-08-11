package com.rama.pdfreaderdemo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView imageView;
	private int currentPage = 0;
	private Button next, previous;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		next = (Button) findViewById(R.id.next);
		previous = (Button) findViewById(R.id.previous);

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				currentPage++;
				CopyReadAssets();
			}
		});

		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				currentPage--;
				CopyReadAssets();
			}
		});

		CopyReadAssets();
	}

	private void CopyReadAssets() {
		AssetManager assetManager = getAssets();

		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), "cad.pdf");
		try {
			in = assetManager.open("abc.pdf");
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/cad.pdf"),
				"application/pdf");

		startActivity(intent);
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}
}
