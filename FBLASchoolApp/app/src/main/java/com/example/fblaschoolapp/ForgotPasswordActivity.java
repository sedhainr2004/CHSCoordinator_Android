package com.example.fblaschoolapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edtTxtemail;
    private Button btnResetPassword;
    private FirebaseAuth firebaseAuth;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtTxtemail = findViewById(R.id.edtTxtEmaillAddress);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        floatingActionButton = findViewById(R.id.floatingActionBtnResetBack);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, StartActivity.class));
            }
        });
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtTxtemail.getText().toString();

                if (TextUtils.isEmpty(email))
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter a email", Toast.LENGTH_SHORT).show();
                else
                    resetPassword(email);
            }

            private void resetPassword(String email) {
                ProgressDialog pd = new ProgressDialog(ForgotPasswordActivity.this);
                pd.setMessage("Sending Email");
                pd.show();
                firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ForgotPasswordActivity.this, "Please check your email shortly for a reset link :)", Toast.LENGTH_SHORT).show();
                        pd.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });


            }
        });

    }
}