package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class cashoutledgerTransactionReport {
    @SerializedName("retailerNumber")
    @Expose
    private String retailerNumber;

    @SerializedName("transactionNumber")
    @Expose
    private String transactionNumber;

    @SerializedName("refNumber")
    @Expose
    private String refNumber;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("operatorName")
    @Expose
    private String operatorName;

    @SerializedName("senderMobile")
    @Expose
    private String senderMobile;

    @SerializedName("transactionDate")
    @Expose
    private String transactionDate;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("commission")
    @Expose
    private String commission;

    @SerializedName("servicecharge")
    @Expose
    private String servicecharge;

    @SerializedName("gst")
    @Expose
    private String gst;

    @SerializedName("tds")
    @Expose
    private String tds;

    @SerializedName("debitAmount")
    @Expose
    private String debitAmount;

    @SerializedName("creditAmount")
    @Expose
    private String creditAmount;

    @SerializedName("effecativeBal")
    @Expose
    private String effecativeBal;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("transactionMode")
    @Expose
    private String transactionMode;

    @SerializedName("retailerName")
    @Expose
    private String retailerName;

    @SerializedName("vendorName")
    @Expose
    private String vendorName;

    @SerializedName("bankName")
    @Expose
    private String bankName;

    @SerializedName("adhaarNo")
    @Expose
    private String adhaarNo;

    @SerializedName("rrn")
    @Expose
    private String rrn;

    @SerializedName("mode")
    @Expose
    private String mode;

    @SerializedName("shopName")
    @Expose
    private String shopName;

    public cashoutledgerTransactionReport(String retailerNumber, String transactionNumber, String refNumber, String status, String operatorName, String senderMobile, String transactionDate, String amount, String commission, String servicecharge, String gst, String tds, String debitAmount, String creditAmount, String effecativeBal, String id, String transactionMode, String retailerName, String vendorName, String bankName, String adhaarNo, String rrn, String mode, String shopName) {
        this.retailerNumber = retailerNumber;
        this.transactionNumber = transactionNumber;
        this.refNumber = refNumber;
        this.status = status;
        this.operatorName = operatorName;
        this.senderMobile = senderMobile;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.commission = commission;
        this.servicecharge = servicecharge;
        this.gst = gst;
        this.tds = tds;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.effecativeBal = effecativeBal;
        this.id = id;
        this.transactionMode = transactionMode;
        this.retailerName = retailerName;
        this.vendorName = vendorName;
        this.bankName = bankName;
        this.adhaarNo = adhaarNo;
        this.rrn = rrn;
        this.mode = mode;
        this.shopName = shopName;
    }

    public String getRetailerNumber() {
        return retailerNumber;
    }

    public void setRetailerNumber(String retailerNumber) {
        this.retailerNumber = retailerNumber;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getEffecativeBal() {
        return effecativeBal;
    }

    public void setEffecativeBal(String effecativeBal) {
        this.effecativeBal = effecativeBal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAdhaarNo() {
        return adhaarNo;
    }

    public void setAdhaarNo(String adhaarNo) {
        this.adhaarNo = adhaarNo;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
