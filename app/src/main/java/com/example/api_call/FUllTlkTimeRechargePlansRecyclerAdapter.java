package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FUllTlkTimeRechargePlansRecyclerAdapter extends RecyclerView.Adapter<FUllTlkTimeRechargePlansRecyclerAdapter.ViewHolder>{

    private static Context mContext;
    private int PLANREQUESTCODE = 200;
    List<FULLTT> data;
    OnClickData onClickData;
    public interface OnClickData {
        void getdata(FULLTT fulltt);
    }
    public FUllTlkTimeRechargePlansRecyclerAdapter(Context aContext, List<FULLTT> data, OnClickData onClickData) {
        mContext = aContext;
        this.onClickData = onClickData;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_mobilerecharge_plans_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTxtAmount.setText(mContext.getResources().getString(R.string.Rs) + " " + String.valueOf(this.data.get(position).getRs()));
        holder.mTxtDescriptionType.setText(this.data.get(position).getDesc());
        holder.mTxtValidaty.setText(this.data.get(position).getValidity());
        holder.mTxtTalkTime.setText("Last Update : " + this.data.get(position).getLastUpdate());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtAmount;
        public TextView mTxtDescriptionType;
        public TextView mTxtTalkTime;
        public TextView mTxtValidaty;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
//            FUllTlkTimeRechargePlansRecyclerAdapter.this = this$0;
            this.mTxtTalkTime = (TextView) itemView.findViewById(R.id.txtTalkTime);
            this.mTxtDescriptionType = (TextView) itemView.findViewById(R.id.txtDescriptionType);
            this.mTxtAmount = (TextView) itemView.findViewById(R.id.txtAmount);
            this.mTxtValidaty = (TextView) itemView.findViewById(R.id.txtValidaty);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.adapters.rechargeplan.FUllTlkTimeRechargePlansRecyclerAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    FUllTlkTimeRechargePlansRecyclerAdapter.this.onClickData.getdata(FUllTlkTimeRechargePlansRecyclerAdapter.this.data.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }

}
