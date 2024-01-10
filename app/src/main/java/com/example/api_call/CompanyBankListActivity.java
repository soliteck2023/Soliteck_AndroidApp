package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyBankListActivity extends AppCompatActivity {
    List<BankListResponse> listUserBanks = new ArrayList();
    RecyclerView recycle_transactions;
    TextView text_no_content;
    EditText text_search;
    newCompanyListAdapter transactionBillAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_bank_list);

        setTitle("Bank List");
        initComponents();
        getBankList();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        if (!ConstantClass.isNetworkAvailable(this)) {
//            ConstantClass.displayMessageDialog(this, "No Internet Connection", "Please enable internet connection first to proceed");
//        } else {
//            getBankList();
//        }



    }

    private void getBankList() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        String DeviceId = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String Token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", DeviceId);
        body.put("Token", Token);
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        ApiInterface apiInterface = RetrofitHandler.getService2();
        Call<newBankListbaseResponse> call = apiInterface.GetnewBankList(body);

        call.enqueue(new Callback<newBankListbaseResponse>() {
            @Override
            public void onResponse(Call<newBankListbaseResponse> call, Response<newBankListbaseResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body().getResponseStatus().intValue() == 1){
                        if (!response.body().getCompanyBankList().isEmpty()){

                            CompanyBankListActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
                            CompanyBankListActivity.this.text_no_content.setVisibility(View.INVISIBLE);
                            CompanyBankListActivity.this.listUserBanks = response.body().getCompanyBankList();
                            CompanyBankListActivity companyBankListActivity = CompanyBankListActivity.this;
                            CompanyBankListActivity.this.transactionBillAdapter = new newCompanyListAdapter(companyBankListActivity,companyBankListActivity.listUserBanks);
                            CompanyBankListActivity.this.recycle_transactions.setAdapter(CompanyBankListActivity.this.transactionBillAdapter);



//
//                            List<BankListResponse> earndata = listUserBanks;
//                            if (earndata != null){
//                                if (!earndata.isEmpty()){
//                            CompanyBankListActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
//                            CompanyBankListActivity.this.text_no_content.setVisibility(View.INVISIBLE);
//                            CompanyBankListActivity.this.listUserBanks = response.body().getCompanyBankList();
//                            CompanyBankListActivity companyBankListActivity = CompanyBankListActivity.this;
//                            CompanyBankListActivity.this.transactionBillAdapter = new newCompanyListAdapter(companyBankListActivity,companyBankListActivity.listUserBanks);
//                            CompanyBankListActivity.this.recycle_transactions.setAdapter(CompanyBankListActivity.this.transactionBillAdapter);
//                                } else {
//                                    CompanyBankListActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
//                                   CompanyBankListActivity.this.text_no_content.setVisibility(View.VISIBLE);
//                                }
//
//
//                            }
//                            else {
//                                CompanyBankListActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
//                                CompanyBankListActivity.this.text_no_content.setVisibility(View.VISIBLE);
//                            }
////                            for (int i = 0; i<earndata.size(); i++){
//                                earndata
//
//
//                            }

                       }
                        else {
                            ConstantClass.displayMessageDialog(CompanyBankListActivity.this, "Response", response.body().getResponseStatus().toString());
                        }
//                        else {
//                            CompanyBankListActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
//                            CompanyBankListActivity.this.text_no_content.setVisibility(View.VISIBLE);
//                        }

                    }
//                    else {
//                        CompanyBankListActivity.this.recycle_transactions.setVisibility(View.INVISIBLE);
//                        CompanyBankListActivity.this.text_no_content.setVisibility(View.VISIBLE);
//
//                    }

//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }

            }

            @Override
            public void onFailure(Call<newBankListbaseResponse> call, Throwable t) {

            }
        });


//        call.enqueue(new Callback<BankListbaseResponse>() { // from class: com.uvapay.activities.CompanyBankListActivity.2
//            @Override // retrofit2.Callback
//            public void onResponse(Call<BankListbaseResponse> call2, Response<BankListbaseResponse> response) {
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                if (response != null) {
//                    CompanyBankListActivity.this.recycle_transactions.setVisibility(View.VISIBLE);
//                    CompanyBankListActivity.this.text_no_content.setVisibility(View.GONE);
//                    CompanyBankListActivity.this.listUserBanks = response.body().getCompanyBankList();
//                    CompanyBankListActivity companyBankListActivity = CompanyBankListActivity.this;
//                    CompanyBankListActivity companyBankListActivity2 = CompanyBankListActivity.this;
//                    companyBankListActivity.transactionBillAdapter = new CompanyListAdapter(companyBankListActivity2, companyBankListActivity2.listUserBanks);
//                    CompanyBankListActivity.this.recycle_transactions.setAdapter(CompanyBankListActivity.this.transactionBillAdapter);
//                    return;
//                }
//                CompanyBankListActivity.this.recycle_transactions.setVisibility(View.GONE);
//                CompanyBankListActivity.this.text_no_content.setVisibility(View.VISIBLE);
//            }
//
//            @Override // retrofit2.Callback
//            public void onFailure(Call<BankListbaseResponse> call2, Throwable t) {
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                CompanyBankListActivity companyBankListActivity = CompanyBankListActivity.this;
//                ConstantClass.displayMessageDialog(companyBankListActivity, companyBankListActivity.getString(R.string.server_problem), t.getMessage().toString());
//            }
//        });
    }

    private void initComponents() {
        this.text_search = (EditText) findViewById(R.id.text_search);
        this.text_no_content = (TextView) findViewById(R.id.text_no_content);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_transactions);
        this.recycle_transactions = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



}