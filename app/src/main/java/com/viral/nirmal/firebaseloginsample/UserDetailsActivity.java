package com.viral.nirmal.firebaseloginsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ((TextView) findViewById(R.id.tv_user_details)).setText(String.valueOf(firebaseUser));
        findViewById(R.id.btn_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
        }
    }
}
