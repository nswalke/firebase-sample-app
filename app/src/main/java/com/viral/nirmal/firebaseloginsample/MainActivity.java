package com.viral.nirmal.firebaseloginsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int RC_FIREBASE_SIGN_IN = 1001;

    private TextView tvMessage;

    private int mRequestCode = 0, mResultCode = 0;
    private Intent mIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        tvMessage = findViewById(R.id.tv_message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                try {
                    //Choose authentication providers
                    List<AuthUI.IdpConfig> authProviders = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build(),
                            new AuthUI.IdpConfig.FacebookBuilder().build());

                    //Create and launch sign in Intent
                    startActivityForResult(AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(authProviders)
                                    .setTheme(R.style.GreenTheme)
                                    .build(),
                            RC_FIREBASE_SIGN_IN);
                } catch (Exception e) {
                    Log.e(getClass().getName(), "Error occurred trying to create firebase sign in Intent", e);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //store activity result data in member variables
        //it will be handled in onResumeFragments
        //so that all fragments will have resumed before we make any fragment commits
        mRequestCode = requestCode;
        mResultCode = resultCode;
        mIntent = data;
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mRequestCode != 0) {
            switch (mRequestCode) {
                case RC_FIREBASE_SIGN_IN:
                    try {
                        IdpResponse response = IdpResponse.fromResultIntent(mIntent);

                        switch (mResultCode) {
                            case RESULT_OK:
                                //successfully signed in
                                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                tvMessage.setText(String.valueOf(firebaseUser));
                                break;
                            default:
                                //some error occurred
                                if (response != null) {
                                    tvMessage.setText(response.getError() != null ? response.getError().getMessage() : "");
                                }
                                break;
                        }
                    } catch (Exception e) {
                        Log.e(getClass().getName(), "error in processing activity result: ", e);
                    }
                    break;
            }
            mIntent = null;
            mRequestCode = 0;
            mResultCode = 0;
        }
    }
}
