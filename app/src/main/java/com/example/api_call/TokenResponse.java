package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse {

    @SerializedName("Data")
    @Expose
    private TokenData data;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;

    public TokenResponse(TokenData data, String message, String statusCode) {
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }

    public TokenData getData() {
        return data;
    }

    public void setData(TokenData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
