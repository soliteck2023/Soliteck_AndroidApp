package com.example.api_call;

public class add {
    private String gifData;

    public add(int gifData) {
        this.gifData = String.valueOf(gifData);
    }

    public String getGifData() {
        return gifData;
    }

    public void setGifData(String gifData) {
        this.gifData = gifData;
    }
}
