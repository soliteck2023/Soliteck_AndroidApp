package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DTHData {
    @SerializedName("Balance")
    @Expose
    private String balance;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("lastrechargeamount")
    @Expose
    private Integer lastrechargeamount;
    @SerializedName("lastrechargedate")
    @Expose
    private String lastrechargedate;
    @SerializedName("MonthlyRecharge")
    @Expose
    private String monthlyRecharge;
    @SerializedName("NextRechargeDate")
    @Expose
    private String nextRechargeDate;
    @SerializedName("planname")
    @Expose
    private String planname;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public String getMonthlyRecharge() {
        return this.monthlyRecharge;
    }

    public void setMonthlyRecharge(String monthlyRecharge) {
        this.monthlyRecharge = monthlyRecharge;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNextRechargeDate() {
        return this.nextRechargeDate;
    }

    public void setNextRechargeDate(String nextRechargeDate) {
        this.nextRechargeDate = nextRechargeDate;
    }

    public String getLastrechargedate() {
        return this.lastrechargedate;
    }

    public void setLastrechargedate(String lastrechargedate) {
        this.lastrechargedate = lastrechargedate;
    }

    public Integer getLastrechargeamount() {
        return this.lastrechargeamount;
    }

    public void setLastrechargeamount(Integer lastrechargeamount) {
        this.lastrechargeamount = lastrechargeamount;
    }

    public String getPlanname() {
        return this.planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }
}
