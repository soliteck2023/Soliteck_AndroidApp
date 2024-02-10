package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VRecipient implements Serializable {
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("ifsc")
    @Expose
    private String ifsc;
    @SerializedName("isValidate")
    @Expose
    private boolean isValidate;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rptid")
    @Expose
    private String rptid;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public VRecipient(String accountNo, String bankName, String ifsc, boolean isValidate, String mobileNo, String name, String rptid, String status) {
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.isValidate = isValidate;
        this.mobileNo = mobileNo;
        this.name = name;
        this.rptid = rptid;
        this.status = status;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public boolean isValidate() {
        return isValidate;
    }

    public void setValidate(boolean validate) {
        isValidate = validate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRptid() {
        return rptid;
    }

    public void setRptid(String rptid) {
        this.rptid = rptid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
