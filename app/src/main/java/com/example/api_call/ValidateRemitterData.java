package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ValidateRemitterData implements Serializable {
    @SerializedName("availableLimit")
    @Expose
    private Double availableLimit;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("monthlyLimit")
    @Expose
    private Double monthlyLimit;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pinCode")
    @Expose
    private Integer pinCode;
    @SerializedName("recipients")
    @Expose
    private List<VRecipient> recipients;
    @SerializedName("remitterID")
    @Expose
    private String remitterID;

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemitterID() {
        return this.remitterID;
    }

    public void setRemitterID(String remitterID) {
        this.remitterID = remitterID;
    }

    public Integer getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public Double getMonthlyLimit() {
        return this.monthlyLimit;
    }

    public void setMonthlyLimit(Double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public Double getAvailableLimit() {
        return this.availableLimit;
    }

    public void setAvailableLimit(Double availableLimit) {
        this.availableLimit = availableLimit;
    }

    public List<VRecipient> getRecipients() {
        return this.recipients;
    }

    public void setRecipients(List<VRecipient> recipients) {
        this.recipients = recipients;
    }
}
