package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetStatementReport {
    @SerializedName("Data")
    @Expose
    private List<StatementReport> data = null;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("TotalCredit")
    @Expose
    private String totalCredit;
    @SerializedName("TotalDebit")
    @Expose
    private String totalDebit;

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotalCredit() {
        return this.totalCredit;
    }

    public void setTotalCredit(String totalCredit) {
        this.totalCredit = totalCredit;
    }

    public String getTotalDebit() {
        return this.totalDebit;
    }

    public void setTotalDebit(String totalDebit) {
        this.totalDebit = totalDebit;
    }

    public List<StatementReport> getData() {
        return this.data;
    }

    public void setData(List<StatementReport> data) {
        this.data = data;
    }
}
