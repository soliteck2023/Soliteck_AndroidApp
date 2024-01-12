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
import java.util.Map;

public class newCompanyListAdapter extends RecyclerView.Adapter<newCompanyListAdapter.MyViewHolder> {

    private Context context;
    List<BankListResponse> listUserBanks = new ArrayList<>();

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
        try {
            holder.id.setText(" " + earndata.getId());
            holder.bankName.setText("Bank Name: " + earndata.getBankName());
            holder.ifsc.setText("IFSC Code: " + earndata.getIfsc());
            holder.branch.setText("Branch Name " + earndata.getBranch());
            holder.accountName.setText("Account Number: " + earndata.getAccountName());
            holder.text_IMPS.setVisibility(View.VISIBLE);
            holder.text_IMPS.removeAllViews(); // Clear previous views

            for (Map.Entry<String, String> entry : earndata.getServices().entrySet()) {
                String serviceName = entry.getKey();
                String charges = entry.getValue();

                // Create new LinearLayout to hold service and charges
                LinearLayout serviceLayout = new LinearLayout(holder.itemView.getContext());
                serviceLayout.setOrientation(LinearLayout.HORIZONTAL);

                // Create new TextViews for service and charges
                TextView serviceNameTextView = new TextView(holder.itemView.getContext());
                TextView chargesTextView = new TextView(holder.itemView.getContext());

                // Set text for service and charges
                serviceNameTextView.setText("Service Name: " + serviceName + "  ");
                chargesTextView.setText("Charges: " + charges);

                // Add new TextViews to the serviceLayout
                serviceLayout.addView(serviceNameTextView);
                serviceLayout.addView(chargesTextView);

                // Add the serviceLayout to the text_IMPS LinearLayout
                holder.text_IMPS.addView(serviceLayout);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
