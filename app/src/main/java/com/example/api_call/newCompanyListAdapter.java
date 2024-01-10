package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class newCompanyListAdapter extends RecyclerView.Adapter<newCompanyListAdapter.MyViewHolder> {

    private Context context;
    List<BankListResponse> listUserBanks = new ArrayList<>();
    private List<servicelist> services;

    public newCompanyListAdapter(Context context, List<BankListResponse> listUserBanks) {
        this.context = context;
        this.listUserBanks = listUserBanks;
    }

    @NonNull
    @Override
    public newCompanyListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_company_bank_list, parent, false);
        return new newCompanyListAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull newCompanyListAdapter.MyViewHolder holder, int position) {
        BankListResponse earndata = this.listUserBanks.get(position);
        servicelist earndata2 = this.services.get(position);
        try {
            holder.id.setText(" " + earndata.getId());
            holder.bankName.setText("Bank Name: " + earndata.getBankName());
            holder.ifsc.setText("IFSC Code: " + earndata.getIfsc());
            holder.branch.setText("Branch Name " + earndata.getBranch());
            holder.accountName.setText("Account Number: " + earndata.getAccountName());
            holder.serviceName.setText(" " + earndata.getServiceName());
            holder.charges.setText(" " + earndata.getCharges());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        try {
//            holder.serviceName.setText(" " + earndata2.getServiceName());
//            holder.charges.setText(" " + earndata2.getCharges());
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        if (earndata.getServiceName() == earndata2.getServiceName()){

            holder.serviceName.setText(" " + earndata2.getServiceName());
            holder.charges.setText(" " + earndata2.getCharges());
        }


//       if (earndata.getId()==1){
//         holder.text_IMPS.setVisibility(View.VISIBLE);
//             holder.serviceId.setText("Service Name: " + earndata.getServiceName());
//            holder.charges.setText("service Charge:  " + earndata.getCharges());
//
//       }

    }

    @Override
    public int getItemCount() {
        return listUserBanks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView bankName;
        TextView ifsc;
        TextView branch;
        TextView accountName;
        TextView serviceId;
        TextView serviceName;
        TextView charges;

        LinearLayout text_IMPS;



        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);

            this.id =(TextView) itemView.findViewById(R.id.id);
            this.bankName =(TextView) itemView.findViewById(R.id.TextBankName);
            this.ifsc =(TextView) itemView.findViewById(R.id.ifsc);
            this.branch =(TextView) itemView.findViewById(R.id.TextBranchName);
            this.accountName =(TextView) itemView.findViewById(R.id.TextAccNo);
//            this.serviceId =(TextView) itemView.findViewById(R.id.serviceId);
            this.serviceName =(TextView) itemView.findViewById(R.id.serviceName);
            this.charges =(TextView) itemView.findViewById(R.id.charges);
            this.text_IMPS =(LinearLayout) itemView.findViewById(R.id.text_IMPS);




        }
    }

}
