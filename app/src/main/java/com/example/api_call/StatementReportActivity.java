package com.example.api_call;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementReportActivity extends AppCompatActivity {
    private LinearLayout layout_fromdate;
    private LinearLayout layout_todate;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private RecyclerView recycle_transactions;
    private List<StatementReport> reportList;
    private EditText text_fromdate;
    private TextView text_no_content;
    private EditText text_search;
    private EditText text_todate;
    private StatementsReportAdapter transactionBillAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_report);
        setTitle("Statements Report");
        initComponents();
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
//        this.recycle_transactions.setLayoutManager(new LinearLayoutManager(this));

        this.text_fromdate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.StatementReportActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(StatementReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.StatementReportActivity.1.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        StatementReportActivity.this.text_fromdate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                        StatementReportActivity.this.text_fromdate.setError(null);
                        StatementReportActivity.this.getAllStatements(StatementReportActivity.this.text_fromdate.getText().toString(), StatementReportActivity.this.text_todate.getText().toString());

//                        if (!StatementReportActivity.this.text_todate.getText().toString().isEmpty()) {
//                            if (!ConstantClass.isNetworkAvailable(StatementReportActivity.this)) {
//                                ConstantClass.displayMessageDialog(StatementReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                            } else {
//                                StatementReportActivity.this.getAllStatements(StatementReportActivity.this.text_fromdate.getText().toString(), StatementReportActivity.this.text_todate.getText().toString());
//                            }
//                        }
                    }
                }, StatementReportActivity.this.mYear, StatementReportActivity.this.mMonth, StatementReportActivity.this.mDay);
                datePickerDialog.show();


            }
        });

        this.text_todate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.StatementReportActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(StatementReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.StatementReportActivity.2.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (StatementReportActivity.this.text_fromdate.getText().toString().isEmpty()) {
                            StatementReportActivity.this.text_fromdate.setError("Select from date first");
                            StatementReportActivity.this.text_fromdate.requestFocus();
                            return;
                        }
                        StatementReportActivity.this.text_todate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                        StatementReportActivity.this.getAllStatements(StatementReportActivity.this.text_fromdate.getText().toString(), StatementReportActivity.this.text_todate.getText().toString());

//                        if (!ConstantClass.isNetworkAvailable(StatementReportActivity.this)) {
//                            ConstantClass.displayMessageDialog(StatementReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                        } else {
//                            StatementReportActivity.this.getAllStatements(StatementReportActivity.this.text_fromdate.getText().toString(), StatementReportActivity.this.text_todate.getText().toString());
//                        }
                    }
                }, StatementReportActivity.this.mYear, StatementReportActivity.this.mMonth, StatementReportActivity.this.mDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());

            }
        });




    }

    private void getAllStatements(String fromDate, String toDate) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Password", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, ""));
        body.put("FromDate", fromDate);
        body.put("ToDate", toDate);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<GetStatementReport> result = apiservice.getStatementReport(body);
        result.enqueue(new Callback<GetStatementReport>() { // from class: com.uvapay.activities.StatementReportActivity.4
            @Override // retrofit2.Callback
            public void onResponse(Call<GetStatementReport> call, Response<GetStatementReport> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                GetStatementReport rechargeHistory = response.body();
                try {
                    if (rechargeHistory.getData() != null) {
                        if (rechargeHistory.getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                            StatementReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
                            StatementReportActivity.this.text_no_content.setVisibility(View.GONE);
                            StatementReportActivity.this.reportList = rechargeHistory.getData();
                            StatementReportActivity statementReportActivity = StatementReportActivity.this;
                            StatementReportActivity statementReportActivity2 = StatementReportActivity.this;
                            statementReportActivity.transactionBillAdapter = new StatementsReportAdapter(statementReportActivity2, statementReportActivity2.reportList);
                            StatementReportActivity.this.recycle_transactions.setAdapter(StatementReportActivity.this.transactionBillAdapter);
                        } else {
                            StatementReportActivity.this.recycle_transactions.setVisibility(View.GONE);
                            StatementReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                        }
                    } else {
                        StatementReportActivity.this.recycle_transactions.setVisibility(View.GONE);
                        StatementReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                        StatementReportActivity.this.text_no_content.setText(rechargeHistory.getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<GetStatementReport> call, Throwable t) {
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

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}