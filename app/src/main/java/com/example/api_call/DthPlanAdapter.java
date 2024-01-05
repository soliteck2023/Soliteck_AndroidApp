package com.example.api_call;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DthPlanAdapter extends RecyclerView.Adapter<DthPlanAdapter.ViewHolder> {
    String callFrom;
    List<String> des;
    private Context mContext;
    List<String> plan_name;
    List<Rs> rs;

    public DthPlanAdapter(Context aContext, List<Rs> rs, List<String> des, List<String> plan_name, String circle) {
        this.mContext = aContext;
        this.rs = rs;
        this.des = des;
        this.plan_name = plan_name;
        this.callFrom = circle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.custom_recharge_plans_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTxtplanname.setText("Plan : " + this.plan_name.get(position).toString());
        holder.mTxtDescription.setText(this.des.get(position).toString());
        try {
            holder.mLin1Month.setVisibility(View.VISIBLE);
            holder.mTxt1MonthRs.setText(this.mContext.getResources().getString(R.string.Rs) + "" + this.rs.get(position).getMONTHS());
        } catch (Exception e) {
            holder.mLin1Month.setVisibility(View.GONE);
        }
        try {
            holder.mLin3Month.setVisibility(View.VISIBLE);
            try {
                if (this.rs.get(position).getmThreeMONTHS() != null) {
                    holder.mTxt3MonthRs.setText(this.mContext.getResources().getString(R.string.Rs) + "" + this.rs.get(position).getmThreeMONTHS().split("\\.")[0]);
                } else {
                    holder.mTxt3MonthRs.setText(this.mContext.getResources().getString(R.string.Rs) + "-");
                }
            } catch (Exception e2) {
            }
        } catch (Exception e3) {
            holder.mLin3Month.setVisibility(View.GONE);
        }
        try {
            holder.mLin6Month.setVisibility(View.VISIBLE);
            try {
                if (this.rs.get(position).getmSixMonths() != null) {
                    holder.mTxt6MonthRs.setText(this.mContext.getResources().getString(R.string.Rs) + "" + this.rs.get(position).getmSixMonths().split("\\.")[0]);
                } else {
                    holder.mTxt6MonthRs.setText(this.mContext.getResources().getString(R.string.Rs) + "-");
                }
            } catch (Exception e4) {
            }
        } catch (Exception e5) {
            holder.mLin6Month.setVisibility(View.GONE);
        }
        try {
            holder.mLin9Month.setVisibility(View.VISIBLE);
            try {
                if (this.rs.get(position).getmNineMonth() != null) {
                    holder.mTxt9MonthRs.setText(this.mContext.getResources().getString(R.string.Rs) + "" + this.rs.get(position).getmNineMonth().split("\\.")[0]);
                } else {
                    holder.mTxt9MonthRs.setText(this.mContext.getResources().getString(R.string.Rs) + "-");
                }
            } catch (Exception e6) {
                holder.mTxt9MonthRs.setText(this.mContext.getResources().getString(R.string.Rs) + "" + this.rs.get(position).getmNineMonth());
            }
        } catch (Exception e7) {
            holder.mLin9Month.setVisibility(View.GONE);
        }
        try {
            holder.mLin1Year.setVisibility(View.VISIBLE);
            try {
                if (this.rs.get(position).getmYEAR() != null) {
                    holder.mTxt1YearRs.setText(this.mContext.getResources().getString(R.string.Rs) + "" + this.rs.get(position).getmYEAR().split("\\.")[0]);
                } else {
                    holder.mTxt1YearRs.setText(this.mContext.getResources().getString(R.string.Rs) + "-");
                }
            } catch (Exception e8) {
                holder.mTxt1YearRs.setText(this.mContext.getResources().getString(R.string.Rs) + "" + this.rs.get(position).getmYEAR());
            }
        } catch (Exception e9) {
            holder.mLin1Year.setVisibility(View.GONE);
            holder.mTxt1YearRs.setText(this.mContext.getResources().getString(R.string.Rs) + "-");
        }
        try {
            holder.mLin5Year.setVisibility(View.VISIBLE);
            try {
                if (this.rs.get(position).getmFiveYEAR() != null) {
                    holder.mTxt5YearRs.setText(this.mContext.getResources().getString(R.string.Rs) + "" + this.rs.get(position).getmFiveYEAR().split("\\.")[0]);
                } else {
                    holder.mTxt5YearRs.setText(this.mContext.getResources().getString(R.string.Rs) + "-");
                }
            } catch (Exception e10) {
                holder.mTxt5YearRs.setText(this.mContext.getResources().getString(R.string.Rs) + "" + this.rs.get(position).getmFiveYEAR());
            }
        } catch (Exception e11) {
            holder.mLin5Year.setVisibility(View.GONE);
            holder.mTxt5YearRs.setText(this.mContext.getResources().getString(R.string.Rs) + "-");
        }
    }

    @Override
    public int getItemCount() {
        return des.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout mFirst;
        private LinearLayout mLin1Month;
        private LinearLayout mLin1Year;
        private LinearLayout mLin3Month;
        private LinearLayout mLin5Year;
        private LinearLayout mLin6Month;
        private LinearLayout mLin9Month;
        private TextView mRs;
        private LinearLayout mSecond;
        private TextView mTxt1MonthRs;
        private TextView mTxt1YearRs;
        private TextView mTxt3MonthRs;
        private TextView mTxt5YearRs;
        private TextView mTxt6MonthRs;
        private TextView mTxt9MonthRs;
        private TextView mTxtDescription;
        private TextView mTxtplanname;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
//            DthPlanAdapter.this = this$0;
            this.mTxtplanname = (TextView) itemView.findViewById(R.id.Txtplanname);
            this.mFirst = (LinearLayout) itemView.findViewById(R.id.First);
            this.mLin1Month = (LinearLayout) itemView.findViewById(R.id.Lin1Month);
            this.mTxt1MonthRs = (TextView) itemView.findViewById(R.id.Txt1MonthRs);
            this.mLin3Month = (LinearLayout) itemView.findViewById(R.id.Lin3Month);
            this.mTxt3MonthRs = (TextView) itemView.findViewById(R.id.Txt3MonthRs);
            this.mLin6Month = (LinearLayout) itemView.findViewById(R.id.Lin6Month);
            this.mTxt6MonthRs = (TextView) itemView.findViewById(R.id.Txt6MonthRs);
            this.mSecond = (LinearLayout) itemView.findViewById(R.id.Second);
            this.mLin9Month = (LinearLayout) itemView.findViewById(R.id.Lin9Month);
            this.mTxt9MonthRs = (TextView) itemView.findViewById(R.id.Txt9MonthRs);
            this.mLin1Year = (LinearLayout) itemView.findViewById(R.id.Lin1Year);
            this.mTxt1YearRs = (TextView) itemView.findViewById(R.id.Txt1YearRs);
            this.mLin5Year = (LinearLayout) itemView.findViewById(R.id.Lin5Year);
            this.mTxt5YearRs = (TextView) itemView.findViewById(R.id.Txt5YearRs);
            this.mTxtDescription = (TextView) itemView.findViewById(R.id.TxtDescription);
            itemView.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
        }
    }

}
