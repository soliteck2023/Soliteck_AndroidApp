package com.example.api_call;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SMSFragment extends Fragment {
    private ArrayList<MobilePlanResponseData> data = new ArrayList<>();
    Context mContext;
    private TextView mEmpty_view;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mMy_recycler_view;
    private TextView mPlanNotes;
    private ProgressBar mRegistrationProgressBar;
    SMSRechargePlansRecyclerAdapter rechargePlansRecyclerAdapter;
    RelativeLayout relativeLayout;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order_history, container, false);
        bindViews(rootView);
        return rootView;
    }

    private void bindViews(View rootView) {
        this.mContext = getActivity();
        this.mRegistrationProgressBar = (ProgressBar) rootView.findViewById(R.id.registrationProgressBar);
        this.mEmpty_view = (TextView) rootView.findViewById(R.id.empty_view);
        this.mMy_recycler_view = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        this.mEmpty_view = (TextView) rootView.findViewById(R.id.empty_view);
        this.relativeLayout = (RelativeLayout) rootView.findViewById(R.id.main_layout);
        String cirleId = getArguments().getString("circleName");
        String operator = getArguments().getString("operator");
        CallWebService(cirleId, operator);
        TextView textView = (TextView) rootView.findViewById(R.id.planNotes);
        this.mPlanNotes = textView;
        textView.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mLayoutManager = linearLayoutManager;
        this.mMy_recycler_view.setLayoutManager(linearLayoutManager);
    }

    private void CallWebService(String circle, String operator) {
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<MobilePlanResponse> call = apiservice.getMobilePlan(PrefUtils.getFromPrefs(getActivity(), ConstantClass.USERDETAILS.UserName, ""), PrefUtils.getFromPrefs(getActivity(), ConstantClass.USERDETAILS.UserPassword, ""), operator, circle);
        call.enqueue(new Callback<MobilePlanResponse>() { // from class: com.uvapay.fragments.plan.SMSFragment.1
            @Override // retrofit2.Callback
            public void onResponse(Call<MobilePlanResponse> call2, Response<MobilePlanResponse> response) {
                SMSFragment.this.mRegistrationProgressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    try {
                        if (!response.body().getStatus().equals("Success")) {
                            SMSFragment.this.mEmpty_view.setVisibility(View.VISIBLE);
                            SMSFragment.this.mEmpty_view.setText("No plans found");
                        } else if (response.body().getData().getSMS().size() > 0) {
                            SMSFragment.this.rechargePlansRecyclerAdapter = new SMSRechargePlansRecyclerAdapter(SMSFragment.this.mContext, response.body().getData().getSMS(), new SMSRechargePlansRecyclerAdapter.OnclickD() { // from class: com.uvapay.fragments.plan.SMSFragment.1.1
                                @Override // com.uvapay.adapters.rechargeplan.SMSRechargePlansRecyclerAdapter.OnclickD
                                public void getdata(SM sm) {
                                    Intent intent = new Intent();
                                    intent.putExtra("MAMOUNT", sm.getRs());
                                    SMSFragment.this.getActivity().setResult(-1, intent);
                                    SMSFragment.this.getActivity().finish();
                                }
                            });
                            SMSFragment.this.mMy_recycler_view.setAdapter(SMSFragment.this.rechargePlansRecyclerAdapter);
                        } else {
                            SMSFragment.this.mMy_recycler_view.setVisibility(View.GONE);
                            SMSFragment.this.mEmpty_view.setVisibility(View.VISIBLE);
                            SMSFragment.this.mEmpty_view.setText("No plans found");
                        }
                    } catch (Exception e) {
                        SMSFragment.this.mMy_recycler_view.setVisibility(View.GONE);
                        SMSFragment.this.mEmpty_view.setVisibility(View.VISIBLE);
                        SMSFragment.this.mEmpty_view.setText("No plans found");
                    }
                    SMSFragment.this.mRegistrationProgressBar.setVisibility(View.GONE);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MobilePlanResponse> call2, Throwable t) {
                SMSFragment.this.mRegistrationProgressBar.setVisibility(View.GONE);
                SMSFragment.this.mMy_recycler_view.setVisibility(View.GONE);
                SMSFragment.this.mEmpty_view.setVisibility(View.VISIBLE);
                SMSFragment.this.mEmpty_view.setText("No plans found");
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
