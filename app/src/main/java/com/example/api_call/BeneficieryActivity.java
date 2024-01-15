package com.example.api_call;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeneficieryActivity extends AppCompatActivity implements BeneficiaryListAdapter.OnDeleteItemListener {
    private FrameLayout add_ben;
    private CardView add_beneficiary;
    private AlertDialog alertDialog;
    private BankListAdapter bankListAdapter;
    private Button btn_add_benef;
    private ImageView btn_send;
    private ImageView btn_send_second;
    private Button btn_verify;
    private EditText edit_account;
    private TextView edit_bank;
    private EditText edit_beneficiary;
    private EditText edit_ifsc;
    private TextView edit_ifsc_code;
    private EditText edit_mobile;
    private EditText edit_mobile_no;
    private EditText edit_remitter;
    EditText edit_search;
    private ImageView image_delete;
    private LinearLayout layout_head;
    private LinearLayout linear_txt;
    private ProgressDialog progressDialog;
    private ScrollView scroll_list;
    private String strBankCode;
    private String strPaymentTypeCode;
    private TextView text_acc_count;
    private TextView text_available_limit;
    private TextView text_month_limit;
    private TextView text_no_content;
    private TextView text_remitter_no;
    BeneficiaryListAdapter transactionBillAdapter;
    private RecyclerView view_bank_list;
    private RecyclerView view_beneficiary_list;
    private List<MBankListResponse> listBanks = new ArrayList();
    private String IFSC = "";
    private String mobile_no = "";
    private String REMITTER = "";
    private String remitter_id = "";
    private String remitter_name = "";
    private boolean isVerified = false;
    private Serializable beneList = new ArrayList();
    List<VRecipient> recipientList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiery);
        setTitle("Beneficiary List");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.add_ben = (FrameLayout) findViewById(R.id.add_ben);
        this.add_beneficiary = (CardView) findViewById(R.id.add_beneficiary);
        this.image_delete = (ImageView) findViewById(R.id.image_delete);
        this.btn_add_benef = (Button) findViewById(R.id.btn_add_benef);
        this.view_beneficiary_list = (RecyclerView) findViewById(R.id.view_beneficiary_list);
        this.edit_bank = (TextView) findViewById(R.id.edit_bank);
        this.edit_ifsc_code = (TextView) findViewById(R.id.edit_ifsc_code);
        this.text_month_limit = (TextView) findViewById(R.id.text_month_limit);
        this.text_remitter_no = (TextView) findViewById(R.id.text_remitter_no);
        this.text_available_limit = (TextView) findViewById(R.id.text_available_limit);
        this.edit_search = (EditText) findViewById(R.id.edit_search);
        this.edit_beneficiary = (EditText) findViewById(R.id.edit_beneficiary);
        this.edit_account = (EditText) findViewById(R.id.edit_account);
        this.edit_ifsc = (EditText) findViewById(R.id.edit_ifsc);
        this.edit_remitter = (EditText) findViewById(R.id.edit_remitter);
        this.layout_head = (LinearLayout) findViewById(R.id.layout_head);
        this.btn_verify = (Button) findViewById(R.id.btn_verify);
        this.edit_mobile_no = (EditText) findViewById(R.id.edit_mobile_no);
        this.text_acc_count = (TextView) findViewById(R.id.text_acc_count);
        this.text_no_content = (TextView) findViewById(R.id.text_no_content);
        this.add_ben.setVisibility(View.GONE);
        this.mobile_no = getIntent().getExtras().getString("MOBILE");
        try {
            this.REMITTER = getIntent().getExtras().getString("REMITTER");
            this.beneList = getIntent().getExtras().getSerializable("BeneList");
        } catch (Exception e) {
            e.printStackTrace();
            this.REMITTER = "";
        }

        this.linear_txt = (LinearLayout) findViewById(R.id.linear_txt);
        this.view_beneficiary_list.setLayoutManager(new LinearLayoutManager(this));
        this.edit_search.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BeneficieryActivity.this.filterBeneficiary(s.toString());
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.add_beneficiary.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BeneficieryActivity.this.getAllBankData();
                BeneficieryActivity.this.edit_beneficiary.setText("");
                BeneficieryActivity.this.edit_bank.setText("");
                BeneficieryActivity.this.edit_ifsc_code.setText("");
                BeneficieryActivity.this.edit_account.setText("");
                BeneficieryActivity.this.view_beneficiary_list.setVisibility(View.GONE);
                BeneficieryActivity.this.linear_txt.setVisibility(View.GONE);
                BeneficieryActivity.this.add_ben.setVisibility(View.VISIBLE);
            }
        });
        this.edit_account.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (BeneficieryActivity.this.edit_account.getText().toString().length() != 0) {
                    BeneficieryActivity.this.text_acc_count.setVisibility(View.VISIBLE);
                    BeneficieryActivity.this.text_acc_count.setText("Acc Digit count: " + BeneficieryActivity.this.edit_account.getText().toString().length());
                    return;
                }
                BeneficieryActivity.this.text_acc_count.setVisibility(View.GONE);
            }
        });

//        this.edit_bank.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(BeneficieryActivity.this,layout_banklist.class);
//                intent.putExtra("NUMBER", BeneficieryActivity.this.edit_mobile.getText().toString());
//               intent.putExtra("CALL", "MOBILE");
//                BeneficieryActivity.this.startActivityForResult(intent, 1);
//
//            }
//        });
        this.edit_bank.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                View view = BeneficieryActivity.this.getLayoutInflater().inflate(R.layout.layout_banklist, (ViewGroup) null);
                AlertDialog.Builder builder = new AlertDialog.Builder(BeneficieryActivity.this);
                BeneficieryActivity.this.alertDialog = builder.create();
                BeneficieryActivity.this.alertDialog.setView(view);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.4.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        BeneficieryActivity.this.alertDialog.dismiss();
                    }
                });
                BeneficieryActivity.this.view_bank_list = (RecyclerView) view.findViewById(R.id.view_bank_list);
                ImageView image_delete = (ImageView) view.findViewById(R.id.image_delete);
                EditText edit_searchbank = (EditText) view.findViewById(R.id.edit_searchbank);
                edit_searchbank.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.4.2
                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        BeneficieryActivity.this.filter(s.toString());
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable s) {
                    }
                });
                image_delete.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.4.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        BeneficieryActivity.this.alertDialog.dismiss();

                    }
                });

                BeneficieryActivity beneficieryActivity = BeneficieryActivity.this;
                BeneficieryActivity beneficieryActivity2 = BeneficieryActivity.this;
                beneficieryActivity.bankListAdapter = new BankListAdapter(beneficieryActivity2, beneficieryActivity2.listBanks);
                BeneficieryActivity.this.view_bank_list.setLayoutManager(new LinearLayoutManager(BeneficieryActivity.this));
                BeneficieryActivity.this.view_bank_list.setAdapter(BeneficieryActivity.this.bankListAdapter);
//                BeneficieryActivity.this.bankListAdapter.setBankListener((BankListAdapter.SelectBankFromList) BeneficieryActivity.this);
                BeneficieryActivity.this.alertDialog.show();
            }
        });
        this.image_delete.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BeneficieryActivity.this.add_ben.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BeneficieryActivity.this.view_beneficiary_list.setVisibility(View.VISIBLE);
                        BeneficieryActivity.this.linear_txt.setVisibility(View.VISIBLE);
                    }
                }, 600L);
            }
        });
        this.btn_add_benef.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BeneficieryActivity.this.edit_beneficiary.getText().toString().isEmpty()) {
                    BeneficieryActivity.this.edit_beneficiary.setError("enter beneficiary name");
                    BeneficieryActivity.this.edit_beneficiary.requestFocus();
                } else if (BeneficieryActivity.this.edit_bank.getText().toString().isEmpty()) {
                    BeneficieryActivity.this.edit_bank.setError("enter bank name");
                    BeneficieryActivity.this.edit_bank.requestFocus();
                } else if (BeneficieryActivity.this.edit_account.getText().toString().isEmpty()) {
                    BeneficieryActivity.this.edit_account.setError("enter account number");
                    BeneficieryActivity.this.edit_account.requestFocus();
                } else {
                    BeneficieryActivity.this.createBeneficiary();
                }
            }
        });
        this.btn_verify.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BeneficieryActivity.this.edit_bank.getText().toString().isEmpty()) {
                    BeneficieryActivity.this.edit_bank.setError("enter bank name for further process");
                    BeneficieryActivity.this.edit_bank.requestFocus();
                } else if (BeneficieryActivity.this.edit_ifsc_code.getText().toString().isEmpty()) {
                    BeneficieryActivity.this.edit_ifsc_code.setError("select bank for ifsc");
                    BeneficieryActivity.this.edit_ifsc_code.requestFocus();
                } else if (BeneficieryActivity.this.edit_account.getText().toString().isEmpty()) {
                    BeneficieryActivity.this.edit_account.setError("enter account number");
                    BeneficieryActivity.this.edit_account.requestFocus();
                } else {
                    BeneficieryActivity beneficieryActivity = BeneficieryActivity.this;
                    beneficieryActivity.verifyRecipient(beneficieryActivity.edit_account.getText().toString().trim(), BeneficieryActivity.this.edit_ifsc_code.getText().toString());
                }
            }
        });


    }

    private void filterBeneficiary(String s) {
        List<VRecipient> list_new = new ArrayList<>();
        for (VRecipient recipientsItem : this.recipientList) {
            if (recipientsItem.getName().toLowerCase().contains(s.toLowerCase()) || recipientsItem.getAccountNo().toLowerCase().contains(s.toLowerCase())) {
                list_new.add(recipientsItem);
            }
        }
        this.transactionBillAdapter.setNewList(list_new);
    }

    private void filter(String s) {
        List<MBankListResponse> listnew_Banks = new ArrayList<>();
        for (MBankListResponse allBankResponse : this.listBanks) {
            if (allBankResponse.getBankName().toLowerCase().contains(s.toLowerCase())) {
                listnew_Banks.add(allBankResponse);
            }
        }
        this.bankListAdapter.setNewList(listnew_Banks);
    }

    private void createBeneficiary() {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("MobileNumber", this.mobile_no);
        body.put(ConstantClass.PROFILEDETAILS.AccountNo, this.edit_account.getText().toString());
        body.put(ConstantClass.PROFILEDETAILS.IFSCCode, this.IFSC);
        body.put("Name", this.edit_beneficiary.getText().toString());
        body.put("senderId", this.remitter_id);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<MBeneficiary> call = apiservice.addBeneficiary(body);
        call.enqueue(new Callback<MBeneficiary>() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.8
            @Override // retrofit2.Callback
            public void onResponse(Call<MBeneficiary> call2, Response<MBeneficiary> response) {
                if (BeneficieryActivity.this.progressDialog != null && BeneficieryActivity.this.progressDialog.isShowing()) {
                    BeneficieryActivity.this.progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                        BeneficieryActivity.this.add_ben.setVisibility(View.GONE);
                        BeneficieryActivity.this.linear_txt.setVisibility(View.VISIBLE);
                        BeneficieryActivity beneficieryActivity = BeneficieryActivity.this;
                        beneficieryActivity.getUserDetails(beneficieryActivity.mobile_no);
                        return;
                    }
                    ConstantClass.displayMessageDialog(BeneficieryActivity.this, " ", response.body().getMessage());
                    return;
                }
                if (BeneficieryActivity.this.progressDialog != null && BeneficieryActivity.this.progressDialog.isShowing()) {
                    BeneficieryActivity.this.progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(BeneficieryActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MBeneficiary> call2, Throwable t) {
                if (BeneficieryActivity.this.progressDialog != null && BeneficieryActivity.this.progressDialog.isShowing()) {
                    BeneficieryActivity.this.progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(BeneficieryActivity.this, "Server Problem", t.getMessage());
            }
        });
    }
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getUserDetails(this.mobile_no);
    }

    private void getUserDetails(final String userName) {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("MobileNumber", userName);
        ApiInterface apiservice =RetrofitHandler.getService();
        Call<ValidateRemitter> call = apiservice.getValidate(body);
        call.enqueue(new Callback<ValidateRemitter>() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.11
            @Override // retrofit2.Callback
            public void onResponse(Call<ValidateRemitter> call2, Response<ValidateRemitter> response) {
                if (response.body() != null) {
                    if (BeneficieryActivity.this.progressDialog != null && BeneficieryActivity.this.progressDialog.isShowing()) {
                        BeneficieryActivity.this.progressDialog.dismiss();
                    }
                    if (response.body().getResponse().getStatusCode().toString().equals(ConstantClass.MOBILESERVICEID)) {
                        BeneficieryActivity.this.remitter_name = response.body().getResponse().getData().getName();
                        BeneficieryActivity.this.remitter_id = response.body().getResponse().getData().getRemitterID();
                        BeneficieryActivity.this.getRemitterLimit(response.body().getResponse().getData().getAvailableLimit(), userName);
                        if (response.body().getResponse().getData().getRecipients().isEmpty()) {
                            BeneficieryActivity.this.view_beneficiary_list.setVisibility(View.GONE);
                            BeneficieryActivity.this.text_no_content.setVisibility(View.VISIBLE);
                            return;
                        }
                        BeneficieryActivity.this.recipientList = response.body().getResponse().getData().getRecipients();
                        BeneficieryActivity.this.view_beneficiary_list.setVisibility(View.VISIBLE);
                        BeneficieryActivity.this.text_no_content.setVisibility(View.GONE);
                        BeneficieryActivity beneficieryActivity = BeneficieryActivity.this;
                        BeneficieryActivity beneficieryActivity2 = BeneficieryActivity.this;
                        beneficieryActivity.transactionBillAdapter = new BeneficiaryListAdapter(beneficieryActivity2, beneficieryActivity2.recipientList, BeneficieryActivity.this.mobile_no, BeneficieryActivity.this.remitter_name, BeneficieryActivity.this.remitter_id);
                        BeneficieryActivity.this.view_beneficiary_list.setAdapter(BeneficieryActivity.this.transactionBillAdapter);
                        BeneficieryActivity.this.transactionBillAdapter.setOnDeleteItemListener(BeneficieryActivity.this);
                        BeneficieryActivity.this.transactionBillAdapter.notifyDataSetChanged();
                        return;
                    }
                    BeneficieryActivity.this.view_beneficiary_list.setVisibility(View.GONE);
                    BeneficieryActivity.this.text_no_content.setVisibility(View.VISIBLE);
                    ConstantClass.displayMessageDialog(BeneficieryActivity.this, " ", response.body().getResponse().getMessage());
                    return;
                }
                if (BeneficieryActivity.this.progressDialog != null && BeneficieryActivity.this.progressDialog.isShowing()) {
                    BeneficieryActivity.this.progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(BeneficieryActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ValidateRemitter> call2, Throwable t) {
                if (BeneficieryActivity.this.progressDialog != null && BeneficieryActivity.this.progressDialog.isShowing()) {
                    BeneficieryActivity.this.progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(BeneficieryActivity.this, "Response", t.getMessage().toString());
            }
        });
    }

    private void getRemitterLimit(Double limit_remitter, String Mobile) {
        try {
            this.text_available_limit.setText(String.valueOf(limit_remitter));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.text_remitter_no.setText("Remitter No: " + Mobile);
    }

    private void getAllBankData() {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<List<MBankListResponse>> call = apiservice.getBanks(body);
        call.enqueue(new Callback<List<MBankListResponse>>() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.9
            @Override // retrofit2.Callback
            public void onResponse(Call<List<MBankListResponse>> call2, Response<List<MBankListResponse>> response) {
                if (BeneficieryActivity.this.progressDialog != null && BeneficieryActivity.this.progressDialog.isShowing()) {
                    BeneficieryActivity.this.progressDialog.dismiss();
                }
                if (response.body() != null) {
                    BeneficieryActivity.this.listBanks = response.body();
                    return;
                }
                if (BeneficieryActivity.this.progressDialog != null && BeneficieryActivity.this.progressDialog.isShowing()) {
                    BeneficieryActivity.this.progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(BeneficieryActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<List<MBankListResponse>> call2, Throwable t) {
                if (BeneficieryActivity.this.progressDialog != null && BeneficieryActivity.this.progressDialog.isShowing()) {
                    BeneficieryActivity.this.progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(BeneficieryActivity.this, "Server Problem", t.getMessage());
            }
        });
    }

    private void verifyRecipient(String account,String ifsc) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("MobileNumber", this.mobile_no);
        body.put(ConstantClass.PROFILEDETAILS.AccountNo, account);
        body.put(ConstantClass.PROFILEDETAILS.IFSCCode, ifsc);
        ApiInterface apiservice =RetrofitHandler.getService();
        Call<MAccVerify> call = apiservice.getVerify(body);
        call.enqueue(new Callback<MAccVerify>() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.12
            @Override // retrofit2.Callback
            public void onResponse(Call<MAccVerify> call2, Response<MAccVerify> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getResponse().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                        if (response.body().getResponse().getData() != null) {
                            try {
                                BeneficieryActivity.this.edit_beneficiary.setText(response.body().getResponse().getData().getRecipientName());
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        ConstantClass.displayMessageDialog(BeneficieryActivity.this, "", response.body().getResponse().getMessage());
                        return;
                    }
                    ConstantClass.displayMessageDialog(BeneficieryActivity.this, "", response.body().getResponse().getMessage());
                    return;
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(BeneficieryActivity.this, "Response", response.errorBody().string());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MAccVerify> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(BeneficieryActivity.this, "Response", t.getMessage().toString());
            }
        });
    }

    @Override // com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.OnDeleteItemListener
    public void onDeleteItem() {
        getUserDetails(this.mobile_no);
    }
}