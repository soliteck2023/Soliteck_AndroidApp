package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class newBankListbaseResponse {
    @SerializedName("data")
    @Expose
    private List<BankListResponse> companyBankList;
    @SerializedName("dataN")
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
    private Integer responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public newBankListbaseResponse(List<BankListResponse> companyBankList, Object data, String errorCode, String remarks, Integer responseStatus, String status) {
        this.companyBankList = companyBankList;
        this.data = data;
        this.errorCode = errorCode;
        this.remarks = remarks;
        this.responseStatus = responseStatus;
        this.status = status;
    }

    public List<BankListResponse> getCompanyBankList() {
        return companyBankList;
    }

    public void setCompanyBankList(List<BankListResponse> companyBankList) {
        this.companyBankList = companyBankList;
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

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
