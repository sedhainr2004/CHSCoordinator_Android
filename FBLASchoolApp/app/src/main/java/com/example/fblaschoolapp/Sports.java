package com.example.fblaschoolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Sports extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private SportsAdapter sportsAdapter;
    private List<Sport> sports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        sports = new ArrayList<>();



        floatingActionButton = findViewById(R.id.floatingActionBtnSports);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sports.this, Extracurriculars.class));
            }
        });

        extractData();
        recyclerView = findViewById(R.id.recyclerViewSports);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sportsAdapter = new SportsAdapter(this,sports);
        recyclerView.setAdapter(sportsAdapter);
        
    }

    private void extractData(){
        String json;

        try{
            InputStream inputStream = getAssets().open("sports.json");
            int size = inputStream.available();
            byte [] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Sport sport = new Sport();
                sport.setTitle(object.getString("Title"));
                sport.setCoach(object.getString("Coach"));
                sport.setEmail(object.getString("Email"));
                sport.setImageURL(object.getString("ImageURL"));
                sport.setTwitterURL(object.getString("Twitter"));
                sport.setMaxPrepsURL(object.getString("MaxPreps"));
                sports.add(sport);

            }
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


}