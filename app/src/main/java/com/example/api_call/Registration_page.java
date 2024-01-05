package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Registration_page extends AppCompatActivity {

    private TextView mobile_no;
    private TextView User_name;
    private TextView Shop_name;
    private TextView Pincode;
    private TextView Parent_no;
    private TextView Employe_no;
    private Spinner Role;
    private Button register_button;

    private TextView Exit;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
//        mobile_no = findViewById(R.id.mobile_reg);
//        User_name = findViewById(R.id.userName_reg);
//        Shop_name = findViewById(R.id.shopname_reg);
//        Pincode = findViewById(R.id.pin_code);
//        Parent_no = findViewById(R.id.parent_reg);
//        Role = findViewById(R.id.spineer);
//        Employe_no = findViewById(R.id.Employ_no);
//        register_button = findViewById(R.id.button_registerin);
//        Exit = findViewById(R.id.exit);
        initComponent();


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(Registration_page.this, MainActivity.class);
                Registration_page.this.startActivity(intent);
            }
        });

    }

    private void initComponent() {

        this.mobile_no = (TextView) findViewById(R.id.mobile_reg);
        this.User_name = (TextView) findViewById(R.id.userName_reg);
        this.Shop_name = (TextView) findViewById(R.id.shopname_reg);
        this.Pincode = (TextView) findViewById(R.id.pin_code);
        this.Parent_no = (TextView) findViewById(R.id.parent_reg);
        this.Employe_no = (TextView) findViewById(R.id.Employ_no);
        this.Role = (Spinner) findViewById(R.id.spineer);
        this.register_button = (Button) findViewById(R.id.button_registerin);
        this.Exit = (TextView) findViewById(R.id.exit);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}