package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sendotp_registernumber extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private EditText Mobile_number;

    Button sendotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendotp_registernumber);

        sendotp = findViewById(R.id.send_otp);
        Mobile_number = findViewById(R.id.enter_username);
        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtpToNumber(Mobile_number.getText().toString());
//                sendotp_registernumber.this.startActivity(new Intent(sendotp_registernumber.this, verifymobilenumberActivity.class));
//                sendotp_registernumber.this.finish();

            }
        });

    }

    private void sendOtpToNumber(final String mobile) {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("UniqueCode", mobile);
        ApiInterface apiInterface = RetrofitHandler.getService();
        Call<OtpSentResponse> call = apiInterface.getsendOtp(body);

//        Call<OtpSentResponse> call = apiInterface.forgotPasswordMethod("ForgotPassword?username=" + mobile);
        call.enqueue(new Callback<OtpSentResponse>() { // from class: com.uvapay.activities.LoginActivity.11
            @Override // retrofit2.Callback
            public void onResponse(Call<OtpSentResponse> call2, Response<OtpSentResponse> response) {

                Toast.makeText(sendotp_registernumber.this, "GetOTP", Toast.LENGTH_SHORT).show();
//                if (response.body() != null) {
//                    if (response.body().getStatusCode().toString().equals("0")) {
//                        if (sendotp_registernumber.this.progressDialog != null && sendotp_registernumber.this.progressDialog.isShowing()) {
//                            sendotp_registernumber.this.progressDialog.dismiss();
//                        }
//                        ConstantClass.displayMessageDialog(sendotp_registernumber.this, "", response.body().getMessage());
//                        return;
//                    } else if (response.body().getStatusCode().toString().equals(ConstantClass.MOBILESERVICEID)) {
//                        if (sendotp_registernumber.this.progressDialog != null && sendotp_registernumber.this.progressDialog.isShowing()) {
//                            sendotp_registernumber.this.progressDialog.dismiss();
//                        }
//                        ConstantClass.displayToastMessage(sendotp_registernumber.this, response.body().getMessage());
//                        PrefUtils.saveToPrefs(sendotp_registernumber.this, ConstantClass.USERDETAILS.UserName, mobile);
//                        Intent intent = new Intent(sendotp_registernumber.this, OtpVerificationActivity.class);
//                        intent.putExtra("control", "forgot_password");
//                        sendotp_registernumber.this.startActivity(intent);
//                        return;
//                    } else {
//                        if (sendotp_registernumber.this.progressDialog != null && sendotp_registernumber.this.progressDialog.isShowing()) {
//                            sendotp_registernumber.this.progressDialog.dismiss();
//                        }
//                        ConstantClass.displayMessageDialog(sendotp_registernumber.this, "", response.body().getMessage());
//                        return;
//                    }
//                }
                if (sendotp_registernumber.this.progressDialog != null && sendotp_registernumber.this.progressDialog.isShowing()) {
                    sendotp_registernumber.this.progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(sendotp_registernumber.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<OtpSentResponse> call2, Throwable t) {
                if (sendotp_registernumber.this.progressDialog != null && sendotp_registernumber.this.progressDialog.isShowing()) {
                    sendotp_registernumber.this.progressDialog.dismiss();
                }
                sendotp_registernumber loginActivity = sendotp_registernumber.this;
                ConstantClass.displayMessageDialog(loginActivity, loginActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });

    }
}