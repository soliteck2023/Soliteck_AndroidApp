package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SoftVersionResponse {
    @com.google.gson.annotations.SerializedName("data")
    @Expose
    private String data;
    @com.google.gson.annotations.SerializedName("errorCode")
    @Expose
    private String errorCode;
    @com.google.gson.annotations.SerializedName("remarks")
    @Expose
    private String remarks;
    @com.google.gson.annotations.SerializedName("responseStatus")
    @Expose
    private String responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public String getResponseStatus() {
        return this.responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
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

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private class String {
    }
}
