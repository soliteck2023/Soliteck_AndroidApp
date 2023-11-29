package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileData {
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("rs")
    @Expose
    private String rs;

    public String getRs() {
        return this.rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
