package com.viral.nirmal.firebaseloginsample;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(getClass().getName(), "Refreshed firebase token: " + token);

        sendTokenToServer(token);
    }

    private void sendTokenToServer(String token) {
        //send token to server, so that server can send notification to individual device too.
    }
}
