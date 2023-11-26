package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyBankResponse {
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("bankId")
    @Expose
    private Integer bankId;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("ifsc")
    @Expose
    private String ifsc;

    public Integer getBankId() {
        return this.bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfsc() {
        return this.ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
