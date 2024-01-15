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

public class Ledger_cashoutLedger_ReportActivity extends AppCompatActivity {
    private LinearLayout layout_fromdate;
    private LinearLayout layout_todate;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private RecyclerView recycle_transactions;
    private List<cashoutledgerTransactionReport2> reportList;
    private EditText text_fromdate;
    private TextView text_no_content;
    private EditText text_search;
    private EditText text_todate;
    private CashoutLedgerReceivedReportAdapter transactionBillAdapter;

    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger_cashout_ledger_report);

        setTitle("Cashout Ledger Received Report");
        initComponents();
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
//        this.text_fromdate.setText(this.mDay + "/" + (this.mMonth + 1) + "/" + this.mYear);
//        this.text_todate.setText(this.mDay + "/" + (this.mMonth + 1) + "/" + this.mYear);

        this.text_fromdate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.text_todate.setText(this.mYear + "/" + (this.mMonth + 1) + "/" + this.mDay);
        this.recycle_transactions.setLayoutManager(new LinearLayoutManager(this));

        this.text_fromdate.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentReceivedReportActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(Ledger_cashoutLedger_ReportActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.PaymentReceivedReportActivity.1.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Ledger_cashoutLedger_ReportActivity.this.text_fromdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        Ledger_cashoutLedger_ReportActivity.this.text_fromdate.setError(null);
                        Ledger_cashoutLedger_ReportActivity.this.getledgarcashoutReceived(Ledger_cashoutLedger_ReportActivity.this.text_fromdate.getText().toString(), Ledger_cashoutLedger_ReportActivity.this.text_todate.getText().toString());

//                        if (!PaymentReceivedReportActivity.this.text_todate.getText().toString().isEmpty()) {
//                            if (!ConstantClass.isNetworkAvailable(PaymentReceivedReportActivity.this)) {
//                                ConstantClass.displayMessageDialog(PaymentReceivedReportActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                            } else {
//                                PaymentReceivedReportActivity.this.getPaymentReceived(PaymentReceivedReportActivity.this.text_fromdate.getText().toString(), PaymentReceivedReportActivity.this.text_todate.getText().toString());
//                            }
//                        }
                    }
                }, Ledger_cashoutLedger_ReportActivity.this.mYear, Ledger_cashoutLedger_ReportActivity.this.mMonth, Ledger_cashoutLedger_ReportActivity.this.mDay);
                datePickerDialog.show();

            }
        });


    }

    private void getledgarcashoutReceived(String fromdate, String todate) {

        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("UserName", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("FromDateTime", fromdate);
        body.put("ToDateTime", todate);
        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<ledgercashoutbaseResponse> result = apiservice.Getcashout_2ndtxnReceived(body);

        result.enqueue(new Callback<ledgercashoutbaseResponse>() {
            @Override
            public void onResponse(Call<ledgercashoutbaseResponse> call, Response<ledgercashoutbaseResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }

                try {
                    if (response.body().getResponseStatus().intValue() == 1){
                        if (!response.body().getCashoutledgerTransactionReport2s().isEmpty()){
                            Ledger_cashoutLedger_ReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
                            Ledger_cashoutLedger_ReportActivity.this.text_no_content.setVisibility(View.INVISIBLE);
                            Ledger_cashoutLedger_ReportActivity.this.reportList = response.body().getCashoutledgerTransactionReport2s();
                            Ledger_cashoutLedger_ReportActivity ledgerCashoutLedgerReportActivity = Ledger_cashoutLedger_ReportActivity.this;
//                            Ledger_cashouttxn_ReportActivity Ledger_cashouttxn_ReportActivity2 = Ledger_cashouttxn_ReportActivity.this;
                           Ledger_cashoutLedger_ReportActivity.this.transactionBillAdapter = new CashoutLedgerReceivedReportAdapter(ledgerCashoutLedgerReportActivity,ledgerCashoutLedgerReportActivity.reportList);
                           Ledger_cashoutLedger_ReportActivity.this.recycle_transactions.setAdapter(Ledger_cashoutLedger_ReportActivity.this.transactionBillAdapter);

                        } else {
                            Ledger_cashoutLedger_ReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                            Ledger_cashoutLedger_ReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                        }

                    }
                    else {
                        Ledger_cashoutLedger_ReportActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
                        Ledger_cashoutLedger_ReportActivity.this.text_no_content.setVisibility(View.VISIBLE);

                    }
                    return;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }



            }

            @Override
            public void onFailure(Call<ledgercashoutbaseResponse> call, Throwable t) {

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