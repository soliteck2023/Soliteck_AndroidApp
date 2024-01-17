package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEarningReportActivity2 extends AppCompatActivity {
    String Oprator = "0";
    private LinearLayout layout_fromdate;
    private LinearLayout layout_todate;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private RecyclerView recycle_transactions;
//    private List<PaymentMode> reportList;
    private List<MyEarningTransaction> reportList;
    Spinner spinner_oprator;
    private TextView text_fromdate;
    private TextView text_no_content;
    private EditText text_search;
    private EditText text_todate;
    private EarningReportAdapter transactionBillAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_earning_report2);
        setTitle("My Earning Report");
        initComponents();
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
        this.text_fromdate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.recycle_transactions.setLayoutManager(new LinearLayoutManager(this));
//        getTransactionReport(this.text_fromdate.getText().toString(), this.Oprator);
        

        Spinner spinner_oprator = findViewById(R.id.oprator_sp);
        String[] states = getResources().getStringArray(R.array.earningoperator);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_oprator.setAdapter(adapter);
        spinner_oprator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selected state here
                String selectedState = states[position];
//                Toast.makeText(MobileRechargeActivity.this, "Selected State: " + selectedState, Toast.LENGTH_SHORT).show();
                // Do something with the selected state
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        this.text_fromdate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.MyEarningReportActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(MyEarningReportActivity2.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.MyEarningReportActivity.1.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        MyEarningReportActivity2.this.text_fromdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        MyEarningReportActivity2.this.text_fromdate.setError(null);
                        MyEarningReportActivity2.this.getTransactionReport(MyEarningReportActivity2.this.text_fromdate.getText().toString(), MyEarningReportActivity2.this.Oprator);}
                }, MyEarningReportActivity2.this.mYear, MyEarningReportActivity2.this.mMonth, MyEarningReportActivity2.this.mDay);
                datePickerDialog.show();
            }
        });

    }

    public void getTransactionReport(String fromDate, String Oprator) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("FromDateTime", fromDate);
        body.put("OperaterId", Oprator);
        ApiInterface apiInterface = RetrofitHandler.getService();
        Call<MyEarningReportBase> result = apiInterface.GetMyEarning(body);
        result.enqueue(new Callback<MyEarningReportBase>() { // from class: com.uvapay.activities.MyEarningReportActivity.4
            @Override // retrofit2.Callback
            public void onResponse(Call<MyEarningReportBase> call, Response<MyEarningReportBase> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    if (response.body().getResponseStatus().intValue() == 1) {
                        if (!response.body().getTransaction().isEmpty()) {
                            MyEarningReportActivity2.this.recycle_transactions.setVisibility(View.VISIBLE);
                            MyEarningReportActivity2.this.text_no_content.setVisibility(View.GONE);
                            MyEarningReportActivity2.this.reportList = response.body().getTransaction();
                            MyEarningReportActivity2 myEarningReportActivity2 = MyEarningReportActivity2.this;
                            myEarningReportActivity2.transactionBillAdapter = new EarningReportAdapter(myEarningReportActivity2, myEarningReportActivity2.reportList);
                            MyEarningReportActivity2.this.recycle_transactions.setAdapter(MyEarningReportActivity2.this.transactionBillAdapter);
                            return;
                        }
                        MyEarningReportActivity2.this.recycle_transactions.setVisibility(View.GONE);
                        MyEarningReportActivity2.this.text_no_content.setVisibility(View.VISIBLE);
                        return;
                    }
                    MyEarningReportActivity2.this.recycle_transactions.setVisibility(View.GONE);
                    MyEarningReportActivity2.this.text_no_content.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MyEarningReportBase> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ApplicationConstant.DisplayMessageDialog(MyEarningReportActivity2.this, "Response", t.getMessage());
            }
        });
    }

//    private void getoprtaorlist() {
//        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
//        progressDialog.show();
//        HashMap<String, String> body = new HashMap<>();
//        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
//        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
//        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
//        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
//        body.put("OperaterId", Oprator);
//        ApiInterface apiservice =RetrofitHandler.getService2();
//        Call<PaymentModeResponse> result = apiservice.GetopratorEarning(body);
//
//        result.enqueue(new Callback<PaymentModeResponse>() { // from class: com.uvapay.activities.MyEarningReportActivity.4
//            @Override // retrofit2.Callback
//            public void onResponse(Call<PaymentModeResponse> call, Response<PaymentModeResponse> response) {
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                try {
//                    if (response.body().getResponseStatus().intValue() == 1) {
//                        if (!response.body().getPaymentMode())) {
//                            MyEarningReportActivity2.this.recycle_transactions.setVisibility(View.VISIBLE);
//                            MyEarningReportActivity2.this.text_no_content.setVisibility(View.INVISIBLE);
//                            MyEarningReportActivity2.this.reportList = response.body().getPaymentMode();
//                            MyEarningReportActivity2 myEarningReportActivity = MyEarningReportActivity2.this;
////                            MyEarningReportActivity myEarningReportActivity2 = MyEarningReportActivity.this;
//                            myEarningReportActivity.transactionBillAdapter = new EarningReportAdapter(myEarningReportActivity, myEarningReportActivity.reportList);
//                            MyEarningReportActivity2.this.recycle_transactions.setAdapter(MyEarningReportActivity2.this.transactionBillAdapter);
//                            return;
//                        }
//                        MyEarningReportActivity2.this.recycle_transactions.setVisibility(View.INVISIBLE);
//                        MyEarningReportActivity2.this.text_no_content.setVisibility(View.VISIBLE);
//                        return;
//                    }
//                    MyEarningReportActivity2.this.recycle_transactions.setVisibility(View.INVISIBLE);
//                    MyEarningReportActivity2.this.text_no_content.setVisibility(View.VISIBLE);
//                } catch (Exception e) {
//                }
//            }
//
//            @Override // retrofit2.Callback
//            public void onFailure(Call<PaymentModeResponse> call, Throwable t) {
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                ApplicationConstant.DisplayMessageDialog(MyEarningReportActivity2.this, "Response", t.getMessage());
//            }
//        });
//
//
//    }

    private void initComponents() {
        this.recycle_transactions = (RecyclerView) findViewById(R.id.recycle_transactions);
        this.layout_fromdate = (LinearLayout) findViewById(R.id.layout_fromdate);
        this.text_fromdate = (TextView) findViewById(R.id.text_fromdate);
        this.text_no_content = (TextView) findViewById(R.id.text_no_content);
//        this.text_search = (EditText) findViewById(R.id.text_search);
        this.spinner_oprator = (Spinner) findViewById(R.id.oprator_sp);
    }
}