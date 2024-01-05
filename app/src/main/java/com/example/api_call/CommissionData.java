package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommissionData {
    @SerializedName("CommPer")
    @Expose
    private String commPer;
    @SerializedName("CommVal")
    @Expose
    private String commVal;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("OperatorName")
    @Expose
    private String operatorName;
    @SerializedName("ServiceChargePer")
    @Expose
    private String serviceChargePer;
    @SerializedName("ServiceChargeVal")
    @Expose
    private String serviceChargeVal;
    @SerializedName("ServiceName")
    @Expose
    private String serviceName;

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getOperatorName() {
        return this.operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCommPer() {
        return this.commPer;
    }

    public void setCommPer(String commPer) {
        this.commPer = commPer;
    }

    public String getCommVal() {
        return this.commVal;
    }

    public void setCommVal(String commVal) {
        this.commVal = commVal;
    }

    public String getServiceChargePer() {
        return this.serviceChargePer;
    }

    public void setServiceChargePer(String serviceChargePer) {
        this.serviceChargePer = serviceChargePer;
    }

    public String getServiceChargeVal() {
        return this.serviceChargeVal;
    }

    public void setServiceChargeVal(String serviceChargeVal) {
        this.serviceChargeVal = serviceChargeVal;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
