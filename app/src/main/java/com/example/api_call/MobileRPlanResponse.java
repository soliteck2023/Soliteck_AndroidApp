package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MobileRPlanResponse {
    @SerializedName("operator")
    @Expose
    private String operator;
    @SerializedName("records")
    @Expose
    private List<MobilePlanRecord> records;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private Integer status;
    @SerializedName("tel")
    @Expose
    private String tel;
    @SerializedName("time")
    @Expose
    private Double time;

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<MobilePlanRecord> getRecords() {
        return this.records;
    }

    public void setRecords(List<MobilePlanRecord> records) {
        this.records = records;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTime() {
        return this.time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
