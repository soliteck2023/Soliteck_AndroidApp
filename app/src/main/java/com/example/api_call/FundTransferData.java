package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FundTransferData {
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("bankAccNo")
    @Expose
    private String bankAccNo;
    @SerializedName("bankTxnId")
    @Expose
    private String bankTxnId;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("recipientName")
    @Expose
    private String recipientName;
    @SerializedName("txnRefNumber")
    @Expose
    private String txnRefNumber;

    public String getBankTxnId() {
        return this.bankTxnId;
    }

    public void setBankTxnId(String bankTxnId) {
        this.bankTxnId = bankTxnId;
    }

    public String getBankAccNo() {
        return this.bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRecipientName() {
        return this.recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getTxnRefNumber() {
        return this.txnRefNumber;
    }

    public void setTxnRefNumber(String txnRefNumber) {
        this.txnRefNumber = txnRefNumber;
    }
}
