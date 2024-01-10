package com.example.api_call;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pendingReceivedReportActivity extends AppCompatActivity {
    private LinearLayout layout_fromdate;
    private LinearLayout layout_todate;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private RecyclerView recycle_transactions;
    private List<NetworkBalanceReceivedReport> reportList;
    private EditText text_fromdate;
    private TextView text_no_content;
    private EditText text_search;
    private EditText text_todate;
    private PaymentReceivedReportAdapter transactionBillAdapter;

    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_received_report);
        setTitle("Pending Transaction Received Report");
        initComponents();
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
        this.text_fromdate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.text_todate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.recycle_transactions.setLayoutManager(new LinearLayoutManager(this));
        getPaymentReceived(this.text_fromdate.getText().toString(), this.text_todate.getText().toString());
        this.text_fromdate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentReceivedReportActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(pendingReceivedReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.PaymentReceivedReportActivity.1.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        pendingReceivedReportActivity.this.text_fromdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        pendingReceivedReportActivity.this.text_fromdate.setError(null);
                        pendingReceivedReportActivity.this.getPaymentReceived(pendingReceivedReportActivity.this.text_fromdate.getText().toString(), pendingReceivedReportActivity.this.text_todate.getText().toString());

//                        if (!PaymentReceivedReportActivity.this.text_todate.getText().toString().isEmpty()) {
//                            if (!ConstantClass.isNetworkAvailable(PaymentReceivedReportActivity.this)) {
//                                ConstantClass.displayMessageDialog(PaymentReceivedReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                            } else {
//                                PaymentReceivedReportActivity.this.getPaymentReceived(PaymentReceivedReportActivity.this.text_fromdate.getText().toString(), PaymentReceivedReportActivity.this.text_todate.getText().toString());
//                            }
//                        }
                    }
                }, pendingReceivedReportActivity.this.mYear, pendingReceivedReportActivity.this.mMonth, pendingReceivedReportActivity.this.mDay);
                datePickerDialog.show();

            }
        });


    }

    private void getPaymentReceived(String fromDate, String toDate) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("UserName", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("FromDateTime", fromDate);
        body.put("ToDateTime", toDate);
        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<PaymentReceivedResponse> result = apiservice.Getpendingreport(body);
        result.enqueue(new Callback<PaymentReceivedResponse>() { // from class: com.uvapay.activities.PaymentReceivedReportActivity.4
            @Override // retrofit2.Callback
            public void onResponse(Call<PaymentReceivedResponse> call, Response<PaymentReceivedResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;

                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    if (response.body().getResponseStatus().intValue() == 1) {
                        if (!response.body().getNetworkBalanceReceivedReport().isEmpty()) {
                            pendingReceivedReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
                            pendingReceivedReportActivity.this.text_no_content.setVisibility(View.INVISIBLE);
                            pendingReceivedReportActivity.this.reportList = response.body().getNetworkBalanceReceivedReport();
                            pendingReceivedReportActivity pendingReceivedReportActivity = pendingReceivedReportActivity.this;
                            pendingReceivedReportActivity paymentReceivedReportActivity2 = pendingReceivedReportActivity.this;
                            pendingReceivedReportActivity.transactionBillAdapter = new PaymentReceivedReportAdapter(paymentReceivedReportActivity2, paymentReceivedReportActivity2.reportList);
                            pendingReceivedReportActivity.this.recycle_transactions.setAdapter(pendingReceivedReportActivity.this.transactionBillAdapter);
                            return;
                        }
                        pendingReceivedReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                        pendingReceivedReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                        return;
                    }

                    pendingReceivedReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                    pendingReceivedReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                }

//                ApplicationConstant.DisplayMessageDialog(pendingReceivedReportActivity.this, "Error", response.body().getRemarks());
//                alertDialog.dismiss();

            }

            @Override // retrofit2.Callback
            public void onFailure(Call<PaymentReceivedResponse> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });


    }

    private void initComponents() {
        this.recycle_transactions = (RecyclerView) findViewById(R.id.recycle_transactions);
        this.layout_fromdate = (LinearLayout) findViewById(R.id.layout_fromdate);
        this.layout_todate = (LinearLayout) findViewById(R.id.layout_todate);
        this.text_fromdate = (EditText) findViewById(R.id.text_fromdate);
        this.text_todate = (EditText) findViewById(R.id.text_todate);
        this.text_no_content = (TextView) findViewById(R.id.text_no_content);
        this.text_search = (EditText) findViewById(R.id.text_search);
    }
}