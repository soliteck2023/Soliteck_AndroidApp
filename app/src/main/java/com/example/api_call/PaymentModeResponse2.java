package com.example.api_call;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentModeResponse2 {

    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("paymentMode")
    @Expose
    private List<PaymentMode> paymentMode;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

}
