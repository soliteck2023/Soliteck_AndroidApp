package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatementReport {
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("ClosingBal")
    @Expose
    private String closingBal;
    @SerializedName("Credit")
    @Expose
    private String credit;
    @SerializedName("Debit")
    @Expose
    private String debit;
    @SerializedName("NarrationText")
    @Expose
    private String narrationText;
    @SerializedName("OpeningBal")
    @Expose
    private String openingBal;
    @SerializedName("OrderID")
    @Expose
    private String orderId;
    @SerializedName("Provider")
    @Expose
    private String provider;
    @SerializedName("ServiceType")
    @Expose
    private String serviceType;
    @SerializedName("TxnDate")
    @Expose
    private String txnDate;
    @SerializedName("TxnStatus")
    @Expose
    private String txnStatus;
    @SerializedName("TxnTime")
    @Expose
    private String txnTime;
    @SerializedName("TxnType")
    @Expose
    private String txnType;
    @SerializedName("UserDetails")
    @Expose
    private String userDetails;

    public String getTxnStatus() {
        return this.txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getTxnDate() {
        return this.txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getTxnTime() {
        return this.txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getOpeningBal() {
        return this.openingBal;
    }

    public void setOpeningBal(String openingBal) {
        this.openingBal = openingBal;
    }

    public String getDebit() {
        return this.debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return this.credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getClosingBal() {
        return this.closingBal;
    }

    public void setClosingBal(String closingBal) {
        this.closingBal = closingBal;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getNarrationText() {
        return this.narrationText;
    }

    public void setNarrationText(String narrationText) {
        this.narrationText = narrationText;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getUserDetails() {
        return this.userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }
}
