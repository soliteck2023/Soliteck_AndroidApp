package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentReceivedResponse {
    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("networkBalanceReceivedReport")
    @Expose
    private List<NetworkBalanceReceivedReport> networkBalanceReceivedReport;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public List<NetworkBalanceReceivedReport> getNetworkBalanceReceivedReport() {
        return this.networkBalanceReceivedReport;
    }

    public void setNetworkBalanceReceivedReport(List<NetworkBalanceReceivedReport> networkBalanceReceivedReport) {
        this.networkBalanceReceivedReport = networkBalanceReceivedReport;
    }

    public Integer getResponseStatus() {
        return this.responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
