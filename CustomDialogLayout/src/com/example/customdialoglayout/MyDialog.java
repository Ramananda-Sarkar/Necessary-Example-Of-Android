package com.example.customdialoglayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MyDialog extends DialogFragment {

	LayoutInflater inflater;
	View v;
	EditText user, pass;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		inflater = getActivity().getLayoutInflater();
		v = inflater.inflate(R.layout.login_dialog, null);
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
		alertDialog.setView(v).setPositiveButton("OK", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				user = (EditText) v.findViewById(R.id.etName);
				pass = (EditText) v.findViewById(R.id.etPass);

				Toast.makeText(
						getActivity(),
						user.getText().toString() + "\n"
								+ pass.getText().toString(), Toast.LENGTH_LONG)
						.show();

			}
		}).setNegativeButton("Cancel", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		return alertDialog.create();
	}
}
