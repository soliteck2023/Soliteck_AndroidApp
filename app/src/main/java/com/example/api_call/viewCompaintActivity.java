package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewCompaintActivity extends AppCompatActivity {
    private List<complaintData> list_margin2 = new ArrayList();
    private ViewCompiantAdapter viewCompiantAdapter;
    RecyclerView view_complaintlist;
    TextView raise_cmpt;

    private ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_compaint);

        view_complaintlist = findViewById(R.id.view_commissions);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        view_complaintlist.setLayoutManager(layoutManager);

        getCompaintMarginList(view_complaintlist);

        raise_cmpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewCompaintActivity.this, user_raisecompliant.class);
                startActivity(intent);
            }
        });


    }

    private void getCompaintMarginList(RecyclerView viewComplaintlist) {

        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        ApiInterface apiservice =RetrofitHandler.getService2();
        Call<Compaint> call = apiservice.getcompaintMargin2(body);


        call.enqueue(new Callback<Compaint>() {
            @Override
            public void onResponse(Call<Compaint> call, Response<Compaint> response) {

                ProgressDialog progressDialog2 = progressDialog;

                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    if (response.body().getResponseStatus().intValue() == 1){
                        if (!response.body().getCompaintData().isEmpty()){
                            viewCompaintActivity.this.view_complaintlist.setVisibility(View.VISIBLE);
                            viewCompaintActivity.this.list_margin2 = response.body().getCompaintData();
                            viewCompaintActivity.this.viewCompiantAdapter = new ViewCompiantAdapter(viewCompaintActivity.this, viewCompaintActivity.this.list_margin2
                            );
                            viewCompaintActivity.this.view_complaintlist.setAdapter(viewCompaintActivity.this.viewCompiantAdapter);
//                            viewCommissionActivity.this.view_commissionlist.setAdapter(viewCommissionActivity.this.viewCommissionAdapter);

                        }else{
                            viewCompaintActivity.this.view_complaintlist.setVisibility(View.INVISIBLE);
                        }
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<Compaint> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;

                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });


    }
}