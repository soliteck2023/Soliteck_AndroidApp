package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MBankListResponse {
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("Name.MARK")
    @Expose
    private String id;
    @SerializedName("ifscCode")
    @Expose
    private String ifscCode;
    @SerializedName("isDown")
    @Expose
    private Boolean isDown;
    @SerializedName("isImpsAllow")
    @Expose
    private Boolean isImpsAllow;
    @SerializedName("isNeftAllow")
    @Expose
    private Boolean isNeftAllow;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Boolean getIsDown() {
        return this.isDown;
    }

    public void setIsDown(Boolean isDown) {
        this.isDown = isDown;
    }

    public Boolean getIsImpsAllow() {
        return this.isImpsAllow;
    }

    public void setIsImpsAllow(Boolean isImpsAllow) {
        this.isImpsAllow = isImpsAllow;
    }

    public Boolean getIsNeftAllow() {
        return this.isNeftAllow;
    }

    public void setIsNeftAllow(Boolean isNeftAllow) {
        this.isNeftAllow = isNeftAllow;
    }
}
