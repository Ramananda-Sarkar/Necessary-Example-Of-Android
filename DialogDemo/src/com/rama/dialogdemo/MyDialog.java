package com.rama.dialogdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class MyDialog extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("This is my dialog.....")
				.setPositiveButton("ok", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						toast("select positive button");

					}
				}).setNegativeButton("cancel", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						toast("select negative button");

					}
				});
		builder.setTitle("Dilog Demo");
		AlertDialog dialog = builder.create();
		return dialog;
	}

	public void toast(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}
}
