package com.example.api_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AggrementCertificateclass {

    @SerializedName("responseStatus")
    @Expose
    private int responseStatus;

    @SerializedName("userData")
    @Expose
    private List<agremment_class> userData;

    public AggrementCertificateclass(int responseStatus, List<agremment_class> userData) {
        this.responseStatus = responseStatus;
        this.userData = userData;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<agremment_class> getUserData() {
        return userData;
    }

    public void setUserData(List<agremment_class> userData) {
        this.userData = userData;
    }
}
