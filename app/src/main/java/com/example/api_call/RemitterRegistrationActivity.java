package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemitterRegistrationActivity extends AppCompatActivity {
    private Button btn_register_remitter;
    private Button btn_send_otp;
    private EditText edit_last_name;
    private EditText edit_mobile;
    private EditText edit_name;
    private EditText edit_otp;
    private EditText edit_pincode;
    private FrameLayout linear_Otp;
    private LinearLayout linear_resend;
    private TextView text_resend;
    private String mobile_no = "";
    private String REMITTER = "";
    private String otpState = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remitter_registration);
        setTitle("Remitter Registration");
        initComponents();
        String string = getIntent().getExtras().getString("MOBILE");
        this.mobile_no = string;
        this.edit_mobile.setText(string);
        this.btn_send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RemitterRegistrationActivity.this.edit_name.getText().toString().isEmpty()) {
                    RemitterRegistrationActivity.this.edit_name.setError("enter first name");
                    RemitterRegistrationActivity.this.edit_name.requestFocus();
                } else if (RemitterRegistrationActivity.this.edit_last_name.getText().toString().isEmpty()) {
                    RemitterRegistrationActivity.this.edit_last_name.setError("enter last name");
                    RemitterRegistrationActivity.this.edit_last_name.requestFocus();
                } else if (RemitterRegistrationActivity.this.edit_mobile.getText().toString().length() != 10) {
                    RemitterRegistrationActivity.this.edit_mobile.setText("enter correct number");
                    RemitterRegistrationActivity.this.edit_mobile.requestFocus();
                } else if (RemitterRegistrationActivity.this.edit_pincode.getText().toString().length() != 6) {
                    RemitterRegistrationActivity.this.edit_pincode.setError("enter correct pincode");
                    RemitterRegistrationActivity.this.edit_pincode.requestFocus();
                } else {
                    RemitterRegistrationActivity remitterRegistrationActivity = RemitterRegistrationActivity.this;
                    remitterRegistrationActivity.sendOtptoRemitter(remitterRegistrationActivity.mobile_no);
                }
            }
        });

        this.btn_register_remitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RemitterRegistrationActivity.this.edit_name.getText().toString().isEmpty()) {
                    RemitterRegistrationActivity.this.edit_name.setError("enter first name");
                    RemitterRegistrationActivity.this.edit_name.requestFocus();
                } else if (RemitterRegistrationActivity.this.edit_last_name.getText().toString().isEmpty()) {
                    RemitterRegistrationActivity.this.edit_last_name.setError("enter last name");
                    RemitterRegistrationActivity.this.edit_last_name.requestFocus();
                } else if (RemitterRegistrationActivity.this.edit_mobile.getText().toString().length() != 10) {
                    RemitterRegistrationActivity.this.edit_mobile.setText("enter correct number");
                    RemitterRegistrationActivity.this.edit_mobile.requestFocus();
                } else if (RemitterRegistrationActivity.this.edit_pincode.getText().toString().length() != 6) {
                    RemitterRegistrationActivity.this.edit_pincode.setError("enter correct pincode");
                    RemitterRegistrationActivity.this.edit_pincode.requestFocus();
                } else if (RemitterRegistrationActivity.this.edit_otp.getText().toString().length() != 6) {
                    RemitterRegistrationActivity.this.edit_otp.setError("enter correct Otp");
                    RemitterRegistrationActivity.this.edit_otp.requestFocus();
                } else {
                    RemitterRegistrationActivity remitterRegistrationActivity = RemitterRegistrationActivity.this;
                    remitterRegistrationActivity.remitterRegistration(remitterRegistrationActivity.edit_name.getText().toString(), RemitterRegistrationActivity.this.edit_last_name.getText().toString(), RemitterRegistrationActivity.this.mobile_no, RemitterRegistrationActivity.this.edit_pincode.getText().toString(), RemitterRegistrationActivity.this.edit_otp.getText().toString());
                }
            }
        });




    }

    public void remitterRegistration(String fName, String lName, String mobile_no, String pincode, String otp) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("Mobile", mobile_no);
        body.put("FirstName", fName);
        body.put("LastName", lName);
        body.put(ConstantClass.PROFILEDETAILS.PinCode, pincode);
        body.put("Otp", otp);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<RemitterRegResponse> call = apiservice.getRemitterRegistered(body);
        call.enqueue(new Callback<RemitterRegResponse>() {
            @Override
            public void onResponse(Call<RemitterRegResponse> call2, Response<RemitterRegResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                        ConstantClass.displayToastMessage(RemitterRegistrationActivity.this, response.body().getMessage());
                        Intent intent = new Intent(RemitterRegistrationActivity.this, BeneficieryActivity.class);
                        intent.putExtra("MOBILE", RemitterRegistrationActivity.this.mobile_no);
                        RemitterRegistrationActivity.this.startActivity(intent);
                        RemitterRegistrationActivity.this.finish();
                        return;
                    }
                    ConstantClass.displayMessageDialog(RemitterRegistrationActivity.this, "", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<RemitterRegResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(RemitterRegistrationActivity.this, "Server Problem", t.getMessage());
            }
        });
    }

    private void sendOtptoRemitter(String mobile_no) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("Mobile", mobile_no);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<OTPResponse> call = apiservice.getOtp(body);
        call.enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call2, Response<OTPResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null && response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                    ApplicationConstant.displayToastMessage(RemitterRegistrationActivity.this,response.body().getMessage());
                    RemitterRegistrationActivity.this.linear_resend.setVisibility(View.VISIBLE);
                    RemitterRegistrationActivity.this.linear_Otp.setVisibility(View.VISIBLE);
                    RemitterRegistrationActivity.this.btn_register_remitter.setVisibility(View.VISIBLE);
                    RemitterRegistrationActivity.this.btn_send_otp.setVisibility(View.GONE);
                    RemitterRegistrationActivity.this.resendOtpTimer();
                }
            }

            @Override
            public void onFailure(Call<OTPResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(RemitterRegistrationActivity.this, "Response", t.getMessage());
            }
        });
    }

    private void resendOtpTimer() {
        new CountDownTimer(60000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                RemitterRegistrationActivity.this.text_resend.setText("seconds: " + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                RemitterRegistrationActivity.this.text_resend.setText("RESEND OTP");
            }
        }.start();
    }

    private void initComponents() {
        this.edit_name = (EditText) findViewById(R.id.edit_name);
        this.edit_otp = (EditText) findViewById(R.id.edit_otp);
        this.edit_last_name = (EditText) findViewById(R.id.edit_last_name);
        this.edit_mobile = (EditText) findViewById(R.id.edit_mobile);
        this.edit_pincode = (EditText) findViewById(R.id.edit_pincode);
        this.btn_register_remitter = (Button) findViewById(R.id.btn_register_remitter);
        this.btn_send_otp = (Button) findViewById(R.id.btn_send_otp);
        this.linear_Otp = (FrameLayout) findViewById(R.id.linear_Otp);
        this.linear_resend = (LinearLayout) findViewById(R.id.linear_resend);
        this.text_resend = (TextView) findViewById(R.id.text_resend);
    }
    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}