package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class raisecomplaint {

    @SerializedName("data")
    @Expose
    private List<compaintlist> transaction;

    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;

    public raisecomplaint(List<compaintlist> transaction, Integer responseStatus) {
        this.transaction = transaction;
        this.responseStatus = responseStatus;
    }

    public List<compaintlist> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<compaintlist> transaction) {
        this.transaction = transaction;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }
}
