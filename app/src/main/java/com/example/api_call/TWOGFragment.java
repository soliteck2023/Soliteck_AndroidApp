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

public class TWOGFragment extends Fragment {
    private ArrayList<MobilePlanResponseData> data = new ArrayList<>();
    Context mContext;
    private TextView mEmpty_view;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mMy_recycler_view;
    private TextView mPlanNotes;
    private ProgressBar mRegistrationProgressBar;
    TWOGRechargePlansRecyclerAdapter rechargePlansRecyclerAdapter;
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
        ApiInterface apiservice =RetrofitHandler.getService();
        Call<MobilePlanResponse> call = apiservice.getMobilePlan(PrefUtils.getFromPrefs(getActivity(), ConstantClass.USERDETAILS.UserName, ""), PrefUtils.getFromPrefs(getActivity(), ConstantClass.USERDETAILS.UserPassword, ""), operator, circle);
        call.enqueue(new Callback<MobilePlanResponse>() { // from class: com.uvapay.fragments.plan.TWOGFragment.1
            @Override // retrofit2.Callback
            public void onResponse(Call<MobilePlanResponse> call2, Response<MobilePlanResponse> response) {
                TWOGFragment.this.mRegistrationProgressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    try {
                        if (!response.body().getStatus().equals("Success")) {
                            TWOGFragment.this.mEmpty_view.setVisibility(View.VISIBLE);
                            TWOGFragment.this.mEmpty_view.setText("No plans found");
                        } else if (response.body().getData().getG().size() > 0) {
                            TWOGFragment.this.rechargePlansRecyclerAdapter = new TWOGRechargePlansRecyclerAdapter(TWOGFragment.this.mContext, response.body().getData().getG(), new TWOGRechargePlansRecyclerAdapter.OnClickDataa() { // from class: com.uvapay.fragments.plan.TWOGFragment.1.1
                                @Override // com.uvapay.adapters.rechargeplan.TWOGRechargePlansRecyclerAdapter.OnClickDataa
                                public void getdata(G g) {
                                    Intent intent = new Intent();
                                    intent.putExtra("MAMOUNT", g.getRs());
                                    TWOGFragment.this.getActivity().setResult(-1, intent);
                                    TWOGFragment.this.getActivity().finish();
                                }
                            });
                            TWOGFragment.this.mMy_recycler_view.setAdapter(TWOGFragment.this.rechargePlansRecyclerAdapter);
                        } else {
                            TWOGFragment.this.mMy_recycler_view.setVisibility(View.GONE);
                            TWOGFragment.this.mEmpty_view.setVisibility(View.VISIBLE);
                            TWOGFragment.this.mEmpty_view.setText("No plans found");
                        }
                    } catch (Exception e) {
                        TWOGFragment.this.mMy_recycler_view.setVisibility(View.GONE);
                        TWOGFragment.this.mEmpty_view.setVisibility(View.VISIBLE);
                        TWOGFragment.this.mEmpty_view.setText("No plans found");
                    }
                    TWOGFragment.this.mRegistrationProgressBar.setVisibility(View.GONE);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MobilePlanResponse> call2, Throwable t) {
                TWOGFragment.this.mRegistrationProgressBar.setVisibility(View.GONE);
                TWOGFragment.this.mMy_recycler_view.setVisibility(View.GONE);
                TWOGFragment.this.mEmpty_view.setVisibility(View.VISIBLE);
                TWOGFragment.this.mEmpty_view.setText("No plans found");
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
