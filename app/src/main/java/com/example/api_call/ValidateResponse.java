package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateResponse {
    @SerializedName("data")
    @Expose
    private ValidateRemitterData data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;

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

    public ValidateRemitterData getData() {
        return this.data;
    }

    public void setData(ValidateRemitterData data) {
        this.data = data;
    }
}
