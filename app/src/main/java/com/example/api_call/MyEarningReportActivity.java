package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEarningReportActivity extends AppCompatActivity {

    String Oprator = "0";
    private LinearLayout layout_fromdate;
    private LinearLayout layout_todate;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private RecyclerView recycle_transactions;
    private List<MyEarningTransaction> reportList;
    Spinner spinner_oprator;
    private EditText text_fromdate;
    private TextView text_no_content;
    private EditText text_search;
    private EditText text_todate;
    private EarningReportAdapter transactionBillAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_earning_report);
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

        Spinner stateSpinner = findViewById(R.id.spinner_oprator);
        String[] states = getResources().getStringArray(R.array.operator);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);
        this.text_fromdate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.MyEarningReportActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(MyEarningReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.MyEarningReportActivity.1.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        MyEarningReportActivity.this.text_fromdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        MyEarningReportActivity.this.text_fromdate.setError(null);

                        MyEarningReportActivity.this.getTransactionReport(MyEarningReportActivity.this.text_fromdate.getText().toString(), MyEarningReportActivity.this.Oprator);}
                }, MyEarningReportActivity.this.mYear, MyEarningReportActivity.this.mMonth, MyEarningReportActivity.this.mDay);
                datePickerDialog.show();
            }
        });

//        this.spinner_oprator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.uvapay.activities.MyEarningReportActivity.2
//            @Override // android.widget.AdapterView.OnItemSelectedListener
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                MyEarningReportActivity myEarningReportActivity = MyEarningReportActivity.this;
//                myEarningReportActivity.Oprator = myEarningReportActivity.spinner_oprator.getSelectedItem().toString();
////                if (!ConstantClass.isNetworkAvailable(MyEarningReportActivity.this)) {
////                    ConstantClass.displayMessageDialog(MyEarningReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
////                    return;
////                }
//                MyEarningReportActivity myEarningReportActivity2 = MyEarningReportActivity.this;
//                myEarningReportActivity2.getTransactionReport(myEarningReportActivity2.text_fromdate.getText().toString(), MyEarningReportActivity.this.Oprator);
//            }
//
//            @Override // android.widget.AdapterView.OnItemSelectedListener
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
        this.text_search.setVisibility(View.GONE);
        this.text_search.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.MyEarningReportActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });


    }

    private void getTransactionReport(String fromDate, String Oprator) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("FromDateTime", fromDate);
        body.put("OperaterId", Oprator);
        ApiInterface apiservice =RetrofitHandler.getService();
        Call<MyEarningReportBase> result = apiservice.GetMyEarning(body);
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
                            MyEarningReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
                            MyEarningReportActivity.this.text_no_content.setVisibility(View.INVISIBLE);
                            MyEarningReportActivity.this.reportList = response.body().getTransaction();
                            MyEarningReportActivity myEarningReportActivity = MyEarningReportActivity.this;
//                            MyEarningReportActivity myEarningReportActivity2 = MyEarningReportActivity.this;
                            myEarningReportActivity.transactionBillAdapter = new EarningReportAdapter(myEarningReportActivity, myEarningReportActivity.reportList);
                            MyEarningReportActivity.this.recycle_transactions.setAdapter(MyEarningReportActivity.this.transactionBillAdapter);
                            return;
                        }
                        MyEarningReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                        MyEarningReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                        return;
                    }
                    MyEarningReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                    MyEarningReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MyEarningReportBase> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ApplicationConstant.DisplayMessageDialog(MyEarningReportActivity.this, "Response", t.getMessage());
            }
        });
    }

    private void initComponents() {
        this.recycle_transactions = (RecyclerView) findViewById(R.id.recycle_transactions);
        this.layout_fromdate = (LinearLayout) findViewById(R.id.layout_fromdate);
        this.text_fromdate = (EditText) findViewById(R.id.text_fromdate);
        this.text_no_content = (TextView) findViewById(R.id.text_no_content);
        this.text_search = (EditText) findViewById(R.id.text_search);
        this.text_todate = (EditText) findViewById(R.id.text_todate);
        this.spinner_oprator = (Spinner) findViewById(R.id.spinner_oprator);
    }
    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void filter(String s) {
        try {
            List<MyEarningTransaction> listnew_Banks = new ArrayList<>();
            for (MyEarningTransaction myEarningTransaction : this.reportList) {
            }
            this.transactionBillAdapter.filter(listnew_Banks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}