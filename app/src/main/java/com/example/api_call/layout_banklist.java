package com.example.api_call;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class layout_banklist extends AppCompatActivity implements BankListAdapter.SelectBankFromList {
    private List<MBankListResponse> listBanks = new ArrayList();
    private BankListAdapter bankListAdapter;
    private AlertDialog alertDialog;
    private RecyclerView view_bank_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_banklist);

        view_bank_list = (RecyclerView) findViewById(R.id.view_bank_list);
        ImageView image_delete = (ImageView) findViewById(R.id.image_delete);
        EditText edit_searchbank = (EditText) findViewById(R.id.edit_searchbank);
        AlertDialog.Builder builder = new AlertDialog.Builder(layout_banklist.this);
             layout_banklist.this.alertDialog = builder.create();
            layout_banklist.this.alertDialog.setView(view_bank_list);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.4.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        layout_banklist.this.alertDialog.dismiss();
                    }
                });
        edit_searchbank.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.4.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               layout_banklist.this.filter(s.toString());
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        image_delete.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BeneficieryActivity.4.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v2) {
                layout_banklist.this.alertDialog.dismiss();
            }
        });

        layout_banklist beneficieryActivity = layout_banklist.this;
        layout_banklist beneficieryActivity2 = layout_banklist.this;
//        beneficieryActivity.bankListAdapter = new BankListAdapter(beneficieryActivity2, beneficieryActivity2.listBanks);
//        layout_banklist.this.view_bank_list.setLayoutManager(new LinearLayoutManager(layout_banklist.this));
//        layout_banklist.this.view_bank_list.setAdapter(layout_banklist.this.bankListAdapter);
//        layout_banklist.this.bankListAdapter.setBankListener((BankListAdapter.SelectBankFromList) layout_banklist.this);
//        layout_banklist.this.alertDialog.show();

        layout_banklist.this.view_bank_list.setAdapter(layout_banklist.this.bankListAdapter);
        layout_banklist.this.view_bank_list.setLayoutManager(new LinearLayoutManager(layout_banklist.this));
        layout_banklist.this.view_bank_list.setAdapter(layout_banklist.this.bankListAdapter);
        layout_banklist.this.alertDialog.show();
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

    @Override
    public void selectbank(String str, String str2) {

    }
}