package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LedgerReportBase {

    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("message")
    @Expose
    private String remarks;
    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<ledgerTransactionReport> transaction;

    public LedgerReportBase(String errorCode, String remarks, Integer responseStatus, String status, List<ledgerTransactionReport> transaction) {
        this.errorCode = errorCode;
        this.remarks = remarks;
        this.responseStatus = responseStatus;
        this.status = status;
        this.transaction = transaction;
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

    public List<ledgerTransactionReport> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<ledgerTransactionReport> transaction) {
        this.transaction = transaction;
    }
}
