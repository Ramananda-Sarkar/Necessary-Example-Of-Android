package com.example.gcmdemo;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {
	// give your server registration url here
    static final String SERVER_URL = "http://10.0.2.2/gcm_app_server/register.php"; 

    // Google project id
    static final String SENDER_ID = "402539153771"; 

    /**
     * Tag used on log messages.
     */
    static final String TAG = "Android GCM";

    static final String DISPLAY_MESSAGE_ACTION =
            "com.example.gcmdemo.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";

    
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
