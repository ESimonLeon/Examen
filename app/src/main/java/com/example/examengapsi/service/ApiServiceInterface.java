package com.example.examengapsi.service;

import static com.example.examengapsi.service.ApiRoutes.FORCE_PLP;
import static com.example.examengapsi.service.ApiRoutes.NUM_FOR_ITEM;
import static com.example.examengapsi.service.ApiRoutes.PAGE_NUMBER;
import static com.example.examengapsi.service.ApiRoutes.QUERY_SEARCH;
import static com.example.examengapsi.service.ApiRoutes.SEARCH_PRODUCTS;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceInterface {

    @GET(SEARCH_PRODUCTS)
    Call<String> getProductos(
            @Query(FORCE_PLP) Boolean forcePlp,
            @Query(QUERY_SEARCH) String text,
            @Query(PAGE_NUMBER) Integer page,
            @Query(NUM_FOR_ITEM) Integer items);
}
