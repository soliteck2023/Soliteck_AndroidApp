package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsReportActivity extends AppCompatActivity {
    private LinearLayout layout_fromdate;
    private LinearLayout layout_todate;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private RecyclerView recycle_transactions;
    private List<TransactionReport> reportList;
    private EditText text_fromdate;
    private TextView text_no_content;
    private EditText text_search;
    private EditText text_todate;

    private SearchView searchView;

    private ImageView image_fromdate;

    private TransactionReportAdapter transactionBillAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_report);




        setTitle("Transaction Report");
        initComponents();
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
        this.text_fromdate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.text_todate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.recycle_transactions.setLayoutManager(new LinearLayoutManager(this));

        this.text_fromdate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.TransactionsReportActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(TransactionsReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.TransactionsReportActivity.1.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        TransactionsReportActivity.this.text_fromdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        TransactionsReportActivity.this.text_fromdate.setError(null);
                        TransactionsReportActivity.this.getTransactionReport(TransactionsReportActivity.this.text_fromdate.getText().toString(), TransactionsReportActivity.this.text_todate.getText().toString());
//                        if (!TransactionsReportActivity.this.text_todate.getText().toString().isEmpty()) {
//                            if (!ConstantClass.isNetworkAvailable(TransactionsReportActivity.this)) {
//                                ConstantClass.displayMessageDialog(TransactionsReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                            } else {
//                                TransactionsReportActivity.this.getTransactionReport(TransactionsReportActivity.this.text_fromdate.getText().toString(), TransactionsReportActivity.this.text_todate.getText().toString());
//                            }
//                        }
                    }
                }, TransactionsReportActivity.this.mYear, TransactionsReportActivity.this.mMonth, TransactionsReportActivity.this.mDay);
                datePickerDialog.show();
            }
        });
        image_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(TransactionsReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.TransactionsReportActivity.1.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        TransactionsReportActivity.this.text_fromdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        TransactionsReportActivity.this.text_fromdate.setError(null);
                        TransactionsReportActivity.this.getTransactionReport(TransactionsReportActivity.this.text_fromdate.getText().toString(), TransactionsReportActivity.this.text_todate.getText().toString());
//                        if (!TransactionsReportActivity.this.text_todate.getText().toString().isEmpty()) {
//                            if (!ConstantClass.isNetworkAvailable(TransactionsReportActivity.this)) {
//                                ConstantClass.displayMessageDialog(TransactionsReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                            } else {
//                                TransactionsReportActivity.this.getTransactionReport(TransactionsReportActivity.this.text_fromdate.getText().toString(), TransactionsReportActivity.this.text_todate.getText().toString());
//                            }
//                        }
                    }
                }, TransactionsReportActivity.this.mYear, TransactionsReportActivity.this.mMonth, TransactionsReportActivity.this.mDay);
                datePickerDialog.show();



            }
        });


        this.text_todate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.TransactionsReportActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(TransactionsReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.TransactionsReportActivity.2.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (TransactionsReportActivity.this.text_fromdate.getText().toString().isEmpty()) {
                            TransactionsReportActivity.this.text_fromdate.setError("Select from date first");
                            TransactionsReportActivity.this.text_fromdate.requestFocus();
                            return;
                        }
                        TransactionsReportActivity.this.text_todate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        if (!ConstantClass.isNetworkAvailable(TransactionsReportActivity.this)) {
                            ConstantClass.displayMessageDialog(TransactionsReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
                        } else {
                            TransactionsReportActivity.this.getTransactionReport(TransactionsReportActivity.this.text_fromdate.getText().toString(), TransactionsReportActivity.this.text_todate.getText().toString());
                        }
                    }
                }, TransactionsReportActivity.this.mYear, TransactionsReportActivity.this.mMonth, TransactionsReportActivity.this.mDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            }
        });

        this.text_todate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.TransactionsReportActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(TransactionsReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.TransactionsReportActivity.2.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (TransactionsReportActivity.this.text_fromdate.getText().toString().isEmpty()) {
                            TransactionsReportActivity.this.text_fromdate.setError("Select from date first");
                            TransactionsReportActivity.this.text_fromdate.requestFocus();
                            return;
                        }
                        TransactionsReportActivity.this.text_todate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        if (!ConstantClass.isNetworkAvailable(TransactionsReportActivity.this)) {
                            ConstantClass.displayMessageDialog(TransactionsReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
                        } else {
                            TransactionsReportActivity.this.getTransactionReport(TransactionsReportActivity.this.text_fromdate.getText().toString(), TransactionsReportActivity.this.text_todate.getText().toString());
                        }
                    }
                }, TransactionsReportActivity.this.mYear, TransactionsReportActivity.this.mMonth, TransactionsReportActivity.this.mDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            }
        });

        this.text_search.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.TransactionsReportActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TransactionsReportActivity.this.filter(charSequence.toString());
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });





    }

    public void filter(String s) {
        try {
            List<TransactionReport> listnew_Banks = new ArrayList<>();
            for (TransactionReport transHistoryData : this.reportList) {
                if (transHistoryData.getRefNumber() != null && transHistoryData.getStatus() != null && transHistoryData.getRetailerNumber() != null && transHistoryData.getSenderMobile() != null && transHistoryData.getOperatorName() != null && (transHistoryData.getRefNumber().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getStatus().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getRetailerNumber().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getSenderMobile().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getOperatorName().toLowerCase().contains(s.toLowerCase()))) {
                    listnew_Banks.add(transHistoryData);
                }
            }
            this.transactionBillAdapter.filter(listnew_Banks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTransactionReport(String fromDate, String toDate) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("MobileNumber", "");
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("FromDateTime", fromDate);
        body.put("ToDateTime", toDate);
        body.put("Amount", "");
        body.put("StatusId", "0");
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<TransactionReportBase> result = apiservice.GetTransactionReport(body);
        result.enqueue(new Callback<TransactionReportBase>() { // from class: com.uvapay.activities.TransactionsReportActivity.4
            @Override // retrofit2.Callback
            public void onResponse(Call<TransactionReportBase> call, Response<TransactionReportBase> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    if (response.body().getResponseStatus().intValue() == 1) {
                        if (!response.body().getTransaction().isEmpty()) {
                            TransactionsReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
                            TransactionsReportActivity.this.text_no_content.setVisibility(View.INVISIBLE);
                            TransactionsReportActivity.this.reportList = response.body().getTransaction();
                            TransactionsReportActivity transactionsReportActivity = TransactionsReportActivity.this;
                            TransactionsReportActivity transactionsReportActivity2 = TransactionsReportActivity.this;
                            transactionsReportActivity.transactionBillAdapter = new TransactionReportAdapter(transactionsReportActivity2, transactionsReportActivity2.reportList);
                            TransactionsReportActivity.this.recycle_transactions.setAdapter(TransactionsReportActivity.this.transactionBillAdapter);
                            return;
                        }
                        TransactionsReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                        TransactionsReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                        return;
                    }
                    TransactionsReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                    TransactionsReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<TransactionReportBase> call, Throwable t) {
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
        this.image_fromdate= (ImageView) findViewById(R.id.image_fromdate);
    }
}