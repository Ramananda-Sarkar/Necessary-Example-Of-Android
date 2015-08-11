package com.rama.vollaywebservicedemo;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WebServiceHolder extends Application {
	private RequestQueue mRequestQueue;
	private static WebServiceHolder mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static synchronized WebServiceHolder getInstance() {
		return mInstance;
	}

	public RequestQueue getReqQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	public <T> void addToReqQueue(Request<T> req, String name) {
		getReqQueue().add(req);
	}

	public  <T> void addToReqQueue(Request<T> req) {
		getReqQueue().add(req);
	}

	public void cancelPendingReq(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}
