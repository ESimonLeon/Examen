package com.example.examengapsi;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.examengapsi.model.Producto;
import com.example.examengapsi.service.ApiService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainInterface.MainPresenter {

    private final MainInterface.MainView view;
    private final ApiService apiService;

    public MainPresenter(MainInterface.MainView mainView) {
        this.view = mainView;
        apiService = new ApiService().initApiService();
    }

    @Override
    public void searchProduct(String text) {
        Call<String> call = apiService.apiInterface.getProductos(true, text, 1, 10);
        call.enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body() != null) {
                            JSONObject jsonObject = new JSONObject(response.body());
                            getResultProductos(jsonObject.getJSONObject("plpResults").getJSONArray("records"));
                        }
                    } catch (JSONException e) {
                        view.resultError(Objects.requireNonNull(e.getCause()).toString(), e.getMessage());
                        e.printStackTrace();
                    }

                }

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                view.resultError(Objects.requireNonNull(t.getCause()).toString(), t.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getResultProductos(JSONArray records) {
        ArrayList<Producto> products = new ArrayList<>();
        for (int x = 0; x < records.length(); x++) {
            try {
                JSONObject jsonObject = records.getJSONObject(x);
                Producto product = new Gson().fromJson(String.valueOf(jsonObject), Producto.class);
                products.add(product);
            } catch (JSONException e) {
                view.resultError(Objects.requireNonNull(e.getCause()).toString(), e.getMessage());
                e.printStackTrace();
            }
        }

        view.resultSearchjProduct(products);

    }

}
