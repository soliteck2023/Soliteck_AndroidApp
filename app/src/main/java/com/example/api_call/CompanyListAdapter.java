package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.MyViewHolder>{
    private Context context;
    List<CompanyBankResponse> listSatetments;

    public CompanyListAdapter(Context context, List<CompanyBankResponse> listSatetments) {
        this.listSatetments = listSatetments;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_company_bank_list, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        CompanyBankResponse earnData = this.listSatetments.get(position);
        holder.TextBranchName.setText("Branch: " + earnData.getBranch());
        holder.TextAccNo.setText("Acc No: " + earnData.getAccountNumber() + " | " + earnData.getIfsc());
        holder.TextBankName.setText(earnData.getBankName());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listSatetments.size();
    }

    public void setNewList(List<CompanyBankResponse> list_new) {
        this.listSatetments = list_new;
        notifyDataSetChanged();
    }

    /* loaded from: classes17.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TextAccNo;
        TextView TextBankName;
        TextView TextBranchName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            CompanyListAdapter.this = this$0;
            this.TextBankName = (TextView) itemView.findViewById(R.id.TextBankName);
            this.TextAccNo = (TextView) itemView.findViewById(R.id.TextAccNo);
            this.TextBranchName = (TextView) itemView.findViewById(R.id.TextBranchName);
        }
    }
}
