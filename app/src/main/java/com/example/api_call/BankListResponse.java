package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankListResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("ifsc")
    @Expose
    private String ifsc;

    @SerializedName("branch")
    @Expose
    private String branch;

    @SerializedName("accountName")
    @Expose
    private String accountName;

    @SerializedName("serviceId")
    @Expose
    private String serviceId;

    @SerializedName("serviceName")
    @Expose
    private String serviceName;

    @SerializedName("charges")
    @Expose
    private String charges;

    private List<servicelist> services;

    public BankListResponse(Integer id, String bankName, String ifsc, String branch, String accountName, String serviceId, String serviceName, String charges) {
        this.id = id;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.branch = branch;
        this.accountName = accountName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.charges = charges;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }
}
