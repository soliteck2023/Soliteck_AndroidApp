package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class receipt_class {
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

    @SerializedName("gst")
    @Expose
    private String gst;

    @SerializedName("servicecharge")
    @Expose
    private String servicecharge;

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
    @SerializedName("beneName")
    @Expose
    private String beneName;
    @SerializedName("beneMobileNumber")
    @Expose
    private String beneMobileNumber;

    @SerializedName("bankAccountNumber")
    @Expose
    private String bankAccountNumber;

    @SerializedName("ifscCode")
    @Expose
    private String ifscCode;

    @SerializedName("senderMobileNumber")
    @Expose
    private String senderMobileNumber;

    @SerializedName("column1")
    @Expose
    private String column1;

    @SerializedName("retailerName")
    @Expose
    private String retailerName;

    @SerializedName("vendorName")
    @Expose
    private String vendorName;
    @SerializedName("bankName")
    @Expose
    private String bankName;

    public receipt_class(String retailerNumber, String transactionNumber, String refNumber, String status, String operatorName, String senderMobile, String transactionDate, String amount, String commission, String gst, String servicecharge, String tds, String debitAmount, String creditAmount, String effecativeBal, String id, String beneName, String beneMobileNumber, String bankAccountNumber, String ifscCode, String senderMobileNumber, String column1, String retailerName, String vendorName, String bankName) {
        this.retailerNumber = retailerNumber;
        this.transactionNumber = transactionNumber;
        this.refNumber = refNumber;
        this.status = status;
        this.operatorName = operatorName;
        this.senderMobile = senderMobile;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.commission = commission;
        this.gst = gst;
        this.servicecharge = servicecharge;
        this.tds = tds;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.effecativeBal = effecativeBal;
        this.id = id;
        this.beneName = beneName;
        this.beneMobileNumber = beneMobileNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.ifscCode = ifscCode;
        this.senderMobileNumber = senderMobileNumber;
        this.column1 = column1;
        this.retailerName = retailerName;
        this.vendorName = vendorName;
        this.bankName = bankName;
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

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
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

    public String getBeneName() {
        return beneName;
    }

    public void setBeneName(String beneName) {
        this.beneName = beneName;
    }

    public String getBeneMobileNumber() {
        return beneMobileNumber;
    }

    public void setBeneMobileNumber(String beneMobileNumber) {
        this.beneMobileNumber = beneMobileNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getSenderMobileNumber() {
        return senderMobileNumber;
    }

    public void setSenderMobileNumber(String senderMobileNumber) {
        this.senderMobileNumber = senderMobileNumber;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
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
}
