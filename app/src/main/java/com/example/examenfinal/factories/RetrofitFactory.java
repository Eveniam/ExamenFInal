package com.example.examenfinal.factories;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    public static Retrofit build(Context context){
        return new Retrofit.Builder()
                .baseUrl("https://62c43933abea8c085a6fd58d.mockapi.io/api/v1/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    private static OkHttpClient getClient() {
        OkHttpClient httpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request().newBuilder();
                requestBuilder.header("Content-Type", "application/json");
                requestBuilder.header("Authorization", "Client-ID 8bcc638875f89d9");
                return chain.proceed(requestBuilder.build());
            }
        }).build();


        return httpClient;
    }
}
