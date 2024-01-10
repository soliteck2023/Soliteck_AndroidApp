package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Commisionbasereport {


    @SerializedName("message")
    @Expose
    private String remarks;
    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private String data;
//    @SerializedName("data")
//    @Expose
//    private List<CommissionData> commissionData;


    public Commisionbasereport(String remarks, String responseStatus, String status, String data) {
        this.remarks = remarks;
        this.responseStatus = responseStatus;
        this.status = status;
        this.data = data;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
