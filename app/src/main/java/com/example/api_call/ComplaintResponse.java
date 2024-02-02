package com.example.api_call;

import java.util.List;

public class ComplaintResponse {

    private int responseStatus;
    private List<compaintlist> data;

    public ComplaintResponse(int responseStatus, List<compaintlist> data) {
        this.responseStatus = responseStatus;
        this.data = data;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<compaintlist> getData() {
        return data;
    }

    public void setData(List<compaintlist> data) {
        this.data = data;
    }
}
