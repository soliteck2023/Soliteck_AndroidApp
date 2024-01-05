package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileROfferActivity extends AppCompatActivity {
    DthPlanAdapter dthPlanAdapter;
    Context mContext;
    DTHSpLanAdapter mDTHSpLanAdapter;
    private TextView mEmpty_view;
    private RecyclerView mMy_recycler_view;
    String number;
    String operator1;
    PrefUtils prefs;
    ProgressDialog progressDialog;
    ROfferAdapter rechargePlansRecyclerAdapter;
    ServiceCallApi serviceCallApi;
    private List<MobilePlanRecord> mobileROfferResponse_data = new ArrayList();
    private List<DTHSPlanResData> dthsPlanResData = new ArrayList();
    private List<Plan> data = new ArrayList();
    private List<Rs> rsList = new ArrayList();
    private List<String> description = new ArrayList();
    private List<String> plane_name = new ArrayList();


    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobileroffer);
        this.mContext = this;
        bindViews();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void bindViews() {
        this.mContext = this;
        this.mEmpty_view = (TextView) findViewById(R.id.empty_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.Recycleview);
        this.mMy_recycler_view = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.operator1 = getIntent().getStringExtra("operatorName1");
        this.number = getIntent().getStringExtra("number");
        if (getIntent().getStringExtra("Call").equals(ConstantClass.MOBILESERVICEID)) {
            CallMobileROffer(this.number, this.operator1);
            setTitle("Mobile R-Offer");
        } else if (getIntent().getStringExtra("Call").equals("3")) {
            setTitle("DTH R-Offer");
            CallDTHSpeCialPlan(getIntent().getStringExtra("DTHOPNAME"), getIntent().getStringExtra("DTHNUMBER"));
        }
    }

    private void CallDTHSpeCialPlan(String dthopname, String dthnumber) {
        try {
            ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
            this.progressDialog = dialogue;
            dialogue.show();
            HashMap<String, String> body = new HashMap<>();
            body.put("mobileno", dthnumber);
            body.put("operatorId", dthopname);
            ServiceCallApi serviceCallApi = RetrofitHandler2.getnewService();
            this.serviceCallApi = serviceCallApi;
            Call<MobileRPlanResponse> result = serviceCallApi.getDTHPlans(body);
            result.enqueue(new Callback<MobileRPlanResponse>() { // from class: com.uvapay.activities.MobileROfferActivity.1
                @Override // retrofit2.Callback
                public void onResponse(Call<MobileRPlanResponse> call, Response<MobileRPlanResponse> response) {
                    if (MobileROfferActivity.this.progressDialog != null && MobileROfferActivity.this.progressDialog.isShowing()) {
                        MobileROfferActivity.this.progressDialog.dismiss();
                    }
                    try {
                        MobileRPlanResponse responseBody = response.body();
                        if (response.body().getStatus().intValue() != 1) {
                            MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                            MobileROfferActivity.this.mEmpty_view.setText("No plans found");
                        } else {
                            MobileROfferActivity.this.mEmpty_view.setVisibility(View.GONE);
                            MobileROfferActivity.this.mobileROfferResponse_data = responseBody.getRecords();
                            if (MobileROfferActivity.this.mobileROfferResponse_data.size() > 0) {
                                MobileROfferActivity.this.rechargePlansRecyclerAdapter = new ROfferAdapter(MobileROfferActivity.this.mContext, MobileROfferActivity.this.mobileROfferResponse_data, new ROfferAdapter.OnclickData() { // from class: com.uvapay.activities.MobileROfferActivity.1.1
                                    @Override // com.uvapay.adapters.ROfferAdapter.OnclickData
                                    public void getamount(MobilePlanRecord datum) {
                                        Intent intent = new Intent();
                                        intent.putExtra("ROFFERAMT", datum.getRs());
                                        MobileROfferActivity.this.setResult(-1, intent);
                                        MobileROfferActivity.this.finish();
                                    }
                                });
                                MobileROfferActivity.this.mMy_recycler_view.setAdapter(MobileROfferActivity.this.rechargePlansRecyclerAdapter);
                            } else {
                                MobileROfferActivity.this.mMy_recycler_view.setVisibility(View.GONE);
                                MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                                MobileROfferActivity.this.mEmpty_view.setText("No plans found");
                            }
                        }
                    } catch (Exception e) {
                        MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                        MobileROfferActivity.this.mEmpty_view.setText("No plans found");
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<MobileRPlanResponse> call, Throwable t) {
                    MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                    MobileROfferActivity.this.mEmpty_view.setText("No plans found");
                    if (MobileROfferActivity.this.progressDialog != null && MobileROfferActivity.this.progressDialog.isShowing()) {
                        MobileROfferActivity.this.progressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    private void CallMobileROffer(String number, String operator1) {
        try {
            ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
            this.progressDialog = dialogue;
            dialogue.show();
            HashMap<String, String> body = new HashMap<>();
            body.put("mobileno", number);
            body.put("operatorId", operator1);
            ServiceCallApi serviceCallApi = RetrofitHandler2.getnewService();
            this.serviceCallApi = serviceCallApi;
            Call<MobileRPlanResponse> result = serviceCallApi.getMobilePlans(body);
            result.enqueue(new Callback<MobileRPlanResponse>() { // from class: com.uvapay.activities.MobileROfferActivity.2
                @Override // retrofit2.Callback
                public void onResponse(Call<MobileRPlanResponse> call, Response<MobileRPlanResponse> response) {
                    if (MobileROfferActivity.this.progressDialog != null && MobileROfferActivity.this.progressDialog.isShowing()) {
                        MobileROfferActivity.this.progressDialog.dismiss();
                    }
                    try {
                        MobileRPlanResponse responseBody = response.body();
                        if (response.body().getStatus().intValue() != 1) {
                            MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                            MobileROfferActivity.this.mEmpty_view.setText("No plans found");
                        } else {
                            MobileROfferActivity.this.mEmpty_view.setVisibility(View.GONE);
                            MobileROfferActivity.this.mobileROfferResponse_data = responseBody.getRecords();
                            if (MobileROfferActivity.this.mobileROfferResponse_data.size() > 0) {
                                MobileROfferActivity.this.rechargePlansRecyclerAdapter = new ROfferAdapter(MobileROfferActivity.this.mContext, MobileROfferActivity.this.mobileROfferResponse_data, new ROfferAdapter.OnclickData() { // from class: com.uvapay.activities.MobileROfferActivity.2.1
                                    @Override // com.uvapay.adapters.ROfferAdapter.OnclickData
                                    public void getamount(MobilePlanRecord datum) {
                                        Intent intent = new Intent();
                                        intent.putExtra("ROFFERAMT", datum.getRs());
                                        MobileROfferActivity.this.setResult(-1, intent);
                                        MobileROfferActivity.this.finish();
                                    }
                                });
                                MobileROfferActivity.this.mMy_recycler_view.setAdapter(MobileROfferActivity.this.rechargePlansRecyclerAdapter);
                            } else {
                                MobileROfferActivity.this.mMy_recycler_view.setVisibility(View.GONE);
                                MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                                MobileROfferActivity.this.mEmpty_view.setText("No plans found");
                            }
                        }
                    } catch (Exception e) {
                        MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                        MobileROfferActivity.this.mEmpty_view.setText("No plans found");
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<MobileRPlanResponse> call, Throwable t) {
                    MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                    MobileROfferActivity.this.mEmpty_view.setText("No plans found");
                    if (MobileROfferActivity.this.progressDialog != null && MobileROfferActivity.this.progressDialog.isShowing()) {
                        MobileROfferActivity.this.progressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    private void CallDTHOffer(String number, String operator) {
        try {
            ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
            this.progressDialog = dialogue;
            dialogue.show();
            ServiceCallApi serviceCallApi = RetrofitHandler2.getnewService();
            this.serviceCallApi = serviceCallApi;
            Call<DthBrowsPlanss> result = serviceCallApi.dthplan(PrefUtils.getFromPrefs(this.mContext, ConstantClass.USERDETAILS.UserName, ""), PrefUtils.getFromPrefs(this.mContext, ConstantClass.USERDETAILS.UserPassword, ""), operator);
            result.enqueue(new Callback<DthBrowsPlanss>() { // from class: com.uvapay.activities.MobileROfferActivity.3
                @Override // retrofit2.Callback
                public void onResponse(Call<DthBrowsPlanss> call, Response<DthBrowsPlanss> response) {
                    if (MobileROfferActivity.this.progressDialog != null && MobileROfferActivity.this.progressDialog.isShowing()) {
                        MobileROfferActivity.this.progressDialog.dismiss();
                    }
                    try {
                        MobileROfferActivity.this.data = response.body().getData().getPlan();
                        for (int i = 0; i < MobileROfferActivity.this.data.size(); i++) {
                            MobileROfferActivity.this.plane_name.add(((Plan) MobileROfferActivity.this.data.get(i)).getPlanName());
                            MobileROfferActivity.this.description.add(((Plan) MobileROfferActivity.this.data.get(i)).getDesc());
                            MobileROfferActivity.this.rsList.add(((Plan) MobileROfferActivity.this.data.get(i)).getRs());
                        }
                        MobileROfferActivity.this.dthPlanAdapter = new DthPlanAdapter(MobileROfferActivity.this.mContext, MobileROfferActivity.this.rsList, MobileROfferActivity.this.description, MobileROfferActivity.this.plane_name, "2");
                        MobileROfferActivity.this.mMy_recycler_view.setAdapter(MobileROfferActivity.this.dthPlanAdapter);
                        MobileROfferActivity.this.mMy_recycler_view.setVisibility(View.VISIBLE);
                        MobileROfferActivity.this.mEmpty_view.setVisibility(View.GONE);
                    } catch (Exception e) {
                        MobileROfferActivity.this.mMy_recycler_view.setVisibility(View.GONE);
                        MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                        MobileROfferActivity.this.mEmpty_view.setText("Plan Not Available");
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<DthBrowsPlanss> call, Throwable t) {
                    MobileROfferActivity.this.mEmpty_view.setVisibility(View.VISIBLE);
                    MobileROfferActivity.this.mEmpty_view.setText("No plans found");
                    if (MobileROfferActivity.this.progressDialog != null && MobileROfferActivity.this.progressDialog.isShowing()) {
                        MobileROfferActivity.this.progressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
        }
    }



    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }
}