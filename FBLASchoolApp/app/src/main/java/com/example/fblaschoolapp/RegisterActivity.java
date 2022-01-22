package com.example.fblaschoolapp;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private FloatingActionButton btnBack;



    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private EditText studentID;
    private EditText email;

    ProgressDialog pd;

    private DatabaseReference mRootRef;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pd = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        //initializing the variables
        firstName = findViewById(R.id.edTxtFirstName);
        lastName = findViewById(R.id.edTxtLastName);
        password = findViewById(R.id.edTxtPassword);
        studentID = findViewById(R.id.edTxtStudentID);
        email = findViewById(R.id.edtTextEmail);
        btnBack = findViewById(R.id.floatingActionBtnBack);



        btnRegister = findViewById(R.id.registrationButton);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_firstName = firstName.getText().toString();
                String txt_lastName = lastName.getText().toString();
                String txt_studentId = studentID.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                //making sure that all the credentials are filled out and the email is in the @kellerisd domain and making sure the password is strong
                if (TextUtils.isEmpty(txt_firstName) || TextUtils.isEmpty(txt_lastName) ||
                        TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) ||
                        TextUtils.isEmpty(txt_studentId)
                ) {
                    Toast.makeText(RegisterActivity.this, "Please fill out ALL of the information", Toast.LENGTH_SHORT).show();
                } else if (!txt_email.contains("@kellerisd.com"))
                    Toast.makeText(RegisterActivity.this, "Please enter your SCHOOL email.", Toast.LENGTH_SHORT).show();
                else if (txt_password.length() < 6)
                    Toast.makeText(RegisterActivity.this, "Weak Password, please make it stronger!", Toast.LENGTH_SHORT).show();
                else {
                    registerUser(txt_firstName, txt_lastName, txt_email, txt_studentId, txt_password);
                }


            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,StartActivity.class));
            }
        });

    }

    //registering the user using firebase auth
    private void registerUser(String firstName, String lastName, String email, String studentId, String password) {
        pd.setMessage("Please wait!");
        pd.show();


        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
               User user = new User(firstName,lastName,email,password,studentId,"default", auth.getCurrentUser().getUid());

                mRootRef.child("Users").child(auth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("user is registered");
                            pd.dismiss();
                            Toast.makeText(RegisterActivity.this, "User is registered!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this , StartActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            //adding a onfailure listener to help users know why they can't log in also acts as a bug reporting system
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //TODO: add instabug reporting system
    //TODO: forgot password



}