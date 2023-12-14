package com.example.api_call;

import com.google.gson.annotations.SerializedName;

public class DTHSPlanResData {
    @SerializedName("desc")
    private String mDesc;
    @SerializedName("rs")
    private String mRs;

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String desc) {
        this.mDesc = desc;
    }

    public String getRs() {
        return this.mRs;
    }

    public void setRs(String rs) {
        this.mRs = rs;
    }
}
