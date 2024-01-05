package com.example.api_call;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeneficiaryListAdapter extends RecyclerView.Adapter<BeneficiaryListAdapter.MyViewHolder>{
    private Context context;
    private List<VRecipient> list_beneficiary;
    private OnDeleteItemListener listener;
    private String mobile;
    private String remitterID;
    private String remitter_name;
    private TextView text_account;
    private TextView text_bank;
    private TextView text_ifsc;
    private TextView text_name;
    private TextView text_pan;
    private TextView txtDesc;
    private TextView txtDesc_text;
    String transaction_type = "";
    String previousText = "";

    public void setOnDeleteItemListener(OnDeleteItemListener listener) {
        this.listener =listener;
    }
    public void setNewList(List<VRecipient> list_items) {
        this.list_beneficiary = list_items;
        notifyDataSetChanged();
    }


    public interface OnDeleteItemListener {
        void onDeleteItem();
    }
    public BeneficiaryListAdapter(Context context, List<VRecipient> list_beneficiary, String mobile, String remitter_name, String remitter_id) {
        this.context = context;
        this.list_beneficiary = list_beneficiary;
        this.mobile = mobile;
        this.remitter_name = remitter_name;
        this.remitterID = remitter_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_beneficiary, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text_name.setText(this.list_beneficiary.get(position).getName());
        holder.text_bank.setText(this.list_beneficiary.get(position).getBankName() + "(" + this.list_beneficiary.get(position).getIfsc() + ")");
        holder.text_account.setText(this.list_beneficiary.get(position).getAccountNo());
        if (this.list_beneficiary.get(position).getIsValidate().equals(false)) {
            holder.btn_verify.setBackgroundColor(this.context.getResources().getColor(R.color.orange));
            holder.btn_verify.setText("Verify");
        } else {
            holder.btn_verify.setBackgroundColor(this.context.getResources().getColor(R.color.dark_green));
            holder.btn_verify.setText("Verified");
        }
        holder.btn_send.setOnClickListener(new AnonymousClass1(position));
        holder.card_transfer.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                holder.btn_send.performClick();
            }
        });
        holder.btn_verify.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (!holder.btn_verify.getText().toString().toLowerCase().equals("verified")) {
                    BeneficiaryListAdapter beneficiaryListAdapter = BeneficiaryListAdapter.this;
                    beneficiaryListAdapter.verifyRecipient(((VRecipient) beneficiaryListAdapter.list_beneficiary.get(position)).getAccountNo(), ((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(position)).getIfsc(), ((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(position)).getMobileNo(), holder.btn_verify);
                    return;
                }
                ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "", "Already verified beneficiary");
            }
        });
        holder.delete_beneficiary.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BeneficiaryListAdapter.this.context);
                builder.setTitle("Delete Beneficiary");
                builder.setMessage("Are you sure to delete beneficiary?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.4.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        VRecipient itemToDelete = (VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(position);
                        BeneficiaryListAdapter.this.list_beneficiary.remove(position);
                        BeneficiaryListAdapter.this.notifyDataSetChanged();
                        BeneficiaryListAdapter.this.removeRecipient(itemToDelete.getRptid());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.4.2
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

    private void removeRecipient(final String rptid) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue((Activity) this.context);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this.context, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this.context, ConstantClass.USERDETAILS.Token, ""));
        body.put("RecipientId", rptid);
        body.put("senderId", this.remitterID);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<MRemoveBene> call = apiservice.removeBene(body);
        call.enqueue(new Callback<MRemoveBene>() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.6
            @Override // retrofit2.Callback
            public void onResponse(Call<MRemoveBene> call2, Response<MRemoveBene> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {


//                        ApplicationConstant.displayToastMessage(BeneficiaryListAdapter.this.context, response.body().getMessage());
                        View view = ((Activity) BeneficiaryListAdapter.this.context).getLayoutInflater().inflate(R.layout.layout_otp_beneficiary, (ViewGroup) null);
                        final EditText edit_otp_number = (EditText) view.findViewById(R.id.edit_otp_number);
                        Button btn_send_otp = (Button) view.findViewById(R.id.btn_send_otp);
                        ImageView image_delete = (ImageView) view.findViewById(R.id.image_cancel);
                        AlertDialog.Builder builder = new AlertDialog.Builder(BeneficiaryListAdapter.this.context);
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.setView(view);
                        alertDialog.show();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        image_delete.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.6.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        btn_send_otp.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.6.2
                            @Override // android.view.View.OnClickListener
                            public void onClick(View v) {
                                alertDialog.dismiss();
                                BeneficiaryListAdapter.this.confirmRemoveBeneficiary(rptid, edit_otp_number.getText().toString());
                            }
                        });
                        return;
                    }
                    ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "", response.body().getMessage());
                    return;
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MRemoveBene> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "Response", t.getMessage());
            }
        });
    }

    private void confirmRemoveBeneficiary(String rptid, String otp) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue((Activity) this.context);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this.context, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this.context, ConstantClass.USERDETAILS.Token, ""));
        body.put("RecipientId", rptid);
        body.put("senderId", this.remitterID);
        body.put("otp", otp);
        ApiInterface apiservice =RetrofitHandler.getService();
        Call<MConfirmRemoveBene> call = apiservice.removeConfirmBene(body);
        call.enqueue(new Callback<MConfirmRemoveBene>() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.8
            @Override // retrofit2.Callback
            public void onResponse(Call<MConfirmRemoveBene> call2, Response<MConfirmRemoveBene> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                        ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "", response.body().getMessage());
                        if (BeneficiaryListAdapter.this.listener != null) {
                            BeneficiaryListAdapter.this.listener.onDeleteItem();
                            return;
                        }
                        return;
                    }
                    ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "", response.body().getMessage());
                    return;
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MConfirmRemoveBene> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "Response", t.getMessage());
            }
        });
    }

    private void verifyRecipient(String account, String ifsc, String mobile, Button verify) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue((Activity) this.context);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this.context, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Token", PrefUtils.getFromPrefs(this.context, ConstantClass.USERDETAILS.Token, ""));
        body.put("MobileNumber", mobile);
        body.put(ConstantClass.PROFILEDETAILS.AccountNo, account);
        body.put(ConstantClass.PROFILEDETAILS.IFSCCode, ifsc);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<MAccVerify> call = apiservice.getVerify(body);
        call.enqueue(new Callback<MAccVerify>() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.7
            @Override // retrofit2.Callback
            public void onResponse(Call<MAccVerify> call2, Response<MAccVerify> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getResponse().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                        ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "", response.body().getResponse().getMessage());
                        if (Build.VERSION.SDK_INT >= 23) {
                            verify.setBackgroundColor(BeneficiaryListAdapter.this.context.getColor(R.color.dark_green));
                            verify.setText("Verified");
                            return;
                        }
                        return;
                    }
                    ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "", response.body().getResponse().getMessage());
                    if (Build.VERSION.SDK_INT >= 23) {
                        verify.setBackgroundColor(BeneficiaryListAdapter.this.context.getColor(R.color.orange));
                        verify.setText("Verify");
                        return;
                    }
                    return;
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<MAccVerify> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "Response", t.getMessage());
            }
        });
    }

    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ int val$position;

        AnonymousClass1(int i) {
//            BeneficiaryListAdapter.this = this$0;
            this.val$position = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            View view = LayoutInflater.from(BeneficiaryListAdapter.this.context).inflate(R.layout.layout_transfer_money, (ViewGroup) null);
            ImageView image_delete = (ImageView) view.findViewById(R.id.image_delete);
            CheckView checkView = (CheckView) view.findViewById(R.id.check);
            Button btn_send_money = (Button) view.findViewById(R.id.btn_send_money);
            CardView cardView = (CardView) view.findViewById(R.id.card_success);
            CardView cardView2 = (CardView) view.findViewById(R.id.card_confirm);
            TextView text_done = (TextView) view.findViewById(R.id.text_done);
            TextView text_beneficiary = (TextView) view.findViewById(R.id.text_beneficiary);
            TextView text_account_no = (TextView) view.findViewById(R.id.text_account_no);
            TextView text_bank_ = (TextView) view.findViewById(R.id.text_bank_);
            TextView text_ifsc_ = (TextView) view.findViewById(R.id.text_ifsc_);
            final EditText text_amount = (EditText) view.findViewById(R.id.text_amount);
            final EditText edit_tpin = (EditText) view.findViewById(R.id.edit_tpin);
            final LinearLayout type_imps = (LinearLayout) view.findViewById(R.id.type_imps);
            final LinearLayout type_neft = (LinearLayout) view.findViewById(R.id.type_neft);
            final TextView text_imps = (TextView) view.findViewById(R.id.text_imps);
            final TextView text_neft = (TextView) view.findViewById(R.id.text_neft);
            final EditText TextWordAmount = (EditText) view.findViewById(R.id.TextWordAmount);
            BeneficiaryListAdapter.this.text_name = (TextView) view.findViewById(R.id.text_name);
            BeneficiaryListAdapter.this.txtDesc = (TextView) view.findViewById(R.id.txtDesc);
            BeneficiaryListAdapter.this.txtDesc_text = (TextView) view.findViewById(R.id.txtDesc_text);
            BeneficiaryListAdapter.this.text_account = (TextView) view.findViewById(R.id.text_account);
            BeneficiaryListAdapter.this.text_bank = (TextView) view.findViewById(R.id.text_bank);
            BeneficiaryListAdapter.this.text_ifsc = (TextView) view.findViewById(R.id.text_ifsc);
            BeneficiaryListAdapter.this.text_pan = (TextView) view.findViewById(R.id.text_pan);
            AlertDialog.Builder builder = new AlertDialog.Builder(BeneficiaryListAdapter.this.context);
            final AlertDialog alertDialog = builder.create();
            alertDialog.setView(view);
            alertDialog.show();
            alertDialog.getWindow().setSoftInputMode(16);
            alertDialog.setCanceledOnTouchOutside(false);
            text_beneficiary.setText(((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(this.val$position)).getName());
            text_account_no.setText(((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(this.val$position)).getAccountNo());
            text_bank_.setText(((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(this.val$position)).getBankName());
            text_ifsc_.setText(((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(this.val$position)).getIfsc());
            image_delete.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.1.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                    alertDialog.dismiss();
                }
            });
            text_amount.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.1.2
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String text = text_amount.getText().toString().trim();
                    if (text.startsWith("0")) {
                        text_amount.setText(text.substring(1));
                    }
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                    String text = text_amount.getText().toString().trim();
                    if (!text.isEmpty()) {
                        String AmountWord = BeneficiaryListAdapter.this.numToWords(Integer.parseInt(text));
                        TextWordAmount.setText(AmountWord + " Rupees");
                        return;
                    }
                    TextWordAmount.setText("");
                }
            });
            type_imps.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.1.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                    if (Build.VERSION.SDK_INT >= 16) {
                        type_imps.setBackground(BeneficiaryListAdapter.this.context.getResources().getDrawable(R.drawable.violet_button_background));
                        text_imps.setTextColor(-1);
                        type_neft.setBackground(BeneficiaryListAdapter.this.context.getResources().getDrawable(R.drawable.border_edittext));
                        text_neft.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        BeneficiaryListAdapter.this.transaction_type = "IMPS";
                    }
                }
            });
            type_neft.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.1.4
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                    if (Build.VERSION.SDK_INT >= 16) {
                        type_neft.setBackground(BeneficiaryListAdapter.this.context.getResources().getDrawable(R.drawable.violet_button_background));
                        text_neft.setTextColor(-1);
                        type_imps.setBackground(BeneficiaryListAdapter.this.context.getResources().getDrawable(R.drawable.border_edittext));
                        text_imps.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        BeneficiaryListAdapter.this.transaction_type = "NEFT";
                    }
                }
            });
            type_imps.performClick();
            btn_send_money.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.1.5
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(BeneficiaryListAdapter.this.context);
                    builder1.setTitle("Transfer Confirmation");
                    builder1.setMessage("Are you sure to transfer Rs " + text_amount.getText().toString() + " ? ");
                    builder1.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.1.5.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.1.5.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            String bankname;
                            if (edit_tpin.getText().toString().isEmpty()) {
                                edit_tpin.setError("enter tpin");
                                edit_tpin.requestFocus();
                            } else if (text_amount.getText().toString().isEmpty()) {
                                text_amount.setError("enter amount");
                                text_amount.requestFocus();
                            } else {
                                if (((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(AnonymousClass1.this.val$position)).getBankName() == null) {
                                    bankname = " ";
                                } else {
                                    bankname = ((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(AnonymousClass1.this.val$position)).getBankName();
                                }
                                alertDialog.dismiss();
                                String EncodedTPIN = ApplicationConstant.EncodeStringToHMACSHA256(edit_tpin.getText().toString().trim());
                                BeneficiaryListAdapter.this.sendmoneyToRecipient(alertDialog, ((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(AnonymousClass1.this.val$position)).getRptid(), ((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(AnonymousClass1.this.val$position)).getAccountNo(), ((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(AnonymousClass1.this.val$position)).getIfsc(), ((VRecipient) BeneficiaryListAdapter.this.list_beneficiary.get(AnonymousClass1.this.val$position)).getName(), bankname, BeneficiaryListAdapter.this.remitter_name, text_amount.getText().toString(), BeneficiaryListAdapter.this.transaction_type, EncodedTPIN);
                            }
                        }
                    });
                    builder1.create();
                    builder1.show();
                }
            });
            text_done.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.1.6
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                    alertDialog.dismiss();
                }
            });
        }
    }

    private void sendmoneyToRecipient(AlertDialog alertDialog, String rptid, String account, String ifsc, String recipient_name, String bank_name, String RemitterName, String amount, String type, String Tpin) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue((Activity) this.context);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this.context, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this.context, ConstantClass.USERDETAILS.Token, ""));
        body.put("txnpass", Tpin);
        body.put("SenderId", this.remitterID);
        body.put("RecipientId", rptid);
        body.put(ConstantClass.PROFILEDETAILS.AccountNo, account);
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this.context, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Amount", amount);
        body.put("TransactionType", type);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<List<MTransferFund>> call = apiservice.makeTransfer(body);
        call.enqueue(new Callback<List<MTransferFund>>() { // from class: com.uvapay.transfer_money.adapter.BeneficiaryListAdapter.5
            @Override // retrofit2.Callback
            public void onResponse(Call<List<MTransferFund>> call2, Response<List<MTransferFund>> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().get(0).getStatusCode().intValue() == 1) {
                        ApplicationConstant.DisplayMessageDialog((Activity) BeneficiaryListAdapter.this.context, "Resposne", response.body().get(0).getMessage());
                        return;
                    } else {
                        ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "Response", response.body().get(0).getMessage());
                        return;
                    }
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<List<MTransferFund>> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ConstantClass.displayMessageDialog(BeneficiaryListAdapter.this.context, "Response", t.getMessage());
            }
        });
    }

    private String numToWords(int n) {
        new NumberToWord();
        return NumberToWord.convert(n);
    }


    @Override
    public int getItemCount() {
        return list_beneficiary.size();
    }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView btn_send;
            private Button btn_verify;
            private CardView card_transfer;
            private ImageView delete_beneficiary;
            private TextView text_account;
            private TextView text_bank;
            private TextView text_name;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public MyViewHolder(View itemView) {
                super(itemView);
//                BeneficiaryListAdapter.this = this$0;
                this.text_name = (TextView) itemView.findViewById(R.id.text_name);
                this.text_bank = (TextView) itemView.findViewById(R.id.text_bank);
                this.text_account = (TextView) itemView.findViewById(R.id.text_account);
                this.btn_send = (ImageView) itemView.findViewById(R.id.btn_send);
                this.card_transfer = (CardView) itemView.findViewById(R.id.card_transfer);
                this.delete_beneficiary = (ImageView) itemView.findViewById(R.id.delete_beneficiary);
                this.btn_verify = (Button) itemView.findViewById(R.id.btn_verify);
            }
        }

    }
