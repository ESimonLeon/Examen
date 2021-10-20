package com.example.examengapsi.model;

public class Producto {
    private final String productDisplayName;
    private final Double listPrice;
    private final String smImage;

    public Producto(String productDisplayName, Double listPrice, String smImage) {
        this.productDisplayName = productDisplayName;
        this.listPrice = listPrice;
        this.smImage = smImage;
    }

    public String getProductDisplayName() {
        return productDisplayName;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public String getSmImage() {
        return smImage;
    }
}
