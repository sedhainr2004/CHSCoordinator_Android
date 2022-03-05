package com.example.fblaschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Instagram extends AppCompatActivity {

    private WebView webView;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);


        //setting up the webview
        webView = findViewById(R.id.wbIG);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.instagram.com/");
        webView.getSettings().setJavaScriptEnabled(true);

        floatingActionButton = findViewById(R.id.floatingActionBtnIG);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Instagram.this, Extracurriculars.class));
            }
        });
    }


    @Override
    public void onBackPressed()
    {
        //this method will help with ease of navigation in the webview and the application
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }

}