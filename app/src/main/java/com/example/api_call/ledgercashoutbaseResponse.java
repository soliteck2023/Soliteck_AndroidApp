package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ledgercashoutbaseResponse {
    @SerializedName("dataN")
    @Expose
    private Object data;
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("data")
    @Expose
    private List<cashoutledgerTransactionReport2> cashoutledgerTransactionReport2s;
    @SerializedName("message")
    @Expose
    private String remarks;
    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public ledgercashoutbaseResponse(Object data, String errorCode, List<cashoutledgerTransactionReport2> cashoutledgerTransactionReport2s, String remarks, Integer responseStatus, String status) {
        this.data = data;
        this.errorCode = errorCode;
        this.cashoutledgerTransactionReport2s = cashoutledgerTransactionReport2s;
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

    public List<cashoutledgerTransactionReport2> getCashoutledgerTransactionReport2s() {
        return cashoutledgerTransactionReport2s;
    }

    public void setCashoutledgerTransactionReport2s(List<cashoutledgerTransactionReport2> cashoutledgerTransactionReport2s) {
        this.cashoutledgerTransactionReport2s = cashoutledgerTransactionReport2s;
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
