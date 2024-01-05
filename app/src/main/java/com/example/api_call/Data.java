package com.example.api_call;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("Add-On Pack")
    private List<AddOnPack> mAddOnPack;
    @SerializedName("Plan")
    private List<Plan> mPlan;

    public List<AddOnPack> getAddOnPack() {
        return this.mAddOnPack;
    }

    public void setAddOnPack(List<AddOnPack> addOnPack) {
        this.mAddOnPack = addOnPack;
    }

    public List<Plan> getPlan() {
        return this.mPlan;
    }

    public void setPlan(List<Plan> plan) {
        this.mPlan = plan;
    }
}
