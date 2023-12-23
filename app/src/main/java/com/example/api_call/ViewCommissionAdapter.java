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
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_commission_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CommissionData c = this.listCommission.get(position);
        holder.text_margin.setText("" + String.format("%.2f", new Double(c.getCommPer())));
        holder.text_surcharge.setText("" + String.format("%.2f", new Double(c.getServiceChargePer())));
        holder.text_operator.setText(c.getOperatorName());
        holder.text_margin_val.setText("" + String.format("%.2f", new Double(c.getCommVal())));
        holder.text_surcharge_val.setText("" + String.format("%.2f", new Double(c.getServiceChargeVal())));
        com.bumptech.glide.request.RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.app_logo);
        requestOptions.error(R.drawable.app_logo);
        Glide.with(this.context).setDefaultRequestOptions(requestOptions).load(ConstantClass.IMAGEWEBSERVICEURL + c.getImageUrl()).into(holder.imge_operator);
    }

    @Override
    public int getItemCount() {
        return listCommission.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imge_operator;
        TextView text_margin;
        TextView text_margin_val;
        TextView text_operator;
        TextView text_surcharge;
        TextView text_surcharge_val;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            ViewCommissionAdapter.this = this$0;
            this.imge_operator = (ImageView) itemView.findViewById(R.id.imge_operator);
            this.text_margin = (TextView) itemView.findViewById(R.id.text_margin);
            this.text_margin_val = (TextView) itemView.findViewById(R.id.text_margin_val);
            this.text_surcharge = (TextView) itemView.findViewById(R.id.text_surcharge);
            this.text_surcharge_val = (TextView) itemView.findViewById(R.id.text_surcharge_val);
            this.text_operator = (TextView) itemView.findViewById(R.id.text_operator);
        }
    }

    public void filter(List<CommissionData> listCommission) {
        this.listCommission = listCommission;
        notifyDataSetChanged();
    }
}
