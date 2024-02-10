package com.example.api_call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoneyTransferActivity extends AppCompatActivity {
    private RelativeLayout btn_transfer_proceed;
    private TextInputEditText edit_mobile_number;
    private ImageView image_select_contact;
    private ProgressDialog progressDialog;
    private TextView text_bank_downs;
    private StringBuilder stringBuilder = new StringBuilder();
    private List<String> StoreContacts = new ArrayList();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);
        setTitle("Money Transfer");
        setupWindowAnimations();
        this.btn_transfer_proceed = (RelativeLayout) findViewById(R.id.btn_transfer_proceed);
        this.edit_mobile_number = (TextInputEditText) findViewById(R.id.edit_mobile_number);
//        this.text_bank_downs = (TextView) findViewById(R.id.text_bank_downs);
//        this.image_select_contact = (ImageView) findViewById(R.id.image_select_contact);
//        this.text_bank_downs.setSelected(true);
        this.edit_mobile_number.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.transfer_money.activities.MoneyTransferActivity.1
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 10) {
                    MoneyTransferActivity moneyTransferActivity = MoneyTransferActivity.this;
                    moneyTransferActivity.getRemitterValidate(moneyTransferActivity.edit_mobile_number.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.btn_transfer_proceed.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.MoneyTransferActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MoneyTransferActivity.this.edit_mobile_number.getText().toString().isEmpty()) {
                    MoneyTransferActivity.this.edit_mobile_number.setError("enter mobile number");
                } else if (MoneyTransferActivity.this.edit_mobile_number.getText().toString().length() != 10) {
                    MoneyTransferActivity.this.edit_mobile_number.setError("enter correct mobile number");
                } else {
                    MoneyTransferActivity moneyTransferActivity = MoneyTransferActivity.this;
                    moneyTransferActivity.getRemitterValidate(moneyTransferActivity.edit_mobile_number.getText().toString());
                }
            }
        });



//        this.image_select_contact.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.MoneyTransferActivity.3
//            @Override // android.view.View.OnClickListener
//            public void onClick(View v) {
//                MoneyTransferActivity.this.GetContactsIntoArrayList();
//                View view_recents = MoneyTransferActivity.this.getLayoutInflater().inflate(R.layout.layout_contact_list, (ViewGroup) null);
//                ListView listview1 = (ListView) view_recents.findViewById(R.id.listview1);
//                EditText search_edit = (EditText) view_recents.findViewById(R.id.search_edit);
//                AlertDialog.Builder builder = new AlertDialog.Builder(MoneyTransferActivity.this);
//                builder.setView(view_recents);
//                final AlertDialog alertDialog = builder.create();
//                MoneyTransferActivity moneyTransferActivity = MoneyTransferActivity.this;
//                @SuppressLint("ResourceType") final ArrayAdapter arrayAdapter = new ArrayAdapter(moneyTransferActivity, 17367043, moneyTransferActivity.StoreContacts);
//                listview1.setAdapter((ListAdapter) arrayAdapter);
//                alertDialog.show();
//                search_edit.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.transfer_money.activities.MoneyTransferActivity.3.1
//                    @Override // android.text.TextWatcher
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    }
//
//                    @Override // android.text.TextWatcher
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        arrayAdapter.getFilter().filter(charSequence);
//                    }
//
//                    @Override // android.text.TextWatcher
//                    public void afterTextChanged(Editable editable) {
//                    }
//                });
//                listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.uvapay.transfer_money.activities.MoneyTransferActivity.3.2
//                    @Override // android.widget.AdapterView.OnItemClickListener
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        String num = parent.getItemAtPosition(position).toString().trim().split(":")[1].replace(" ", "");
//                        if (num.contains("+")) {
//                            int len = num.length();
//                            String update_num = num.substring(3, len).trim();
//                            MoneyTransferActivity.this.edit_mobile_number.setText(update_num);
//                        } else {
//                            MoneyTransferActivity.this.edit_mobile_number.setText(num);
//                        }
//                        alertDialog.dismiss();
//                    }
//                });
//            }
//        });


    }

    private void GetContactsIntoArrayList() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("display_name"));
            @SuppressLint("Range") String phonenumber = cursor.getString(cursor.getColumnIndex("data1"));
            this.StoreContacts.add(name + " : " + phonenumber);
        }
        cursor.close();
    }


    private void getRemitterValidate(String userName) {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("MobileNumber", userName);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<ValidateRemitter> call = apiservice.getValidate(body);
        call.enqueue(new Callback<ValidateRemitter>() {
            @Override
            public void onResponse(Call<ValidateRemitter> call2, Response<ValidateRemitter> response) {
                if (response.body() != null) {
                    if (MoneyTransferActivity.this.progressDialog != null && MoneyTransferActivity.this.progressDialog.isShowing()) {
                        MoneyTransferActivity.this.progressDialog.dismiss();
                    }
                     if (!response.body().getResponse().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                        if (response.body().getResponse().getStatusCode().equals("2")) {
                            Intent intent = new Intent(MoneyTransferActivity.this, RemitterRegistrationActivity.class);
                            intent.putExtra("MOBILE", MoneyTransferActivity.this.edit_mobile_number.getText().toString());
                            MoneyTransferActivity.this.startActivity(intent);
                            return;
                        }
                        ConstantClass.displayMessageDialog(MoneyTransferActivity.this, "", response.body().getResponse().getMessage());
                        return;
                    } else if (response.body().getResponse().getData() != null) {
                        PrefUtils.saveToPrefs(MoneyTransferActivity.this, "senderId", response.body().getResponse().getData().getRemitterID());
                        Intent intent2 = new Intent(MoneyTransferActivity.this, BeneficieryActivity.class);
                        intent2.putExtra("MOBILE", MoneyTransferActivity.this.edit_mobile_number.getText().toString());
                        intent2.putExtra("REMITTER", response.body().getResponse().getData().getName());
                        MoneyTransferActivity.this.startActivity(intent2);
                        return;
                    } else {
                        return;
                    }
                }
                if (MoneyTransferActivity.this.progressDialog != null && MoneyTransferActivity.this.progressDialog.isShowing()) {
                    MoneyTransferActivity.this.progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(MoneyTransferActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ValidateRemitter> call2, Throwable t) {
                if (MoneyTransferActivity.this.progressDialog != null && MoneyTransferActivity.this.progressDialog.isShowing()) {
                    MoneyTransferActivity.this.progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @NonNull
    @Override
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
        return super.getOnBackInvokedDispatcher();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    private void setupWindowAnimations() {
        getWindow().setEnterTransition(new Explode());
    }
}