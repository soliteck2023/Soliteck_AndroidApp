package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EarningReportAdapter extends RecyclerView.Adapter<EarningReportAdapter.MyViewHolder> {
    private Context context;
    List<MyEarningTransaction> listSatetments;

    public EarningReportAdapter(Context context, List<MyEarningTransaction> listSatetments) {
        this.context = context;
        this.listSatetments = listSatetments;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_myearning_reports, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MyEarningTransaction earnData = this.listSatetments.get(position);
        try {
            holder.text_totalPrimary.setText("Total Primary: " + earnData.getTotalPrimary());
            holder.text_totalTransctionAmount.setText("Total Transaction Amount: " + earnData.getTotalTransctionAmount());
            holder.text_actualcommission.setText("Actual commission: " + earnData.getActualcommission());
            holder.text_totalcommission.setText("Total commission: " + earnData.getTotalcommission());
            holder.text_gst.setText("GST: " + earnData.getGst());
            holder.text_tds.setText("TDS: " + earnData.getTds());
            holder.text_totalNetworkLoad.setText("Total Network Load: " + earnData.getTotalNetworkLoad());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listSatetments.size();
    }

    public void filter(List<MyEarningTransaction> listnew_banks) {
        this.listSatetments = listnew_banks;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_actualcommission;
        TextView text_gst;
        TextView text_tds;
        TextView text_totalNetworkLoad;
        TextView text_totalPrimary;
        TextView text_totalTransctionAmount;
        TextView text_totalcommission;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            EarningReportAdapter.this = this$0;
            this.text_totalPrimary = (TextView) itemView.findViewById(R.id.text_totalPrimary);
            this.text_totalTransctionAmount = (TextView) itemView.findViewById(R.id.text_totalTransctionAmount);
            this.text_actualcommission = (TextView) itemView.findViewById(R.id.text_actualcommission);
            this.text_totalcommission = (TextView) itemView.findViewById(R.id.text_totalcommission);
            this.text_gst = (TextView) itemView.findViewById(R.id.text_gst);
            this.text_tds = (TextView) itemView.findViewById(R.id.text_tds);
            this.text_totalNetworkLoad = (TextView) itemView.findViewById(R.id.text_totalNetworkLoad);
        }
    }

}
