package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserProfileDetails {
    @SerializedName("AadharNo")
    @Expose
    private String aadharNo;
    @SerializedName(ConstantClass.PROFILEDETAILS.AccountNo)
    @Expose
    private String accountNo;
    @SerializedName(ConstantClass.PROFILEDETAILS.BanksName)
    @Expose
    private String banksName;
    @SerializedName(ConstantClass.PROFILEDETAILS.CityName)
    @Expose
    private String cityName;
    @SerializedName("ContactNo")
    @Expose
    private String contactNo;
    @SerializedName("EmailID")
    @Expose
    private String emailID;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName(ConstantClass.PROFILEDETAILS.IFSCCode)
    @Expose
    private String iFSCCode;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("MiddleName")
    @Expose
    private String middleName;
    @SerializedName("PanCardNo")
    @Expose
    private String panCardNo;
    @SerializedName(ConstantClass.PROFILEDETAILS.PermanentAddress)
    @Expose
    private String permanentAddress;
    @SerializedName(ConstantClass.PROFILEDETAILS.PinCode)
    @Expose
    private String pinCode;
    @SerializedName(ConstantClass.PROFILEDETAILS.RefrenceNumber)
    @Expose
    private String refrenceNumber;
    @SerializedName(ConstantClass.PROFILEDETAILS.ShopAddress)
    @Expose
    private String shopAddress;
    @SerializedName("StatesName")
    @Expose
    private String statesName;
    @SerializedName(ConstantClass.PROFILEDETAILS.WhiteUser)
    @Expose
    private String whiteUser;
    @SerializedName("ZipCode")
    @Expose
    private String zipCode;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPanCardNo() {
        return this.panCardNo;
    }

    public void setPanCardNo(String panCardNo) {
        this.panCardNo = panCardNo;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailID() {
        return this.emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getAadharNo() {
        return this.aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getShopAddress() {
        return this.shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getPermanentAddress() {
        return this.permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getStatesName() {
        return this.statesName;
    }

    public void setStatesName(String statesName) {
        this.statesName = statesName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBanksName() {
        return this.banksName;
    }

    public void setBanksName(String banksName) {
        this.banksName = banksName;
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

    public String getRefrenceNumber() {
        return this.refrenceNumber;
    }

    public void setRefrenceNumber(String refrenceNumber) {
        this.refrenceNumber = refrenceNumber;
    }

    public String getWhiteUser() {
        return this.whiteUser;
    }

    public void setWhiteUser(String whiteUser) {
        this.whiteUser = whiteUser;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
