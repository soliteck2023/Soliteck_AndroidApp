package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class viewPaymentResponse {
    @SerializedName("data")
    @Expose
    private List<receipt_class> transaction;

    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;

    public viewPaymentResponse(List<receipt_class> transaction, Integer responseStatus) {
        this.transaction = transaction;
        this.responseStatus = responseStatus;
    }

    public List<receipt_class> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<receipt_class> transaction) {
        this.transaction = transaction;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }
}
