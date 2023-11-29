package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkBalanceReceivedReport {
    @SerializedName("balanceAfter")
    @Expose
    private Double balanceAfter;
    @SerializedName("cr")
    @Expose
    private Double cr;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("currentBal")
    @Expose
    private Double currentBal;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("dr")
    @Expose
    private Object dr;
    @SerializedName("transferBy")
    @Expose
    private String transferBy;
    @SerializedName("transferTo")
    @Expose
    private String transferTo;
    @SerializedName("transferdByName")
    @Expose
    private String transferdByName;

    public String getTransferTo() {
        return this.transferTo;
    }

    public void setTransferTo(String transferTo) {
        this.transferTo = transferTo;
    }

    public Double getCr() {
        return this.cr;
    }

    public void setCr(Double cr) {
        this.cr = cr;
    }

    public Object getDr() {
        return this.dr;
    }

    public void setDr(Object dr) {
        this.dr = dr;
    }

    public Double getBalanceAfter() {
        return this.balanceAfter;
    }

    public void setBalanceAfter(Double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getTransferBy() {
        return this.transferBy;
    }

    public void setTransferBy(String transferBy) {
        this.transferBy = transferBy;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Double getCurrentBal() {
        return this.currentBal;
    }

    public void setCurrentBal(Double currentBal) {
        this.currentBal = currentBal;
    }

    public String getTransferdByName() {
        return this.transferdByName;
    }

    public void setTransferdByName(String transferdByName) {
        this.transferdByName = transferdByName;
    }
}
