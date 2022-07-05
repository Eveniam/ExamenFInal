package com.example.examenfinal.services;

import com.example.examenfinal.entities.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LibroService {

    @GET("libros")
    Call<List<Libro>> getAll();

    @GET("libros/{id}")
    Call<Libro> find(@Path("id") int id);

    @POST("libros")
    Call<Libro> create(@Body Libro contact);

    @PUT("libros/{id}")
    Call<Libro> update(@Path("id") int id, @Body Libro contact);

    @DELETE("libros/{id}")
    Call<Libro> delete(@Path("id") int id);
}
