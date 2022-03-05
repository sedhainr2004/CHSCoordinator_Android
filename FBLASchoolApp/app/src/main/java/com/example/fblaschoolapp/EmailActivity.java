package com.example.fblaschoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class EmailActivity extends AppCompatActivity {

    private EditText txtEmail, txtSubject, txtMessage;
    private Button btnSend;
    private FloatingActionButton btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        //initializing the variables
        txtEmail = findViewById(R.id.editTextTextEmailAddress);
        txtSubject = findViewById(R.id.edtTxtSubject);
        txtMessage = findViewById(R.id.edtTxtMessage);
        btnSend = findViewById(R.id.btnSendEmail);
        btnBack = findViewById(R.id.floatingActionBtnHome);

        //TODO: add a warning activity letting the users kn0ow to log into the gmail app before hand


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //making sure that everything is filled out
                if(TextUtils.isEmpty(txtEmail.getText().toString())
                        || TextUtils.isEmpty(txtSubject.getText().toString()) || TextUtils.isEmpty(txtMessage.getText().toString()))
                {
                    Toast.makeText(EmailActivity.this, "Please enter all of the information before sending an email", Toast.LENGTH_SHORT).show();

                }
                //making sure that the user can only send emails to the @kellerisd domain
                else if(!txtEmail.getText().toString().contains("@kellerisd"))
                {
                    Toast.makeText(EmailActivity.this,"Please send the email to people INSIDE of KellerISD :)", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    sendEmail();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmailActivity.this,OptionsPage.class));
            }
        });


    }


    //this method will help send the email
    public void sendEmail() {
        String[] email = txtEmail.getText().toString().split(",");
        String subject = txtSubject.getText().toString();
        String message = txtMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL,email);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");
        try {
            startActivity(Intent.createChooser(intent, "Choose an Email Service"));

        }
        catch (Exception e)
        {
            Toast.makeText(EmailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }
}