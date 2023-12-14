package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MAccVerify {
    @SerializedName("response")
    @Expose
    private MAccVerification response;

    public MAccVerification getResponse() {
        return this.response;
    }

    public void setResponse(MAccVerification response) {
        this.response = response;
    }
}
