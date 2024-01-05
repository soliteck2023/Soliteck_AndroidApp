package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class PostpaidRechargeActivity extends AppCompatActivity {
    private Button btn_postpaid_Recharge;
    private EditText edit_mobile_number;
    private EditText edit_postpaid_amt;
    private EditText edit_postpaid_operator;
    private GetOperatorByNum getOperatorByNum;
    private EditText mEditTPin;
    RecyclerView recyclerView;
    private String NUMBER = "";
    private String AMT = "";
    private String CALL = "";
    private String ourcode = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postpaid_recharge);
        setTitle("Postpaid Recharge");
        initComponents();

        this.btn_postpaid_Recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PostpaidRechargeActivity.this.edit_mobile_number.getText().toString().isEmpty()) {
                    PostpaidRechargeActivity.this.edit_mobile_number.setError("Enter number");
                    PostpaidRechargeActivity.this.edit_mobile_number.requestFocus();
                } else if (PostpaidRechargeActivity.this.edit_mobile_number.getText().toString().trim().isEmpty()){
                    PostpaidRechargeActivity.this.edit_mobile_number.setError("Enter number");
                    PostpaidRechargeActivity.this.edit_mobile_number.requestFocus();
                    return;
                }
                PostpaidRechargeActivity.this.edit_mobile_number.setError(null);
               if ( PostpaidRechargeActivity.this.edit_postpaid_amt.getText().toString().trim().isEmpty()){
                   PostpaidRechargeActivity.this.edit_postpaid_amt.setError("Enter amount");
                   PostpaidRechargeActivity.this.edit_mobile_number.requestFocus();
                   return;
               }
                PostpaidRechargeActivity.this.edit_postpaid_amt.setError(null);
                if (TextUtils.isEmpty(PostpaidRechargeActivity.this.mEditTPin.getText().toString().trim())) {
                    PostpaidRechargeActivity.this.mEditTPin.setError("Enter TPIN");
                    return;
                }
                PostpaidRechargeActivity.this.mEditTPin.setError(null);
                PostpaidRechargeActivity postpaidRechargeActivity = PostpaidRechargeActivity.this;
                postpaidRechargeActivity.getMobileConformation(postpaidRechargeActivity.edit_postpaid_amt.getText().toString(), PostpaidRechargeActivity.this.edit_mobile_number.getText().toString(), PostpaidRechargeActivity.this.edit_postpaid_operator.getText().toString());



            }
        });
        this.edit_postpaid_operator.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PostpaidRechargeActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(PostpaidRechargeActivity.this, OperatorsActivity.class);
                intent.putExtra("NUMBER", PostpaidRechargeActivity.this.edit_mobile_number.getText().toString());
                intent.putExtra("AMT", PostpaidRechargeActivity.this.edit_postpaid_amt.getText().toString());
                intent.putExtra("CALL", "POSTPAID");
                PostpaidRechargeActivity.this.startActivityForResult(intent, 1);
            }
        });



    }

    private void getMobileConformation(String edit_amount, String edit_mobile, String edit_operator) {
        Intent intent = new Intent(this, RechargeConfirmationActivity.class);
        intent.putExtra("MOBILE_NO", edit_mobile);
        intent.putExtra("AMOUNT", edit_amount);
        intent.putExtra("CALL", "MOBILE_POSTPAID");
        intent.putExtra("OPERATOR", edit_operator);
        String EncodedTPIN = ApplicationConstant.EncodeStringToHMACSHA256(this.mEditTPin.getText().toString().trim());
        intent.putExtra("TPIN", EncodedTPIN);
        startActivity(intent);
    }

    private void initComponents() {
        this.edit_mobile_number = (EditText) findViewById(R.id.edit_mobile_number);
        this.edit_postpaid_operator = (EditText) findViewById(R.id.edit_postpaid_operator);
        this.edit_postpaid_amt = (EditText) findViewById(R.id.edit_postpaid_amt);
        this.btn_postpaid_Recharge = (Button) findViewById(R.id.btn_postpaid_Recharge);
        this.mEditTPin = (EditText) findViewById(R.id.EditTPin);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == -1) {
                try {
                    if (data.getExtras() != null) {
                        this.NUMBER = data.getExtras().getString("NUMBER");
                        this.AMT = data.getExtras().getString("AMT");
                        this.CALL = data.getExtras().getString("CALL");
                        this.edit_postpaid_amt.setText(this.AMT);
                        this.edit_mobile_number.setText(this.NUMBER);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (ConstantClass.datum != null && ConstantClass.datum.getServiceName().equals("POSTPAID")) {
                        this.edit_postpaid_operator.setText(ConstantClass.datum.getName());
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            Toast.makeText(this, "Result Not Ok", Toast.LENGTH_SHORT).show();
        }
    }

}