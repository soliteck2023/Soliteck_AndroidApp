package com.example.api_call;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class raisecompliant extends AppCompatActivity {

    TextView Date;
    EditText discription;
    Button summit, cancel;
    String API_DESCRIPTION;
    String concatenatedValue;
    private List<compaintlist> listSatetments;
    private String TXNID = "";
    private String _ = "";
    private String Describe = "";
    private String text_subject = "";
    String amt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raisecompliant);

        Date = findViewById(R.id.date);
        discription = findViewById(R.id.edit_msg);
        summit = findViewById(R.id.btn_submit);
        cancel = findViewById(R.id.btn_cancel);

        TextView transactionNumberTextView = findViewById(R.id.text_txn);
        TextView Date = findViewById(R.id.date);
        String rawDate = getIntent().getStringExtra("Date");
        LocalDateTime dateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateTime = LocalDateTime.parse(rawDate, DateTimeFormatter.ISO_DATE_TIME);
        }
        DateTimeFormatter outputFormatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        }
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = dateTime.format(outputFormatter);
        }

        Date.setText("Date: " + formattedDate);
        transactionNumberTextView.setText("Txn No : " + (getIntent().getExtras().getString("TxnId")));

        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rasiecomplaint();
            }
        });
    }

    private void rasiecomplaint() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(raisecompliant.this);
        progressDialog.show();
        String deviceId = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        String uniqueCode = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", deviceId);
        body.put("Token", token);
        body.put("Description", getIntent().getExtras().getString("TxnId") + "-" + discription.getText().toString().trim() + "-" + (getIntent().getExtras().getString("amt")));
        body.put("UniqueCode", uniqueCode);
        body.put("ComplaintType", ConstantClass.MOBILESERVICEID);

        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<ComplaintResponse> call = apiservice.Getcomplaintraise(body);

        call.enqueue(new Callback<ComplaintResponse>() {
            @Override
            public void onResponse(Call<ComplaintResponse> call, Response<ComplaintResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }

                if (response.isSuccessful() && response.body() != null) {
                    ComplaintResponse complaintResponse = response.body();
                    List<compaintlist> complaintList = complaintResponse.getData();
                    onSupportNavigateUp();
                    Toast.makeText(raisecompliant.this, "Complaint Raise Successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ComplaintResponse> call, Throwable t) {
                Toast.makeText(raisecompliant.this, "Error", Toast.LENGTH_SHORT).show();
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(raisecompliant.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

//    private void rasiecomplaint() {
//        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(raisecompliant.this);
//        progressDialog.show();
//        HashMap<String, String> body = new HashMap<>();
//        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
//        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
//        body.put("Description", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.TransactionIds, ""));
//        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
//        body.put("ComplaintType",ConstantClass.MOBILESERVICEID);
////        body.put("Description",PrefUtils.getFromPrefs(this,ConstantClass.USERDETAILS.TransactionIds,""));
//        ApiInterface apiservice = RetrofitHandler.getService2();
//        Call<raisecomplaint> call = apiservice.Getcomplaintraise(body);
//
//        call.enqueue(new Callback<raisecomplaint>() {
//            @Override
//            public void onResponse(Call<raisecomplaint> call, Response<raisecomplaint> response) {
//
////                Toast.makeText(raisecompliant.this, "Response", Toast.LENGTH_SHORT).show();
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                if (response.body().getResponseStatus().intValue() == 1){
//                    if (!response.body().getTransaction().isEmpty()){
//
//
//                        raisecompliant.this.listSatetments = response.body().getTransaction();
//                        List<compaintlist> earndata = listSatetments;
////                        for (int i = 0; i < earndata.size(); i++){
//////                            raisecompliant.this.Txn_no.setText("Txn Id: " + earndata.get(i).getDescription());
////                            raisecompliant.this.Date.setText("Date: " + earndata.get(i).getCreatedDate());
////
////
////                        }
//
//                    }else {
//                        ConstantClass.displayMessageDialog(raisecompliant.this, "Response", response.body().getResponseStatus().toString());
//
//                    }
//                }
//                return;
//
//            }
//
//            @Override
//            public void onFailure(Call<raisecomplaint> call, Throwable t) {
//                Toast.makeText(raisecompliant.this, "Error", Toast.LENGTH_SHORT).show();
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                raisecompliant paymentRequestActivity = raisecompliant.this;
//                ConstantClass.displayMessageDialog(paymentRequestActivity, paymentRequestActivity.getString(R.string.server_problem), t.getMessage().toString());
//            }
//        });
//
//
//
//
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}