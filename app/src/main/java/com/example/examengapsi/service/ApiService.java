package com.example.examengapsi.service;

import android.content.Context;

import com.example.examengapsi.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiService {

    public ApiServiceInterface apiInterface;
    private String URL = "https://shoppapp.liverpool.com.mx";

    public ApiService() {
    }

    public ApiService initApiService(Context context) {

        Retrofit.Builder apiClientConfig = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(ScalarsConverterFactory.create());

        apiInterface = apiClientConfig.build().create(ApiServiceInterface.class);
        return this;
    }

    private static Map<String, String> mapHeaders = null;

    public static Map<String, String> getMapHeaders(Context context) {
        if (mapHeaders == null) {
            mapHeaders = new HashMap<>();
        } else {
            mapHeaders.clear();
        }
        mapHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        return mapHeaders;
    }

}
