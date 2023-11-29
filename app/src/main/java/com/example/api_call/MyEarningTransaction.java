package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyEarningTransaction {
    @SerializedName("actualcommission")
    @Expose
    private Double actualcommission;
    @SerializedName("gst")
    @Expose
    private Double gst;
    @SerializedName("tds")
    @Expose
    private Double tds;
    @SerializedName("totalNetworkLoad")
    @Expose
    private Double totalNetworkLoad;
    @SerializedName("totalPrimary")
    @Expose
    private Double totalPrimary;
    @SerializedName("totalTransctionAmount")
    @Expose
    private Double totalTransctionAmount;
    @SerializedName("totalcommission")
    @Expose
    private Double totalcommission;

    public Double getTds() {
        return this.tds;
    }

    public void setTds(Double tds) {
        this.tds = tds;
    }

    public Double getGst() {
        return this.gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public Double getTotalcommission() {
        return this.totalcommission;
    }

    public void setTotalcommission(Double totalcommission) {
        this.totalcommission = totalcommission;
    }

    public Double getActualcommission() {
        return this.actualcommission;
    }

    public void setActualcommission(Double actualcommission) {
        this.actualcommission = actualcommission;
    }

    public Double getTotalTransctionAmount() {
        return this.totalTransctionAmount;
    }

    public void setTotalTransctionAmount(Double totalTransctionAmount) {
        this.totalTransctionAmount = totalTransctionAmount;
    }

    public Double getTotalPrimary() {
        return this.totalPrimary;
    }

    public void setTotalPrimary(Double totalPrimary) {
        this.totalPrimary = totalPrimary;
    }

    public Double getTotalNetworkLoad() {
        return this.totalNetworkLoad;
    }

    public void setTotalNetworkLoad(Double totalNetworkLoad) {
        this.totalNetworkLoad = totalNetworkLoad;
    }
}
