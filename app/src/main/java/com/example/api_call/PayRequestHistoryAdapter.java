package com.example.api_call;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayRequestHistoryAdapter extends RecyclerView.Adapter<PayRequestHistoryAdapter.MyViewHolder>{
    private Context context;
    private List<PaymentRequestHistory> list_requests;

    public PayRequestHistoryAdapter(Context context, List<PaymentRequestHistory> list_requests) {
        this.context = context;
        this.list_requests = list_requests;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_view_request, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("WrongConstant")
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        PaymentRequestHistory allRequests = this.list_requests.get(position);
        holder.text_transid.setText("ID : " + allRequests.getId());
        holder.text_username_.setText("User : " + allRequests.getUserName());
        holder.text_requestdate_.setText(allRequests.getCreateDate());
        holder.text_amount_.setText("₹ " + allRequests.getAmount());
        holder.text_status_.setText(allRequests.getStatus());
        holder.text_bank.setText(allRequests.getBankName());
        holder.text_paymode.setText(allRequests.getPaymentMode());
        holder.text_refNo.setText("Ref No:" + allRequests.getBankTransactionNumber());
        holder.text_remark.setText("Remark:" + allRequests.getDescription());
        if (allRequests.getStatus().equalsIgnoreCase("Approved")) {
            holder.text_status_.setText(allRequests.getStatus());
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.dark_green));
            holder.text_amount_.setText("₹ " + allRequests.getAmount());
            holder.text_amount_.setTextColor(this.context.getResources().getColor(R.color.dark_green));
            holder.linear_status.setVisibility(8);
        } else if (allRequests.getStatus().equalsIgnoreCase("Pending")) {
            holder.text_status_.setText(allRequests.getStatus());
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.orange));
            holder.text_amount_.setText("₹ " + allRequests.getAmount());
            holder.text_amount_.setTextColor(this.context.getResources().getColor(R.color.orange));
            holder.linear_status.setVisibility(8);
        } else if (allRequests.getStatus().equalsIgnoreCase("Rejected")) {
            holder.text_status_.setText(allRequests.getStatus());
            holder.text_status_.setTextColor(this.context.getResources().getColor(R.color.colorAccent));
            holder.text_amount_.setText("₹ " + allRequests.getAmount());
            holder.text_amount_.setTextColor(this.context.getResources().getColor(R.color.colorAccent));
            holder.linear_status.setVisibility(8);
        }
        holder.btn_approve.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.adapters.PayRequestHistoryAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PayRequestHistoryAdapter.this.context);
                builder.setTitle("Approve");
                builder.setMessage("Are you want to Approve request?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.uvapay.adapters.PayRequestHistoryAdapter.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        PayRequestHistoryAdapter.this.SendStatusRequest("Approved", holder.text_refNo.getText().toString().split(":")[1].trim(), position);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.uvapay.adapters.PayRequestHistoryAdapter.1.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
        holder.btn_reject.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.adapters.PayRequestHistoryAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PayRequestHistoryAdapter.this.context);
                builder.setTitle("Reject");
                builder.setMessage("Are you want to Reject request?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.uvapay.adapters.PayRequestHistoryAdapter.2.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        PayRequestHistoryAdapter.this.SendStatusRequest("Rejected", holder.text_refNo.getText().toString().split(":")[1].trim(), position);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.uvapay.adapters.PayRequestHistoryAdapter.2.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list_requests.size();
    }

    /* loaded from: classes17.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout btn_approve;
        private LinearLayout btn_reject;
        private LinearLayout linear_status;
        private TextView text_amount_;
        private TextView text_bank;
        private TextView text_paymode;
        private TextView text_refNo;
        private TextView text_remark;
        private TextView text_requestdate_;
        private TextView text_status_;
        private TextView text_transid;
        private TextView text_username_;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
//            PayRequestHistoryAdapter.this = this$0;
            this.text_refNo = (TextView) itemView.findViewById(R.id.text_refNo);
            this.text_amount_ = (TextView) itemView.findViewById(R.id.text_amount_);
            this.text_bank = (TextView) itemView.findViewById(R.id.text_bank);
            this.text_paymode = (TextView) itemView.findViewById(R.id.text_paymode);
            this.text_status_ = (TextView) itemView.findViewById(R.id.text_status_);
            this.text_username_ = (TextView) itemView.findViewById(R.id.text_username_);
            this.text_requestdate_ = (TextView) itemView.findViewById(R.id.text_requestdate_);
            this.text_transid = (TextView) itemView.findViewById(R.id.text_transid);
            this.text_remark = (TextView) itemView.findViewById(R.id.text_remark);
            this.btn_reject = (LinearLayout) itemView.findViewById(R.id.btn_reject);
            this.btn_approve = (LinearLayout) itemView.findViewById(R.id.btn_approve);
            this.linear_status = (LinearLayout) itemView.findViewById(R.id.linear_status);
        }
    }

    public void SendStatusRequest(String status, String refNo, final int position) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue((Activity) this.context);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this.context, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Password", PrefUtils.getFromPrefs(this.context, ConstantClass.USERDETAILS.UserPassword, ""));
        body.put("Action", status);
        body.put("ReferenceNo", refNo);

        ApiInterface apiservice = RetrofitHandler.getService();
        Call<PaymentRequestHistoryResponse> result = apiservice.GetPaymentRequestHistory(body);


        ReportsApiServices apiServiceGenerator = (ReportsApiServices)RetrofitHandler.getService();
        Call<UpdatePaymentRequestResponse> objbanklist = apiServiceGenerator.UpdateChildPaymentRequest(body);
        objbanklist.enqueue(new Callback<UpdatePaymentRequestResponse>() { // from class: com.uvapay.adapters.PayRequestHistoryAdapter.3
            @Override // retrofit2.Callback
            public void onResponse(Call<UpdatePaymentRequestResponse> call, Response<UpdatePaymentRequestResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                UpdatePaymentRequestResponse balanceResponse = response.body();
                if (balanceResponse.getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                    ConstantClass.displayMessageDialog(PayRequestHistoryAdapter.this.context, "Response", balanceResponse.getMessage());
                    PayRequestHistoryAdapter.this.list_requests.remove(position);
                    PayRequestHistoryAdapter.this.notifyItemRemoved(position);
                    PayRequestHistoryAdapter payRequestHistoryAdapter = PayRequestHistoryAdapter.this;
                    payRequestHistoryAdapter.notifyItemRangeChanged(position, payRequestHistoryAdapter.list_requests.size());
                    return;
                }
                ConstantClass.displayMessageDialog(PayRequestHistoryAdapter.this.context, "Response", balanceResponse.getMessage());
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<UpdatePaymentRequestResponse> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(PayRequestHistoryAdapter.this.context, PayRequestHistoryAdapter.this.context.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }
}
