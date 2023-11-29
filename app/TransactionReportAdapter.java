import android.content.Context;

import java.util.List;

public class TransactionReportAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context context;
    List<TransactionReport> listSatetments;

    public TransactionReportAdapter(Context context, List<TransactionReport> listSatetments) {
        this.context = context;
        this.listSatetments = listSatetments;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_transaction_reports, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
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
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.dark_red));
        }
    }
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listSatetments.size();
    }

    public void filter(List<TransactionReport> listnew_banks) {
        this.listSatetments = listnew_banks;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_amount_;
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
            TransactionReportAdapter.this = this$0;
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
