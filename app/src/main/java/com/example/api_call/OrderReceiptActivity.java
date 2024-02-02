package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class OrderReceiptActivity extends AppCompatActivity {
    private TextView TextOID;
    private String amount;
    private Button btn_done_receipt;
    private CheckView check;
    private ImageView image_failed;
    private ImageView image_pending;
    private String number;
    private String operator;
    private Integer status;
    private TextView text_Data;
    private TextView text_OperatorId;
    private TextView text_amount_receipt;
    private TextView text_customer_number;
    private TextView text_date_time;
    private TextView text_message;
    private TextView text_operator;
    private TextView text_remark;
    private String REMARK = "";
    private String STATUS_MESSAGE = "";
    private String DATA = "";
    private String OperatorID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_receipt);
        setTitle("Order Receipt");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initComponents();
        this.operator = getIntent().getExtras().getString("OPERATOR");
        this.number = getIntent().getExtras().getString("MOB_NUMBER");
        this.amount = getIntent().getExtras().getString("AMOUNT");
        this.status = Integer.valueOf(getIntent().getExtras().getInt("STATUS"));
        this.REMARK = getIntent().getExtras().getString("REMARK");
        this.STATUS_MESSAGE = getIntent().getExtras().getString("STATUS_MESSAGE");
        this.DATA = getIntent().getExtras().getString("DATA");
        this.OperatorID = getIntent().getExtras().getString("OperatorID");
        this.text_operator.setText(this.operator);
        this.text_amount_receipt.setText(this.amount);
        this.text_customer_number.setText(this.number);
        this.text_Data.setText(this.DATA);
        this.text_OperatorId.setText(this.OperatorID);
        if (this.status.intValue() == 1) {
            this.text_message.setText(this.STATUS_MESSAGE);
            this.text_Data.setVisibility(View.GONE);
//            this.check.setVisibility(View.VISIBLE);
//            this.check.check();
            this.image_failed.setVisibility(View.GONE);
            this.image_pending.setVisibility(View.GONE);
            this.text_message.setTextColor(getResources().getColor(R.color.dark_green));
            this.text_Data.setTextColor(getResources().getColor(R.color.dark_green));
        } else if (this.status.intValue() == 1) {
            this.text_message.setText(this.STATUS_MESSAGE);
            this.image_failed.setVisibility(View.GONE);  //visible to invisible
            this.image_pending.setVisibility(View.GONE);
//            this.check.setVisibility(8);
            this.text_OperatorId.setVisibility(View.VISIBLE);
            this.TextOID.setVisibility(View.GONE);
            this.text_Data.setTextColor(getResources().getColor(R.color.colorAccent));
            this.text_message.setTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            this.text_message.setText(this.STATUS_MESSAGE);
            this.image_pending.setVisibility(View.GONE);
//            this.check.setVisibility(8);
//            this.image_failed.setVisibility(View.GONE);
            this.text_OperatorId.setVisibility(View.INVISIBLE);
            this.TextOID.setVisibility(View.INVISIBLE);
            this.text_Data.setTextColor(getResources().getColor(R.color.orange));
            this.text_message.setTextColor(getResources().getColor(R.color.orange));
        }

        this.text_remark.setText(this.REMARK);

        if (Build.VERSION.SDK_INT >= 24) {
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            this.text_date_time.setText(currentDateTimeString);
        }
        this.btn_done_receipt.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.OrderReceiptActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                OrderReceiptActivity.this.onBackPressed();
            }
        });


    }

    private void initComponents() {
//        this.check = (CheckView) findViewById(R.id.check);
        this.btn_done_receipt = (Button) findViewById(R.id.btn_done_receipt);
        this.text_amount_receipt = (TextView) findViewById(R.id.text_amount_receipt);
        this.TextOID = (TextView) findViewById(R.id.TextOID);
        this.text_OperatorId = (TextView) findViewById(R.id.text_OperatorId);
        this.text_date_time = (TextView) findViewById(R.id.text_date_time);
        this.text_customer_number = (TextView) findViewById(R.id.text_customer_number);
        this.text_operator = (TextView) findViewById(R.id.text_operator);
        this.text_message = (TextView) findViewById(R.id.text_message);
        this.text_amount_receipt = (TextView) findViewById(R.id.text_amount_receipt);
        this.image_failed = (ImageView) findViewById(R.id.image_failed);
        this.image_pending = (ImageView) findViewById(R.id.image_pending);
        this.text_remark = (TextView) findViewById(R.id.text_remark);
        this.text_Data = (TextView) findViewById(R.id.text_Data);
    }
    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

}