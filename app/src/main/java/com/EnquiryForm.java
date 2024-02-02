package com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.api_call.ApiInterface;
import com.example.api_call.ApplicationConstant;
import com.example.api_call.ConstantClass;
import com.example.api_call.CustomProgressDialog;
import com.example.api_call.PrefUtils;
import com.example.api_call.R;
import com.example.api_call.RetrofitHandler;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnquiryForm extends AppCompatActivity {

    RelativeLayout btn_submit;
    EditText edit_email;
    EditText edit_message;
    EditText edit_mobile;
    EditText edit_name;
    EditText edit_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_form);
        setTitle("Enquiry Now");
        bindView();

        this.btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EnquiryForm.this.edit_name.getText().toString().isEmpty()) {
                    EnquiryForm.this.edit_name.setError("Please enter name");
                    EnquiryForm.this.edit_name.requestFocus();
                } else if (EnquiryForm.this.edit_email.getText().toString().isEmpty()) {
                    EnquiryForm.this.edit_name.setError(null);
                    EnquiryForm.this.edit_email.setError("Please enter email");
                    EnquiryForm.this.edit_email.requestFocus();
                } else if (EnquiryForm.this.edit_mobile.getText().toString().isEmpty()) {
                    EnquiryForm.this.edit_email.setError(null);
                    EnquiryForm.this.edit_mobile.setError("Please enter mobile");
                    EnquiryForm.this.edit_mobile.requestFocus();
                } else if (EnquiryForm.this.edit_mobile.getText().toString().length() < 10) {
                    EnquiryForm.this.edit_email.setError(null);
                    EnquiryForm.this.edit_mobile.setError("Please enter correct mobile");
                    EnquiryForm.this.edit_mobile.requestFocus();
                } else if (EnquiryForm.this.edit_subject.getText().toString().isEmpty()) {
                    EnquiryForm.this.edit_mobile.setError(null);
                    EnquiryForm.this.edit_subject.setError("Please enter subject");
                    EnquiryForm.this.edit_subject.requestFocus();
                } else if (EnquiryForm.this.edit_message.getText().toString().isEmpty()) {
                    EnquiryForm.this.edit_subject.setError(null);
                    EnquiryForm.this.edit_message.setError("Please enter message");
                    EnquiryForm.this.edit_message.requestFocus();
                } else {
                    EnquiryForm enquiryForm = EnquiryForm.this;
                    enquiryForm.getEnquiryNow(enquiryForm.edit_name.getText().toString(), EnquiryForm.this.edit_email.getText().toString(), EnquiryForm.this.edit_mobile.getText().toString(), EnquiryForm.this.edit_subject.getText().toString(), EnquiryForm.this.edit_message.getText().toString());
                }
            }
        });

    }

    private void bindView() {
        this.edit_name = (EditText) findViewById(R.id.edit_name);
        this.edit_email = (EditText) findViewById(R.id.edit_email);
        this.edit_mobile = (EditText) findViewById(R.id.edit_mobile);
        this.edit_subject = (EditText) findViewById(R.id.edit_subject);
        this.edit_message = (EditText) findViewById(R.id.edit_message);
        this.btn_submit = (RelativeLayout) findViewById(R.id.btn_submit);
    }
    public void getEnquiryNow(String name, String email, String mobile, String subject, String message) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        String password = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
        body.put("Password", password);
        body.put("EmailID", email);
        body.put("ContactNo", mobile);
        body.put("Messge", message);
        body.put("Subject", subject);
        body.put("Name", name);

        ApiInterface apiservice = RetrofitHandler.getService();

        Call<ContactUsResponse> call = apiservice.getEnquiry(body);
        call.enqueue(new Callback<ContactUsResponse>() {
            @Override
            public void onResponse(Call<ContactUsResponse> call2, Response<ContactUsResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                        ApplicationConstant.DisplayMessageDialog(EnquiryForm.this, "Response", response.body().getMessage());
                        return;
                    } else {
                        ApplicationConstant.DisplayMessageDialog(EnquiryForm.this, "Response", response.body().getMessage());
                        return;
                    }
                }
                ApplicationConstant.DisplayMessageDialog(EnquiryForm.this, "Response", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ContactUsResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ApplicationConstant.DisplayMessageDialog(EnquiryForm.this, "Response", t.getMessage());
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}