package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StatementsReportAdapter extends RecyclerView.Adapter<StatementsReportAdapter.MyViewHolder> {
    private Context context;
    List<StatementReport> listSatetments;

    public StatementsReportAdapter(Context context, List<StatementReport> listSatetments) {
        this.context = context;
        this.listSatetments = listSatetments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_settlement, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StatementReport earnData = this.listSatetments.get(position);
        try {
            holder.text_transid.setText("Order Id: " + earnData.getOrderId());
            holder.text_paymode.setText("TxnType: " + earnData.getTxnType());
            holder.text_status_.setText(earnData.getTxnStatus());
            holder.text_date_time_.setText(earnData.getTxnDate() + "," + earnData.getTxnTime());
            holder.text_amount_.setText("Rs " + earnData.getAmount());
            holder.text_oldbal_.setText("New Bal:  Rs " + earnData.getClosingBal());
            holder.text_narration.setText(earnData.getNarrationText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (earnData.getTxnStatus().trim().toLowerCase().equals("success")) {
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.dark_green));
        } else {
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public int getItemCount() {
        return listSatetments.size();
    }
    public void filter(List<StatementReport> listnew_banks) {
        this.listSatetments = listnew_banks;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imge_operator;
        TextView text_amount_;
        TextView text_commission_;
        TextView text_date_time_;
        TextView text_mob_;
        TextView text_narration;
        TextView text_newbal_;
        TextView text_oldbal_;
        TextView text_paymode;
        TextView text_reason_;
        TextView text_status_;
        TextView text_transid;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            StatementsReportAdapter.this = this$0;
            this.text_transid = (TextView) itemView.findViewById(R.id.text_transid);
            this.text_paymode = (TextView) itemView.findViewById(R.id.text_paymode);
            this.text_status_ = (TextView) itemView.findViewById(R.id.text_status_);
//            this.text_reason_ = (TextView) itemView.findViewById(R.id.text_reason_);
            this.text_amount_ = (TextView) itemView.findViewById(R.id.text_amount_);
            this.text_commission_ = (TextView) itemView.findViewById(R.id.text_commission_);
//            this.text_oldbal_ = (TextView) itemView.findViewById(R.id.text_oldbal_);
//            this.text_newbal_ = (TextView) itemView.findViewById(R.id.text_newbal_);
            this.text_date_time_ = (TextView) itemView.findViewById(R.id.text_date_time_);
            this.text_mob_ = (TextView) itemView.findViewById(R.id.text_mob_);
//            this.text_narration = (TextView) itemView.findViewById(R.id.text_narration);
        }
    }

}
