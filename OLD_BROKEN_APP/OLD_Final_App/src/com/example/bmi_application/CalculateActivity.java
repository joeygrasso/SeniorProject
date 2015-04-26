package com.example.bmi_application;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

import android.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bmi_application.app.AppConfig;
import com.example.bmi_application.app.AppController;
import com.example.bmi_application.helper.SQLiteHandler;
import com.example.bmi_application.helper.SessionManager;
import android.graphics.Color;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class CalculateActivity extends Activity{
	
    private Button btnStore;
    Button btnCalculate;
    private ImageButton btnCalc;
    private ImageButton btnTrack;
    private ImageButton btnCompare;
    private ImageButton btnSettings;
    private EditText weightEditText;
    private EditText heightEditText;
    private float bmiValue;
    private TextView resultTextView, rangeTextView;
    
    private SQLiteHandler db;
	private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        btnStore = (Button) findViewById(R.id.btnStore);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalc = (ImageButton) findViewById(R.id.btnCalc);
        btnTrack = (ImageButton) findViewById(R.id.btnTrack);
        btnCompare = (ImageButton) findViewById(R.id.btnCompare);
        btnSettings = (ImageButton) findViewById(R.id.btnSettings);
        weightEditText = (EditText)findViewById(R.id.weightEditText);
        heightEditText = (EditText)findViewById(R.id.heightEditText);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        rangeTextView = (TextView) findViewById(R.id.rangeTextView);
        
        
        btnStore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            	session = new SessionManager(getApplicationContext());
            	// Fetching user details from sqlite
        		HashMap<String, String> user = db.getUserDetails();
        		String uid = user.get("uid");
            	
            	
            	// Get Current BMI Value
            	String bmi = weightEditText.getText().toString();
            	
            	/*/ Store BMI Value With UserID
            	if (!bmi.isEmpty()) {
					storeBMI(uid, bmi);
				} else {
					Toast.makeText(getApplicationContext(),"Please calculate a BMI first!", Toast.LENGTH_LONG).show();
				}*/
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Get User Input Height & Weight
                float weight = Float.parseFloat(weightEditText.getText().toString());
                float height = Float.parseFloat(heightEditText.getText().toString());


                //Need to get Pound/Kilogram setting from Settings page


                //Calculate
                bmiValue = calculateBMIPounds(weight, height);
                //bmiValue = calculateBMIKilograms(weight, height);
                
                // Find Analysis of BMI
                // Show weight range that is healthy for the height entered
                float minWeight = 500;
                float maxWeight = 0;
                float tmp1 = 20;
                float tmp2 = 0;

                while (tmp1 > 18.5){
                	tmp1 = calculateBMIPounds(minWeight, height);
                	minWeight-= .01;
                }
                
                while (tmp2 < 24.99){
                	tmp2 = calculateBMIPounds(maxWeight, height);
                	maxWeight += .01;
                }
                
                // Display BMI result
                bmiValue = roundBMI(bmiValue);
        		resultTextView.setText(bmiValue + " - " + interpretBMI(bmiValue));
        		
        		// Display BMI analysis
        		minWeight = roundBMI(minWeight);
        		maxWeight = roundBMI(maxWeight);
        		rangeTextView.setText ("An individual with a height of " + height + " should weigh between " 
        				+ minWeight + " and " + maxWeight);
            }
        });



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
                //Intent i = new Intent(getApplicationContext(), TrackChangesActivity.class);
                //startActivity(i);
            }
        });

        btnCompare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Launching Compare Activity
               // Intent i = new Intent(getApplicationContext(), CompareActivity.class);
               // startActivity(i);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Launching Settings Activity
                //Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                //startActivity(i);
            }
        });

    }//End OnCreate method

    private float calculateBMIPounds (float weight, float height){
        return (float) (weight * 703) / (height * height);
    }// end calculateBMIPounds

    private float calculateBMIKilograms (float weight, float height){
        return (float) (weight / (height*height));
    }// end calculateBMIKilograms
    
    private float roundBMI(float bmi) {
    	// Round float to two decimal places
        BigDecimal bd = new BigDecimal(bmi);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        return bd.floatValue();
    }
    
    private String interpretBMI(float bmiValue){
    	if (bmiValue < 16){
    		 resultTextView.setTextColor(Color.parseColor("#F9BF3B"));
    		return "Severely Underweight";
    	}else if(bmiValue < 18.5){
    		resultTextView.setTextColor(Color.parseColor("#F9BF3B"));
    		return "Underweight";
    	}else if(bmiValue < 25){
    		resultTextView.setTextColor(Color.parseColor("#26ae90"));
    		return "Normal";
    	}else if(bmiValue < 30){
    		resultTextView.setTextColor(Color.parseColor("#D24D57"));
    		return "Overweight";
    	}else{
    		resultTextView.setTextColor(Color.parseColor("#96281B"));
    		return "Obese";
    	}
    }
    
    private void storeBMI(String uid, String bmi){
    	//
    }
}