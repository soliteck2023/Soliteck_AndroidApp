package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LatestReportAdapter extends RecyclerView.Adapter<LatestReportAdapter.MyViewHolder>{
    private Context context;
    List<LatestTransaction> listSatetments;

    public LatestReportAdapter(Context context, List<LatestTransaction> listSatetments) {
        this.context = context;
        this.listSatetments = listSatetments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_latest_reports, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LatestTransaction earnData = this.listSatetments.get(position);
        try {
            holder.text_transid.setText("Txn Id: " + earnData.getTransactionNumber());
            holder.text_paymode.setText("Operator Name: " + earnData.getOperatorName());
            holder.text_status_.setText(earnData.getStatus());
            holder.text_date_time_.setText(earnData.getDateTime());
            holder.text_amount_.setText("Rs " + earnData.getAmount());
            holder.text_refid.setText("Ref No: " + earnData.getRefNumber());
            holder.text_mob_.setText("" + earnData.getSenderMobile());
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

    public void setNewList(List<LatestTransaction> list_new) {
        this.listSatetments = list_new;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_amount_;
        TextView text_date_time_;
        TextView text_mob_;
        TextView text_paymode;
        TextView text_refid;
        TextView text_status_;
        TextView text_transid;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            LatestReportAdapter.this = this$0;
            this.text_transid = (TextView) itemView.findViewById(R.id.text_transid);
            this.text_paymode = (TextView) itemView.findViewById(R.id.text_paymode);
            this.text_status_ = (TextView) itemView.findViewById(R.id.text_status_);
            this.text_refid = (TextView) itemView.findViewById(R.id.text_refid);
            this.text_amount_ = (TextView) itemView.findViewById(R.id.text_amount_);
            this.text_date_time_ = (TextView) itemView.findViewById(R.id.text_date_time_);
            this.text_mob_ = (TextView) itemView.findViewById(R.id.text_mob_);
        }
    }

}
