package com.example.fblaschoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.Inet4Address;
import java.security.cert.PKIXRevocationChecker;

public class OptionsPage extends AppCompatActivity {
    private Button btnCalendar, btnLunch, btnSchedule, btnExtracurriculars, btnEmail;
    private FloatingActionButton btnLogout;
    private TextView title;

    private FirebaseUser user;
    private String uid;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_page);
        System.out.println("Here is the user email and password: " + StartActivity.userEmail + StartActivity.userPassword);


        //getting the current logged on user
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        System.out.println("Hello" + uid);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        System.out.println("Data" + databaseReference);
        if(user != null)
        {
            databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    title.setText("Welcome " + userProfile.getFirstName() + ", please select one of the options to continue");
                    //adding personalization to the profiles


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        title = findViewById(R.id.txtViewTitle);

        //setting up the buttons and the onclick methods
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionsPage.this, SchoolCalendar.class );
                startActivity(intent);
            }
        });

        btnLunch = (Button) findViewById(R.id.btnLunchMenu);
        btnLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionsPage.this, LunchMenu.class );
                startActivity(intent);
            }
        });

        btnSchedule = findViewById(R.id.btnSchedule);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionsPage.this,Schedule.class));
            }
        });

        btnLogout = findViewById(R.id.floatingActionBtnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(OptionsPage.this,"User is logged out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OptionsPage.this,StartActivity.class));
            }
        });

        btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionsPage.this,EmailActivity.class));
            }
        });

        btnExtracurriculars = findViewById(R.id.btnExtracurriculars);
        btnExtracurriculars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionsPage.this, Extracurriculars.class));
            }
        });




    }


}