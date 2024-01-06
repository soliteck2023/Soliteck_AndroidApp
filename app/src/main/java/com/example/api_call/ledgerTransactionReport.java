package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ledgerTransactionReport {

    @SerializedName("uniqueCode")
    @Expose
    private String UniqueCode;

    @SerializedName("fName")  //pending
    @Expose
    private String FName;
    @SerializedName("shopName")
    @Expose
    private String ShopName;
    @SerializedName("transactionId")
    @Expose
    private String TransactionId;
    @SerializedName("refNumber")
    @Expose
    private String RefNumber;

    @SerializedName("receiverDetails")
    @Expose
    private String ReceiverDetails;
    @SerializedName("txnDate")
    @Expose
    private String TxnDate;
    @SerializedName("status")
    @Expose
    private String Status;
    @SerializedName("operator")
    private String Operator;

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    @SerializedName("success")
    @Expose
    private String Success;
    @SerializedName("amount")
    @Expose
    private String Amount;
    @SerializedName("commission")
    @Expose
    private String Commission;
    @SerializedName("tds")
    @Expose
    private String TDS;
    @SerializedName("gst")
    @Expose
    private String GST;
    @SerializedName("servicecharge")
    @Expose
    private String Servicecharge;

    @SerializedName("cr")
    @Expose
    private String CR;

    @SerializedName("dr")
    @Expose
    private String DR;
    @SerializedName("mbBefore")
    @Expose
    private String MBBefore;
    @SerializedName("mbAfter")
    @Expose
    private String MBAfter;

    public ledgerTransactionReport(String uniqueCode, String FName, String shopName, String transactionId, String refNumber, String receiverDetails, String txnDate, String status, String operator, String success, String amount, String commission, String TDS, String GST, String servicecharge, String CR, String DR, String MBBefore, String MBAfter) {
        UniqueCode = uniqueCode;
        this.FName = FName;
        ShopName = shopName;
        TransactionId = transactionId;
        RefNumber = refNumber;
        ReceiverDetails = receiverDetails;
        TxnDate = txnDate;
        Status = status;
        Operator = operator;
        Success = success;
        Amount = amount;
        Commission = commission;
        this.TDS = TDS;
        this.GST = GST;
        Servicecharge = servicecharge;
        this.CR = CR;
        this.DR = DR;
        this.MBBefore = MBBefore;
        this.MBAfter = MBAfter;
    }

    public String getUniqueCode() {
        return UniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        UniqueCode = uniqueCode;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public String getRefNumber() {
        return RefNumber;
    }

    public void setRefNumber(String refNumber) {
        RefNumber = refNumber;
    }

    public String getReceiverDetails() {
        return ReceiverDetails;
    }

    public void setReceiverDetails(String receiverDetails) {
        ReceiverDetails = receiverDetails;
    }

    public String getTxnDate() {
        return TxnDate;
    }

    public void setTxnDate(String txnDate) {
        TxnDate = txnDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getCommission() {
        return Commission;
    }

    public void setCommission(String commission) {
        Commission = commission;
    }

    public String getTDS() {
        return TDS;
    }

    public void setTDS(String TDS) {
        this.TDS = TDS;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getServicecharge() {
        return Servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        Servicecharge = servicecharge;
    }

    public String getCR() {
        return this.CR;
    }

    public void setCR(String CR) {
        this.CR = CR;
    }

    public String getDR() {
        return DR;
    }

    public void setDR(String DR) {
        this.DR = DR;
    }

    public String getMBBefore() {
        return MBBefore;
    }

    public void setMBBefore(String MBBefore) {
        this.MBBefore = MBBefore;
    }

    public String getMBAfter() {
        return MBAfter;
    }

    public void setMBAfter(String MBAfter) {
        this.MBAfter = MBAfter;
    }
}
