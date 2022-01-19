package com.example.fblaschoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class StartActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnRegister;
    private EditText email;
    private EditText password;
    private FirebaseAuth auth;
    private FirebaseUser user;

    public static String userEmail,userPassword; //this is for future use throughout the app in case we need the emails and passwords


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        email = findViewById(R.id.editTxtEmail);
        password = findViewById(R.id.editTxtPassword);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser(); //getting the current user
        userEmail = "";
        userPassword = "";

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();
                if(TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) //making sure that the user has entered all of the correct information
                    Toast.makeText(StartActivity.this,"Please enter all of the information",Toast.LENGTH_SHORT).show();
                else{

                    loginUser(txtEmail,txtPassword);

                }

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,RegisterActivity.class));
            }
        });


    }

    //using firebase auth to login the user
    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                userEmail = email;
                userPassword = password;
                Toast.makeText(StartActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StartActivity.this,OptionsPage.class));
                Intent i = new Intent(StartActivity.this, OptionsPage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StartActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
