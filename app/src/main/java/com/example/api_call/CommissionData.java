package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommissionData {
    @SerializedName("fName")
    @Expose
    private String fName;
    @SerializedName("uniqueCode")
    @Expose
    private String uniquecode;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("isFixed")
    @Expose
    private String isfixed;
    @SerializedName("isApplicable")
    @Expose
    private String isapplicable;
    @SerializedName("serviceCharge")
    @Expose
    private String servicecharge;
    @SerializedName("isServiceFixed")
    @Expose
    private String isservicefixed;

    @SerializedName("isServiceApplicable")
    @Expose
    private String isserviceapplicable;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("operator")
    @Expose
    private String operator;

    public CommissionData(String fName, String uniquecode, String commission, String isfixed, String isapplicable, String servicecharge, String isservicefixed, String isserviceapplicable, String product, String description, String operator) {
        this.fName = fName;
        this.uniquecode = uniquecode;
        this.commission = commission;
        this.isfixed = isfixed;
        this.isapplicable = isapplicable;
        this.servicecharge = servicecharge;
        this.isservicefixed = isservicefixed;
        this.isserviceapplicable = isserviceapplicable;
        this.product = product;
        this.description = description;
        this.operator = operator;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getUniquecode() {
        return uniquecode;
    }

    public void setUniquecode(String uniquecode) {
        this.uniquecode = uniquecode;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getIsfixed() {
        return isfixed;
    }

    public void setIsfixed(String isfixed) {
        this.isfixed = isfixed;
    }

    public String getIsapplicable() {
        return isapplicable;
    }

    public void setIsapplicable(String isapplicable) {
        this.isapplicable = isapplicable;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getIsservicefixed() {
        return isservicefixed;
    }

    public void setIsservicefixed(String isservicefixed) {
        this.isservicefixed = isservicefixed;
    }

    public String getIsserviceapplicable() {
        return isserviceapplicable;
    }

    public void setIsserviceapplicable(String isserviceapplicable) {
        this.isserviceapplicable = isserviceapplicable;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
