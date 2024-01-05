package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeConfirmResponse {
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("operatorTxnNumber")
    @Expose
    private String operatorTxnNumber;
    @SerializedName("remarks")
    @Expose
    private String remarks;


    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;
    @SerializedName("transactionNumber")
    @Expose
    private String transactionNumber;
    @SerializedName("venderTxnNumber")
    @Expose
    private String venderTxnNumber;

    public String getTransactionNumber() {
        return this.transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getOperatorTxnNumber() {
        return this.operatorTxnNumber;
    }

    public void setOperatorTxnNumber(String operatorTxnNumber) {
        this.operatorTxnNumber = operatorTxnNumber;
    }

    public String getVenderTxnNumber() {
        return this.venderTxnNumber;
    }

    public void setVenderTxnNumber(String venderTxnNumber) {
        this.venderTxnNumber = venderTxnNumber;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

//    public Integer getResponseStatus() {
//        return this.responseStatus;
//    }


//    public void setResponseStatus(Integer responseStatus) {
//        this.responseStatus = responseStatus;
//    }


//    public String getResponseStatus() {
//        return responseStatus;
//    }
//

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }


//    public void setResponseStatus(String responseStatus) {
//        this.responseStatus = responseStatus;
//    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
