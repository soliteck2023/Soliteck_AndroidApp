package com.example.api_call;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String Flag_Remember = "";
    RelativeLayout btn_login;
    private EditText edit_password;
    private EditText edit_username;
    private int READ_PHONE_REQUEST = 20;
    private ImageView image_logo;
    private String passWord;
    private ProgressDialog progressDialog;
    private CheckBox show_password;
    private CheckBox checkbox_remember;

    private TextView text_forgot_password;
    private TextView New_user;
    private String userName;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String Privacy = PrefUtils.getFromPrefs(this, ApplicationConstant.PRIVACY_POLICY, "");
        if (Privacy.trim().isEmpty()) {
            DisplayPrivacyPolicy();
        } else {
            checkAndRequestPermissions();
        }
        btn_login = findViewById(R.id.btn_login);
        this.btn_login = (RelativeLayout) findViewById(R.id.btn_login);
        this.show_password = (CheckBox) findViewById(R.id.show_password);
        this.edit_password = (EditText) findViewById(R.id.password_login);
        this.edit_username = (EditText) findViewById(R.id.edit_username);
        this.edit_username = (EditText) findViewById(R.id.edit_username);
        this.New_user = (TextView) findViewById(R.id.New_user);
        this.text_forgot_password = (TextView) findViewById(R.id.text_forgot_password);
        this.image_logo = (ImageView) findViewById(R.id.image_logo);
        this.Flag_Remember = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.FlagRemember, "");
        this.checkbox_remember = (CheckBox) findViewById(R.id.checkbox_remember);

        New_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration_page.class);
                MainActivity.this.startActivity(intent);

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!ConstantClass.isNetworkAvailable(MainActivity.this)) {
//                    ConstantClass.displayMessageDialog(MainActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                } else
                if (MainActivity.this.edit_username.getText().toString().trim().isEmpty()) {
                    MainActivity.this.edit_username.setError("Enter username");
                    MainActivity.this.edit_username.requestFocus();
                } else {
                    MainActivity.this.edit_username.setError(null);
                    if (MainActivity.this.edit_password.getText().toString().trim().isEmpty()) {
                        MainActivity.this.edit_password.setError("Enter Password");
                        MainActivity.this.edit_password.requestFocus();
                        return;
                    }
                    MainActivity.this.edit_password.setError(null);
//                    if (!ConstantClass.isNetworkAvailable(MainActivity.this)) {
//                        ConstantClass.displayMessageDialog(MainActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                        return;
//                    }
                    String android_id = Settings.Secure.getString(MainActivity.this.getContentResolver(), "android_id");
                    String KYC_status = Settings.Secure.getString(MainActivity.this.getContentResolver(),"KYC_status");
                    MainActivity loginActivity = MainActivity.this;

                    loginActivity.getstoreDeviceId(loginActivity.edit_username.getText().toString(), android_id,KYC_status);
                }
            }
        });
        if (this.Flag_Remember.equals(ConstantClass.USERDETAILS.First_Time_Login)) {
            this.edit_username.setText(this.userName);
            this.edit_password.setText(this.passWord);
            this.checkbox_remember.setChecked(true);
        } else {
            this.checkbox_remember.setChecked(false);
        }
        this.checkbox_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.uvapay.activities.LoginActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (MainActivity.this.checkbox_remember.isChecked()) {
                    PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.FlagRemember, ConstantClass.USERDETAILS.First_Time_Login);
                    return;
                }
                PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.FlagRemember, "false");
                PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.UserName, "");
                PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.UserPassword, "");
            }
        });

        this.text_forgot_password.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.LoginActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                View view = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_forgot_password, (ViewGroup) null);
                ImageView image_cancel = (ImageView) view.findViewById(R.id.image_cancel);
                Button btn_send_password = (Button) view.findViewById(R.id.btn_send_password);
                final EditText edit_user_name = (EditText) view.findViewById(R.id.edit_user_name);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setView(view);
                image_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.LoginActivity.3.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        alertDialog.dismiss();
                    }
                });
                btn_send_password.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.LoginActivity.3.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        MainActivity.this.sendOtpToNumber(edit_user_name.getText().toString());

                    }
                });
                if (Build.VERSION.SDK_INT >= 21) {
                    alertDialog.create();
                    alertDialog.show();
                }
            }
        });
        this.show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.uvapay.activities.LoginActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    MainActivity.this.edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    MainActivity.this.edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


//            private void getloinuserdata() {
//                ApiInterface apiInterface = RetrofitHandler.getnewclient();
//                HashMap<String, String> body = new HashMap<>();
//                body.put("UserName","9372879094");
//                body.put("Password","OGRlMzQxYWQ5YmE4ODFjMDVlNjNjMzQwNGI4OWVkNjNiMDYyZDJiMzYwZmY5NTAxNWZhMmJlNzJlMDRkYmM2NQ==");
//                body.put("DeviceId","8c3e0df221d385e7");
//                Call<LoginResponse> call = apiInterface.getLoginInfo(body);
//                call.enqueue(new Callback<LoginResponse>() {
//                    @Override
//                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                        Log.e("TAG", "onResponse: "+response.body().data );
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginResponse> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//
//            }
    }

    private void DisplayPrivacyPolicy() {
        View view = getLayoutInflater().inflate(R.layout.activity_privacy_policy, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        ImageView mImage_cancel = (ImageView) view.findViewById(R.id.image_cancel);
        TextView textView = (TextView) view.findViewById(R.id.text_terms);
        final CheckBox mCheck_policy = (CheckBox) view.findViewById(R.id.check_policy);
        TextView mText_policy_link = (TextView) view.findViewById(R.id.text_policy_link);
        Button mButton_proceed = (Button) view.findViewById(R.id.button_proceed);
        mText_policy_link.setText(Html.fromHtml("<a href=\"http://www.soliteck.com/\">Privacy Policy Details</a>"));
        mText_policy_link.setClickable(true);
        mText_policy_link.setMovementMethod(LinkMovementMethod.getInstance());
        alertDialog.setView(view);
        alertDialog.setCanceledOnTouchOutside(false);
        mImage_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.LoginActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        mButton_proceed.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.LoginActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!mCheck_policy.isChecked()) {
                    ApplicationConstant.DisplayMessageDialog(MainActivity.this, "User Privacy Policy", "Agree to privacy Policy details first");
                    return;
                }
                alertDialog.dismiss();
                PrefUtils.saveToPrefs(MainActivity.this, ApplicationConstant.PRIVACY_POLICY, "AGREE");
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Thank you for going through our privacy policy details and accepting it");
                builder1.setCancelable(true);
                builder1.setTitle("Privacy Policy");
                builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() { // from class: com.uvapay.activities.LoginActivity.17.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        MainActivity.this.checkAndRequestPermissions();
                    }
                });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        alertDialog.show();
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != 0 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0 || ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS") != 0 || ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") != 0 || ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA") && ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE") && ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE") && ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_PHONE_STATE") && ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_CONTACTS")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton(HttpHeaders.ALLOW, new DialogInterface.OnClickListener() { // from class: com.uvapay.activities.LoginActivity.6
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.alertDialog.dismiss();
                        MainActivity loginActivity = MainActivity.this;
                        ActivityCompat.requestPermissions(loginActivity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.READ_PHONE_STATE", "android.permission.READ_CONTACTS"},MainActivity.this.READ_PHONE_REQUEST);
                    }
                });
                builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() { // from class: com.uvapay.activities.LoginActivity.7
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.alertDialog.dismiss();
                    }
                });
                AlertDialog create = builder.create();
                this.alertDialog = create;
                create.show();
                this.alertDialog.setCanceledOnTouchOutside(false);
                this.alertDialog.setCancelable(false);
                return;
            }
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.READ_PHONE_STATE", "android.permission.READ_CONTACTS"}, this.READ_PHONE_REQUEST);
        }
    }

    private void sendOtpToNumber(final String mobile) {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        ApiInterface apiInterface = RetrofitHandler.getService();
        Call<OtpSentResponse> call = apiInterface.forgotPasswordMethod("ForgotPassword?username=" + mobile);
        call.enqueue(new Callback<OtpSentResponse>() { // from class: com.uvapay.activities.LoginActivity.11
            @Override // retrofit2.Callback
            public void onResponse(Call<OtpSentResponse> call2, Response<OtpSentResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusCode().toString().equals("0")) {
                        if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                            MainActivity.this.progressDialog.dismiss();
                        }
                        ConstantClass.displayMessageDialog(MainActivity.this, "", response.body().getMessage());
                        return;
                    } else if (response.body().getStatusCode().toString().equals(ConstantClass.MOBILESERVICEID)) {
                        if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                            MainActivity.this.progressDialog.dismiss();
                        }
                        ConstantClass.displayToastMessage(MainActivity.this, response.body().getMessage());
                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.UserName, mobile);
                        Intent intent = new Intent(MainActivity.this, OtpVerificationActivity.class);
                        intent.putExtra("control", "forgot_password");
                        MainActivity.this.startActivity(intent);
                        return;
                    } else {
                        if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                            MainActivity.this.progressDialog.dismiss();
                        }
                        ConstantClass.displayMessageDialog(MainActivity.this, "", response.body().getMessage());
                        return;
                    }
                }
                if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                    MainActivity.this.progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(MainActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<OtpSentResponse> call2, Throwable t) {
                if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                    MainActivity.this.progressDialog.dismiss();
                }
                MainActivity loginActivity = MainActivity.this;
                ConstantClass.displayMessageDialog(loginActivity, loginActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });

    }

    private void getstoreDeviceId(String username, String android_id,String KYC_status) {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
        body.put("DeviceId", android_id);
        body.put("KYCStatus",KYC_status);
        ApiInterface apiInterface = RetrofitHandler.getService();
        Call<DeviceIdResponse> call = apiInterface.getStoreDeviceId(body);
        call.enqueue(new Callback<DeviceIdResponse>() { // from class: com.uvapay.activities.LoginActivity.5
            @Override // retrofit2.Callback
            public void onResponse(Call<DeviceIdResponse> call2, Response<DeviceIdResponse> response) {
                if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                    MainActivity.this.progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getResponseStatus().equals(ConstantClass.MOBILESERVICEID)) {
                        String message = MainActivity.this.edit_password.getText().toString().trim();
                        String EncodedPass = ApplicationConstant.EncodeStringToHMACSHA256(message);
                        String Kyc_status = ConstantClass.PROFILEDETAILS.KYCStatus.toString().trim();
                        MainActivity.this.getloginUser(username, EncodedPass, android_id,Kyc_status);
                        return;
                    }
                    ApplicationConstant.DisplayMessageDialog(MainActivity.this, "Response", response.body().getRemarks());
                    return;
                }
//                if (response.body().getResponseStatus().equals(0)){
//                    Intent intent = new Intent(MainActivity.this, Registration_page.class);
//                    startActivity(intent);
//                }
                if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                    MainActivity.this.progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(MainActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<DeviceIdResponse> call2, Throwable t) {
                if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                    MainActivity.this.progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(MainActivity.this, "Response", t.getMessage().toString());
            }
        });
    }

    private void getloginUser(String username, final String password, final String picDeviceID,final String Kyc_status) {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("UserName", username);
        body.put("Password", password);
        body.put("DeviceId", picDeviceID);
        body.put("KYC_status",Kyc_status);
        ApiInterface apiInterface = RetrofitHandler.getService();
        Call<LoginResponse> call = apiInterface.getLoginInfo(body);

        call.enqueue(new Callback<LoginResponse>() { // from class: com.uvapay.activities.LoginActivity.10
            @Override // retrofit2.Callback
            public void onResponse(Call<LoginResponse> call2, Response<LoginResponse> response) {
                JSONException e;
                if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                    MainActivity.this.progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (!response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                        if (response.body().getStatusCode().equals("2")) {
                            ApplicationConstant.DisplayMessageDialog(MainActivity.this, "Error", response.body().getMessage());
                            return;
                        } else if (response.body().getData().equals("3")) {
                            ApplicationConstant.DisplayMessageDialog(MainActivity.this, "Error", response.body().getMessage());
                            return;
                        } else {
                            ApplicationConstant.DisplayMessageDialog(MainActivity.this, "Error", response.body().getMessage());
                            return;
                        }
                    }
                    ApplicationConstant.displayToastMessage(MainActivity.this,"Response", response.body().getMessage());
                    String jsonString = response.body().getData();

                    try {
                        JSONArray jsonArray = new JSONArray(jsonString);
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String username2 = MainActivity.this.edit_username.getText().toString();
                            try {
                                String toekn = jsonObject.getString("Token");
                                try {
                                    jsonObject.getString(ConstantClass.PROFILEDETAILS.KYCStatus);
                                    String UserPassword = password;
                                    PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.UserName, username2);
                                    PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.Token, toekn);
                                    PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.UserPassword, UserPassword);
                                    MainActivity MainActivity = MainActivity.this;
                                    try {
                                        String username3 = MainActivity.edit_password.getText().toString();
                                        PrefUtils.saveToPrefs(MainActivity, ConstantClass.USERDETAILS.LoginPassword, username3);
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.DeviceId, picDeviceID);
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.Name, jsonObject.getString("FirstName"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.Middle, jsonObject.getString("MiddleName"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.Last, jsonObject.getString("LastName"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.PanCard, jsonObject.getString("PanCardNo"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.MobileNo, jsonObject.getString("ContactNo"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.EmailId, jsonObject.getString("EmailID"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.ShopAddress, jsonObject.getString(ConstantClass.PROFILEDETAILS.ShopAddress));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.PermanentAddress, jsonObject.getString(ConstantClass.PROFILEDETAILS.PermanentAddress));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.PinCode, jsonObject.getString(ConstantClass.PROFILEDETAILS.PinCode));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.StateName, jsonObject.getString("StatesName"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.MainBalance, jsonObject.getString("Balance"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.CityName, jsonObject.getString(ConstantClass.PROFILEDETAILS.CityName));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.KYCStatus, jsonObject.getString(ConstantClass.PROFILEDETAILS.KYCStatus));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.PROFILE_IMAGE, jsonObject.getString("ProfileImage"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.SchemeId, jsonObject.getString("SchemeId"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.WhiteUser, jsonObject.getString("WhitelabelID"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.IsTerms, jsonObject.getString("IsTerms"));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.AEPSBalance, jsonObject.getString(ConstantClass.USERDETAILS.AEPSBalance));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.FirmName, jsonObject.getString(ConstantClass.PROFILEDETAILS.FirmName));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.PROFILEDETAILS.ShopName, jsonObject.getString(ConstantClass.PROFILEDETAILS.ShopName));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.News, jsonObject.getString(ConstantClass.USERDETAILS.News));
                                        PrefUtils.saveToPrefs(MainActivity.this, ConstantClass.USERDETAILS.UserType, jsonObject.getString("RoleId"));
                                        Integer roleId = Integer.valueOf(jsonObject.getInt("RoleId"));
                                        Integer Kyc_status = Integer.valueOf(jsonObject.getInt("KYCStatus"));
                                        if (roleId.intValue() == 2) {
                                            MainActivity.this.startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                                            MainActivity.this.finish();
                                        }
                                    } catch (JSONException e2) {
                                        e = e2;
                                        e.printStackTrace();
                                    }
                                } catch (JSONException e5) {
                                    e = e5;
                                }
                            } catch (JSONException e6) {
                                e = e6;
                            }
                        } catch (JSONException e7) {
                            e = e7;
                        }
                    } catch (JSONException e8) {
                        e = e8;
                    }
                } else {
                    if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                        MainActivity.this.progressDialog.dismiss();
                    }
                    try {
                        ConstantClass.displayMessageDialog(MainActivity.this, "Response", response.errorBody().string());
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<LoginResponse> call2, Throwable t) {
                if (MainActivity.this.progressDialog != null && MainActivity.this.progressDialog.isShowing()) {
                    MainActivity.this.progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(MainActivity.this, "Response", t.getMessage().toString());
            }
        });
    }

}


//    @Override // android.app.Activity
//    protected void onResume() {
//        super.onResume();
////        checkApplicationVersion();
//    }

//    private void checkApplicationVersion() {
//        PackageManager manager = getPackageManager();
//        try {
//            final PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
//            HashMap<String, String> body = new HashMap<>();
//            body.put("data", info.packageName);
//            ApiInterface apiInterface = RetrofitHandler.getService();
//            Call<SoftVersionResponse> result = apiInterface.AppVersionCheck(body);
//            result.enqueue(new Callback<SoftVersionResponse>() { // from class: com.uvapay.activities.LoginActivity.15
//                @Override // retrofit2.Callback
//                public void onResponse(Call<SoftVersionResponse> call, Response<SoftVersionResponse> response) {
//                    String versionCheckRes;
//
//                    if (response.body() != null && response.body().getResponseStatus().equals(ConstantClass.MOBILESERVICEID) && response.body().getData() != null && (versionCheckRes = response.body().getData().toString()) != null && versionCheckRes != null && Double.parseDouble(versionCheckRes) > Double.parseDouble(info.versionName)) {
//                        final Dialog dialog = new Dialog(MainActivity.this);
//                        dialog.requestWindowFeature(1);
//                        dialog.setCancelable(false);
//                        dialog.setContentView(R.layout.appupdate);
//                        Button dialogButton = (Button) dialog.findViewById(R.id.btnUpdate);
//                        dialogButton.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.LoginActivity.15.1
//                            @Override // android.view.View.OnClickListener
//                            public void onClick(View v) {
//                                dialog.dismiss();
//                                try {
//                                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + info.packageName)));
//                                } catch (ActivityNotFoundException e) {
//                                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + info.packageName)));
//                                }
//                            }
//                        });
//                        dialog.show();
//                    }
//                }
//
//                @Override // retrofit2.Callback
//                public void onFailure(Call<SoftVersionResponse> call, Throwable t) {
//                    ApplicationConstant.displayToastMessage(MainActivity.this, t.getMessage().toString());
//                }
//            });
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


//}




