package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceIdResponse {
    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;
//    @SerializedName("responseStatus")
//    @Expose
//    private int responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;


    public DeviceIdResponse(Object data, String errorCode, String remarks, String responseStatus, String status) {
        this.data = data;
        this.errorCode = errorCode;
        this.remarks = remarks;
        this.responseStatus = responseStatus;
        this.status = status;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

//    public int getResponseStatus() {
//        return responseStatus;
//    }

//    public void setResponseStatus(int responseStatus) {
//        this.responseStatus = responseStatus;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
