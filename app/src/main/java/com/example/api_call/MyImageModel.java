package com.example.api_call;

public class MyImageModel {

    private String bencodedImage;

    public MyImageModel(String bencodedImage) {
        this.bencodedImage = bencodedImage;
    }

    public String getBencodedImage() {
        return bencodedImage;
    }

    public void setBencodedImage(String bencodedImage) {
        this.bencodedImage = bencodedImage;
    }
}

