package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileRechargePlansActivity extends AppCompatActivity implements MobileRechargePlansAdapter.SelectPlanfromBack {
    private ProgressDialog progressDialog;
    private RecyclerView recycle_plans;
    private String mob = "";
    private String operator = "";
    private List<MobileData> list_rechargeplans = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge_plans);

        setTitle("Mobile Recharge Plans");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.recycle_plans = (RecyclerView) findViewById(R.id.recycle_transactions);
        this.mob = getIntent().getExtras().getString("MOBILE");
        this.operator = getIntent().getExtras().getString("OPERATOR");
        this.recycle_plans.setLayoutManager(new LinearLayoutManager(this));
        getMobilePlans();

    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void getMobilePlans() {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<MobilePlansResponse> call = apiservice.getMobileSpecialPlan(PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""), PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, ""), this.operator, this.mob);
        call.enqueue(new Callback<MobilePlansResponse>() { // from class: com.uvapay.activities.MobileRechargePlansActivity.1
            @Override // retrofit2.Callback
            public void onResponse(Call<MobilePlansResponse> call2, Response<MobilePlansResponse> response) {
                if (MobileRechargePlansActivity.this.progressDialog != null && MobileRechargePlansActivity.this.progressDialog.isShowing()) {
                    MobileRechargePlansActivity.this.progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatus().equals("Success")) {
                        MobileRechargePlansActivity.this.list_rechargeplans = response.body().getData();
                        MobileRechargePlansActivity mobileRechargePlansActivity = MobileRechargePlansActivity.this;
                        MobileRechargePlansAdapter viewCommissionAdapter = new MobileRechargePlansAdapter(mobileRechargePlansActivity, mobileRechargePlansActivity.list_rechargeplans);
                        MobileRechargePlansActivity.this.recycle_plans.setAdapter(viewCommissionAdapter);
                        viewCommissionAdapter.ChoosePlan(MobileRechargePlansActivity.this);
                        return;
                    }
                    ConstantClass.displayMessageDialog(MobileRechargePlansActivity.this, response.body().getStatus(), response.body().getRemarks());
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MobilePlansResponse> call2, Throwable t) {
                if (MobileRechargePlansActivity.this.progressDialog != null && MobileRechargePlansActivity.this.progressDialog.isShowing()) {
                    MobileRechargePlansActivity.this.progressDialog.dismiss();
                }
                MobileRechargePlansActivity mobileRechargePlansActivity = MobileRechargePlansActivity.this;
                ConstantClass.displayMessageDialog(mobileRechargePlansActivity, mobileRechargePlansActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }

    @Override
    public void selectPlanfromlist(int position) {
        Intent intent = new Intent(this, MobileRechargeActivity.class);
        intent.putExtra("NUMBER", this.mob);
        intent.putExtra("AMT", this.list_rechargeplans.get(position).getRs());
        intent.putExtra("CALL", getIntent().getExtras().getString("CALL"));
        ConstantClass.datum.setName(this.operator);
        ConstantClass.datum.setServiceName("Mobile");
        startActivity(intent);
        finish();
    }
}