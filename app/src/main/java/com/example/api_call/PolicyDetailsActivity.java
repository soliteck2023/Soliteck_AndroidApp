package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PolicyDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_details);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String selectedOption = extras.getString("selectedOption");
            String parameter1 = extras.getString("User Agrement");

            // Access other parameters based on your needs
            // String parameter2 = extras.getString("parameter2");
            // String parameter3 = extras.getString("parameter3");
            // String parameter4 = extras.getString("parameter4");

            // Example: Display the selected option and parameter1 in a TextView
            TextView textView = findViewById(R.id.textViewPolicyDetails);
            textView.setText("Selected Option: " + selectedOption + "\nParameter 1: " + parameter1);
        }

    }
}