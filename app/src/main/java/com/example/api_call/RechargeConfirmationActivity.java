package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechargeConfirmationActivity extends AppCompatActivity {
    private String Password;
    private String UserName;
    private Button btn_recharge_topay;
    private ProgressDialog progressDialog;
    private TextView text_amount;
    private TextView text_customer_id;
    private TextView text_datetime;
    private TextView text_operator;
    private TextView text_wallet_balance;
    private String Amount = "";
    private String OPERATOR = "";
    private String MobileNumber = "";
   private String OPTID = "";
    private String Circle = "";
    private String TPIN = "";
    List<Operater> sorted_operatorList = new ArrayList();

    private String Operator = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_confirmation);
        setTitle("Recharge Confirmation");
        initComponents();
        this.Amount = getIntent().getExtras().getString("AMOUNT");
        this.OPERATOR = getIntent().getExtras().getString("OPERATOR");
        this.MobileNumber = getIntent().getExtras().getString("MOBILE_NO");
        this.UserName = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        this.Password = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
        this.TPIN = getIntent().getStringExtra("TPIN");
        this.OPTID = ConstantClass.datum.getId().toString();
//      this.OPTID = ConstantClass.MOBILESERVICEID;
        this.Circle = ConstantClass.MOBILESERVICEID;
        this.text_wallet_balance.setText(PrefUtils.getFromPrefs(this, "Wallet_Main_Balance", ""));
        this.text_operator.setText(this.OPERATOR);
        this.text_amount.setText(this.Amount);
        this.text_customer_id.setText(this.MobileNumber);
        if (Build.VERSION.SDK_INT >= 24) {
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            this.text_datetime.setText(currentDateTimeString);
        }
        this.btn_recharge_topay.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.RechargeConfirmationActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                getRechargeDone(OPTID);

//                if (!ConstantClass.isNetworkAvailable(RechargeConfirmationActivity.this)) {
//                    ConstantClass.displayMessageDialog(RechargeConfirmationActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                } else {
//                    RechargeConfirmationActivity.this.getRechargeDone();
//                }
            }
        });


    }

    private void getRechargeDone(String OPTID) {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, this.UserName);
        body.put("Amount", this.Amount);
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
//        body.put("OPTID",ConstantClass.datum.getId().toString());
        body.put("OPTID", OPTID);
        body.put("Circle",this.Circle);
        body.put("TPIN", this.TPIN);
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("Number", this.MobileNumber);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<RechargeConfirmResponse> call = apiservice.getRechargeResponse(body);
        call.enqueue(new Callback<RechargeConfirmResponse>() { // from class: com.uvapay.activities.RechargeConfirmationActivity.2
            @Override // retrofit2.Callback
            public void onResponse(Call<RechargeConfirmResponse> call2, Response<RechargeConfirmResponse> response) {
                if (RechargeConfirmationActivity.this.progressDialog.isShowing() && RechargeConfirmationActivity.this.progressDialog != null) {
                    RechargeConfirmationActivity.this.progressDialog.dismiss();
                }
                if (response != null) {
                    if (response.body().getResponseStatus().intValue()==2){
                        Intent intent = new Intent(RechargeConfirmationActivity.this, OrderReceiptActivity.class);
                        intent.putExtra("OPERATOR", RechargeConfirmationActivity.this.text_operator.getText().toString());
                        intent.putExtra("MOB_NUMBER", RechargeConfirmationActivity.this.text_customer_id.getText().toString());
                        intent.putExtra("AMOUNT", RechargeConfirmationActivity.this.text_amount.getText().toString());
                        intent.putExtra("REMARK", response.body().getRemarks());
                        intent.putExtra("DATA", response.body().getData());
                        intent.putExtra("OperatorID", response.body().getOperatorTxnNumber());
                        intent.putExtra("STATUS_MESSAGE", response.body().getStatus());
                        intent.putExtra("STATUS", 2);
                        RechargeConfirmationActivity.this.startActivity(intent);
                        RechargeConfirmationActivity.this.finish();
                    }
                    if (response.body().getResponseStatus().intValue()==3) {
                        Intent intent2 = new Intent(RechargeConfirmationActivity.this, OrderReceiptActivity.class);
                        intent2.putExtra("OPERATOR", RechargeConfirmationActivity.this.text_operator.getText().toString());
                        intent2.putExtra("MOB_NUMBER", RechargeConfirmationActivity.this.text_customer_id.getText().toString());
                        intent2.putExtra("AMOUNT", RechargeConfirmationActivity.this.text_amount.getText().toString());
                        intent2.putExtra("REMARK", response.body().getRemarks());
                        intent2.putExtra("DATA", response.body().getData());
                        intent2.putExtra("OperatorID", response.body().getOperatorTxnNumber());
                        intent2.putExtra("STATUS_MESSAGE", response.body().getStatus());
                        intent2.putExtra("STATUS", "3");
                        RechargeConfirmationActivity.this.startActivity(intent2);
                        RechargeConfirmationActivity.this.finish();
                        return;
                    }
                    if (RechargeConfirmationActivity.this.progressDialog.isShowing() && RechargeConfirmationActivity.this.progressDialog != null) {
                        RechargeConfirmationActivity.this.progressDialog.dismiss();
                    }
                    Intent intent3 = new Intent(RechargeConfirmationActivity.this, OrderReceiptActivity.class);
                    intent3.putExtra("OPERATOR", RechargeConfirmationActivity.this.text_operator.getText().toString());
                    intent3.putExtra("MOB_NUMBER", RechargeConfirmationActivity.this.text_customer_id.getText().toString());
                    intent3.putExtra("AMOUNT", RechargeConfirmationActivity.this.text_amount.getText().toString());
                    intent3.putExtra("REMARK", response.body().getRemarks());
                    intent3.putExtra("STATUS_MESSAGE", response.body().getStatus());
                    intent3.putExtra("DATA", response.body().getData());
                    intent3.putExtra("STATUS", ConstantClass.MOBILESERVICEID);
                    RechargeConfirmationActivity.this.startActivity(intent3);
                    RechargeConfirmationActivity.this.finish();
                }

            }

            @Override // retrofit2.Callback
            public void onFailure(Call<RechargeConfirmResponse> call2, Throwable t) {
                if (RechargeConfirmationActivity.this.progressDialog.isShowing() && RechargeConfirmationActivity.this.progressDialog != null) {
                    RechargeConfirmationActivity.this.progressDialog.dismiss();
                }
                RechargeConfirmationActivity rechargeConfirmationActivity = RechargeConfirmationActivity.this;
                ConstantClass.displayMessageDialog(rechargeConfirmationActivity, rechargeConfirmationActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }

    private void initComponents() {
        this.text_operator = (TextView) findViewById(R.id.text_operator);
        this.text_customer_id = (TextView) findViewById(R.id.text_customer_id);
        this.text_amount = (TextView) findViewById(R.id.text_amount);
        this.text_wallet_balance = (TextView) findViewById(R.id.text_wallet_balance);
        this.btn_recharge_topay = (Button) findViewById(R.id.btn_recharge_topay);
        this.text_datetime = (TextView) findViewById(R.id.text_datetime);
    }




}