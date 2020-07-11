package com.example.examengapsi.service;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceInterface {

    @GET("/appclienteservices/services/v3/plp")
    Call<String> getProductos(
            @Query("force-plp") Boolean forcePlp,
            @Query("search-string") String text,
            @Query("page-number") Integer page,
            @Query("number-of-items-per-page") Integer items);
}
