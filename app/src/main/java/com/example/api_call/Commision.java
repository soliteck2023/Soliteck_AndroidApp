package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Commision {
    @SerializedName("Data")
    @Expose
    private List<CommissionData> data = null;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("TotalAmount")
    @Expose
    private String totalAmount;


    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CommissionData> getData() {
        return this.data;
    }

    public void setData(List<CommissionData> data) {
        this.data = data;
    }
}
