package com.rama.vollaywebservicedemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rama.bean.Contact;

public class MainActivity extends Activity {

	String getUrl = "http://10.0.2.2/onlineregistration/db_getall.php";
	String addUrl = "http://10.0.2.2/onlineregistration/db_add.php";

	ArrayList<HashMap<String, String>> itemList;
	ProgressDialog pd;
	ArrayList<String> nameList;
	ArrayList<String> emailList;
	ArrayList<String> phoneList;
	ArrayList<String> addressList;
	ListView listView;
	JSONArray jsonArray;

	Contact contact;

	// initialize object
	EditText Name, Email, Phone, Address;

	// JSON Node names
	public static final String ITEM_NAME = "NAME";
	public static final String ITEM_EMAIL = "EMAIL";
	public static final String ITEM_PHONE = "PHONE";
	public static final String ITEM_ADDRESS = "ADDRESS";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.lV);
		Name = (EditText) findViewById(R.id.etName);
		Email = (EditText) findViewById(R.id.etEmail);
		Phone = (EditText) findViewById(R.id.etPhone);
		Address = (EditText) findViewById(R.id.etAddress);

		nameList = new ArrayList<String>();
		emailList = new ArrayList<String>();
		phoneList = new ArrayList<String>();
		addressList = new ArrayList<String>();

		pd = new ProgressDialog(this);
		pd.setMessage("Loading.....");
		pd.setCancelable(false);

	}

	public void show(View v) {
		readDataFromDB();
	}

	public void create(View v) {
		insertData();
	}

	private void insertData() {
		pd.show();
		String name = Name.getText().toString();
		String email = Email.getText().toString();
		String phone = Phone.getText().toString();
		String address = Address.getText().toString();

		contact = new Contact(name, email, phone, address);

		StringRequest postRequest = new StringRequest(Method.POST, addUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {

						pd.dismiss();
						Name.setText("");
						Email.setText("");
						Phone.setText("");
						Address.setText("");

						Toast.makeText(getApplicationContext(),
								"Data Inserted Successfully", Toast.LENGTH_LONG)
								.show();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						pd.dismiss();
						Toast.makeText(getApplicationContext(),
								"failed to insert", Toast.LENGTH_LONG).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				Map<String, String> params = new HashMap<String, String>();
				params.put("name", contact.getName());
				params.put("email", contact.getEmail());
				params.put("phone", contact.getPhone());
				params.put("address", contact.getAddress());
				return params;
			}
		};

		// adding request queue

		WebServiceHolder.getInstance().addToReqQueue(postRequest);
	}

	private void readDataFromDB() {
		pd.show();
		StringRequest stringRequest = new StringRequest(Method.GET, getUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						try {
							JSONObject jonj = new JSONObject(response);
							jsonArray = jonj.getJSONArray("student");
							for (int i = 0; i < jsonArray.length(); i++) {
								String names = jsonArray.getJSONObject(i)
										.getString(ITEM_NAME);
								String email = jsonArray.getJSONObject(i)
										.getString(ITEM_EMAIL);
								String ph = jsonArray.getJSONObject(i)
										.getString(ITEM_PHONE);
								String ad = jsonArray.getJSONObject(i)
										.getString(ITEM_ADDRESS);

								nameList.add(names);
								emailList.add(email);
								phoneList.add(ph);
								addressList.add(ad);

								ArrayAdapter adapter = new ArrayAdapter(
										getApplicationContext(),
										R.layout.list_patern, R.id.tv, nameList);
								listView.setAdapter(adapter);
								pd.dismiss();
								listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
									@Override
									public void onItemClick(
											AdapterView<?> parent, View view,
											int position, long id) {
										
										Intent intent = new Intent(
												MainActivity.this,
												UpdateActivity.class);
										intent.putExtra("nameValue",
												nameList.get(position));
										intent.putExtra("emailValue",
												emailList.get(position));
										intent.putExtra("phoneValue",
												phoneList.get(position));
										intent.putExtra("addressValue",
												addressList.get(position));
										startActivity(intent);
									}
								});
							}
						} catch (JSONException e) {

						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {

					}
				});
		WebServiceHolder.getInstance().addToReqQueue(stringRequest);

	}

}
