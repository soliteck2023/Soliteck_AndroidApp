package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateRemitter {
    @SerializedName("response")
    @Expose
    private ValidateResponse response;

    public ValidateResponse getResponse() {
        return this.response;
    }

    public void setResponse(ValidateResponse response) {
        this.response = response;
    }
}
