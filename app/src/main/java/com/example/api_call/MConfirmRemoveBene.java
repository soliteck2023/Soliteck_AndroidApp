package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MConfirmRemoveBene {
    @SerializedName("data")
    @Expose
    private ConfirmRemovalData data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ConfirmRemovalData getData() {
        return this.data;
    }

    public void setData(ConfirmRemovalData data) {
        this.data = data;
    }
}
