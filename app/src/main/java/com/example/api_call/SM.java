package com.example.api_call;

import com.google.gson.annotations.SerializedName;

public class SM {
    @SerializedName("desc")
    private String mDesc;
    @SerializedName("last_update")
    private String mLastUpdate;
    @SerializedName("rs")
    private String mRs;
    @SerializedName("validity")
    private String mValidity;

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String desc) {
        this.mDesc = desc;
    }

    public String getLastUpdate() {
        return this.mLastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.mLastUpdate = lastUpdate;
    }

    public String getRs() {
        return this.mRs;
    }

    public void setRs(String rs) {
        this.mRs = rs;
    }

    public String getValidity() {
        return this.mValidity;
    }

    public void setValidity(String validity) {
        this.mValidity = validity;
    }
}
