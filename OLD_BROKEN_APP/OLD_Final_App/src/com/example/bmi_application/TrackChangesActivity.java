package com.example.bmi_application;

import android.app.Activity;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by marse_000 on 3/26/2015.
 */
public class TrackChangesActivity extends Activity {
    ImageButton btnCalc;
    ImageButton btnTrack;
    ImageButton btnCompare;
    ImageButton btnSettings;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_changes);

        btnCalc = (ImageButton) findViewById(R.id.btnCalc);
        btnTrack = (ImageButton) findViewById(R.id.btnTrack);
        btnCompare = (ImageButton) findViewById(R.id.btnCompare);
        btnSettings = (ImageButton) findViewById(R.id.btnSettings);

        btnCalc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Launching Calculate Activity
                Intent i = new Intent(getApplicationContext(), CalculateActivity.class);
                startActivity(i);
            }
        });

        btnTrack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Launching Track Changes Activity
                Intent i = new Intent(getApplicationContext(), TrackChangesActivity.class);
                startActivity(i);
            }
        });

        btnCompare.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Launching Compare Activity
                Intent i = new Intent(getApplicationContext(), CompareActivity.class);
                startActivity(i);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Launching Settings Activity
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
            }
        });

    }
}