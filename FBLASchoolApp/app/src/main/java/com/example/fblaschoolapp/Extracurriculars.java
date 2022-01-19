package com.example.fblaschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.Inet4Address;

public class Extracurriculars extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private Button btnClubs, btnSports;
    private ImageView btnImgTwitter, btnImgIG, btnImgMaxPreps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extracurriculars);

        floatingActionButton = findViewById(R.id.floatingActionBtnOptionsPage);
        btnClubs = findViewById(R.id.btnClubs);
        btnSports = findViewById(R.id.btnSports);


        //setting up the onclick methods for all of the buttons and images...
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Extracurriculars.this,OptionsPage.class));
            }
        });

        btnImgTwitter = findViewById(R.id.imgViewTwitter);
        btnImgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Extracurriculars.this, Twitter.class));
            }
        });


        btnImgIG = findViewById(R.id.imgViewIG);
        btnImgIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Extracurriculars.this,Instagram.class));
            }
        });

        btnImgMaxPreps = findViewById(R.id.imgViewMaxPreps);
        btnImgMaxPreps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Extracurriculars.this, CHSMaxPreps.class));
            }
        });

        btnClubs = findViewById(R.id.btnClubs);
        btnClubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Extracurriculars.this, Organizations.class));
            }
        });

        btnSports = findViewById(R.id.btnSports);
        btnSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Extracurriculars.this, Sports.class));
            }
        });
    }
}