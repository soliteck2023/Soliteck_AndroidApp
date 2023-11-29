package com.example.api_call;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MobileRechargePlansAdapter extends RecyclerView.Adapter<MobileRechargePlansAdapter.MyViewHolder>  {
    private Context context;
    List<MobileData> listMobileplans;
    SelectPlanfromBack selectPlan;

    public void ChoosePlan(SelectPlanfromBack selectPlan) {
        this.selectPlan = selectPlan;
    }

    public interface SelectPlanfromBack {
        void selectPlanfromlist(int i);
    }

    public MobileRechargePlansAdapter(Context context, List<MobileData> listMobileplans) {
        this.context = context;
        this.listMobileplans = listMobileplans;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_mobile_plans, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        MobileData c = this.listMobileplans.get(position);
        holder.text_amount.setText(c.getRs());
        holder.text_desc.setText(c.getDesc());
        holder.card_plans.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.adapters.MobileRechargePlansAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MobileRechargePlansAdapter.this.selectPlan.selectPlanfromlist(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMobileplans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card_plans;
        ImageView imge_operator;
        TextView text_amount;
        TextView text_desc;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            MobileRechargePlansAdapter.this = this$0;
            this.text_amount = (TextView) itemView.findViewById(R.id.text_amount);
            this.text_desc = (TextView) itemView.findViewById(R.id.text_desc);
            this.card_plans = (CardView) itemView.findViewById(R.id.card_plans);
        }
    }

}
