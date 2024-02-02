package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sendotp_registernumber extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private EditText Mobile_number;

    Button sendotp;
    private AlertDialog alertDialog;
    private RegistrationMobilenumber registrationMobileNumberCallback;

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
    private Button btn_verify_otp;
    private String change_password_user;
    private EditText fifth_num;
    private EditText first_num;
    private EditText forth_num;
    private Matcher matcher;
    private String password_;
    private Pattern pattern;

    private BroadcastReceiver receiver;
    private EditText second_num;
    private EditText sixth_num;
    private TextView text_resend;
    private TextView text_user;
    private EditText third_num;
    private String user;
    private String user_;
    private String control = "";
    private String verify_trough = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendotp_registernumber);

        sendotp = findViewById(R.id.send_otp);
        Mobile_number = findViewById(R.id.enter_username);
        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidMobileNumber(Mobile_number.getText().toString())) {

                    SendOTP(Mobile_number.getText().toString());
                } else {
                    // Display an error toast for invalid mobile number
                    Toast.makeText(sendotp_registernumber.this, "Invalid mobile number. Please enter a 10-digit number.", Toast.LENGTH_SHORT).show();
                }

//                RegistrationMobilenumber registrationMobileNumberCallback= null;
//                SendOTP(Mobile_number.getText().toString(), registrationMobileNumberCallback);
            }
        });
    }



    private void SendOTP(String Mobile_number) {

        HashMap<String, String> body = new HashMap<>();

        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
//        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.Registration.Token, ""));
        body.put("UniqueCode", Mobile_number);
        body.put("OTPType", "Registration");

        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<BalTransferResponse> result = apiservice.GetMOBOtp(body);
        result.enqueue(new Callback<BalTransferResponse>() {
            @Override
            public void onResponse(Call<BalTransferResponse> call, Response<BalTransferResponse> response) {
                if (response != null && response.isSuccessful()) {

                   ConstantClass.displayToastMessage(sendotp_registernumber.this, response.body().getMessage());
                    Intent intent = new Intent(sendotp_registernumber.this, OtpVerificationActivity.class);
                    intent.putExtra("control", "forgot_password");
                    intent.putExtra("UniqueCode", Mobile_number);
                    sendotp_registernumber.this.startActivity(intent);
                } else {

                }
            }
            @Override
            public void onFailure(Call<BalTransferResponse> call, Throwable t) {
                Toast.makeText(sendotp_registernumber.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidMobileNumber(String mobileNumber) {
        // Check if the mobile number has exactly 10 digits
        return mobileNumber.length() == 10;
    }

    public void verifyOTP(View view) {
        if (btn_verify_otp != null){
            this.btn_verify_otp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(sendotp_registernumber.this, "User Mobile Number Registration is Done", Toast.LENGTH_SHORT).show();
                }
            });

        }else {
        }

    }
}