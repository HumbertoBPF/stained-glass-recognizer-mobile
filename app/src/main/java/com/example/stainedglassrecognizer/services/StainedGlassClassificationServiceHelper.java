package com.example.stainedglassrecognizer.services;

import com.example.stainedglassrecognizer.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StainedGlassClassificationServiceHelper {

    public static StainedGlassClassificationService getService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StainedGlassClassificationService.class);
    }
}
