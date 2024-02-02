package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class agremment_class {

    @SerializedName("uniqueCode")
    @Expose
    private String uniqueCode;
    @SerializedName("incorporateDate")
    @Expose
    private String incorporateDate;
    @SerializedName("isActive")
    @Expose
    private String isActive;
    @SerializedName("fName")
    @Expose
    private String fName;
    @SerializedName("roleId")
    @Expose
    private String roleId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shopName")
    @Expose
    private String shopName;

    @SerializedName("isAggrement")
    @Expose
    private boolean isAggrement;
    @SerializedName("shopAddress")
    @Expose
    private String shopAddress;

    public agremment_class(String uniqueCode, String incorporateDate, String isActive, String fName, String roleId, String name, String shopName, boolean isAggrement, String shopAddress) {
        this.uniqueCode = uniqueCode;
        this.incorporateDate = incorporateDate;
        this.isActive = isActive;
        this.fName = fName;
        this.roleId = roleId;
        this.name = name;
        this.shopName = shopName;
        this.isAggrement = isAggrement;
        this.shopAddress = shopAddress;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getIncorporateDate() {
        return incorporateDate;
    }

    public void setIncorporateDate(String incorporateDate) {
        this.incorporateDate = incorporateDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public boolean isAggrement() {
        return isAggrement;
    }

    public void setAggrement(boolean aggrement) {
        isAggrement = aggrement;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}
