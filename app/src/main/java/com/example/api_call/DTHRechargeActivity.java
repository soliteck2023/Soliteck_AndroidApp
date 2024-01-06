package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DTHRechargeActivity extends AppCompatActivity implements View.OnClickListener{
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
        setContentView(R.layout.activity_dth_dashboard);
        initComponents();
        setTitle("DTH Recharge");

        this.layout_dth_plans.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DTHRechargeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DTHRechargeActivity.this.startActivity(new Intent(DTHRechargeActivity.this, DthechargeInfoActivity.class));
            }
        });

//        this.layout_dth_plans.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DTHRechargeActivity.1
//            @Override // android.view.View.OnClickListener
//            public void onClick(View v) {
////                DTHRechargeActivity.this.startActivity(new Intent(DTHRechargeActivity.this, DthechargeInfoActivity.class));
//                if (DTHRechargeActivity.this.edit_dth_operator.getText().toString().trim().isEmpty()) {
//                    DTHRechargeActivity.this.edit_dth_operator.setError("Select operator");
//                    DTHRechargeActivity.this.edit_dth_operator.requestFocus();
//                }else {
//                    {
//                        DTHRechargeActivity.this.edit_dth_operator.setError(null);
//                        Intent intent = new Intent(DTHRechargeActivity.this, DthechargeInfoActivity.class);
//                        intent.putExtra("MOBILE", DTHRechargeActivity.this.edit_consumer_id.getText().toString());
//                        intent.putExtra("OPERATOR", DTHRechargeActivity.this.edit_dth_operator.getText().toString());
//                        intent.putExtra("AMT", DTHRechargeActivity.this.edit_dth_amount.getText().toString());
//                        intent.putExtra("CALL", "DTH");
//                        DTHRechargeActivity.this.startActivity(intent);
//                    }
//                }
//
//
//            }
//        });
        this.btn_mobile_Recharge.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DTHRechargeActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (DTHRechargeActivity.this.edit_dth_operator.getText().toString().trim().isEmpty()) {
                    DTHRechargeActivity.this.edit_dth_operator.setError("Select operator");
                    DTHRechargeActivity.this.edit_dth_operator.requestFocus();
                } else if (DTHRechargeActivity.this.edit_dth_consumerid.getText().toString().trim().isEmpty()) {
                    DTHRechargeActivity.this.edit_dth_operator.setError(null);
                    DTHRechargeActivity.this.edit_dth_consumerid.setError("Enter Consumer Id");
                    DTHRechargeActivity.this.edit_dth_consumerid.requestFocus();
                } else {
                    DTHRechargeActivity.this.edit_dth_consumerid.setError(null);
                    if (DTHRechargeActivity.this.edit_dth_amount.getText().toString().trim().isEmpty()) {
                        DTHRechargeActivity.this.edit_dth_amount.setError("Enter amount");
                        DTHRechargeActivity.this.edit_dth_amount.requestFocus();
                        return;
                    }
                    DTHRechargeActivity.this.edit_dth_amount.setError(null);
                    if (TextUtils.isEmpty(DTHRechargeActivity.this.mEditTPin.getText().toString().trim())) {
                        DTHRechargeActivity.this.mEditTPin.setError("Enter TPIN");
                        return;
                    }
                    DTHRechargeActivity.this.mEditTPin.setError(null);
                    DTHRechargeActivity dTHRechargeActivity = DTHRechargeActivity.this;
                    dTHRechargeActivity.getDTHConformation(dTHRechargeActivity.edit_dth_amount.getText().toString(), DTHRechargeActivity.this.edit_dth_consumerid.getText().toString(), DTHRechargeActivity.this.edit_dth_operator.getText().toString(), DTHRechargeActivity.this.mEditTPin.getText().toString().trim());
                }
            }
        });

        this.layout_dth_plans.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DTHRechargeActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (DTHRechargeActivity.this.edit_dth_operator.getText().toString().trim().isEmpty()) {
                    DTHRechargeActivity.this.edit_dth_operator.setError("Select operator");
                    DTHRechargeActivity.this.edit_dth_operator.requestFocus();
                } else if (DTHRechargeActivity.this.edit_dth_consumerid.getText().toString().trim().isEmpty()) {
                    DTHRechargeActivity.this.edit_dth_operator.setError(null);
                    DTHRechargeActivity.this.edit_dth_consumerid.setError("Enter Consumer Id");
                    DTHRechargeActivity.this.edit_dth_consumerid.requestFocus();
                } else {
                    DTHRechargeActivity.this.edit_dth_consumerid.setError(null);
                    Intent intent = new Intent(DTHRechargeActivity.this, DthechargeInfoActivity.class);
                    intent.putExtra("MOBILE", DTHRechargeActivity.this.edit_dth_consumerid.getText().toString());
                    intent.putExtra("OPERATOR", DTHRechargeActivity.this.edit_dth_operator.getText().toString());
                    DTHRechargeActivity.this.startActivity(intent);
                }
            }
        });

        this.edit_dth_operator.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DTHRechargeActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(DTHRechargeActivity.this, OperatorsActivity.class);
                intent.putExtra("NUMBER", DTHRechargeActivity.this.edit_consumer_id.getText().toString());
                intent.putExtra("AMT", DTHRechargeActivity.this.edit_dth_amount.getText().toString());
                intent.putExtra("CALL", "DTH");
                DTHRechargeActivity.this.startActivityForResult(intent, 1);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {

        int txtDetails = R.id.TxtDetails;
        int txtROffer = R.id.TxtROffer;
        int txtSpecialPlan = R.id.TxtSpecialPlan;

        if (txtDetails == txtDetails){
            if (TextUtils.isEmpty(this.edit_dth_operator.getText().toString().trim())) {
                this.edit_dth_operator.setError("Select Operator");
                return;
            }
            this.edit_dth_operator.setError(null);
            if (TextUtils.isEmpty(this.edit_dth_consumerid.getText().toString().trim())) {
                this.edit_dth_consumerid.setError("Enter number");
                return;
            }
            this.edit_dth_consumerid.setError(null);
            getInfo(this.edit_dth_consumerid.getText().toString().trim(), this.edit_dth_operator.getText().toString().trim());
            return;
        } else if (txtROffer==txtROffer) {
            if (TextUtils.isEmpty(this.edit_dth_operator.getText().toString().trim())) {
                this.edit_dth_operator.setError("Select Operator");
                return;
            }
            this.edit_dth_operator.setError(null);
            if (TextUtils.isEmpty(this.edit_dth_consumerid.getText().toString().trim())) {
                this.edit_dth_consumerid.setError("Enter number");
                return;
            }
            this.edit_dth_consumerid.setError(null);
            Intent intent1 = new Intent(this, MobileROfferActivity.class);
            intent1.putExtra("operatorName1", this.edit_dth_operator.getText().toString().trim());
            intent1.putExtra("number", this.edit_dth_consumerid.getText().toString().trim());
            intent1.putExtra("OPID", this.opCode);
            intent1.putExtra("Call", "2");
            startActivity(intent1);
            return;
        } else if (txtSpecialPlan==txtSpecialPlan ) {
            if (TextUtils.isEmpty(this.edit_dth_operator.getText().toString().trim())) {
                this.edit_dth_operator.setError("Select Operator");
                return;
            }
            this.edit_dth_operator.setError(null);
            if (TextUtils.isEmpty(this.edit_dth_consumerid.getText().toString().trim())) {
                this.edit_dth_consumerid.setError("Enter number");
                return;
            }
            this.edit_dth_consumerid.setError(null);
            getSpecialPlan(this.edit_dth_consumerid.getText().toString().trim(), this.opCode);
            return;


        }

//        switch (v.getId()) {
//            case R.id.TxtDetails /* 2131361869 */:
//                if (TextUtils.isEmpty(this.edit_dth_operator.getText().toString().trim())) {
//                    this.edit_dth_operator.setError("Select Operator");
//                    return;
//                }
//                this.edit_dth_operator.setError(null);
//                if (TextUtils.isEmpty(this.edit_dth_consumerid.getText().toString().trim())) {
//                    this.edit_dth_consumerid.setError("Enter number");
//                    return;
//                }
//                this.edit_dth_consumerid.setError(null);
//                getInfo(this.edit_dth_consumerid.getText().toString().trim(), this.edit_dth_operator.getText().toString().trim());
//                return;
//            case R.id.TxtROffer /* 2131361879 */:
//                if (TextUtils.isEmpty(this.edit_dth_operator.getText().toString().trim())) {
//                    this.edit_dth_operator.setError("Select Operator");
//                    return;
//                }
//                this.edit_dth_operator.setError(null);
//                if (TextUtils.isEmpty(this.edit_dth_consumerid.getText().toString().trim())) {
//                    this.edit_dth_consumerid.setError("Enter number");
//                    return;
//                }
//                this.edit_dth_consumerid.setError(null);
//                Intent intent1 = new Intent(this, MobileROfferActivity.class);
//                intent1.putExtra("operatorName1", this.edit_dth_operator.getText().toString().trim());
//                intent1.putExtra("number", this.edit_dth_consumerid.getText().toString().trim());
//                intent1.putExtra("OPID", this.opCode);
//                intent1.putExtra("Call", "2");
//                startActivity(intent1);
//                return;
//            case R.id.TxtSpecialPlan /* 2131361883 */:
//                if (TextUtils.isEmpty(this.edit_dth_operator.getText().toString().trim())) {
//                    this.edit_dth_operator.setError("Select Operator");
//                    return;
//                }
//                this.edit_dth_operator.setError(null);
//                if (TextUtils.isEmpty(this.edit_dth_consumerid.getText().toString().trim())) {
//                    this.edit_dth_consumerid.setError("Enter number");
//                    return;
//                }
//                this.edit_dth_consumerid.setError(null);
//                getSpecialPlan(this.edit_dth_consumerid.getText().toString().trim(), this.opCode);
//                return;
//            default:
//                return;

    }

    private void getSpecialPlan(String number, String operatorName) {
        Intent intent1 = new Intent(this, MobileROfferActivity.class);
        intent1.putExtra("DTHOPNAME", operatorName);
        intent1.putExtra("DTHNUMBER", number);
        intent1.putExtra("Call", "3");
        startActivityForResult(intent1, 102);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getInfo(String Number, String operator) {
        final ProgressDialog pDialog = CustomProgressDialog.getDialogue(this);
        pDialog.show();
        ApiInterface apiservice =RetrofitHandler.getService();
        Call<DthCustInfo> rechargeress = apiservice.getInfoDetails(PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""), PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, ""), operator, Number);
        rechargeress.enqueue(new Callback<DthCustInfo>() { // from class: com.uvapay.activities.DTHRechargeActivity.6
            @Override // retrofit2.Callback
            public void onResponse(Call<DthCustInfo> call, Response<DthCustInfo> response) {
                ProgressDialog progressDialog = pDialog;
                if (progressDialog != null && progressDialog.isShowing()) {
                    pDialog.dismiss();
                }
                DthCustInfo dthCustInfo = response.body();
                if (dthCustInfo.getStatus().equalsIgnoreCase("Success")) {
                    if (dthCustInfo.getData() != null) {
                        LayoutInflater layoutInflater = (LayoutInflater) DTHRechargeActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View dialog = layoutInflater.inflate(R.layout.customer_information, (ViewGroup) null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(DTHRechargeActivity.this);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.setView(dialog);
                        TextView customerName = (TextView) dialog.findViewById(R.id.txtCustomerName);
                        TextView status = (TextView) dialog.findViewById(R.id.txtStatus);
                        TextView nextRecharge = (TextView) dialog.findViewById(R.id.txtNextRechargeDate);
                        TextView Balance = (TextView) dialog.findViewById(R.id.txtBalance);
                        TextView mTxtPlan = (TextView) dialog.findViewById(R.id.TxtPlan);
                        TextView mTxtLastAmount = (TextView) dialog.findViewById(R.id.TxtLastAmount);
                        customerName.setText(dthCustInfo.getData().getCustomerName());
                        status.setText(dthCustInfo.getData().getStatus());
                        nextRecharge.setText(dthCustInfo.getData().getNextRechargeDate());
                        Balance.setText(dthCustInfo.getData().getBalance());
                        customerName.setText(dthCustInfo.getData().getCustomerName());
                        mTxtPlan.setText(dthCustInfo.getData().getPlanname());
                        mTxtLastAmount.setText(dthCustInfo.getData().getMonthlyRecharge());
                        alertDialog.show();
                        return;
                    }
                    return;
                }
                ApplicationConstant.DisplayMessageDialog(DTHRechargeActivity.this, "Response", dthCustInfo.getRemarks());
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<DthCustInfo> call, Throwable t) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                if (t instanceof IOException) {
                    Toast.makeText(DTHRechargeActivity.this, "Please turn on your internet connection ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getDTHConformation(String edit_amount, String edit_mobile, String edit_operator,String trim) {
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

//        textView.setOnClickListener(this);
        this.TxtROffer = (TextView) findViewById(R.id.TxtROffer);
        this.TxtDetails = (TextView) findViewById(R.id.TxtDetails);
//        this.TxtROffer.setOnClickListener(this);
//        this.TxtDetails.setOnClickListener(this);
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
                if (DTHRechargeActivity.this.edit_consumer_id.getText().toString().trim().length() >= 9) {
                    DTHRechargeActivity dTHRechargeActivity = DTHRechargeActivity.this;
                    dTHRechargeActivity.callOperator(dTHRechargeActivity.edit_consumer_id.getText().toString().trim());
                }
            }
        });
    }

    public void callOperator(String number) {
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<OperatorWithCircleResponse> rechargeress = apiservice.getDTHOPT(PrefUtils.getFromPrefs(this, "userid", ""), PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, ""), number);
        rechargeress.enqueue(new Callback<OperatorWithCircleResponse>() { // from class: com.uvapay.activities.DTHRechargeActivity.8
            @Override // retrofit2.Callback
            public void onResponse(Call<OperatorWithCircleResponse> call, Response<OperatorWithCircleResponse> response) {
                OperatorWithCircleResponse dthCustInfo = response.body();
                if (dthCustInfo.getStatus().equalsIgnoreCase("Success")) {
                    try {
                        for (Datum data : DTHRechargeActivity.this.DTHoperatorList) {
                            if (data.equals(dthCustInfo.getData().getOperator())) {
                                DTHRechargeActivity.this.edit_dth_operator.setText(dthCustInfo.getData().getOperator());
                                DTHRechargeActivity.this.opCode = data.getOurCode();
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<OperatorWithCircleResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(DTHRechargeActivity.this, "Please turn on your internet connection ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}