package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ViewCommissionAdapter extends RecyclerView.Adapter<ViewCommissionAdapter.MyViewHolder>{

    private Context context;
    List<CommissionData> listCommission;

    public ViewCommissionAdapter(Context context, List<CommissionData> listCommission) {
        this.context = context;
        this.listCommission = listCommission;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_commission_view2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CommissionData c = this.listCommission.get(position);
        try{
            holder.operator.setText("operator Id: " + c.getOperator());
            holder.serviceCharge.setText("serviceCharge: " +c.getServicecharge());
            holder.commission.setText("commission:" +c.getCommission());
            holder.isFixed.setText("isfixed:" + c.getIsfixed());
            holder.isApplicable.setText("isapplicable:" +c.getIsapplicable());
            holder.operator.setText("operator Id: " + c.getOperator());
            holder.serviceCharge.setText("serviceCharge: " +c.getServicecharge());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }



//        holder.text_margin.setText("" + String.format("%.2f", new Double(c.getCommPer())));
//        holder.text_surcharge.setText("" + String.format("%.2f", new Double(c.getServiceChargePer())));
//        holder.text_operator.setText(c.getOperatorName());
//        holder.text_margin_val.setText("" + String.format("%.2f", new Double(c.getCommVal())));
//        holder.text_surcharge_val.setText("" + String.format("%.2f", new Double(c.getServiceChargeVal())));
//        com.bumptech.glide.request.RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.app_logo);
//        requestOptions.error(R.drawable.app_logo);
//        Glide.with(this.context).setDefaultRequestOptions(requestOptions).load(ConstantClass.IMAGEWEBSERVICEURL + c.getImageUrl()).into(holder.imge_operator);
    }

    @Override
    public int getItemCount() {
        return listCommission.size();
    }

    public void filter(List<CommissionData> listCommission){
        this.listCommission = listCommission;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
      TextView fName;
        TextView uniqueCode;
        TextView commission;
        TextView isFixed;
        TextView isApplicable;
        TextView serviceCharge;
        TextView isServiceApplicable;
        TextView isServiceFixed;
        TextView product;
        TextView description;
        TextView operator;


        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            ViewCommissionAdapter.this = this$0;
            this.fName = (TextView) itemView.findViewById(R.id.username);
            this.uniqueCode = (TextView) itemView.findViewById(R.id.mobile_no);
            this.commission = (TextView) itemView.findViewById(R.id.commission);
            this.isFixed = (TextView) itemView.findViewById(R.id.isFixed);
            this.isApplicable = (TextView) itemView.findViewById(R.id.isApplicable);
            this.serviceCharge = (TextView) itemView.findViewById(R.id.serviceCharge);
            this.isServiceFixed =  (TextView) itemView.findViewById(R.id.isServiceFixed);
            this.isServiceApplicable = (TextView) itemView.findViewById(R.id.isServiceApplicable);
            this.product = (TextView) itemView.findViewById(R.id.product);
            this.description = (TextView) itemView.findViewById(R.id.description);
            this.operator = (TextView) itemView.findViewById(R.id.operator);

        }
    }


}
