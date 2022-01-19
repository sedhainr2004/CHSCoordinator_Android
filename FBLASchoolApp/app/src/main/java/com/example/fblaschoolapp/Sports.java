package com.example.fblaschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Sports extends AppCompatActivity {

    private WebView webView;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        webView = findViewById(R.id.wbSports);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.kellerisd.net/domain/3495");
        webView.getSettings().setJavaScriptEnabled(true);

        floatingActionButton = findViewById(R.id.floatingActionBtnSports);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sports.this, Extracurriculars.class));
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