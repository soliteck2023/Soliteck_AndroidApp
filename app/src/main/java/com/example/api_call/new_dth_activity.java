package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class new_dth_activity extends AppCompatActivity {
    private static final int DTHPLANREQUESTCODE = 102;
    private TextView TxtDetails;
    private TextView TxtROffer;
    private TextView TxtSpecialPlan;
    private Button btn_mobile_Recharge;
    private EditText edit_consumer_id;
    private EditText edit_dth_amount;
    private EditText edit_dth_consumerid;
    private EditText edit_dth_operator;
    private RelativeLayout layout_dth_plans;
    private EditText mEditTPin;
    PrefUtils prefs;
    List<Datum> DTHoperatorList = new ArrayList();
    List<Datum> operatorDataArrayList = new ArrayList();
    private String NUMBER = "";
    private String AMT = "";
    private String CALL = "";
    private String opCode = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dth);

        initComponents();
        setTitle("DTH Recharge");

        this.layout_dth_plans.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DTHRechargeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                new_dth_activity.this.startActivity(new Intent(new_dth_activity.this, DthechargeInfoActivity.class));
            }
        });

        this.btn_mobile_Recharge.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DTHRechargeActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (new_dth_activity.this.edit_dth_operator.getText().toString().trim().isEmpty()) {
                    new_dth_activity.this.edit_dth_operator.setError("Select operator");
                    new_dth_activity.this.edit_dth_operator.requestFocus();
                } else if (new_dth_activity.this.edit_dth_consumerid.getText().toString().trim().isEmpty()) {
                    new_dth_activity.this.edit_dth_operator.setError(null);
                    new_dth_activity.this.edit_dth_consumerid.setError("Enter Consumer Id");
                    new_dth_activity.this.edit_dth_consumerid.requestFocus();
                } else {
                    new_dth_activity.this.edit_dth_consumerid.setError(null);
                    if (new_dth_activity.this.edit_dth_amount.getText().toString().trim().isEmpty()) {
                        new_dth_activity.this.edit_dth_amount.setError("Enter amount");
                        new_dth_activity.this.edit_dth_amount.requestFocus();
                        return;
                    }
                    new_dth_activity.this.edit_dth_amount.setError(null);
                    if (TextUtils.isEmpty(new_dth_activity.this.mEditTPin.getText().toString().trim())) {
                        new_dth_activity.this.mEditTPin.setError("Enter TPIN");
                        return;
                    }
                    new_dth_activity.this.mEditTPin.setError(null);
                    new_dth_activity dTHRechargeActivity = new_dth_activity.this;
                    dTHRechargeActivity.getDTHConformation(dTHRechargeActivity.edit_dth_amount.getText().toString(), new_dth_activity.this.edit_dth_consumerid.getText().toString(), new_dth_activity.this.edit_dth_operator.getText().toString(), new_dth_activity.this.mEditTPin.getText().toString().trim());
                }
            }
        });

        this.edit_dth_operator.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DTHRechargeActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(new_dth_activity.this, OperatorsActivity.class);
                intent.putExtra("NUMBER", new_dth_activity.this.edit_consumer_id.getText().toString());
                intent.putExtra("AMT", new_dth_activity.this.edit_dth_amount.getText().toString());
                intent.putExtra("CALL", "DTH");
                new_dth_activity.this.startActivityForResult(intent, 1);
            }
        });





    }

    private void getDTHConformation(String edit_amount, String edit_mobile, String edit_operator, String trim) {
        Intent intent = new Intent(this, RechargeConfirmationActivity.class);
        intent.putExtra("MOBILE_NO", edit_mobile);
        intent.putExtra("AMOUNT", edit_amount);
        intent.putExtra("CALL", "MOBILE");
        intent.putExtra("OPERATOR", edit_operator);
        String EncodedTPIN = ApplicationConstant.EncodeStringToHMACSHA256(trim);
        intent.putExtra("TPIN", EncodedTPIN);
        startActivity(intent);

    }

    private void initComponents() {
        this.mEditTPin = (EditText) findViewById(R.id.EditTPin);
        this.edit_consumer_id = (EditText) findViewById(R.id.edit_consumer_id);
        this.edit_dth_amount = (EditText) findViewById(R.id.edit_dth_amount);
        this.edit_dth_operator = (EditText) findViewById(R.id.edit_dth_operator);
        this.btn_mobile_Recharge = (Button) findViewById(R.id.btn_mobile_Recharge);
        this.layout_dth_plans = (RelativeLayout) findViewById(R.id.layout_dth_plans);
        TextView textView = (TextView) findViewById(R.id.TxtSpecialPlan);
        this.TxtSpecialPlan = textView;
//        textView.setOnClickListener((View.OnClickListener) this);
        this.TxtROffer = (TextView) findViewById(R.id.TxtROffer);
        this.TxtDetails = (TextView) findViewById(R.id.TxtDetails);
//        this.TxtROffer.setOnClickListener((View.OnClickListener) this);
//        this.TxtDetails.setOnClickListener((View.OnClickListener) this);
        this.edit_dth_consumerid = (EditText) findViewById(R.id.edit_consumer_id);
        this.edit_dth_operator = (EditText) findViewById(R.id.edit_dth_operator);
        this.edit_dth_amount = (EditText) findViewById(R.id.edit_dth_amount);
        this.edit_consumer_id.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.DTHRechargeActivity.7
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (new_dth_activity.this.edit_consumer_id.getText().toString().trim().length() >= 9) {
                    new_dth_activity dTHRechargeActivity = new_dth_activity.this;
                    dTHRechargeActivity.callOperator(dTHRechargeActivity.edit_consumer_id.getText().toString().trim());
                }
            }
        });
    }

    private void callOperator(String number) {
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<OperatorWithCircleResponse> rechargeress = apiservice.getDTHOPT(PrefUtils.getFromPrefs(this, "userid", ""), PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, ""), number);
        rechargeress.enqueue(new Callback<OperatorWithCircleResponse>() { // from class: com.uvapay.activities.DTHRechargeActivity.8
            @Override // retrofit2.Callback
            public void onResponse(Call<OperatorWithCircleResponse> call, Response<OperatorWithCircleResponse> response) {
                OperatorWithCircleResponse dthCustInfo = response.body();
                if (dthCustInfo.getStatus().equalsIgnoreCase("Success")) {
                    try {
                        for (Datum data : new_dth_activity.this.DTHoperatorList) {
                            if (data.equals(dthCustInfo.getData().getOperator())) {
                                new_dth_activity.this.edit_dth_operator.setText(dthCustInfo.getData().getOperator());
                                new_dth_activity.this.opCode = data.getOurCode();
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<OperatorWithCircleResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(new_dth_activity.this, "Please turn on your internet connection ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}