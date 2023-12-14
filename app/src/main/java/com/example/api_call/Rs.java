package com.example.api_call;

import com.google.gson.annotations.SerializedName;

public class Rs {
    @SerializedName("5 YEAR")
    private String mFiveYEAR;
    @SerializedName("1 MONTHS")
    private String mMONTHS;
    @SerializedName("9 MONTHS")
    private String mNineMonth;
    @SerializedName("6 MONTHS")
    private String mSixMonths;
    @SerializedName("3 MONTHS")
    private String mThreeMONTHS;
    @SerializedName("1 YEAR")
    private String mYEAR;

    public String getmThreeMONTHS() {
        return this.mThreeMONTHS;
    }

    public void setmThreeMONTHS(String mThreeMONTHS) {
        this.mThreeMONTHS = mThreeMONTHS;
    }

    public String getmSixMonths() {
        return this.mSixMonths;
    }

    public void setmSixMonths(String mSixMonths) {
        this.mSixMonths = mSixMonths;
    }

    public String getmYEAR() {
        return this.mYEAR;
    }

    public void setmYEAR(String mYEAR) {
        this.mYEAR = mYEAR;
    }

    public String getmFiveYEAR() {
        return this.mFiveYEAR;
    }

    public void setmFiveYEAR(String mFiveYEAR) {
        this.mFiveYEAR = mFiveYEAR;
    }

    public String getmMONTHS() {
        return this.mMONTHS;
    }

    public void setmMONTHS(String mMONTHS) {
        this.mMONTHS = mMONTHS;
    }

    public String getmNineMonth() {
        return this.mNineMonth;
    }

    public void setmNineMonth(String mNineMonth) {
        this.mNineMonth = mNineMonth;
    }

    public String getMONTHS() {
        return this.mMONTHS;
    }

    public void setMONTHS(String mONTHS) {
        this.mMONTHS = mONTHS;
    }

    public String getYEAR() {
        return this.mYEAR;
    }

    public void setYEAR(String yEAR) {
        this.mYEAR = yEAR;
    }
}
