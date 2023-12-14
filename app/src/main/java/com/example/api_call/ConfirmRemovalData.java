package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmRemovalData {
    @SerializedName("recipientCode")
    @Expose
    private String recipientCode;

    public String getRecipientCode() {
        return this.recipientCode;
    }

    public void setRecipientCode(String recipientCode) {
        this.recipientCode = recipientCode;
    }
}
