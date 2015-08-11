package com.rama.multiselectdialog;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class MultiChoiceDialog extends DialogFragment {

	ArrayList<String> list = new ArrayList<String>();

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final String[] items = getResources().getStringArray(
				R.array.my_color_selection);
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
		alertDialog
				.setTitle("Choose Color")
				.setMultiChoiceItems(R.array.my_color_selection, null,
						new OnMultiChoiceClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int watch, boolean isCheck) {
								if (isCheck) {
									list.add(items[watch]);
								} else if (list.contains(items[watch])) {
									list.remove(items[watch]);
								}
							}
						}).setPositiveButton("OK", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int watch) {
						String selection = "";

						for (String ms : list) {
							selection = selection + "\n" + ms;
						}
						mytoast("Color Selection : " + selection);
					}
				});
		return alertDialog.create();
	}

	public void mytoast(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}

}
