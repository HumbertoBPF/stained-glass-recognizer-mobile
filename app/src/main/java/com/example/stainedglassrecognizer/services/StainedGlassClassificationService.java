package com.example.stainedglassrecognizer.services;

import com.example.stainedglassrecognizer.models.StainedGlass;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface StainedGlassClassificationService {
    @Multipart
    @POST("classify")
    Call<StainedGlass> classify(@Part MultipartBody.Part image);
}
