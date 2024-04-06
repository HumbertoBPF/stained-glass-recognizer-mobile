package com.example.stainedglassrecognizer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Imaging {
    public static Bitmap getBitmapFromBase64(String imageBase64) {
        byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
