package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DthechargeInfoActivity extends AppCompatActivity implements OperatorAdapter.SelectOperatorfromBack {
    private ProgressDialog progressDialog;
    private RecyclerView viewDthPlaninfo;
    private String mob = "";
    private String operator = "";
    private List<DTHData> list_dth_planinfo = new ArrayList();
    private List<MobileData> list_rechargeplans = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dthecharge_info);
        setTitle("DTH Recharge Info");
        this.viewDthPlaninfo = (RecyclerView) findViewById(R.id.recycle_dth_info);
        this.mob = getIntent().getExtras().getString("MOBILE");
        this.operator = getIntent().getExtras().getString("OPERATOR");
        this.viewDthPlaninfo.setLayoutManager(new LinearLayoutManager(this));
        getDTHPlansInfo();

    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getDTHPlansInfo() {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        Log.e("Operator", this.operator);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<DTHInfoResponse> call = apiservice.getDthCustInfo(PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""), PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, ""), this.operator, this.mob);
        call.enqueue(new Callback<DTHInfoResponse>() { // from class: com.uvapay.activities.DthechargeInfoActivity.1
            @Override // retrofit2.Callback
            public void onResponse(Call<DTHInfoResponse> call2, Response<DTHInfoResponse> response) {
                if (DthechargeInfoActivity.this.progressDialog != null && DthechargeInfoActivity.this.progressDialog.isShowing()) {
                    DthechargeInfoActivity.this.progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatus().equals("Success")) {
                        DthechargeInfoActivity.this.list_dth_planinfo = response.body().getData();
                        DthechargeInfoActivity dthechargeInfoActivity = DthechargeInfoActivity.this;
                        DTHRechargeInfoAdapter viewCommissionAdapter = new DTHRechargeInfoAdapter(dthechargeInfoActivity, dthechargeInfoActivity.list_dth_planinfo);
                        DthechargeInfoActivity.this.viewDthPlaninfo.setAdapter(viewCommissionAdapter);
                        return;
                    }
                    ConstantClass.displayMessageDialog(DthechargeInfoActivity.this, response.body().getStatus(), response.body().getRemarks());
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<DTHInfoResponse> call2, Throwable t) {
                if (DthechargeInfoActivity.this.progressDialog != null && DthechargeInfoActivity.this.progressDialog.isShowing()) {
                    DthechargeInfoActivity.this.progressDialog.dismiss();
                }
                DthechargeInfoActivity dthechargeInfoActivity = DthechargeInfoActivity.this;
                ConstantClass.displayMessageDialog(dthechargeInfoActivity, dthechargeInfoActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });


    }

    @Override
    public void selectOperatorfromlist(int position) {
        Intent intent = new Intent(this, MobileRechargeActivity.class);
        intent.putExtra("NUMBER", this.mob);
        intent.putExtra("AMT", this.list_rechargeplans.get(position).getRs());
        intent.putExtra("CALL", getIntent().getExtras().getString("CALL"));
        ConstantClass.datum.setName(this.operator);
        ConstantClass.datum.setServiceName("Mobile");
        startActivity(intent);
        finish();
    }

//    @Override
//    public void selectPlanfromlist(int position) {
//        Intent intent = new Intent(this, MobileRechargeActivity.class);
//        intent.putExtra("NUMBER", this.mob);
//        intent.putExtra("AMT", this.list_rechargeplans.get(position).getRs());
//        intent.putExtra("CALL", getIntent().getExtras().getString("CALL"));
//        ConstantClass.datum.setName(this.operator);
//        ConstantClass.datum.setServiceName("Mobile");
//        startActivity(intent);
//        finish();
//    }

}