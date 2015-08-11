package com.rama.vollaywebservicedemo;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class UpdateActivity extends Activity {

	EditText etName, etEmail, etPhone, etAddress;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		etName = (EditText) findViewById(R.id.upName);
		etEmail = (EditText) findViewById(R.id.upEmail);
		etPhone = (EditText) findViewById(R.id.upPhone);
		etAddress = (EditText) findViewById(R.id.upAddress);

		etName.setText(getIntent().getStringExtra("nameValue"));
		etEmail.setText(getIntent().getStringExtra("emailValue"));
		etPhone.setText(getIntent().getStringExtra("phoneValue"));
		etAddress.setText(getIntent().getStringExtra("addressValue"));

		pd = new ProgressDialog(this);
		pd.setMessage("Loading.....");
		pd.setCancelable(false);

	}

	public void update(View v) {
		updateData();
	}

	private void updateData() {
		pd.show();

		String name = etName.getText().toString();
		String email = etEmail.getText().toString();

		String updateUrl = "http://10.0.2.2/onlineregistration/update_contact.php?name="
				+ name + "&email=" + email;

//		String update_url = "http://api.tutorialsbuzz.com/Orders/update_item.php?id="
//				+ id + "&item=" + item_name;

		JsonObjectRequest update_request = new JsonObjectRequest(updateUrl,
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						try {
							int success = response.getInt("success");

							if (success == 1) {
								pd.dismiss();
								Toast.makeText(getApplicationContext(),
										"Updated Successfully",
										Toast.LENGTH_SHORT).show();
								// redirect to readdata
								MoveToReadData();

							} else {
								pd.dismiss();
								Toast.makeText(getApplicationContext(),
										"failed to update", Toast.LENGTH_SHORT)
										.show();
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});

		// Adding request to request queue
		WebServiceHolder.getInstance().addToReqQueue(update_request);
	}

	private void MoveToReadData() {
		Intent read_intent = new Intent(UpdateActivity.this, MainActivity.class)
				.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(read_intent);

	}
}
