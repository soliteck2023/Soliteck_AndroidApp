package com.example.api_call;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionReportAdapter extends RecyclerView.Adapter<TransactionReportAdapter.MyViewHolder> {

    private Context context;
    List<TransactionReport> listSatetments;
    private ItemClickListener itemClickListener;

    public TransactionReportAdapter(Context context, List<TransactionReport> listSatetments) {
        this.context = context;
        this.listSatetments = listSatetments;
    }

    public TransactionReportAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    //    public TransactionReportAdapter(Context context, List<TransactionReport> listSatetments, ItemClickListener itemClickListener) {
//        this.context = context;
//        this.listSatetments = listSatetments;
//        this.itemClickListener = itemClickListener;
//    }

    //    public TransactionReportAdapter(Context context, List<TransactionReport> listSatetments) {
//        this.context = context;
//        this.listSatetments = listSatetments;
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_transaction_reports, parent, false);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TransactionReport earnData = this.listSatetments.get(position);
        try {
            holder.text_transid.setText("Txn Id: " + earnData.getTransactionNumber());
            holder.text_paymode.setText("Operator Name: " + earnData.getOperatorName());
            holder.text_status_.setText(earnData.getStatus());
            holder.text_date_time_.setText(earnData.getTransactionDate());
            holder.text_amount_.setText("Rs " + earnData.getAmount());
            holder.text_refid.setText("Ref No: " + earnData.getRefNumber());
            holder.text_mob_.setText("" + earnData.getSenderMobile());
            holder.text_retailerNumber.setText("Retailer Number: " + earnData.getRetailerNumber());
            holder.text_commission.setText("Commission: " + earnData.getCommission());
            holder.text_servicecharge.setText("Service charge: " + earnData.getServicecharge());
            holder.text_gst.setText("GST: " + earnData.getGst());
            holder.text_tds.setText("TDS: " + earnData.getTds());
            holder.text_creditAmount.setText("Credit Amount: " + earnData.getCreditAmount());
            holder.text_debitAmount.setText("Debit Amount: " + earnData.getDebitAmount());
            holder.text_effecativeBal.setText("Effective Bal.: " + earnData.getEffecativeBal());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (earnData.getStatus().trim().toLowerCase().equals("success")) {
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.dark_green));
        } else {
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.colorAccent));
        }

        holder.View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pendingreceipt_view();
//                TransactionsReportActivity activity = (TransactionsReportActivity) context;
//                FragmentTransaction transaction =activity.getSupportFragmentManager().beginTransaction();
//                Fragment fragment = new layout_view_receipt();
//                Bundle bundle = new Bundle();
//                bundle.putString("id",earnData.getTransactionNumber().toString());
//                fragment.setArguments(bundle);
//                transaction.add(R.id.freme_soli,fragment);
//                transaction.commit();

//                itemClickListener.onItemClick(earnData.getTransactionDate());
//                itemClickListener.onItemClick(earnData.getRefNumber());

//                FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
//                Fragment fragment = new layout_view_receipt();
//                transaction.add(R.id.frame,fragment);

            }
        });
        holder.Raise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, view_receipt_pending.class);
                intent.putExtra("transactionId","earnData.getTransactionNumber()");
                context.startActivity(intent);


            }
        });

    }

//    private void pendingreceipt_view() {
//        HashMap<String, String> body = new HashMap<>();
//        body.put("DeviceId", PrefUtils.getFromPrefs(context.getApplicationContext(), ConstantClass.PROFILEDETAILS.DeviceId, ""));
//        body.put("Token", PrefUtils.getFromPrefs(context.getApplicationContext(), ConstantClass.USERDETAILS.Token, ""));
//        body.put("UserName", PrefUtils.getFromPrefs(context.getApplicationContext(), ConstantClass.PROFILEDETAILS.UserName_, ""));
//        body.put("TransactionIds", "");
//        ApiInterface apiservice = RetrofitHandler.getService2();
//        Call<LedgerReportBase> result = apiservice.GetReceiptReport(body);
//
//        result.enqueue(new Callback<LedgerReportBase>() {
//            @Override
//            public void onResponse(Call<LedgerReportBase> call, Response<LedgerReportBase> response) {
//
//
//            }
//
//            @Override
//            public void onFailure(Call<LedgerReportBase> call, Throwable t) {
//
//            }
//        });
//
//
//    }


    @Override
    public int getItemCount() {
        return listSatetments.size();
    }
    public void filter(List<TransactionReport> listnew_banks) {
        this.listSatetments = listnew_banks;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_amount_;
        TextView Raise,View;
        TextView text_commission;
        TextView text_creditAmount;
        TextView text_date_time_;
        TextView text_debitAmount;
        TextView text_effecativeBal;
        TextView text_gst;
        TextView text_mob_;
        TextView text_paymode;
        TextView text_refid;
        TextView text_retailerNumber;
        TextView text_servicecharge;
        TextView text_status_;
        TextView text_tds;
        TextView text_transid;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            TransactionReportAdapter.this = this$0;
            this.Raise =(TextView) itemView.findViewById(R.id.RAISE);
            this.View =(TextView) itemView.findViewById(R.id.view_raise);
            this.text_transid = (TextView) itemView.findViewById(R.id.text_transid);
            this.text_retailerNumber = (TextView) itemView.findViewById(R.id.text_retailerNumber);
            this.text_paymode = (TextView) itemView.findViewById(R.id.text_paymode);
            this.text_status_ = (TextView) itemView.findViewById(R.id.text_status_);
            this.text_refid = (TextView) itemView.findViewById(R.id.text_refid);
            this.text_amount_ = (TextView) itemView.findViewById(R.id.text_amount_);
            this.text_date_time_ = (TextView) itemView.findViewById(R.id.text_date_time_);
            this.text_mob_ = (TextView) itemView.findViewById(R.id.text_mob_);
            this.text_commission = (TextView) itemView.findViewById(R.id.text_commission);
            this.text_servicecharge = (TextView) itemView.findViewById(R.id.text_servicecharge);
            this.text_gst = (TextView) itemView.findViewById(R.id.text_gst);
            this.text_tds = (TextView) itemView.findViewById(R.id.text_tds);
            this.text_debitAmount = (TextView) itemView.findViewById(R.id.text_debitAmount);
            this.text_effecativeBal = (TextView) itemView.findViewById(R.id.text_effecativeBal);
            this.text_creditAmount = (TextView) itemView.findViewById(R.id.text_creditAmount);

        }
    }

}
