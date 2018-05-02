package com.viral.nirmal.firebaseloginsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etEmail, etPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etEmail = findViewById(R.id.et_email);
        etPwd = findViewById(R.id.et_password);
        findViewById(R.id.btn_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                try {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(String.valueOf(etEmail.getText()), String.valueOf(etPwd.getText()))
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        setResult(RESULT_OK);
                                    } else {
                                        setResult(RESULT_CANCELED);
                                    }
                                    finish();
                                }
                            });
                } catch (Exception e) {
                    Log.e(getClass().getName(), "Error in Sign Up");
                }
                break;
        }
    }
}
