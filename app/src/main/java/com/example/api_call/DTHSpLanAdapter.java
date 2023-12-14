package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DTHSpLanAdapter extends RecyclerView.Adapter<DTHSpLanAdapter.ViewHolder> {

    List<MobilePlanRecord> data;
    private Context mContext;
    OnclickData onclickData;

    public interface OnclickData {
        void getamount(MobilePlanRecord mobilePlanRecord);
    }

    public DTHSpLanAdapter(Context aContext, List<MobilePlanRecord> data, OnclickData onclickData) {
        this.mContext = aContext;
        this.data = data;
        this.onclickData = onclickData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.r_offer_custom_recharge_plans_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.mTxtAmount.setText("Amount : " + this.mContext.getResources().getString(R.string.Rs) + " " + String.valueOf(this.data.get(position).getRs()));
        } catch (NullPointerException e) {
            holder.mTxtAmount.setText(this.mContext.getResources().getString(R.string.Rs) + " -");
        }
        try {
            holder.mTxtDescription.setText("Description : " + this.data.get(position).getDesc());
        } catch (NullPointerException e2) {
            holder.mTxtDescription.setText("Description : " + e2.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtAmount;
        private TextView mTxtDescription;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
//            DTHSpLanAdapter.this = this$0;
            this.mTxtAmount = (TextView) itemView.findViewById(R.id.TxtAmount);
            this.mTxtDescription = (TextView) itemView.findViewById(R.id.TxtDescription);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.adapters.DTHSpLanAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    DTHSpLanAdapter.this.onclickData.getamount(DTHSpLanAdapter.this.data.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }

}
