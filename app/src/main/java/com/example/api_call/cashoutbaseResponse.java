package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class cashoutbaseResponse {
    @SerializedName("dataN")
    @Expose
    private Object data;
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("data")
    @Expose
    private List<cashoutledgerTransactionReport> cashoutledgerTransactionReports;
    @SerializedName("message")
    @Expose
    private String remarks;
    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public cashoutbaseResponse(Object data, String errorCode, List<cashoutledgerTransactionReport> cashoutledgerTransactionReports, String remarks, Integer responseStatus, String status) {
        this.data = data;
        this.errorCode = errorCode;
        this.cashoutledgerTransactionReports = cashoutledgerTransactionReports;
        this.remarks = remarks;
        this.responseStatus = responseStatus;
        this.status = status;
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

    public List<cashoutledgerTransactionReport> getCashoutledgerTransactionReports() {
        return cashoutledgerTransactionReports;
    }

    public void setCashoutledgerTransactionReports(List<cashoutledgerTransactionReport> cashoutledgerTransactionReports) {
        this.cashoutledgerTransactionReports = cashoutledgerTransactionReports;
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
