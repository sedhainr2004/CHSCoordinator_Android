package com.example.fblaschoolapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LunchMenu extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    public static final String PREFS_NAME = "MyPrefsFile1";
    private CheckBox dontShowAgain;
    private Button btnBfast, btnLunch;



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_menu);

        btnBfast = findViewById(R.id.btnBfastItems);
        btnLunch = findViewById(R.id.btnLunchItems);
        btnBfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LunchMenu.this,BreakfastItems.class));
            }
        });

        btnLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LunchMenu.this, LunchItems.class));
            }
        });

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
    protected void onResume() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        LayoutInflater adbInflator = LayoutInflater.from(this);
        View eulaLayout = adbInflator.inflate(R.layout.checkbox, null);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String skipMessage  = settings.getString("skipMessage", "NOT checked");

        dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
        adb.setView(eulaLayout);
        adb.setTitle("Disclaimer");
        adb.setMessage("Please note that this simply an overview of the lunch menu at CHS, if you want more information about the macros and allergy information for each item " +
                "please download the SoHappy App from the link below.");
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT Checked";
                if (dontShowAgain.isChecked())
                    checkBoxResult = "checked";

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("skipMessage", checkBoxResult);
                editor.commit();

                return;
            }
        });

        adb.setNeutralButton("Download SoHappy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT checked";

                if (dontShowAgain.isChecked()) {
                    checkBoxResult = "checked";
                }

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("skipMessage", checkBoxResult);
                editor.commit();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.sodexo.sohappy.us&hl=en_US&gl=US"));
                startActivity(i);
            }
        });

        if (!skipMessage.equals("checked")) {
            adb.show();
        }

        super.onResume();

    }
}