package com.example.api_call;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MobilePlanResponseData {
    @SerializedName("COMBO")
    private List<COMBO> mCOMBO;
    @SerializedName("FULLTT")
    private List<FULLTT> mFULLTT;
    @SerializedName("2G")
    private List<G> mG;
    @SerializedName("3G/4G")
    private List<G4G> mG4G;
    @SerializedName("RATE CUTTER")
    private List<RATECUTTER> mRATECUTTER;
    @SerializedName("SMS")
    private List<SM> mSMS;
    @SerializedName("TOPUP")
    private List<TOPUP> mTOPUP;

    public List<COMBO> getCOMBO() {
        return this.mCOMBO;
    }

    public void setCOMBO(List<COMBO> cOMBO) {
        this.mCOMBO = cOMBO;
    }

    public List<FULLTT> getFULLTT() {
        return this.mFULLTT;
    }

    public void setFULLTT(List<FULLTT> fULLTT) {
        this.mFULLTT = fULLTT;
    }

    public List<G> getG() {
        return this.mG;
    }

    public void setG(List<G> g) {
        this.mG = g;
    }

    public List<G4G> getG4G() {
        return this.mG4G;
    }

    public void setG4G(List<G4G> g4G) {
        this.mG4G = g4G;
    }

    public List<RATECUTTER> getRATECUTTER() {
        return this.mRATECUTTER;
    }

    public void setRATECUTTER(List<RATECUTTER> rATECUTTER) {
        this.mRATECUTTER = rATECUTTER;
    }

    public List<SM> getSMS() {
        return this.mSMS;
    }

    public void setSMS(List<SM> sMS) {
        this.mSMS = sMS;
    }

    public List<TOPUP> getTOPUP() {
        return this.mTOPUP;
    }

    public void setTOPUP(List<TOPUP> tOPUP) {
        this.mTOPUP = tOPUP;
    }
}
