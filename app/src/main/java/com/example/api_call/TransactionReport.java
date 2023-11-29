package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionReport {
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("commission")
    @Expose
    private Double commission;
    @SerializedName("creditAmount")
    @Expose
    private Double creditAmount;
    @SerializedName("debitAmount")
    @Expose
    private Double debitAmount;
    @SerializedName("effecativeBal")
    @Expose
    private Double effecativeBal;
    @SerializedName("gst")
    @Expose
    private Double gst;
    @SerializedName("Name.MARK")
    @Expose
    private Integer id;
    @SerializedName("operatorName")
    @Expose
    private String operatorName;
    @SerializedName("refNumber")
    @Expose
    private String refNumber;
    @SerializedName("retailerNumber")
    @Expose
    private String retailerNumber;
    @SerializedName("senderMobile")
    @Expose
    private String senderMobile;
    @SerializedName("servicecharge")
    @Expose
    private Double servicecharge;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;
    @SerializedName("tds")
    @Expose
    private Double tds;
    @SerializedName("transactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("transactionNumber")
    @Expose
    private String transactionNumber;

    public String getRetailerNumber() {
        return this.retailerNumber;
    }

    public void setRetailerNumber(String retailerNumber) {
        this.retailerNumber = retailerNumber;
    }

    public String getTransactionNumber() {
        return this.transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getRefNumber() {
        return this.refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperatorName() {
        return this.operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getSenderMobile() {
        return this.senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public String getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCommission() {
        return this.commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getServicecharge() {
        return this.servicecharge;
    }

    public void setServicecharge(Double servicecharge) {
        this.servicecharge = servicecharge;
    }

    public Double getGst() {
        return this.gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public Double getTds() {
        return this.tds;
    }

    public void setTds(Double tds) {
        this.tds = tds;
    }

    public Double getDebitAmount() {
        return this.debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Double getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getEffecativeBal() {
        return this.effecativeBal;
    }

    public void setEffecativeBal(Double effecativeBal) {
        this.effecativeBal = effecativeBal;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
