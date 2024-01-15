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

public class Ledger_cashouttxn_ReportActivity extends AppCompatActivity {

    private LinearLayout layout_fromdate;
    private LinearLayout layout_todate;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private RecyclerView recycle_transactions;
    private List<cashoutledgerTransactionReport> reportList;
    private EditText text_fromdate;
    private TextView text_no_content;
    private EditText text_search;
    private EditText text_todate;
    private CashoutReceivedReportAdapter transactionBillAdapter;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger_cashouttxn_report);
        setTitle("Ledger Transcation Received Report");
        initComponents();
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
        this.text_fromdate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.text_todate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.recycle_transactions.setLayoutManager(new LinearLayoutManager(this));

        this.text_fromdate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentReceivedReportActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(Ledger_cashouttxn_ReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.PaymentReceivedReportActivity.1.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Ledger_cashouttxn_ReportActivity.this.text_fromdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        Ledger_cashouttxn_ReportActivity.this.text_fromdate.setError(null);
                        Ledger_cashouttxn_ReportActivity.this.getcashoutReceived(Ledger_cashouttxn_ReportActivity.this.text_fromdate.getText().toString(), Ledger_cashouttxn_ReportActivity.this.text_todate.getText().toString());

//                        if (!PaymentReceivedReportActivity.this.text_todate.getText().toString().isEmpty()) {
//                            if (!ConstantClass.isNetworkAvailable(PaymentReceivedReportActivity.this)) {
//                                ConstantClass.displayMessageDialog(PaymentReceivedReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                            } else {
//                                PaymentReceivedReportActivity.this.getPaymentReceived(PaymentReceivedReportActivity.this.text_fromdate.getText().toString(), PaymentReceivedReportActivity.this.text_todate.getText().toString());
//                            }
//                        }
                    }
                }, Ledger_cashouttxn_ReportActivity.this.mYear, Ledger_cashouttxn_ReportActivity.this.mMonth, Ledger_cashouttxn_ReportActivity.this.mDay);
                datePickerDialog.show();

            }
        });



    }

    private void getcashoutReceived(String fromDate, String toDate) {

        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("FromDateTime", fromDate);
        body.put("ToDateTime", toDate);
        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<cashoutbaseResponse> result = apiservice.Getcashout_txnReceived(body);

        result.enqueue(new Callback<cashoutbaseResponse>() {
            @Override
            public void onResponse(Call<cashoutbaseResponse> call, Response<cashoutbaseResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    if (response.body().getResponseStatus().intValue() == 1){
                        if (!response.body().getCashoutledgerTransactionReports().isEmpty()){
                            Ledger_cashouttxn_ReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
                            Ledger_cashouttxn_ReportActivity.this.text_no_content.setVisibility(View.INVISIBLE);
                            Ledger_cashouttxn_ReportActivity.this.reportList = response.body().getCashoutledgerTransactionReports();
                            Ledger_cashouttxn_ReportActivity ledger_cashouttxn_ReportActivity = Ledger_cashouttxn_ReportActivity.this;
//                            Ledger_cashouttxn_ReportActivity Ledger_cashouttxn_ReportActivity2 = Ledger_cashouttxn_ReportActivity.this;
                            ledger_cashouttxn_ReportActivity.transactionBillAdapter = new CashoutReceivedReportAdapter(ledger_cashouttxn_ReportActivity, ledger_cashouttxn_ReportActivity.reportList);
                            Ledger_cashouttxn_ReportActivity.this.recycle_transactions.setAdapter(Ledger_cashouttxn_ReportActivity.this.transactionBillAdapter);
                            return;
                        }
                        Ledger_cashouttxn_ReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                        Ledger_cashouttxn_ReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                        return;
                    }
                    else {
                        Ledger_cashouttxn_ReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                        Ledger_cashouttxn_ReportActivity.this.text_no_content.setVisibility(View.VISIBLE);

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }

            @Override
            public void onFailure(Call<cashoutbaseResponse> call, Throwable t) {

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