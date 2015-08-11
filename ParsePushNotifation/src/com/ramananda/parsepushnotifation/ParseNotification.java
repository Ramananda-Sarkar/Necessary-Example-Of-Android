package com.ramananda.parsepushnotifation;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseNotification extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "sKo3cEfdX4bMrSSPpYZxt6KK8pSOX3iMRDpE0G7o",
				"YSIaneYWvW2TyVnqPiC9F0V1JCOYhMGqPHq4eWOP");

		ParseUser.enableAutomaticUser();
		ParseACL defaultAcl = new ParseACL();
		defaultAcl.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultAcl, true);
	}
}
