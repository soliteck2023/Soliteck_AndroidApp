package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaymentReceivedReportAdapter extends RecyclerView.Adapter<PaymentReceivedReportAdapter.MyViewHolder>{

    private Context context;
    List<NetworkBalanceReceivedReport> listSatetments;

    public PaymentReceivedReportAdapter(Context context, List<NetworkBalanceReceivedReport> listSatetments) {
        this.context = context;
        this.listSatetments = listSatetments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_payment_received, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NetworkBalanceReceivedReport earnData = this.listSatetments.get(position);
        try {
            holder.text_date_time_.setText(earnData.getCreateDate());
            holder.text_amount_.setText("Rs " + earnData.getCr());
            holder.text_type.setText("Credit");
            holder.text_currentBal.setText("Rs " + earnData.getCurrentBal());
            holder.text_newBal.setText("Rs " + earnData.getBalanceAfter());
            holder.text_transferBy.setText(earnData.getTransferdByName() + " " + earnData.getTransferBy());
            holder.text_transferTo.setText(earnData.getTransferTo());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listSatetments.size();
    }
    public void filter(List<NetworkBalanceReceivedReport> listnew_banks) {
        this.listSatetments = listnew_banks;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_amount_;
        TextView text_currentBal;
        TextView text_date_time_;
        TextView text_newBal;
        TextView text_transferBy;
        TextView text_transferTo;
        TextView text_type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            PaymentReceivedReportAdapter.this = this$0;
            this.text_transferBy = (TextView) itemView.findViewById(R.id.text_transferBy);
            this.text_transferTo = (TextView) itemView.findViewById(R.id.text_transferTo);
            this.text_newBal = (TextView) itemView.findViewById(R.id.text_newBal);
            this.text_currentBal = (TextView) itemView.findViewById(R.id.text_currentBal);
            this.text_amount_ = (TextView) itemView.findViewById(R.id.text_amount_);
            this.text_date_time_ = (TextView) itemView.findViewById(R.id.text_date_time_);
            this.text_type = (TextView) itemView.findViewById(R.id.text_type);
        }
    }

}
