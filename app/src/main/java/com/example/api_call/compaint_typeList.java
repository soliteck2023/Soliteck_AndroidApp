package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class compaint_typeList {

    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;

    @SerializedName("data")
    @Expose
    private List<comptTyperesponse> data;

    public compaint_typeList(Integer responseStatus, List<comptTyperesponse> data) {
        this.responseStatus = responseStatus;
        this.data = data;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<comptTyperesponse> getData() {
        return data;
    }

    public void setData(List<comptTyperesponse> data) {
        this.data = data;
    }
}
