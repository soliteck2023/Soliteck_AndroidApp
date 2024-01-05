package com.example.api_call;

import com.google.gson.annotations.SerializedName;

public class MobilePlanResponse {
    @SerializedName("Data")
    private MobilePlanResponseData mData;
    @SerializedName("ErrorCode")
    private String mErrorCode;
    @SerializedName("Remarks")
    private String mRemarks;
    @SerializedName("ResponseStatus")
    private String mResponseStatus;
    @SerializedName("Status")
    private String mStatus;

    public MobilePlanResponseData getData() {
        return this.mData;
    }

    public void setData(MobilePlanResponseData data) {
        this.mData = data;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorCode(String errorCode) {
        this.mErrorCode = errorCode;
    }

    public String getRemarks() {
        return this.mRemarks;
    }

    public void setRemarks(String remarks) {
        this.mRemarks = remarks;
    }

    public String getResponseStatus() {
        return this.mResponseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.mResponseStatus = responseStatus;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }
}
