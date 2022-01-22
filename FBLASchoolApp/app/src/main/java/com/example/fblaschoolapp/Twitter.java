package com.example.fblaschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Twitter extends AppCompatActivity {

    private WebView webView;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);


        //setting up the webview client and loading the url
        webView = findViewById(R.id.wbTwitter);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://twitter.com/");
        webView.getSettings().setJavaScriptEnabled(true);

        floatingActionButton = findViewById(R.id.floatingActionBtnTwitter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Twitter.this, Extracurriculars.class));
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();

    }


}