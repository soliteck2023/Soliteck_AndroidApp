package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_raisecompliant extends AppCompatActivity {

    TextView raise_cmpt,raise_discription,clear,submit,complaintlist;
    List<comptTyperesponse> datalist= new ArrayList();

    private List<comptTyperesponse> complaintTypes;
    private ArrayAdapter<String> adapter;
    private List<complaintData> list_margin2 = new ArrayList();
    private ViewCompiantAdapter viewCompiantAdapter;
    RecyclerView view_complaintlist;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_raisecompliant);

        raise_cmpt = findViewById(R.id.raise_cmpt);
        raise_discription = findViewById(R.id.raise_discription);
        clear = findViewById(R.id.clear);
        submit = findViewById(R.id.submit);
        complaintlist = findViewById(R.id.complaintlist);

        view_complaintlist = findViewById(R.id.view_compaint);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        view_complaintlist.setLayoutManager(layoutManager);
//        String txnId = getIntent().getExtras().getString("TxnId");
//        String amount = getIntent().getExtras().getString("amt");

        complaintTypes = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.material_list, R.id.textView);

        raise_cmpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compaint_type();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_raisecompliant.this.rasiecomplaint();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raise_discription.setText("");                  // Clear the text in raise_discription TextView
                raise_cmpt.setText("Select Compaint Type");
            }
        });
        complaintlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_complaintlist.setVisibility(View.VISIBLE);
                getCompaintMarginList(view_complaintlist);


            }
        });

    }

    private void getCompaintMarginList(RecyclerView viewComplaintlist) {

        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
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
                            user_raisecompliant.this.view_complaintlist.setVisibility(View.VISIBLE);
                            user_raisecompliant.this.list_margin2 = response.body().getCompaintData();
                            user_raisecompliant.this.viewCompiantAdapter = new ViewCompiantAdapter(user_raisecompliant.this, user_raisecompliant.this.list_margin2
                            );
                            user_raisecompliant.this.view_complaintlist.setAdapter(user_raisecompliant.this.viewCompiantAdapter);
//                            viewCommissionActivity.this.view_commissionlist.setAdapter(viewCommissionActivity.this.viewCommissionAdapter);

                        }else{
                            user_raisecompliant.this.view_complaintlist.setVisibility(View.INVISIBLE);
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

    private void rasiecomplaint() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(user_raisecompliant.this);
        progressDialog.show();
        String deviceId = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        String uniqueCode = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", deviceId);
        body.put("Token", token);
        body.put("Description", getIntent().getExtras().getString("TxnId") + "-" + raise_discription.getText().toString().trim() + "-" + (getIntent().getExtras().getString("amt")));
        body.put("UniqueCode", uniqueCode);
        body.put("ComplaintType", ConstantClass.MOBILESERVICEID);

        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<ComplaintResponse> call = apiservice.Getcomplaintraise(body);

        call.enqueue(new Callback<ComplaintResponse>() {
            @Override
            public void onResponse(Call<ComplaintResponse> call, Response<ComplaintResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }

                if (response.isSuccessful() && response.body() != null) {
                    ComplaintResponse complaintResponse = response.body();
                    List<compaintlist> complaintList = complaintResponse.getData();
                    onSupportNavigateUp();
                    Toast.makeText(user_raisecompliant.this, "Complaint Raise Successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ComplaintResponse> call, Throwable t) {
                Toast.makeText(user_raisecompliant.this, "Error", Toast.LENGTH_SHORT).show();
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(user_raisecompliant.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void compaint_type() {
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<compaint_typeList> call = apiservice.compaint_typeList(body);

        call.enqueue(new Callback<compaint_typeList>() {
            @Override
            public void onResponse(Call<compaint_typeList> call, Response<compaint_typeList> response) {

                if (response != null){
                    user_raisecompliant.this.datalist.clear();
                    datalist = response.body().getData();
                    user_raisecompliant userRaisecompliant= user_raisecompliant.this;
                    userRaisecompliant.DisplayUsercmpltlist(userRaisecompliant.datalist);
                }
            }

            @Override
            public void onFailure(Call<compaint_typeList> call, Throwable t) {
                user_raisecompliant userRaisecompliant = user_raisecompliant.this;
                ConstantClass.displayMessageDialog(userRaisecompliant,"Response",t.getMessage());

            }
        });
    }

    private void DisplayUsercmpltlist(List<comptTyperesponse> datalist) {
        ComptTypeAdapter adapter = new ComptTypeAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, datalist);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.material_list);
        dialog.setCancelable(false);
        dialog.setTitle("Select Bank");
        ListView listView = (ListView) dialog.findViewById(R.id.list);
        EditText search_edit = (EditText) dialog.findViewById(R.id.search_edit);
        dialog.show();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                comptTyperesponse selectedComplaintType = datalist.get(position);
                user_raisecompliant.this.raise_cmpt.setText(selectedComplaintType.getName());

                // Check if the selected complaint type is "Transaction"
                if ("Transaction".equalsIgnoreCase(selectedComplaintType.getName())) {
                    findViewById(R.id.transction_id).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.transction_id).setVisibility(View.GONE);
                }

                dialog.cancel();
            }
        });

        handleBackPressed(dialog);
    }
    private void handleBackPressed(Dialog dialog) {
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == 4) {
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    private void updateUIWithData() {
        List<String> complaintTypeNames = new ArrayList<>();
        for (comptTyperesponse complaintType : complaintTypes) {
            complaintTypeNames.add(complaintType.getName());


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}