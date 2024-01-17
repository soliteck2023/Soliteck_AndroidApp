package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class viewAEPSResponse {

    @SerializedName("data")
    @Expose
    private List<cashoutledgerTransactionReport> transaction;

    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;

    public viewAEPSResponse(List<cashoutledgerTransactionReport> transaction, Integer responseStatus) {
        this.transaction = transaction;
        this.responseStatus = responseStatus;
    }

    public List<cashoutledgerTransactionReport> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<cashoutledgerTransactionReport> transaction) {
        this.transaction = transaction;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }
}
