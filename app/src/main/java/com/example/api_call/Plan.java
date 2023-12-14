package com.example.api_call;

import com.google.gson.annotations.SerializedName;

public class Plan {
    @SerializedName("desc")
    private String mDesc;
    @SerializedName("last_update")
    private String mLastUpdate;
    @SerializedName("plan_name")
    private String mPlanName;
    @SerializedName("rs")
    private Rs mRs;

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

    public String getPlanName() {
        return this.mPlanName;
    }

    public void setPlanName(String planName) {
        this.mPlanName = planName;
    }

    public Rs getRs() {
        return this.mRs;
    }

    public void setRs(Rs rs) {
        this.mRs = rs;
    }
}
