package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class raisecompliant extends AppCompatActivity {

    TextView Date;
    TextView Ref_no;
    EditText discription;
    Button summit,cancel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raisecompliant);

        Date = findViewById(R.id.date);
        Ref_no = findViewById(R.id.ref);
        discription = findViewById(R.id.edit_msg);






    }
}