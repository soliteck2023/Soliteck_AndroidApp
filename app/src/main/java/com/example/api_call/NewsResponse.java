package com.example.api_call;

import java.util.List;

public class NewsResponse {
    private List<DataItem> data;
    private int responseStatus;

    public void setData(List<DataItem> data){
        this.data = data;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setResponseStatus(int responseStatus){
        this.responseStatus = responseStatus;
    }

    public int getResponseStatus(){
        return responseStatus;
    }
}
