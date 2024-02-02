package com.example.api_call;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerificationActivity extends AppCompatActivity {
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
    private Button btn_verify_otp;
    private String change_password_user;
    private EditText fifth_num;
    private EditText first_num;
    private EditText forth_num;
    private Matcher matcher;
    private String password_;
    private Pattern pattern;
    private ProgressDialog progressDialog;
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
    private String Mobile_number ="";
    private String Otp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        bindView();

        this.first_num.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.OtpVerificationActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    OtpVerificationActivity.this.second_num.requestFocus();
                }
            }
        });

        this.second_num.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.OtpVerificationActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    OtpVerificationActivity.this.third_num.requestFocus();
                } else if (s.length() == 0) {
                    OtpVerificationActivity.this.first_num.requestFocus();
                }
            }
        });


        this.third_num.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.OtpVerificationActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    OtpVerificationActivity.this.forth_num.requestFocus();
                } else if (s.length() == 0) {
                    OtpVerificationActivity.this.second_num.requestFocus();
                }
            }
        });

        this.forth_num.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.OtpVerificationActivity.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    OtpVerificationActivity.this.fifth_num.requestFocus();
                } else if (s.length() == 0) {
                    OtpVerificationActivity.this.third_num.requestFocus();
                }
            }
        });

        this.fifth_num.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.OtpVerificationActivity.5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    OtpVerificationActivity.this.sixth_num.requestFocus();
                } else if (s.length() == 0) {
                    OtpVerificationActivity.this.forth_num.requestFocus();
                }
            }
        });

        this.sixth_num.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.OtpVerificationActivity.6
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    OtpVerificationActivity.this.fifth_num.requestFocus();
                }
            }
        });
        try {
            this.control = getIntent().getExtras().getString("control");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.verify_trough = getIntent().getExtras().getString("verify_trough");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        resendOtpTimer();
//        showSoftKeyboard(this.first_num);
        this.btn_verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {

                OtpVerificationActivity.this.validate();
            }
        });
        this.text_resend.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.OtpVerificationActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (OtpVerificationActivity.this.text_resend.getText().toString().equals("RESEND OTP") && !OtpVerificationActivity.this.control.equals("verification")) {
                    if (OtpVerificationActivity.this.control.equals("forgot_password")) {
                        String mobile = PrefUtils.getFromPrefs(OtpVerificationActivity.this, ConstantClass.USERDETAILS.UserName, "");
//                        OtpVerificationActivity.this.resendForgotOtp(mobile);
                    } else if (OtpVerificationActivity.this.control.equals("kyc")) {
                        OtpVerificationActivity otpVerificationActivity = OtpVerificationActivity.this;
//                        otpVerificationActivity.sendOtpMethod(otpVerificationActivity.verify_trough);
                    }
                }
            }
        });
    }


    private void validate() {
        if (this.first_num.getText().toString().isEmpty()) {
            this.first_num.setError("Enter correct otp");
            this.first_num.requestFocus();
        } else if (this.second_num.getText().toString().isEmpty()) {
            this.second_num.setError("Enter correct otp");
            this.second_num.requestFocus();
        } else if (this.third_num.getText().toString().isEmpty()) {
            this.third_num.setError("Enter correct otp");
            this.third_num.requestFocus();
        } else if (this.forth_num.getText().toString().isEmpty()) {
            this.forth_num.setError("Enter correct otp");
            this.forth_num.requestFocus();
        } else if (this.fifth_num.getText().toString().isEmpty()) {
            this.fifth_num.setError("Enter correct otp");
            this.fifth_num.requestFocus();
        } else if (this.sixth_num.getText().toString().isEmpty()) {
            this.sixth_num.setError("Enter correct otp");
            this.sixth_num.requestFocus();
        } else if (this.control.equals("Registration")) {
//            ApplicationConstant.DisplayMessageDialog(OtpVerificationActivity.this,"Mobile Number Verify Sucessfully.","");
            getUserDetailsthroughOtp();
        }
        else if (this.control.equals("forgot_password")) {
//            ApplicationConstant.DisplayMessageDialog(OtpVerificationActivity.this,"Mobile Number Verify Sucessfully.","");
           getUserDetailsthroughOtp();
        } else if (this.control.equals("kyc")) {
            resetNewPasswordForKYC();
        }
    }

    private void getUserDetailsthroughOtp() {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        String otp = this.first_num.getText().toString() + "" + this.second_num.getText().toString() + "" + this.third_num.getText().toString() + "" + this.forth_num.getText().toString() + "" + this.fifth_num.getText().toString() + "" + this.sixth_num.getText().toString();
        HashMap<String, String> body = new HashMap<>();
        String uniqueCode = getIntent().getStringExtra("UniqueCode");
        body.put("UniqueCode", uniqueCode);
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("OtpType", "Registration");
        body.put("Otp", otp);
        ApiInterface apiInterface = RetrofitHandler.getService2();
        Call<OtpverifyResponse> call = apiInterface.otpSendmethod(body);
        call.enqueue(new Callback<OtpverifyResponse>() {
            @Override
            public void onResponse(Call<OtpverifyResponse> call, Response<OtpverifyResponse> response) {
                if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
                    OtpVerificationActivity.this.progressDialog.dismiss();
                }

                if (response.body() != null) {
                    String message = response.body().getMessage();
                    int responseStatus = response.body().getResponseStatus();

                    if (responseStatus == 0) {
                        Intent intent = new Intent(OtpVerificationActivity.this, UserRegistration_page.class);
                        startActivity(intent);
                    } else {
                        ConstantClass.displayMessageDialog(OtpVerificationActivity.this, "Response", message);
                    }
                } else {
                    if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
                        OtpVerificationActivity.this.progressDialog.dismiss();
                    }

                    try {
                        ConstantClass.displayMessageDialog(OtpVerificationActivity.this, "Response", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<OtpverifyResponse> call, Throwable t) {
                if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
                    OtpVerificationActivity.this.progressDialog.dismiss();
                }
                OtpVerificationActivity otpVerificationActivity = OtpVerificationActivity.this;
                ConstantClass.displayMessageDialog(otpVerificationActivity, otpVerificationActivity.getString(R.string.server_problem), t.getMessage().toString());

            }
        });

    }

    @SuppressLint("MissingInflatedId")
    private void resetNewPasswordForKYC() {
        View view = getLayoutInflater().inflate(R.layout.layout_change_password, (ViewGroup) null);
        ImageView image_cancel = (ImageView) view.findViewById(R.id.image_cancel);
        EditText edit_old_password = (EditText) view.findViewById(R.id.edit_old_password);
        final EditText edit_new_password = (EditText) view.findViewById(R.id.edit_new_password);
        final EditText edit_confirm_password = (EditText) view.findViewById(R.id.edit_confirm_password);
        TextView textView = (TextView) view.findViewById(R.id.text_pass_change);
        Button btn_change_password = (Button) view.findViewById(R.id.btn_change_password);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_hide);
//        CheckView checkView = (CheckView) view.findViewById(R.id.check);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);
//        edit_old_password.setVisibility(8);
        image_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.OtpVerificationActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_change_password.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.OtpVerificationActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (edit_new_password.getText().toString().isEmpty()) {
                    edit_new_password.setError("enter password");
                    edit_new_password.requestFocus();
                } else if (!OtpVerificationActivity.this.pattern.matcher(edit_new_password.getText().toString()).matches()) {
                    edit_new_password.setError("password should contain capital character, numbers and characters.");
                    edit_new_password.requestFocus();
                } else if (edit_confirm_password.getText().toString().isEmpty()) {
                    edit_confirm_password.setError("enter confirm password");
                    edit_confirm_password.requestFocus();
                } else if (!edit_new_password.getText().toString().trim().equals(edit_confirm_password.getText().toString().trim())) {
                    edit_confirm_password.setError("confirm password and password didn't match");
                    edit_confirm_password.requestFocus();
                } else {
//                    OtpVerificationActivity.this.setPasswordForUser(edit_new_password.getText().toString(), alertDialog);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            alertDialog.create();
            alertDialog.show();
        }
    }



    private void resetNewPassword() {
        View view = getLayoutInflater().inflate(R.layout.layout_change_password, (ViewGroup) null);
        ImageView image_cancel = (ImageView) view.findViewById(R.id.image_cancel);
        EditText edit_old_password = (EditText) view.findViewById(R.id.edit_old_password);
        final EditText edit_new_password = (EditText) view.findViewById(R.id.edit_new_password);
        final EditText edit_confirm_password = (EditText) view.findViewById(R.id.edit_confirm_password);
        TextView textView = (TextView) view.findViewById(R.id.text_pass_change);
        Button btn_change_password = (Button) view.findViewById(R.id.btn_change_password);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_hide);
//        CheckView checkView = (CheckView) view.findViewById(R.id.check);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);
//        edit_old_password.setVisibility(8);
        image_cancel.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_change_password.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.OtpVerificationActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (edit_new_password.getText().toString().isEmpty()) {
                    edit_new_password.setError("enter password");
                    edit_new_password.requestFocus();
                } else if (!OtpVerificationActivity.this.pattern.matcher(edit_new_password.getText().toString()).matches()) {
                    edit_new_password.setError("password should contain capital character, numbers and characters.");
                    edit_new_password.requestFocus();
                } else if (edit_confirm_password.getText().toString().isEmpty()) {
                    edit_confirm_password.setError("enter confirm password");
                    edit_confirm_password.requestFocus();
                } else if (!edit_new_password.getText().toString().trim().equals(edit_confirm_password.getText().toString().trim())) {
                    edit_confirm_password.setError("confirm password and password didn't match");
                    edit_confirm_password.requestFocus();
                } else {
//                    OtpVerificationActivity.this.changePasswordWithOtp(edit_new_password.getText().toString());
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            alertDialog.create();
            alertDialog.show();
        }
    }

//    private void getUserDetailsthroughOtp() {
//        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
//        this.progressDialog = dialogue;
//        dialogue.show();
//        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
//        String password = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
//        String android_id = Settings.Secure.getString(getContentResolver(), "android_id");
//        String otp = this.first_num.getText().toString() + "" + this.second_num.getText().toString() + "" + this.third_num.getText().toString() + "" + this.forth_num.getText().toString() + "" + this.fifth_num.getText().toString() + "" + this.sixth_num.getText().toString();
//        HashMap<String, String> body = new HashMap<>();
//        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
//        body.put("Password", password);
//        body.put("IMEINo", android_id);
//        body.put("OTP", otp);
//        LoginApiAervices apiservice = (LoginApiAervices) ApiService.getApiClient().create(LoginApiAervices.class);
//        Call<OtpSentResponse> call = apiservice.otpSendmethod(body);
//        call.enqueue(new Callback<OtpSentResponse>() { // from class: com.uvapay.activities.OtpVerificationActivity.15
//            @Override // retrofit2.Callback
//            public void onResponse(Call<OtpSentResponse> call2, Response<OtpSentResponse> response) {
//                if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                    OtpVerificationActivity.this.progressDialog.dismiss();
//                }
//                if (response.body() != null) {
//                    if (!response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
//                        ConstantClass.displayMessageDialog(OtpVerificationActivity.this, "Response", response.body().getMessage().toString());
//                        return;
//                    }
//                    return;
//                }
//                if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                    OtpVerificationActivity.this.progressDialog.dismiss();
//                }
//                try {
//                    ConstantClass.displayMessageDialog(OtpVerificationActivity.this, "Response", response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override // retrofit2.Callback
//            public void onFailure(Call<OtpSentResponse> call2, Throwable t) {
//                if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                    OtpVerificationActivity.this.progressDialog.dismiss();
//                }
//                OtpVerificationActivity otpVerificationActivity = OtpVerificationActivity.this;
//                ConstantClass.displayMessageDialog(otpVerificationActivity, otpVerificationActivity.getString(R.string.server_problem), t.getMessage().toString());
//            }
//        });
//    }

//    private void sendOtpMethod(String verify_trough) {
//        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
//        this.progressDialog = dialogue;
//        dialogue.show();
//        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
//        String password = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
//        HashMap<String, String> body = new HashMap<>();
//        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
//        body.put("Password", password);
//        body.put("Type", verify_trough);
//        LoginApiAervices apiservice = (LoginApiAervices) ApiService.getApiClient().create(LoginApiAervices.class);
//        Call<OtpSentResponse> call = apiservice.VerificationRequest(body);
//        call.enqueue(new Callback<OtpSentResponse>() { // from class: com.uvapay.activities.OtpVerificationActivity.18
//            @Override // retrofit2.Callback
//            public void onResponse(Call<OtpSentResponse> call2, Response<OtpSentResponse> response) {
//                if (response.body() != null) {
//                    if (response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
//                        if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                            OtpVerificationActivity.this.progressDialog.dismiss();
//                        }
//                        Intent intent = new Intent(OtpVerificationActivity.this, OtpVerificationActivity.class);
//                        intent.putExtra("control", "kyc");
//                        OtpVerificationActivity.this.startActivity(intent);
//                        return;
//                    } else if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                        OtpVerificationActivity.this.progressDialog.dismiss();
//                        return;
//                    } else {
//                        return;
//                    }
//                }
//                if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                    OtpVerificationActivity.this.progressDialog.dismiss();
//                }
//                try {
//                    ConstantClass.displayMessageDialog(OtpVerificationActivity.this, "Response", response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override // retrofit2.Callback
//            public void onFailure(Call<OtpSentResponse> call2, Throwable t) {
//                if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                    OtpVerificationActivity.this.progressDialog.dismiss();
//                }
//                ConstantClass.displayMessageDialog(OtpVerificationActivity.this, "Response", t.getMessage().toString());
//            }
//        });
//    }

//    public void resendForgotOtp(String mobile) {
//        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
//        this.progressDialog = dialogue;
//        dialogue.show();
//        LoginApiAervices apiservice = (LoginApiAervices) ApiService.getApiClient().create(LoginApiAervices.class);
//        Call<OtpSentResponse> call = apiservice.forgotPasswordMethod("Authentication/ForgotPassword?username=" + mobile);
//        call.enqueue(new Callback<OtpSentResponse>() { // from class: com.uvapay.activities.OtpVerificationActivity.17
//            @Override // retrofit2.Callback
//            public void onResponse(Call<OtpSentResponse> call2, Response<OtpSentResponse> response) {
//                if (response.body() != null) {
//                    if (response.body().getStatusCode().toString().equals("0")) {
//                        if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                            OtpVerificationActivity.this.progressDialog.dismiss();
//                        }
//                        ConstantClass.displayMessageDialog(OtpVerificationActivity.this, "", response.body().getMessage());
//                        return;
//                    } else if (response.body().getStatusCode().toString().equals(ConstantClass.MOBILESERVICEID)) {
//                        if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                            OtpVerificationActivity.this.progressDialog.dismiss();
//                        }
//                        ConstantClass.displayToastMessage(OtpVerificationActivity.this, response.body().getMessage());
//                        return;
//                    } else {
//                        if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                            OtpVerificationActivity.this.progressDialog.dismiss();
//                        }
//                        ConstantClass.displayMessageDialog(OtpVerificationActivity.this, "", response.body().getMessage());
//                        return;
//                    }
//                }
//                if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                    OtpVerificationActivity.this.progressDialog.dismiss();
//                }
//                try {
//                    ConstantClass.displayMessageDialog(OtpVerificationActivity.this, "Response", response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override // retrofit2.Callback
//            public void onFailure(Call<OtpSentResponse> call2, Throwable t) {
//                if (OtpVerificationActivity.this.progressDialog != null && OtpVerificationActivity.this.progressDialog.isShowing()) {
//                    OtpVerificationActivity.this.progressDialog.dismiss();
//                }
//                OtpVerificationActivity otpVerificationActivity = OtpVerificationActivity.this;
//                ConstantClass.displayMessageDialog(otpVerificationActivity, otpVerificationActivity.getString(R.string.server_problem), t.getMessage().toString());
//            }
//        });
//    }

//    public static void showSoftKeyboard(View view) {
//        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
//        view.requestFocus();
//        inputMethodManager.showSoftInput(view, 0);
//    }

    private void resendOtpTimer() {
        new CountDownTimer(60000L, 1000L) { // from class: com.uvapay.activities.OtpVerificationActivity.16
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                OtpVerificationActivity.this.text_resend.setText("seconds: " + (millisUntilFinished / 1000));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                OtpVerificationActivity.this.text_resend.setText("RESEND OTP");
            }
        }.start();
    }


    private void bindView() {
        this.btn_verify_otp = (Button) findViewById(R.id.btn_verify_otp);
        this.first_num = (EditText) findViewById(R.id.first_num);
        this.second_num = (EditText) findViewById(R.id.second_num);
        this.third_num = (EditText) findViewById(R.id.third_num);
        this.forth_num = (EditText) findViewById(R.id.forth_num);
        this.fifth_num = (EditText) findViewById(R.id.fifth_num);
        this.sixth_num = (EditText) findViewById(R.id.sixth_num);
        this.text_resend = (TextView) findViewById(R.id.text_resend);
        this.text_user = (TextView) findViewById(R.id.text_user);
        this.pattern = Pattern.compile(PASSWORD_PATTERN);
    }

}