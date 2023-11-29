package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.SerializedName;

public class OperatorResponseData {
    @SerializedName("circle")
    private String mCircle;
    @SerializedName("Operator")
    private String mOperator;
    @SerializedName("segment")
    private String mSegment;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private Long mStatus;

    public String getCircle() {
        return this.mCircle;
    }

    public void setCircle(String circle) {
        this.mCircle = circle;
    }

    public String getOperator() {
        return this.mOperator;
    }

    public void setOperator(String operator) {
        this.mOperator = operator;
    }

    public String getSegment() {
        return this.mSegment;
    }

    public void setSegment(String segment) {
        this.mSegment = segment;
    }

    public Long getStatus() {
        return this.mStatus;
    }

    public void setStatus(Long status) {
        this.mStatus = status;
    }
}
