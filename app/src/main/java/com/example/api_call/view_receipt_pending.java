package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class view_receipt_pending extends AppCompatActivity {
    List<TransactionReport> listSatetments;
    TextView text_amount_;
    TextView Raise,View;
    TextView text_commission;
    TextView text_creditAmount;
    TextView text_date_time_;
    TextView text_debitAmount;
    TextView text_effecativeBal;
    TextView text_gst;
    TextView text_mob_;
    TextView text_paymode;
    TextView text_refid;
    TextView text_retailerNumber;
    TextView text_servicecharge;
    TextView text_status_;
    TextView text_tds;
    TextView text_transid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt_pending);
//        this.Raise =(TextView) findViewById(R.id.RAISE);
//        this.View =(TextView) findViewById(R.id.view_raise);
        this.text_transid = (TextView) findViewById(R.id.text_transid);
        this.text_retailerNumber = (TextView)findViewById(R.id.text_retailerNumber);
        this.text_paymode = (TextView) findViewById(R.id.text_paymode);
        this.text_status_ = (TextView) findViewById(R.id.text_status_);
        this.text_refid = (TextView) findViewById(R.id.text_refid);
        this.text_amount_ = (TextView) findViewById(R.id.text_amount_);
        this.text_date_time_ = (TextView) findViewById(R.id.text_date_time_);
        this.text_mob_ = (TextView)findViewById(R.id.text_mob_);
        this.text_commission = (TextView)findViewById(R.id.text_commission);
        this.text_servicecharge = (TextView) findViewById(R.id.text_servicecharge);
        this.text_gst = (TextView)findViewById(R.id.text_gst);
        this.text_tds = (TextView)findViewById(R.id.text_tds);
        this.text_debitAmount = (TextView) findViewById(R.id.text_debitAmount);
        this.text_effecativeBal = (TextView)findViewById(R.id.text_effecativeBal);
        this.text_creditAmount = (TextView) findViewById(R.id.text_creditAmount);
        GetReceiptReport();
        
    }

    private void GetReceiptReport() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("TransactionIds","202410050105113702957" );
        body.put("UserName", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<viewPaymentResponse> call = apiservice.GetReceiptReport(body);
        call.enqueue(new Callback<viewPaymentResponse>() { // from class: com.uvapay.activities.PaymentRequestActivity.13
            @Override // retrofit2.Callback
            public void onResponse(Call<viewPaymentResponse> call2, Response<viewPaymentResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response != null) {
                    if (response.body().getResponseStatus().intValue() == 1) {
                        ConstantClass.displayMessageDialog(view_receipt_pending.this, "Response", response.body().getResponseStatus().toString());
                        TransactionReport earnData = (TransactionReport) view_receipt_pending.this.listSatetments;
                        view_receipt_pending.this.text_transid.setText("Txn Id: " + earnData.getTransactionNumber());
                        view_receipt_pending.this.text_paymode.setText("Operator Name: " + earnData.getOperatorName());
                        view_receipt_pending.this.text_status_.setText(earnData.getStatus());
                        view_receipt_pending.this.text_date_time_.setText(earnData.getTransactionDate());
                        view_receipt_pending.this.text_amount_.setText("Rs " + earnData.getAmount());
                        view_receipt_pending.this.text_refid.setText("Ref No: " + earnData.getRefNumber());
                        view_receipt_pending.this.text_mob_.setText("" + earnData.getSenderMobile());
                        view_receipt_pending.this.text_retailerNumber.setText("Retailer Number: " + earnData.getRetailerNumber());
                        view_receipt_pending.this.text_commission.setText("Commission: " + earnData.getCommission());
                        view_receipt_pending.this.text_servicecharge.setText("Service charge: " + earnData.getServicecharge());
                        view_receipt_pending.this.text_gst.setText("GST: " + earnData.getGst());
                        view_receipt_pending.this.text_tds.setText("TDS: " + earnData.getTds());
                        view_receipt_pending.this.text_creditAmount.setText("Credit Amount: " + earnData.getCreditAmount());
                        view_receipt_pending.this.text_debitAmount.setText("Debit Amount: " + earnData.getDebitAmount());
                        view_receipt_pending.this.text_effecativeBal.setText("Effective Bal.: " + earnData.getEffecativeBal());
                        return;
                    }
                    ConstantClass.displayMessageDialog(view_receipt_pending.this, "Response", response.body().getResponseStatus().toString());
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<viewPaymentResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                view_receipt_pending paymentRequestActivity = view_receipt_pending.this;
                ConstantClass.displayMessageDialog(paymentRequestActivity, paymentRequestActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }
}