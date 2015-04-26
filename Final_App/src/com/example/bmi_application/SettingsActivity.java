package com.example.bmi_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingsActivity extends Activity {
    Button btnLogoff;
    ImageButton btnCalc;
    ImageButton btnTrack;
    ImageButton btnCompare;
    ImageButton btnSettings;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnCalc = (ImageButton) findViewById(R.id.btnCalc);
        btnTrack = (ImageButton) findViewById(R.id.btnTrack);
        btnCompare = (ImageButton) findViewById(R.id.btnCompare);
        btnSettings = (ImageButton) findViewById(R.id.btnSettings);


        btnCalc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Launching All products Activity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnTrack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Launching All products Activity
                Intent i = new Intent(getApplicationContext(), CalculateActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnCompare.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Launching All products Activity
                Intent i = new Intent(getApplicationContext(), CompareActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Launching All products Activity
                Intent i = new Intent(getApplicationContext(), TrackChangesActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}