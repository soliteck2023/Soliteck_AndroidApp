package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class raiseAepscompliant extends AppCompatActivity {
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
        setContentView(R.layout.activity_raise_aepscompliant);

        Date = findViewById(R.id.date);
        discription = findViewById(R.id.edit_msg);
        summit = findViewById(R.id.btn_submit);
        cancel = findViewById(R.id.btn_cancel);

        TextView transactionNumberTextView = findViewById(R.id.text_txn);
        TextView Date = findViewById(R.id.date);
//        String rawDate = getIntent().getStringExtra("Date");
//        LocalDateTime dateTime = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            dateTime = LocalDateTime.parse(rawDate, DateTimeFormatter.ISO_DATE_TIME);
//        }
//        DateTimeFormatter outputFormatter = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
//        }
//        String formattedDate = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            formattedDate = dateTime.format(outputFormatter);
//        }

        Date.setText("Date: " + (getIntent().getExtras().getString("Date")));
        transactionNumberTextView.setText("Txn No : " + (getIntent().getExtras().getString("TxnId")));


        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rasiecomplaint();
            }
        });


    }

    private void rasiecomplaint() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(raiseAepscompliant.this);
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
                    Toast.makeText(raiseAepscompliant.this, "Complaint Raise Successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ComplaintResponse> call, Throwable t) {
                Toast.makeText(raiseAepscompliant.this, "Error", Toast.LENGTH_SHORT).show();
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(raiseAepscompliant.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}