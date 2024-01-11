package com.example.api_call;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewCommissionActivity extends AppCompatActivity {
    private List<CommissionData> list_margin = new ArrayList();
    private ViewCommissionAdapter viewCommissionAdapter;
    RecyclerView view_commissionlist;
    private RelativeLayout layout_commission;
    private ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_commission);

        // Initialize views
        this.layout_commission = findViewById(R.id.layout_commission);
        view_commissionlist = findViewById(R.id.view_commissions);

        // Set up RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        view_commissionlist.setLayoutManager(layoutManager);

        // Call the method to get and display commission data
        getCommissionMarginList(view_commissionlist);


    }



    private void getCommissionMarginList( final RecyclerView view_commissionlist ) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        ApiInterface apiservice =RetrofitHandler.getService2();
        Call<Commision> call = apiservice.getCommissionMargin2(body);

        call.enqueue(new Callback<Commision>() {
            @Override
            public void onResponse(Call<Commision> call, Response<Commision> response) {

                ProgressDialog progressDialog2 = progressDialog;

                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    if (response.body().getResponseStatus().intValue() == 1){
                        if (!response.body().getCommissionData().isEmpty()){
                            viewCommissionActivity.this.view_commissionlist.setVisibility(View.VISIBLE);
                            viewCommissionActivity.this.list_margin = response.body().getCommissionData();
                            viewCommissionActivity.this.viewCommissionAdapter = new ViewCommissionAdapter(
                                    viewCommissionActivity.this, viewCommissionActivity.this.list_margin
                            );
                            viewCommissionActivity.this.view_commissionlist.setAdapter(viewCommissionActivity.this.viewCommissionAdapter);
//                            viewCommissionActivity.this.view_commissionlist.setAdapter(viewCommissionActivity.this.viewCommissionAdapter);

                        }else{
                            viewCommissionActivity.this.view_commissionlist.setVisibility(View.INVISIBLE);
                        }
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<Commision> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;

                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });








    }

}