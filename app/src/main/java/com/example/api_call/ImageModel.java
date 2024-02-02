package com.example.api_call;

import android.graphics.Bitmap;

public class ImageModel {
    private Bitmap bitmap;

    public ImageModel(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
