package com.example.fblaschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LunchMenu extends AppCompatActivity {

    private WebView webView;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_menu);

        //setting up the webview
        webView = (WebView) findViewById(R.id.wbLunch);
        webView.setWebViewClient(new WebViewClient());
        //loading the URL
        webView.loadUrl("http://menus.sodexomyway.com/BiteMenu/Menu?menuId=14035&locationId=20491001&whereami=https://kellerisd.sodexomyway.com/landing/high.html");
        webView.getSettings().setJavaScriptEnabled(true);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionBtnSchedule);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LunchMenu.this,OptionsPage.class);
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