package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBankResponse {
    @SerializedName(ConstantClass.PROFILEDETAILS.AccountNo)
    @Expose
    private String accountNo;
    @SerializedName("BankDetails")
    @Expose
    private String bankDetails;
    @SerializedName("BankName")
    @Expose
    private String bankName;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("HolderName")
    @Expose
    private String holderName;
    @SerializedName(ConstantClass.PROFILEDETAILS.IFSCCode)
    @Expose
    private String iFSCCode;
    @SerializedName("ReferenceCode")
    @Expose
    private String referenceCode;
    @SerializedName("ReferenceNo")
    @Expose
    private String referenceNo;

    public String getBankDetails() {
        return this.bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public String getReferenceNo() {
        return this.referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getReferenceCode() {
        return this.referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getHolderName() {
        return this.holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIFSCCode() {
        return this.iFSCCode;
    }

    public void setIFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
