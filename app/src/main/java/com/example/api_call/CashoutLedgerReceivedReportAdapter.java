package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CashoutLedgerReceivedReportAdapter extends  RecyclerView.Adapter<CashoutLedgerReceivedReportAdapter.MyViewHolder> {

    private Context context;
    private List<cashoutledgerTransactionReport2> reportList;

    public CashoutLedgerReceivedReportAdapter(Context context, List<cashoutledgerTransactionReport2> reportList) {
        this.context = context;
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public CashoutLedgerReceivedReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_cashoutpayment2_received, parent, false);
        return new CashoutLedgerReceivedReportAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CashoutLedgerReceivedReportAdapter.MyViewHolder holder, int position) {

        cashoutledgerTransactionReport2 earnData = this.reportList.get(position);
        try {
            holder.adhar_no_.setText("Retailer Number: " + earnData.getRetailerNumber());
            holder.text_transid.setText("Txn Id: " + earnData.getTransactionNumber());
            holder.text_refid.setText("Ref No: " + earnData.getRefNumber());
            holder.text_status_.setText(earnData.getStatus());
            holder.text_paymode.setText("Operator Name: " + earnData.getOperatorName());
            holder.text_retailerNumber.setText("Sender Number: " + earnData.getSenderMobile());
            holder.text_date_time_.setText(earnData.getTransactionDate());
            holder.text_amount_.setText("Rs " + earnData.getAmount());
            holder.text_commission.setText("Commission: " + earnData.getCommission());
            holder.text_servicecharge.setText("Service charge: " + earnData.getServicecharge());
            holder.text_gst.setText("GST: " + earnData.getGst());
            holder.text_tds.setText("TDS: " + earnData.getTds());
            holder.text_debitAmount.setText("Debit Amount: " + earnData.getDebitAmount());
            holder.text_creditAmount.setText("Credit Amount: " + earnData.getCreditAmount());
            holder.text_effecativeBal.setText("Effective Bal.: " + earnData.getEffecativeBal());
            holder.retailer_name.setText("Retailer Name: " + earnData.getRetailerName());
            holder.vendor_name.setText("Vendor Name: " + earnData.getVendorName());
            holder.shopname.setText("Shop Name: " + earnData.getShopName());
            holder.payment_type.setText("Payment Type: " + earnData.getPaymentType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public int getItemCount() {
        return reportList.size();
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
        TextView text_transid,adhar_no_,retailer_name;
        TextView vendor_name;
        TextView shopname;
        TextView payment_type;


        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);

            this.Raise =(TextView) itemView.findViewById(R.id.RAISE);
            this.View =(TextView) itemView.findViewById(R.id.view_raise);
            this.adhar_no_ =(TextView) itemView.findViewById(R.id.adhar_no_);
            this.retailer_name =(TextView) itemView.findViewById(R.id.retailer_name);
            this.shopname = (TextView) itemView.findViewById(R.id.shop_name);
            this.payment_type = (TextView) itemView.findViewById(R.id.payment_type);
            this.vendor_name =(TextView) itemView.findViewById(R.id.vendor_name);
            this.text_paymode = (TextView) itemView.findViewById(R.id.text_paymode);
            this.text_status_ = (TextView) itemView.findViewById(R.id.text_status_);
            this.text_transid = (TextView) itemView.findViewById(R.id.text_transid);
            this.text_retailerNumber = (TextView) itemView.findViewById(R.id.text_senderNumber);
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
