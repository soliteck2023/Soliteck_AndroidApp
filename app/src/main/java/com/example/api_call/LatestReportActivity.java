package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestReportActivity extends AppCompatActivity {

    private RecyclerView recycle_transactions;
    private List<LatestTransaction> reportList;
    private TextView text_no_content;
    private EditText text_search;
    private LatestReportAdapter transactionBillAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_report);
        setTitle("Latest Report");
        initComponents();
        getLatestReport();
        this.recycle_transactions.setLayoutManager(new LinearLayoutManager(this));
        this.text_search.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.LatestReportActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {
                LatestReportActivity.this.filter(c.toString());
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });



    }

    private void initComponents() {
        this.recycle_transactions = (RecyclerView) findViewById(R.id.recycle_transactions);
        this.text_no_content = (TextView) findViewById(R.id.text_no_content);
        this.text_search = (EditText) findViewById(R.id.text_search);
    }
    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void filter(String s) {
        try {
            List<LatestTransaction> list_new = new ArrayList<>();
            for (LatestTransaction transHistoryData : this.reportList) {
                if (transHistoryData.getRefNumber() != null && transHistoryData.getStatus() != null && transHistoryData.getSenderMobile() != null && transHistoryData.getOperatorName() != null && (transHistoryData.getRefNumber().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getStatus().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getSenderMobile().toLowerCase().contains(s.toLowerCase()) || transHistoryData.getOperatorName().toLowerCase().contains(s.toLowerCase()))) {
                    list_new.add(transHistoryData);
                }
            }
            this.transactionBillAdapter.setNewList(list_new);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getLatestReport() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<GetLatestReportBase> result = apiservice.getLatestReport(body);
        result.enqueue(new Callback<GetLatestReportBase>() { // from class: com.uvapay.activities.LatestReportActivity.2
            @Override // retrofit2.Callback
            public void onResponse(Call<GetLatestReportBase> call, Response<GetLatestReportBase> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    if (response.body().getResponseStatus().intValue() == 1) {
                        LatestReportActivity.this.reportList = response.body().getTransaction();
                        if (!LatestReportActivity.this.reportList.isEmpty()) {
                            LatestReportActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
                            LatestReportActivity.this.text_no_content.setVisibility(View.GONE);
                            LatestReportActivity latestReportActivity = LatestReportActivity.this;
                            LatestReportActivity latestReportActivity2 = LatestReportActivity.this;
                            latestReportActivity.transactionBillAdapter = new LatestReportAdapter(latestReportActivity2, latestReportActivity2.reportList);
                            LatestReportActivity.this.recycle_transactions.setAdapter(LatestReportActivity.this.transactionBillAdapter);
                            return;
                        }
                        LatestReportActivity.this.recycle_transactions.setVisibility(View.GONE);
                        LatestReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                        return;
                    }
                    LatestReportActivity.this.recycle_transactions.setVisibility(View.GONE);
                    LatestReportActivity.this.text_no_content.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<GetLatestReportBase> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }
}