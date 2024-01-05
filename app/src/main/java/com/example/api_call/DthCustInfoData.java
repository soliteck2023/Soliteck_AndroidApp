package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.SerializedName;

public class DthCustInfoData {
    @SerializedName("Balance")
    private String mBalance;
    @SerializedName("customerName")
    private String mCustomerName;
    @SerializedName("MonthlyRecharge")
    private String mMonthlyRecharge;
    @SerializedName("NextRechargeDate")
    private String mNextRechargeDate;
    @SerializedName("planname")
    private String mPlanname;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String mStatus;

    public String getBalance() {
        return this.mBalance;
    }

    public void setBalance(String balance) {
        this.mBalance = balance;
    }

    public String getCustomerName() {
        return this.mCustomerName;
    }

    public void setCustomerName(String customerName) {
        this.mCustomerName = customerName;
    }

    public String getMonthlyRecharge() {
        return this.mMonthlyRecharge;
    }

    public void setMonthlyRecharge(String monthlyRecharge) {
        this.mMonthlyRecharge = monthlyRecharge;
    }

    public String getNextRechargeDate() {
        return this.mNextRechargeDate;
    }

    public void setNextRechargeDate(String nextRechargeDate) {
        this.mNextRechargeDate = nextRechargeDate;
    }

    public String getPlanname() {
        return this.mPlanname;
    }

    public void setPlanname(String planname) {
        this.mPlanname = planname;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }
}
