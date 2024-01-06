package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class receipt_class {
    @SerializedName("retailernumber")
    @Expose
    private String retailernumber;

    @SerializedName("transactionnumber")
    @Expose
    private String transactionnumber;

    @SerializedName("refnumber")
    @Expose
    private String refnumber;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("operatorname")
    @Expose
    private String operatorname;

    @SerializedName("sendernobile")
    @Expose
    private String sendernobile;


    @SerializedName("transactiondate")
    @Expose
    private String transactiondate;

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

    @SerializedName("debitamount")
    @Expose
    private String debitamount;

    @SerializedName("creditamount")
    @Expose
    private String creditamount;
    @SerializedName("effecativebal")
    @Expose
    private String effecativebal;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("benename")
    @Expose
    private String benename;
    @SerializedName("benemobilenumber")
    @Expose
    private String benemobilenumber;

    @SerializedName("bankaccountnumber")
    @Expose
    private String bankaccountnumber;

    @SerializedName("ifsccode")
    @Expose
    private String ifsccode;

    @SerializedName("sendermobilenumber")
    @Expose
    private String sendermobilenumber;

    @SerializedName("column1")
    @Expose
    private String column1;

    @SerializedName("retailername")
    @Expose
    private String retailername;

    @SerializedName("vendorname")
    @Expose
    private String vendorname;
    @SerializedName("bankname")
    @Expose
    private String bankname;

    public receipt_class(String retailernumber, String transactionnumber, String refnumber, String status, String operatorname, String sendernobile, String transactiondate, String amount, String commission, String gst, String servicecharge, String tds, String debitamount, String creditamount, String effecativebal, String id, String benename, String benemobilenumber, String bankaccountnumber, String ifsccode, String sendermobilenumber, String column1, String retailername, String vendorname, String bankname) {
        this.retailernumber = retailernumber;
        this.transactionnumber = transactionnumber;
        this.refnumber = refnumber;
        this.status = status;
        this.operatorname = operatorname;
        this.sendernobile = sendernobile;
        this.transactiondate = transactiondate;
        this.amount = amount;
        this.commission = commission;
        this.gst = gst;
        this.servicecharge = servicecharge;
        this.tds = tds;
        this.debitamount = debitamount;
        this.creditamount = creditamount;
        this.effecativebal = effecativebal;
        this.id = id;
        this.benename = benename;
        this.benemobilenumber = benemobilenumber;
        this.bankaccountnumber = bankaccountnumber;
        this.ifsccode = ifsccode;
        this.sendermobilenumber = sendermobilenumber;
        this.column1 = column1;
        this.retailername = retailername;
        this.vendorname = vendorname;
        this.bankname = bankname;
    }

    public String getRetailernumber() {
        return retailernumber;
    }

    public void setRetailernumber(String retailernumber) {
        this.retailernumber = retailernumber;
    }

    public String getTransactionnumber() {
        return transactionnumber;
    }

    public void setTransactionnumber(String transactionnumber) {
        this.transactionnumber = transactionnumber;
    }

    public String getRefnumber() {
        return refnumber;
    }

    public void setRefnumber(String refnumber) {
        this.refnumber = refnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    public String getSendernobile() {
        return sendernobile;
    }

    public void setSendernobile(String sendernobile) {
        this.sendernobile = sendernobile;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate;
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

    public String getDebitamount() {
        return debitamount;
    }

    public void setDebitamount(String debitamount) {
        this.debitamount = debitamount;
    }

    public String getCreditamount() {
        return creditamount;
    }

    public void setCreditamount(String creditamount) {
        this.creditamount = creditamount;
    }

    public String getEffecativebal() {
        return effecativebal;
    }

    public void setEffecativebal(String effecativebal) {
        this.effecativebal = effecativebal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBenename() {
        return benename;
    }

    public void setBenename(String benename) {
        this.benename = benename;
    }

    public String getBenemobilenumber() {
        return benemobilenumber;
    }

    public void setBenemobilenumber(String benemobilenumber) {
        this.benemobilenumber = benemobilenumber;
    }

    public String getBankaccountnumber() {
        return bankaccountnumber;
    }

    public void setBankaccountnumber(String bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getSendermobilenumber() {
        return sendermobilenumber;
    }

    public void setSendermobilenumber(String sendermobilenumber) {
        this.sendermobilenumber = sendermobilenumber;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getRetailername() {
        return retailername;
    }

    public void setRetailername(String retailername) {
        this.retailername = retailername;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
}
