package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LedgerReportAdapter extends RecyclerView.Adapter<LedgerReportAdapter.MyViewHolder>{

    private Context context;
    List<ledgerTransactionReport> listSatetments;

    public LedgerReportAdapter(Context context, List<ledgerTransactionReport> listSatetments) {
        this.context = context;
        this.listSatetments = listSatetments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_ledger_transaction_reports, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ledgerTransactionReport earnData = this.listSatetments.get(position);
        String CreatedDate = earnData.getTxnDate();
        String[] part = CreatedDate.split("T");
        String Date = part[0];

        try {
            holder.text_transid.setText("Txn Id: " + earnData.getTransactionId());
            holder.text_paymode.setText("Operator Name: " + earnData.getOperator());
            holder.text_status_.setText(earnData.getStatus());
            holder.text_date_time_.setText(Date);
            holder.text_amount_.setText("Rs " + earnData.getAmount());
            holder.text_refid.setText("Ref No: " + earnData.getRefNumber());
            holder.text_mob_.setText("" + earnData.getUniqueCode());
            holder.text_retailerNumber.setText("Receiver Details: " + earnData.getReceiverDetails());
            holder.text_commission.setText("Commission: " + earnData.getCommission());
            holder.text_servicecharge.setText("Service charge: " + earnData.getServicecharge());
            holder.text_gst.setText("GST: " + earnData.getGST());
            holder.text_tds.setText("TDS: " + earnData.getTDS());
            holder.text_creditAmount.setText("Opening Bal: " + earnData.getMBBefore());
            holder.text_debitAmount.setText("Closing Bal: " + earnData.getMBAfter());


            if ("true".equals(earnData.getCR())) {
                holder.credit_debit.setText("Credit");
                holder.credit_debit.setTextColor(this.context.getResources().getColor(R.color.dark_green));
            } else {
                holder.credit_debit.setText("Debit");
                holder.credit_debit.setTextColor(this.context.getResources().getColor(R.color.colorAccent));
            }


//            holder.text_effecativeBal.setText("Effective Bal.: " + earnData.getEffecativeBal());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (earnData.getStatus().trim().toLowerCase().equals("success")) {
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.dark_green));
        } else {
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.colorAccent));
        }


    }

    @Override
    public int getItemCount() {
        return listSatetments.size();
    }
    public void filter(List<ledgerTransactionReport> listnew_banks) {
        this.listSatetments = listnew_banks;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_amount_;
        TextView text_commission;
        TextView text_creditAmount;
        TextView text_date_time_;
        TextView text_debitAmount;
        TextView text_effecativeBal;
        TextView text_gst;
        TextView text_mob_;
        TextView text_paymode;
        TextView text_refid;
        TextView text_retailerNumber;
        TextView text_servicecharge;
        TextView text_status_;
        TextView text_tds;
        TextView text_transid;
        TextView credit_debit;
        TextView text_Debit;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            LedgerReportAdapter.this = this$0;
            this.text_transid = (TextView) itemView.findViewById(R.id.text_transid);
            this.text_retailerNumber = (TextView) itemView.findViewById(R.id.text_retailerNumber);
            this.text_paymode = (TextView) itemView.findViewById(R.id.text_paymode);
            this.text_status_ = (TextView) itemView.findViewById(R.id.text_status_);
            this.text_refid = (TextView) itemView.findViewById(R.id.text_refid);
            this.text_amount_ = (TextView) itemView.findViewById(R.id.text_amount_);
            this.text_date_time_ = (TextView) itemView.findViewById(R.id.text_date_time_);
            this.text_mob_ = (TextView) itemView.findViewById(R.id.text_mob_);
            this.text_commission = (TextView) itemView.findViewById(R.id.text_commission);
            this.text_servicecharge = (TextView) itemView.findViewById(R.id.text_servicecharge);
            this.text_gst = (TextView) itemView.findViewById(R.id.text_gst);
            this.text_tds = (TextView) itemView.findViewById(R.id.text_tds);
            this.text_debitAmount = (TextView) itemView.findViewById(R.id.text_debitAmount);
            this.text_effecativeBal = (TextView) itemView.findViewById(R.id.text_effecativeBal);
            this.text_creditAmount = (TextView) itemView.findViewById(R.id.text_creditAmount);
            this.credit_debit = (TextView) itemView.findViewById(R.id.text_cr_dr_);
            this.text_Debit = (TextView) itemView.findViewById(R.id.text_debituser);


        }
    }

}
