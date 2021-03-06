package com.example.examenfinal.services;

import com.example.examenfinal.entities.Image;
import com.example.examenfinal.entities.ImagePost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ImageService {
    @POST("image")
    Call<Image> create(@Body ImagePost body);
}
