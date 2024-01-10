package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LedgerReportActivity extends AppCompatActivity {
    private LinearLayout layout_fromdate;
    private LinearLayout layout_todate;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private RecyclerView recycle_transactions;
    private List<ledgerTransactionReport> reportList;
    private EditText text_fromdate;
    private TextView text_no_content;
    private EditText text_search;
    private EditText text_todate;
    private LedgerReportAdapter transactionBillAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger_report);
        setTitle("Ledger Report");
        initComponents();
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
        this.text_fromdate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.text_todate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.recycle_transactions.setLayoutManager(new LinearLayoutManager(this));
//        getTransactionReport(this.text_fromdate.getText().toString(), this.text_todate.getText().toString());

        this.text_fromdate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.LedgerReportActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(LedgerReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.LedgerReportActivity.1.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        LedgerReportActivity.this.text_fromdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        LedgerReportActivity.this.text_fromdate.setError(null);
                        LedgerReportActivity.this.getTransactionReport(LedgerReportActivity.this.text_fromdate.getText().toString(), LedgerReportActivity.this.text_todate.getText().toString());

//                        if (!LedgerReportActivity.this.text_todate.getText().toString().isEmpty()) {
//                            if (!ConstantClass.isNetworkAvailable(LedgerReportActivity.this)) {
//                                ConstantClass.displayMessageDialog(LedgerReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                            } else {
//                                LedgerReportActivity.this.getTransactionReport(LedgerReportActivity.this.text_fromdate.getText().toString(), LedgerReportActivity.this.text_todate.getText().toString());
//                            }
//                        }
                    }
                }, LedgerReportActivity.this.mYear, LedgerReportActivity.this.mMonth, LedgerReportActivity.this.mDay);
                datePickerDialog.show();
            }
        });

        this.text_todate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.LedgerReportActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(LedgerReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.LedgerReportActivity.2.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (LedgerReportActivity.this.text_fromdate.getText().toString().isEmpty()) {
                            LedgerReportActivity.this.text_fromdate.setError("Select from date first");
                            LedgerReportActivity.this.text_fromdate.requestFocus();
                            return;
                        }
                        LedgerReportActivity.this.text_todate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        LedgerReportActivity.this.getTransactionReport(LedgerReportActivity.this.text_fromdate.getText().toString(), LedgerReportActivity.this.text_todate.getText().toString());

//                        if (!ConstantClass.isNetworkAvailable(LedgerReportActivity.this)) {
//                            ConstantClass.displayMessageDialog(LedgerReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                        } else {
//                            LedgerReportActivity.this.getTransactionReport(LedgerReportActivity.this.text_fromdate.getText().toString(), LedgerReportActivity.this.text_todate.getText().toString());
//                        }
                    }
                }, LedgerReportActivity.this.mYear, LedgerReportActivity.this.mMonth, LedgerReportActivity.this.mDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            }
        });
        this.text_search.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.LedgerReportActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LedgerReportActivity.this.filter(charSequence.toString());
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });


    }

    private void filter(String s) {
        try {
            List<ledgerTransactionReport> listnew_Banks = new ArrayList<>();
            for (ledgerTransactionReport transHistoryData : this.reportList){
                if (transHistoryData.getRefNumber().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getStatus().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getUniqueCode().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getFName().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getShopName().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getTransactionId().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getRefNumber().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getReceiverDetails().toLowerCase().contains(s.toLowerCase())||transHistoryData.getTxnDate().toLowerCase().contains(s.toLowerCase())||transHistoryData.getStatus().toLowerCase().contains(s.toLowerCase())||transHistoryData.getSuccess().toLowerCase().contains(s.toLowerCase())   ||transHistoryData.getAmount().toLowerCase().contains(s.toLowerCase())|| transHistoryData.getCommission().toLowerCase().contains(s.toLowerCase()) ||transHistoryData.getTDS().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getGST().toLowerCase().contains(s.toLowerCase())  || transHistoryData.getServicecharge().toLowerCase().contains(s.toLowerCase())  || transHistoryData.getCR().toLowerCase().contains(s.toLowerCase()) ||transHistoryData.getDR().toLowerCase().contains(s.toLowerCase()) ||  transHistoryData.getMBAfter().toLowerCase().contains(s.toLowerCase()) ||transHistoryData.getMBBefore().toLowerCase().contains(s.toLowerCase())) {
                    listnew_Banks.add(transHistoryData);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

//    private void filter(String s) {
//        try {
//            List<ledgerTransactionReport> listnew_Banks = new ArrayList<>();
//            for (TransactionReport transHistoryData : this.reportList) {
//                if (transHistoryData.getRefNumber().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getStatus().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getRetailerNumber().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getSenderMobile().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getOperatorName().toLowerCase().contains(s.toLowerCase())) {
//                    listnew_Banks.add(transHistoryData);
//                }
//            }
//            this.transactionBillAdapter.filter(listnew_Banks);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void getTransactionReport(String fromDate, String toDate) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
//        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("UserName", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("FromDateTime", fromDate);
        body.put("ToDateTime", toDate);
        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<LedgerReportBase> result = apiservice.GetLedgerReportnew(body);

        result.enqueue(new Callback<LedgerReportBase>() {
            @Override
            public void onResponse(Call<LedgerReportBase> call, Response<LedgerReportBase> response) {

                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body().getResponseStatus().intValue()==1){
                    if (!response.body().getTransaction().isEmpty()){
                     LedgerReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
                     LedgerReportActivity.this.text_no_content.setVisibility(View.GONE);
                     LedgerReportActivity.this.reportList = response.body().getTransaction();
                     LedgerReportActivity ledgerReportActivity = LedgerReportActivity.this;
                     LedgerReportActivity ledgerReportActivity2 = LedgerReportActivity.this;
                     ledgerReportActivity.transactionBillAdapter = new LedgerReportAdapter(ledgerReportActivity2, ledgerReportActivity2.reportList);
                     LedgerReportActivity.this.recycle_transactions.setAdapter(LedgerReportActivity.this.transactionBillAdapter);
                     return;
                    }
                    LedgerReportActivity.this.recycle_transactions.setVisibility(View.GONE);
                    LedgerReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                    return;
                }
                LedgerReportActivity.this.recycle_transactions.setVisibility(View.GONE);
                    LedgerReportActivity.this.text_no_content.setVisibility(View.VISIBLE);


                //                if (response.body().getResponseStatus().intValue()==1) {
//                    // Log the response body or any other relevant information
//                    Log.d("Response", "Response Body: " + response.body());
//                    // Add more logging as needed
//                } else {
//                    // Log the error response if the request was not successful
//                    Log.e("Response", "Error Response: " + response.errorBody());
//                    // Add more logging as needed
//                }



//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                try {
//
//                    if (response.body().getResponseStatus().intValue() == 1) {
//                        if (!response.body().getTransaction().isEmpty()) {
//                            LedgerReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
//                            LedgerReportActivity.this.text_no_content.setVisibility(View.GONE);
//                            LedgerReportActivity.this.reportList = response.body().getTransaction();
//                            LedgerReportActivity ledgerReportActivity = LedgerReportActivity.this;
//                            LedgerReportActivity ledgerReportActivity2 = LedgerReportActivity.this;
//                            ledgerReportActivity.transactionBillAdapter = new LedgerReportAdapter(ledgerReportActivity2, ledgerReportActivity2.reportList);
//                            LedgerReportActivity.this.recycle_transactions.setAdapter(LedgerReportActivity.this.transactionBillAdapter);
//                            return;
//                        }
//                        LedgerReportActivity.this.recycle_transactions.setVisibility(View.GONE);
//                        LedgerReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
//                        return;
//                    }
//                    LedgerReportActivity.this.recycle_transactions.setVisibility(View.GONE);
//                    LedgerReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
//
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }


//                        if (!response.body().getTransaction().isEmpty()) {
//                            LedgerReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
//                            LedgerReportActivity.this.text_no_content.setVisibility(View.GONE);
//                            LedgerReportActivity.this.reportList = response.body().getTransaction();
//                            LedgerReportActivity ledgerReportActivity = LedgerReportActivity.this;
//                            LedgerReportActivity ledgerReportActivity2 = LedgerReportActivity.this;
//                            ledgerReportActivity.transactionBillAdapter = new LedgerReportAdapter(ledgerReportActivity2, ledgerReportActivity2.reportList);
//                            LedgerReportActivity.this.recycle_transactions.setAdapter(LedgerReportActivity.this.transactionBillAdapter);
//                            return;
//                        }
//                        LedgerReportActivity.this.recycle_transactions.setVisibility(View.GONE);
//                        LedgerReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
//                        return;
//
//                    }
//                    LedgerReportActivity.this.recycle_transactions.setVisibility(View.GONE);
//                    LedgerReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }

            }

            @Override
            public void onFailure(Call<LedgerReportBase> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });


//        result.enqueue(new Callback<LedgerReportBase>() {
//            @Override
//            public void onResponse(Call<LedgerReportBase> call, Response<LedgerReportBase> response) {
//                                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                try {
//                    if (response.body().getResponseStatus().intValue() == 1) {
//                        if (!response.body().getTransaction().isEmpty()) {
//                            LedgerReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
//                            LedgerReportActivity.this.text_no_content.setVisibility(View.INVISIBLE);
//                            LedgerReportActivity.this.reportList = response.body().getTransaction();
//                            LedgerReportActivity ledgerReportActivity = LedgerReportActivity.this;
//                            LedgerReportActivity ledgerReportActivity2 = LedgerReportActivity.this;
//                            ledgerReportActivity.transactionBillAdapter = new LedgerReportAdapter(ledgerReportActivity2, ledgerReportActivity2.reportList);
//                            LedgerReportActivity.this.recycle_transactions.setAdapter(LedgerReportActivity.this.transactionBillAdapter);
//                            return;
//                        }
//                        LedgerReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
//                        LedgerReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
//                        return;
//                    }
//                    LedgerReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
//                    LedgerReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
//                } catch (Exception e) {
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LedgerReportBase> call, Throwable t) {
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//            }
//        });



    }
    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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