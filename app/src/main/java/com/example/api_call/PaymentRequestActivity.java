package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentRequestActivity extends AppCompatActivity {

    private Button btn_payment_request;
    private String[] cashType;
    private CheckBox checkCredit;
    private CheckBox check_admin;
    private CheckBox check_parent;
    private EditText edit_amount_;
    private TextView edit_bank;
    private TextView edit_cashtype;
    private TextView edit_check_date;
    private EditText edit_cheque_number;
    private TextView edit_payment_date;
    private TextView edit_paymentmode;
    private EditText edit_remarks;
    private EditText edit_transactionno;
    private TextView edit_user_bank;
    private EditText edit_utr_no;
    private ImageView image_slip;
    private FrameLayout layout_cash;
    private FrameLayout layout_transaction;
    private FrameLayout layout_userbank;
    private FrameLayout layout_utr;
    private FrameLayout linear_dd_date;
    private FrameLayout linear_dd_number;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private String[] paymentTypeCode;
    private ProgressDialog progressDialog;
    private String strBankCode;
    private String strPaymentTypeCode;
    List<PaymentMode> paymentType = new ArrayList();
    List<String> paymentMode = new ArrayList();
    private List<GetBankResponse> listAdminBanks = new ArrayList();
    private List<GetBankResponse> listParentBanks = new ArrayList();
    private List<CompanyBankResponse> listUserBanks = new ArrayList();
    private List<String> listAdminBanksAccount = new ArrayList();
    private List<String> listParentBanksAccount = new ArrayList();
    private List<String> listUserBanksAccount = new ArrayList();
    private String branchname = "";
    String photo_1 = " ";
    private String checkCreditString = "false";
    private String PaymentAmount = "";
    private String BankName = "";
    private String PaymentMode = "";
    private String CashType = "";
    private String UserBankName = "";
    private String BranchName = "";
    private String BranchCode = "";
    private String ChequeNo = " ";
    private String TransactionNo = "";
    private String Location = "";
    private String PaymentDate = "";
    private String PaymentTime = "";
    private String UTRNumber = "";
    private String UserId = "";
    private String PayId = "";
    private String BankId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_request);
        setTitle("Payment Request");
        initComponents();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
        this.cashType = getResources().getStringArray(R.array.payment_request_cash_type);
        this.paymentTypeCode = getResources().getStringArray(R.array.banks_payment_request_Payment_type_code);
        this.layout_cash.setVisibility(View.VISIBLE);
        this.layout_utr.setVisibility(View.INVISIBLE);
        getPaymentMode();
        this.check_parent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.uvapay.activities.PaymentRequestActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PaymentRequestActivity.this.check_parent.isChecked()) {
                    PaymentRequestActivity.this.check_parent.setChecked(true);
                    PaymentRequestActivity.this.check_admin.setChecked(false);
                    PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                    paymentRequestActivity.UserId = PrefUtils.getFromPrefs(paymentRequestActivity, ConstantClass.USERDETAILS.ParentId, "");
                    return;
                }
                PaymentRequestActivity.this.check_admin.setChecked(true);
                PaymentRequestActivity.this.check_parent.setChecked(false);
                PaymentRequestActivity.this.UserId = ConstantClass.MOBILESERVICEID;
            }
        });

        this.check_admin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.uvapay.activities.PaymentRequestActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PaymentRequestActivity.this.check_admin.isChecked()) {
                    PaymentRequestActivity.this.check_admin.setChecked(true);
                    PaymentRequestActivity.this.check_parent.setChecked(false);
                    PaymentRequestActivity.this.UserId = ConstantClass.MOBILESERVICEID;
                    return;
                }
                PaymentRequestActivity.this.check_parent.setChecked(true);
                PaymentRequestActivity.this.check_admin.setChecked(false);
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                paymentRequestActivity.UserId = PrefUtils.getFromPrefs(paymentRequestActivity, ConstantClass.USERDETAILS.ParentId, "");
            }
        });

        this.check_admin.setChecked(true);
        this.checkCredit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.uvapay.activities.PaymentRequestActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PaymentRequestActivity.this.checkCredit.isChecked()) {
                    PaymentRequestActivity.this.checkCreditString = ConstantClass.USERDETAILS.First_Time_Login;
                } else {
                    PaymentRequestActivity.this.checkCreditString = "false";
                }
            }
        });
        this.edit_paymentmode.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                paymentRequestActivity.DisplayPaymentType(paymentRequestActivity.paymentMode);
            }
        });
        this.edit_cashtype.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                paymentRequestActivity.DisplayCashType(paymentRequestActivity.cashType);
            }
        });


    }

    private void DisplayCashType(String[] paymentType) {

        @SuppressLint("ResourceType") ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 17367043, 16908308, paymentType);

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.material_list);
        dialog.setCancelable(false);
        dialog.setTitle("Select Bank");
        final ListView listView = (ListView) dialog.findViewById(R.id.list);
        EditText search_edit = (EditText) dialog.findViewById(R.id.search_edit);
        search_edit.setVisibility(View.VISIBLE);
        dialog.show();
        listView.setAdapter((ListAdapter) adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.20
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentRequestActivity.this.edit_cashtype.setText((String) listView.getItemAtPosition(position));
                dialog.cancel();
            }
        });
        handleBackPressed(dialog);
    }

    private void DisplayPaymentType(List<String> paymentMode) {
        @SuppressLint("ResourceType") ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 17367043, 16908308, paymentMode);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.material_list);
        dialog.setCancelable(false);
        dialog.setTitle("Select Payment Mode");
        final ListView listView = (ListView) dialog.findViewById(R.id.list);
        EditText search_edit = (EditText) dialog.findViewById(R.id.search_edit);
        search_edit.setVisibility(View.VISIBLE);
        dialog.show();
        listView.setAdapter((ListAdapter) adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.19
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentRequestActivity.this.edit_paymentmode.setText((String) listView.getItemAtPosition(position));
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                paymentRequestActivity.PayId = String.valueOf(paymentRequestActivity.paymentType.get(position).getId());
                Log.i("Name", " : " + PaymentRequestActivity.this.edit_paymentmode.getText().toString());
                Log.i("Id", " : " + PaymentRequestActivity.this.PayId);
                dialog.cancel();
            }
        });
        handleBackPressed(dialog);
    }
    private void handleBackPressed(final Dialog mDialog) {
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.uvapay.activities.PaymentRequestActivity.21
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == 4) {
                    mDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
    }


    private void getPaymentMode() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        ApiInterface apiservice = (ApiInterface) RetrofitHandler.getService();
        Call<PaymentModeResponse> call = apiservice.GetPaymentMode(body);
        call.enqueue(new Callback<PaymentModeResponse>() { // from class: com.uvapay.activities.PaymentRequestActivity.12
            @Override // retrofit2.Callback
            public void onResponse(Call<PaymentModeResponse> call2, Response<PaymentModeResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getResponseStatus().intValue() == 1) {
                        PaymentRequestActivity.this.paymentType.clear();
                        if (response.body().getPaymentMode() != null || !response.body().getPaymentMode().isEmpty()) {
                            PaymentRequestActivity.this.paymentType = response.body().getPaymentMode();
                            PaymentRequestActivity.this.paymentMode.clear();
                            for (int i = 0; i < PaymentRequestActivity.this.paymentType.size(); i++) {
                                String payMode = PaymentRequestActivity.this.paymentType.get(i).getName();
                                PaymentRequestActivity.this.paymentMode.add(payMode);
                            }
                            return;
                        }
                        return;
                    }
                    ApplicationConstant.DisplayMessageDialog(PaymentRequestActivity.this, "Response", response.body().getRemarks());
                    return;
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(PaymentRequestActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<PaymentModeResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                ConstantClass.displayMessageDialog(paymentRequestActivity, paymentRequestActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }

    private void initComponents() {
        this.edit_amount_ = (EditText) findViewById(R.id.edit_amount_);
        this.edit_remarks = (EditText) findViewById(R.id.edit_remarks);
        this.edit_paymentmode = (TextView) findViewById(R.id.edit_paymentmode);
        this.edit_cashtype = (TextView) findViewById(R.id.edit_cashtype);
        this.edit_payment_date = (TextView) findViewById(R.id.edit_payment_date);
        this.edit_cheque_number = (EditText) findViewById(R.id.edit_cheque_number);
        this.edit_check_date = (TextView) findViewById(R.id.edit_check_date);
        this.edit_bank = (TextView) findViewById(R.id.edit_bank);
        this.edit_utr_no = (EditText) findViewById(R.id.edit_utr_no);
        this.btn_payment_request = (Button) findViewById(R.id.btn_payment_request);
        this.linear_dd_date = (FrameLayout) findViewById(R.id.linear_dd_date);
        this.linear_dd_number = (FrameLayout) findViewById(R.id.linear_dd_number);
        this.layout_cash = (FrameLayout) findViewById(R.id.layout_cash);
        this.edit_transactionno = (EditText) findViewById(R.id.edit_transactionno);
        this.layout_transaction = (FrameLayout) findViewById(R.id.layout_transaction);
        this.layout_userbank = (FrameLayout) findViewById(R.id.layout_userbank);
        this.edit_user_bank = (TextView) findViewById(R.id.edit_user_bank);
        this.layout_utr = (FrameLayout) findViewById(R.id.layout_utr);
        this.image_slip = (ImageView) findViewById(R.id.image_slip);
        this.checkCredit = (CheckBox) findViewById(R.id.checkCredit);
        this.check_parent = (CheckBox) findViewById(R.id.check_parent);
        this.check_admin = (CheckBox) findViewById(R.id.check_admin);
    }
    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}