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

public class DTHRechargeInfoAdapter extends RecyclerView.Adapter<DTHRechargeInfoAdapter.MyViewHolder>{
    private Context context;
    List<DTHData> listDTHplansinfo;

    public DTHRechargeInfoAdapter(Context context, List<DTHData> listDTHplansinfo) {
        this.context = context;
        this.listDTHplansinfo = listDTHplansinfo;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_dth_info, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DTHData c = this.listDTHplansinfo.get(position);
        holder.text_cust_name.setText(c.getCustomerName());
        holder.text_recharge.setText("Rs " + c.getMonthlyRecharge());
        holder.text_balance.setText("Rs " + c.getBalance());
        holder.text_plan_detail.setText("" + c.getPlanname());
        holder.text_nextdate.setText("" + c.getNextRechargeDate());
        holder.text_last_recharge_date.setText("" + c.getLastrechargedate());
        holder.text_last_recharge_amt.setText(" Rs " + c.getLastrechargeamount());
        try {
            if (c.getStatus().toLowerCase().equals("active")) {
                holder.text_status.setTextColor(this.context.getResources().getColor(R.color.dark_green));
                holder.text_status.setText(c.getStatus());
                holder.text_balance.setTextColor(this.context.getResources().getColor(R.color.dark_green));
            } else {
                holder.text_status.setTextColor(this.context.getResources().getColor(R.color.colorAccent));
                holder.text_status.setText(c.getStatus());
                holder.text_balance.setTextColor(this.context.getResources().getColor(R.color.colorAccent));
            }
        } catch (Exception e) {
            e.printStackTrace();
            holder.text_status.setText(c.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return listDTHplansinfo.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
//        ImageView imge_operator;
        TextView text_balance;
        TextView text_cust_name;
        TextView text_last_recharge_amt;
        TextView text_last_recharge_date;
        TextView text_nextdate;
        TextView text_plan_detail;
        TextView text_recharge;
        TextView text_status;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            DTHRechargeInfoAdapter.this = this$0;
            this.text_status = (TextView) itemView.findViewById(R.id.text_status);
            this.text_cust_name = (TextView) itemView.findViewById(R.id.text_cust_name);
            this.text_recharge = (TextView) itemView.findViewById(R.id.text_recharge);
            this.text_balance = (TextView) itemView.findViewById(R.id.text_balance);
            this.text_plan_detail = (TextView) itemView.findViewById(R.id.text_plan_detail);
            this.text_nextdate = (TextView) itemView.findViewById(R.id.text_nextdate);
            this.text_last_recharge_date = (TextView) itemView.findViewById(R.id.text_last_recharge_date);
            this.text_last_recharge_amt = (TextView) itemView.findViewById(R.id.text_last_recharge_amt);
        }
    }

}
