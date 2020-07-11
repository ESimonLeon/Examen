package com.example.examengapsi;

import com.example.examengapsi.model.Producto;

import java.util.ArrayList;

interface MainInterface {
    interface MainView {
        void resultSearchjProduct(ArrayList<Producto> products);
        void resultError(String cause, String message);
    }

    interface MainPresenter {
        void searchProduct(String text);
    }
}
