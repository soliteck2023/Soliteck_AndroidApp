package com.example.api_call;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.MyViewHolder>{

    private SelectBankFromList bankListener;
    private Context context;
    private List<MBankListResponse> listBanks;

    public interface SelectBankFromList {
        void selectbank(String str, String str2);
    }

    public BankListAdapter(Context context, List<MBankListResponse> listBanks) {
        this.context = context;
        this.listBanks = listBanks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_bankdata, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text_bank.setText(this.listBanks.get(position).getBankName());
        holder.text_ifsc.setText(this.listBanks.get(position).getIfscCode());
        holder.card_bank.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.transfer_money.activities.BankListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BankListAdapter.this.bankListener.selectbank(((MBankListResponse) BankListAdapter.this.listBanks.get(position)).getBankName(), ((MBankListResponse) BankListAdapter.this.listBanks.get(position)).getIfscCode());
            }
        });
        if (holder.text_ifsc.getText().toString().trim().isEmpty()) {
            holder.text_ifsc.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listBanks.size();
    }
    public void setBankListener(SelectBankFromList bankListener) {
        this.bankListener = bankListener;
    }

    public void setNewList(List<MBankListResponse> listnew_banks) {
        this.listBanks = listnew_banks;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView card_bank;
        private TextView text_bank;
        private TextView text_ifsc;

        public MyViewHolder(View itemView) {
            super(itemView);
//            BankListAdapter.this = this$0;
            this.text_bank = (TextView) itemView.findViewById(R.id.text_bank);
            this.text_ifsc = (TextView) itemView.findViewById(R.id.text_ifsc);
            this.card_bank = (CardView) itemView.findViewById(R.id.card_bank);
        }
    }

}
