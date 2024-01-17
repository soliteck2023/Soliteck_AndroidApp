package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayRequestHistoryActivity extends AppCompatActivity {
    private Button btn_search;
    private int mDay;
    private EditText mEdit_search;
    private ImageView mImage_fromdate;
    private ImageView mImage_todate;
    private LinearLayout mLayout_fromdate;
    private LinearLayout mLayout_todate;
    private int mMonth;
    private RecyclerView mRecycle_transactions;
    private TextView mText_fromdate;
    private TextView mText_no_content;
    private TextView mText_todate;
    private int mYear;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_request_history);
        bindViews();
        setTitle("Pay Request History");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
        this.mText_fromdate.setText(this.mYear + "/" + this.mDay + "/" + (this.mMonth + 1));
        this.mText_todate.setText(this.mYear + "/" + this.mDay + "/" + (this.mMonth + 1));
        getAllHistoryRequests(mText_fromdate.getText().toString(), PayRequestHistoryActivity.this.mText_todate.getText().toString());

        this.mImage_fromdate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PayRequestHistoryActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {

                PayRequestHistoryActivity.this.fromDateSelection();
            }
        });

        mText_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDateSelection();
            }
        });




    }


        private void getAllHistoryRequests(String fromDate, String toDate) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("FromDateTime", fromDate);
        body.put("ToDateTime", toDate);
        body.put("StatusId", "0");
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<PaymentRequestHistoryResponse> result = apiservice.GetPaymentRequestHistory(body);
        result.enqueue(new Callback<PaymentRequestHistoryResponse>() { // from class: com.uvapay.activities.PayRequestHistoryActivity.5
            @SuppressLint("WrongConstant")
            @Override // retrofit2.Callback
            public void onResponse(Call<PaymentRequestHistoryResponse> call, Response<PaymentRequestHistoryResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                PaymentRequestHistoryResponse rechargeHistory = response.body();
                if (rechargeHistory.getPaymentRequestHistory() != null && rechargeHistory.getPaymentRequestHistory().size() != 0) {
                    PayRequestHistoryActivity.this.mRecycle_transactions.setVisibility(0);
                    PayRequestHistoryActivity.this.mText_no_content.setVisibility(8);
                    PayRequestHistoryAdapter transactionBillAdapter = new PayRequestHistoryAdapter(PayRequestHistoryActivity.this,rechargeHistory.getPaymentRequestHistory());
                    PayRequestHistoryActivity.this.mRecycle_transactions.setAdapter(transactionBillAdapter);
                    return;
                }
                PayRequestHistoryActivity.this.mText_no_content.setVisibility(0);
                PayRequestHistoryActivity.this.mText_no_content.setText("Not Available");
                PayRequestHistoryActivity.this.mRecycle_transactions.setVisibility(8);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<PaymentRequestHistoryResponse> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                PayRequestHistoryActivity payRequestHistoryActivity = PayRequestHistoryActivity.this;
                ConstantClass.displayMessageDialog(payRequestHistoryActivity, payRequestHistoryActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }


    private void bindViews() {
        this.mLayout_fromdate = (LinearLayout) findViewById(R.id.layout_fromdate);
        this.mText_fromdate = (TextView) findViewById(R.id.text_fromdate);
        this.mImage_fromdate = (ImageView) findViewById(R.id.image_fromdate);
        this.mLayout_todate = (LinearLayout) findViewById(R.id.layout_todate);
        this.mText_todate = (TextView) findViewById(R.id.text_todate);
        this.mImage_todate = (ImageView) findViewById(R.id.image_todate);
        this.mText_no_content = (TextView) findViewById(R.id.text_no_content);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_transactions);
        this.mRecycle_transactions = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//    public  void fromDateSelection(){
//        final Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        // Do something with the selected date
//                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
//                        mText_fromdate.setText(selectedDate);
//                    }
//                }, year, month, dayOfMonth);
//
//        datePickerDialog.show();
//
//    }
    public void fromDateSelection() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() {
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                PayRequestHistoryActivity.this.mText_fromdate.setText(year + "/" + dayOfMonth + "/" + (monthOfYear + 1));
                PayRequestHistoryActivity.this.mText_fromdate.setError(null);
                if (!PayRequestHistoryActivity.this.mText_todate.getText().toString().isEmpty()) {
//                    if (!ConstantClass.isNetworkAvailable(PayRequestHistoryActivity.this)) {
//                        ConstantClass.displayMessageDialog(PayRequestHistoryActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                        return;
//                    }
                    PayRequestHistoryActivity payRequestHistoryActivity = PayRequestHistoryActivity.this;
                    payRequestHistoryActivity.getAllHistoryRequests(payRequestHistoryActivity.mText_fromdate.getText().toString(), PayRequestHistoryActivity.this.mText_todate.getText().toString());
                }
            }
        }, this.mYear, this.mMonth, this.mDay);
        datePickerDialog.show();
    }

    public void toDateSelection() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.PayRequestHistoryActivity.7
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (PayRequestHistoryActivity.this.mText_fromdate.getText().toString().isEmpty()) {
                    PayRequestHistoryActivity.this.mText_fromdate.setError("Select from date first");
                    PayRequestHistoryActivity.this.mText_fromdate.requestFocus();
                    return;
                }
                PayRequestHistoryActivity.this.mText_todate.setText(year + "/" + dayOfMonth + "/" + (monthOfYear + 1));
                if (!ConstantClass.isNetworkAvailable(PayRequestHistoryActivity.this)) {
                    ConstantClass.displayMessageDialog(PayRequestHistoryActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
                    return;
                }
                PayRequestHistoryActivity payRequestHistoryActivity = PayRequestHistoryActivity.this;
                payRequestHistoryActivity.getAllHistoryRequests(payRequestHistoryActivity.mText_fromdate.getText().toString(), PayRequestHistoryActivity.this.mText_todate.getText().toString());
            }
        }, this.mYear, this.mMonth, this.mDay);
        datePickerDialog.show();
//        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
    }

}