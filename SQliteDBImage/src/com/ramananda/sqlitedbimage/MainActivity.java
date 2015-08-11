package com.ramananda.sqlitedbimage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int PICK_FROM_CAMERA = 1;
	private static final int PICK_FROM_GALLERY = 2;

	ImageView imageView;
	Button btnSelect, btnShow, save;
	ListView lv;

	ArrayList<Contact> imageList = new ArrayList<Contact>();
	ArrayList<Contact> contacts;
	ImageAdapter adapter;
	DBOpenHelper dbHelper;
	Bitmap bitmap, circleBitmap;
	EditText etName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dbHelper = DBOpenHelper.getInstance(getApplicationContext());
		imageView = (ImageView) findViewById(R.id.ivImage);
		etName = (EditText) findViewById(R.id.etName);
		lv = (ListView) findViewById(R.id.listView);
		contacts = new ArrayList<Contact>();

		save = (Button) findViewById(R.id.btnSave);
		btnSelect = (Button) findViewById(R.id.btnSelectPhoto);
		btnShow = (Button) findViewById(R.id.btnShow);
		btnSelect.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				selectImage();
			}
		});

		btnShow.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				contacts = dbHelper.getAllContacts();
				for (Contact conts : contacts) {
					imageList.add(conts);
				}

				adapter = new ImageAdapter(getApplicationContext(), imageList);
				lv.setAdapter(adapter);
			}
		});
	}

	public void save(View v) {

		String name = etName.getText().toString();
		if (circleBitmap == null) {
			circleBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
		}
		// convert bitmap to byte
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		circleBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte imageInByte[] = stream.toByteArray();

		Contact cont = new Contact(name, imageInByte);

		long inserted = dbHelper.addContact(cont);
		if (inserted > 0) {
			Toast.makeText(getApplicationContext(), "data insert sucessfull",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), "data insert failed",
					Toast.LENGTH_LONG).show();
		}
	}

	private void selectImage() {
		final CharSequence[] items = { "Take Photo", "Choose from Libray",
				"Cancel" };

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("Add Photo !");
		builder.setItems(items, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (items[which].equals("Take Photo")) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, PICK_FROM_CAMERA);

				} else if (items[which].equals("Choose from Libray")) {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(
							Intent.createChooser(intent, "Select File"),
							PICK_FROM_GALLERY);
				} else if (items[which].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == PICK_FROM_GALLERY) {
				onSelectFromGalleryResult(data);
			} else if (requestCode == PICK_FROM_CAMERA) {
				onCaptureImageResult(data);
			}
		}
	}

	private void onCaptureImageResult(Intent data) {
		bitmap = (Bitmap) data.getExtras().get("data");
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();

		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

		File designation = new File(Environment.getExternalStorageDirectory(),
				System.currentTimeMillis() + ".jpg");

		FileOutputStream fo;

		try {
			designation.createNewFile();
			fo = new FileOutputStream(designation);
			fo.write(bytes.toByteArray());
			fo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		circleBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 75);
		imageView.setImageBitmap(circleBitmap);
	}

	private void onSelectFromGalleryResult(Intent data) {
		Uri selectUriImage = data.getData();

		String[] projection = { MediaColumns.DATA };

		Cursor cursor = managedQuery(selectUriImage, projection, null, null,
				null);

		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();

		String selectImagePath = cursor.getString(column_index);

		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(selectImagePath, options);

		final int REQUIRED_SIZE = 200;

		int scale = 1;
		while (options.outWidth / scale / 2 > REQUIRED_SIZE
				&& options.outHeight / scale / 2 >= REQUIRED_SIZE) {
			scale *= 2;
		}
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(selectImagePath, options);

		circleBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 75);
		imageView.setImageBitmap(circleBitmap);
	}

}
