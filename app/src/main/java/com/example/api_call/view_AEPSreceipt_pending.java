package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class view_AEPSreceipt_pending extends AppCompatActivity {

    List<cashoutledgerTransactionReport> listSatetments;
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
    TextView text_transid,adhar_no_,Bank_name,Cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aepsreceipt_pending);

        this.Raise =(TextView)findViewById(R.id.RAISE);
        this.Cancel =(TextView)findViewById(R.id.cancel_button);
        this.adhar_no_ =(TextView) findViewById(R.id.adhar_no_);
        this.Bank_name =(TextView) findViewById(R.id.Bank_name);
        this.text_paymode = (TextView) findViewById(R.id.text_paymode);
        this.text_status_ = (TextView) findViewById(R.id.text_status_);
        this.text_transid = (TextView) findViewById(R.id.text_transid);
        this.text_retailerNumber = (TextView)findViewById(R.id.text_senderNumber);
        this.text_refid = (TextView)findViewById(R.id.text_refid);
        this.text_amount_ = (TextView) findViewById(R.id.text_amount_);
        this.text_date_time_ = (TextView) findViewById(R.id.text_date_time_);
        this.text_commission = (TextView) findViewById(R.id.text_commission);
        this.text_servicecharge = (TextView) findViewById(R.id.text_servicecharge);
        this.text_gst = (TextView) findViewById(R.id.text_gst);
        this.text_tds = (TextView) findViewById(R.id.text_tds);
        this.text_debitAmount = (TextView) findViewById(R.id.text_debitAmount);
        this.text_effecativeBal = (TextView) findViewById(R.id.text_effecativeBal);
        this.text_creditAmount = (TextView) findViewById(R.id.text_creditAmount);
        GetAEPSReceiptReport();

        this.Cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_AEPSreceipt_pending.this.startActivity(new Intent(view_AEPSreceipt_pending.this, Ledger_cashouttxn_ReportActivity.class));

            }
        });

    }

    private void GetAEPSReceiptReport() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(view_AEPSreceipt_pending.this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("TransactionIds", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.TransactionIds, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<viewAEPSResponse> call = apiservice.GetReceiptReport2(body);

        call.enqueue(new Callback<viewAEPSResponse>() {
            @Override
            public void onResponse(Call<viewAEPSResponse> call, Response<viewAEPSResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body().getResponseStatus().intValue() == 1){

                    if (!response.body().getTransaction().isEmpty()){
                        view_AEPSreceipt_pending.this.listSatetments = response.body().getTransaction();
                        List<cashoutledgerTransactionReport> earnData = listSatetments;
                        for (int i = 0; i < earnData.size(); i++){
                            view_AEPSreceipt_pending.this.text_transid.setText("Txn Id: " + earnData.get(i).getTransactionNumber());

                            view_AEPSreceipt_pending.this.text_paymode.setText("Operator Name: " + earnData.get(i).getOperatorName());
                            view_AEPSreceipt_pending.this.text_status_.setText(earnData.get(i).getStatus());
                            view_AEPSreceipt_pending.this.text_date_time_.setText(earnData.get(i).getTransactionDate());
                            view_AEPSreceipt_pending.this.adhar_no_.setText("Adhar No: " + earnData.get(i).getAdhaarNo());
                            view_AEPSreceipt_pending.this.Bank_name.setText("Bank Name: " + earnData.get(i).getBankName());
                            view_AEPSreceipt_pending.this.text_amount_.setText("Rs " + earnData.get(i).getAmount());
                            view_AEPSreceipt_pending.this.text_refid.setText("Ref No: " + earnData.get(i).getRefNumber());
                            view_AEPSreceipt_pending.this.text_retailerNumber.setText("Sender Number: " + earnData.get(i).getSenderMobile());
                            view_AEPSreceipt_pending.this.text_commission.setText("Commission: " + earnData.get(i).getCommission());
                            view_AEPSreceipt_pending.this.text_servicecharge.setText("Service charge: " + earnData.get(i).getServicecharge());
                            view_AEPSreceipt_pending.this.text_gst.setText("GST: " + earnData.get(i).getGst());
                            view_AEPSreceipt_pending.this.text_tds.setText("TDS: " + earnData.get(i).getTds());
                            view_AEPSreceipt_pending.this.text_creditAmount.setText("Credit Amount: " + earnData.get(i).getCreditAmount());
                            view_AEPSreceipt_pending.this.text_debitAmount.setText("Debit Amount: " + earnData.get(i).getDebitAmount());
                            view_AEPSreceipt_pending.this.text_effecativeBal.setText("Effective Bal.: " + earnData.get(i).getEffecativeBal());


                        }


                    }

//                    if (!response.body().getTransaction().isEmpty()) {
//                        view_AEPSreceipt_pending.this.listSatetments = response.body().getTransaction();
//                        List<cashoutledgerTransactionReport> earnData = listSatetments;
//                        for (int i = 0; i < earnData.size(); i++){
//                            view_AEPSreceipt_pending.this.text_transid.setText("Txn Id: " + earnData.get(i).getTransactionNumber());
//                            view_AEPSreceipt_pending.this.text_paymode.setText("Operator Name: " + earnData.get(i).getOperatorName());
//                            view_AEPSreceipt_pending.this.text_status_.setText(earnData.get(i).getStatus());
//                            view_AEPSreceipt_pending.text_date_time_.setText(earnData.getTransactionDate());
//                            view_AEPSreceipt_pending.adhar_no_.setText("Adhar No: " + earnData.getAdhaarNo());
//                            view_AEPSreceipt_pending.Bank_name.setText("Bank Name: " + earnData.getBankName());
//                            view_AEPSreceipt_pending.text_amount_.setText("Rs " + earnData.getAmount());
//                            view_AEPSreceipt_pending.text_refid.setText("Ref No: " + earnData.getRefNumber());
//                            view_AEPSreceipt_pending.text_retailerNumber.setText("Sender Number: " + earnData.getSenderMobile());
//                            view_AEPSreceipt_pending.text_commission.setText("Commission: " + earnData.getCommission());
//                            view_AEPSreceipt_pending.text_servicecharge.setText("Service charge: " + earnData.getServicecharge());
//                            view_AEPSreceipt_pending.text_gst.setText("GST: " + earnData.getGst());
//                            view_AEPSreceipt_pending.text_tds.setText("TDS: " + earnData.getTds());
//                            view_AEPSreceipt_pending.text_creditAmount.setText("Credit Amount: " + earnData.getCreditAmount());
//                            view_AEPSreceipt_pending.text_debitAmount.setText("Debit Amount: " + earnData.getDebitAmount());
//                            view_AEPSreceipt_pending.text_effecativeBal.setText("Effective Bal.: " + earnData.getEffecativeBal());
//
//
//                        }
//
//                    }

                }else {
                    ConstantClass.displayMessageDialog(view_AEPSreceipt_pending.this, "Response", response.body().getResponseStatus().toString());

                }
                return;
            }

            @Override
            public void onFailure(Call<viewAEPSResponse> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });


    }


}