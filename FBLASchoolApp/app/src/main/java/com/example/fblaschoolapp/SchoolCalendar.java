package com.example.fblaschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SchoolCalendar extends AppCompatActivity {
    private WebView webView;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_calendar);


        webView = (WebView) findViewById(R.id.wbCalendar);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.kellerisd.net/Page/2#calendar180/20211211/month");
        webView.getSettings().setJavaScriptEnabled(true);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionBtnCal);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SchoolCalendar.this, OptionsPage.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }


}