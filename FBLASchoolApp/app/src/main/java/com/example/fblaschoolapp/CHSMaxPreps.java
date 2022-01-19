package com.example.fblaschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CHSMaxPreps extends AppCompatActivity {

    private WebView webView;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chsmax_preps);

        //setting up the webview and loading the URL
        webView = findViewById(R.id.wbOrganizations);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.maxpreps.com/tx/keller/keller-central-chargers/");
        webView.getSettings().setJavaScriptEnabled(true);

        floatingActionButton = findViewById(R.id.floatingActionBtnMaxPreps);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CHSMaxPreps.this,Extracurriculars.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {

        //this is for ease of navigation when inside of the webview and the app
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}