package com.example.examengapsi;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.examengapsi.model.Producto;
import com.example.examengapsi.service.ApiService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainInterface.MainPresenter {

    private Context context;
    private MainInterface.MainView view;
    private ApiService apiService;

    public MainPresenter(Context context, MainInterface.MainView mainView) {
        this.context = context;
        this.view = mainView;
        apiService = new ApiService().initApiService(context);
    }

    @Override
    public void searchProduct(String text) {
        Call<String> call = apiService.apiInterface.getProductos(true, text, 1, 10);
        call.enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    try {
                        if (response.body() != null) {
                            JSONObject jsonObject = new JSONObject(response.body());
                            getResultProductos(jsonObject.getJSONObject("plpResults").getJSONArray("records"));
                        }
                    } catch (JSONException e) {
                        view.resultError(e.getCause().toString(), e.getMessage());
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.resultError(t.getCause().toString(), t.getMessage());
            }
        });
    }

    private void getResultProductos(JSONArray records) {
        ArrayList<Producto> products = new ArrayList<>();
        for (int x = 0; x < records.length(); x++) {
            try {
                JSONObject jsonObject = records.getJSONObject(x);
                Producto product = new Gson().fromJson(String.valueOf(jsonObject), Producto.class);
                products.add(product);
            } catch (JSONException e) {
                view.resultError(e.getCause().toString(), e.getMessage());
                e.printStackTrace();
            }
        }

        view.resultSearchjProduct(products);

    }

}
