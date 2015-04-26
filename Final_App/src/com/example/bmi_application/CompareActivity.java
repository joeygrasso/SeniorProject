package com.example.bmi_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CompareActivity extends Activity {
    ImageButton btnCalc;
    ImageButton btnTrack;
    ImageButton btnCompare;
    ImageButton btnSettings;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        btnCalc = (ImageButton) findViewById(R.id.btnCalc);
        btnTrack = (ImageButton) findViewById(R.id.btnTrack);
        btnCompare = (ImageButton) findViewById(R.id.btnCompare);
        btnSettings = (ImageButton) findViewById(R.id.btnSettings);

        btnCalc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Launching Calculate Activity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnTrack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Launching Track Changes Activity
                Intent i = new Intent(getApplicationContext(), CalculateActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnCompare.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Launching Compare Activity
                Intent i = new Intent(getApplicationContext(), TrackChangesActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Launching Settings Activity
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
