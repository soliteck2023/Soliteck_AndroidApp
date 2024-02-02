package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpverifyResponse {

    @SerializedName("Data")
    @Expose
    private String Data;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("responseStatus")
    @Expose
    private int responseStatus;

    public OtpverifyResponse(String data, String message, int responseStatus) {
        Data = data;
        this.message = message;
        this.responseStatus = responseStatus;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }
}
