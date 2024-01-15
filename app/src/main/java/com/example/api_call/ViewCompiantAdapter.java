package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewCompiantAdapter extends RecyclerView.Adapter<ViewCompiantAdapter.MyViewHolder> {
    private Context context;
    List<complaintData> complaintDataList;

    public ViewCompiantAdapter(Context context, List<complaintData> complaintDataList) {
        this.context = context;
        this.complaintDataList = complaintDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_compaint_view2, parent, false);
        return new ViewCompiantAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        complaintData c = this.complaintDataList.get(position);
        try{
            holder.status.setText("Status: " + c.getStatus());
            holder.complaintType.setText("Complaint Type: " + c.getComplaintType());
            holder.description.setText("Description: " + c.getDescription());
            holder.raisedate.setText("Raise Date: " + c.getCurrentOwner());
            holder.updatedate.setText("Update Date: " +c.getUpdatedDate());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public int getItemCount() {
        return complaintDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        TextView raisedate;
        TextView updatedate;
        TextView status;
        TextView complaintType;
        TextView service_charge;


        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            ViewCommissionAdapter.this = this$0;
            this.description = (TextView) itemView.findViewById(R.id.description);
            this.raisedate = (TextView) itemView.findViewById(R.id.raisedate);
            this.updatedate = (TextView) itemView.findViewById(R.id.updatedDate);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.complaintType = (TextView) itemView.findViewById(R.id.complaintType);
            this.service_charge = (TextView) itemView.findViewById(R.id.service_charge);


        }
    }

}
