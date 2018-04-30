package com.viral.nirmal.firebaseloginsample;

import android.app.Application;

import com.facebook.FacebookSdk;

public class MyFirebaseSampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
