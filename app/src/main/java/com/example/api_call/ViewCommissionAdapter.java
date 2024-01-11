package com.example.api_call;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
            String productNameText = "Product Name: " + c.getProduct();
            SpannableString productNameSpannable = new SpannableString(productNameText);
            productNameSpannable.setSpan(new StyleSpan(Typeface.BOLD), 0, 13, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            holder.product_name.setText(productNameSpannable);

            holder.product_name.setText("Product Name: "  + c.getProduct());

            holder.operator_name.setText("Operator Name: " + c.getOperator());
            holder.discription.setTypeface(null, Typeface.BOLD);
            holder.discription.setText("Discription: " + c.getDescription());
//            holder.commission_charge.setText("commission: " + c.getCommission());
            holder.service_charge.setText("service_charge:" + c.getServicecharge());


            if (c.getIsfixed()== "true") {
                holder.commission_charge.setTypeface(null, Typeface.BOLD);
                holder.commission_charge.setText("commission: Rs"  + c.getCommission());
                if (c.getIsapplicable() == "true") {
                    holder.commission_charge.setTextColor(ContextCompat.getColor(context, R.color.dark_green));
                } else {
                    holder.commission_charge.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                }

            } else{
                holder.commission_charge.setTypeface(null, Typeface.BOLD);
                holder.commission_charge.setText("Commission: " + c.getCommission() + " %");
                if (c.getIsapplicable() == "true") {
                    holder.commission_charge.setTextColor(ContextCompat.getColor(context, R.color.dark_green));
                } else {
                    holder.commission_charge.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                }
            }

            if (c.getServicecharge()=="true"){
                holder.service_charge.setText("Service charge: Rs"  + c.getCommission());
                if (c.getServicecharge() == "true") {
                    holder.service_charge.setTextColor(ContextCompat.getColor(context, R.color.dark_green));
                } else {
                    holder.service_charge.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                }

            }else{
                holder.service_charge.setTypeface(null, Typeface.BOLD);
                holder.service_charge.setText("Service charge: " + c.getCommission() + " %");
                if (c.getIsapplicable() == "true") {
                    holder.service_charge.setTextColor(ContextCompat.getColor(context, R.color.dark_green));
                } else {
                    holder.service_charge.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                }
            }

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
        TextView product_name;
        TextView operator_name;
        TextView discription;
        TextView commission_charge;
        TextView service_charge;


        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            ViewCommissionAdapter.this = this$0;
            this.product_name = (TextView) itemView.findViewById(R.id.product_name);
            this.operator_name = (TextView) itemView.findViewById(R.id.operator_name);
            this.discription = (TextView) itemView.findViewById(R.id.discription);
            this.commission_charge = (TextView) itemView.findViewById(R.id.commission_charge);
            this.service_charge = (TextView) itemView.findViewById(R.id.service_charge);


        }
    }


}
