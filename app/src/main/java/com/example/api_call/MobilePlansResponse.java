package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MobilePlansResponse {
    @SerializedName("Data")
    @Expose
    private List<MobileData> data = null;
    @SerializedName("ErrorCode")
    @Expose
    private String errorCode;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("ResponseStatus")
    @Expose
    private String responseStatus;
    @SerializedName("Status")
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

    public List<MobileData> getData() {
        return this.data;
    }

    public void setData(List<MobileData> data) {
        this.data = data;
    }
}
