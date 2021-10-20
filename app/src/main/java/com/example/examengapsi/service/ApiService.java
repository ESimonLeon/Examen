package com.example.examengapsi.service;

import static com.example.examengapsi.service.ApiRoutes.URLSERVICE;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiService {

    public ApiServiceInterface apiInterface;

    public ApiService initApiService() {

        Retrofit.Builder apiClientConfig = new Retrofit.Builder()
                .baseUrl(URLSERVICE)
                .addConverterFactory(ScalarsConverterFactory.create());

        apiInterface = apiClientConfig.build().create(ApiServiceInterface.class);
        return this;
    }

}
