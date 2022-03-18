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

public class Organizations extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private List<Extracurricular> ecs;
    private ECsAdapter eCsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations);
        ecs = new ArrayList<>();


        floatingActionButton = findViewById(R.id.floatingActionBtnClubs);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Organizations.this, Extracurriculars.class));
            }
        });

        extractData();
        recyclerView = findViewById(R.id.recyclerViewECs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eCsAdapter = new ECsAdapter(this,ecs);
        recyclerView.setAdapter(eCsAdapter);
    }

    private void extractData() {
        String json;

        try{
            InputStream is = getAssets().open("ECs.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Extracurricular ec = new Extracurricular();
                ec.setTitle(object.getString("Name of Organization"));
                ec.setSponsor(object.getString("Sponsor"));
                ec.setEmail(object.getString("Sponsor Email"));
                ec.setImageURL(object.getString("ImageURL"));
                ec.setTwitterURL(object.getString("Twitter"));
                ec.setIgURl(object.getString("Instagram"));
                ecs.add(ec);

            }
        }
        catch(IOException | JSONException e)
        {
            e.printStackTrace();
        }
    }
}