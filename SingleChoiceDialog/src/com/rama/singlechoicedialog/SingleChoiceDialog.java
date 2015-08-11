package com.rama.singlechoicedialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class SingleChoiceDialog extends DialogFragment {

	final CharSequence[] items = { "Easy", "Medium", "Hard" };
	String selection;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
		alertDialog.setTitle("Choose Difficult Level....")
				.setSingleChoiceItems(items, -1, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							selection = (String) items[which];
							break;
						case 1:
							selection = (String) items[which];
							break;
						case 2:
							selection = (String) items[which];
							break;
						default:
							break;
						}
					}
				}).setPositiveButton("OK", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mytoast("Your selection level is " + selection);
					}
				});
		return alertDialog.create();
	}

	public void mytoast(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}

}
