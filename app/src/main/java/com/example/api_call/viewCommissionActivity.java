package com.example.api_call;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
//        this.layout_commission.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.CommissionActivity.1
//            @Override // android.view.View.OnClickListener
//            public void onClick(View v) {
//                View view = viewCommissionActivity.this.getLayoutInflater().inflate(R.layout.layout_commission_list, (ViewGroup) null);
//                ImageView image_cancel = (ImageView) view.findViewById(R.id.image_cancel);
//                RecyclerView view_commissionlist = (RecyclerView) view.findViewById(R.id.view_commissionlist);
//                AlertDialog.Builder builder = new AlertDialog.Builder(viewCommissionActivity.this);
//                view_commissionlist.setLayoutManager(new GridLayoutManager(viewCommissionActivity.this, 2));
//                final AlertDialog alertDialog = builder.create();
//                alertDialog.setView(view);
//                image_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.CommissionActivity.1.1
//                    @Override // android.view.View.OnClickListener
//                    public void onClick(View v2) {
//                        alertDialog.dismiss();
//                    }
//                });
//                if (Build.VERSION.SDK_INT >= 21) {
//                    alertDialog.create();
//                    alertDialog.show();
//                }
//                if (!ConstantClass.isNetworkAvailable(viewCommissionActivity.this)) {
//                    ConstantClass.displayMessageDialog(viewCommissionActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//                } else {
//                    viewCommissionActivity.this.getCommissionMarginList(view_commissionlist);
//                }
//            }
//        });


//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        view_commissionlist.setLayoutManager(new GridLayoutManager(this, 2));
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.show();

//        image_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.35
//            @Override // android.view.View.OnClickListener
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        search_operator.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.DashboardActivity.36
//            @Override // android.text.TextWatcher
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override // android.text.TextWatcher
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                viewCommissionActivity.this.filter(charSequence.toString());
//            }
//
//            @Override // android.text.TextWatcher
//            public void afterTextChanged(Editable editable) {
//            }
//        });
//
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            alertDialog.create();
//            alertDialog.show();
//        }



    }

//    private void setupRecyclerView() {
//        view_commissionlist = findViewById(R.id.view_commissions);
//        view_commissionlist.setLayoutManager(new LinearLayoutManager(this));
//    }

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
                                    viewCommissionActivity.this,
                                    viewCommissionActivity.this.list_margin
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