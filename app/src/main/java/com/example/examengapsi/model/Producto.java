package com.example.examengapsi.model;

public class Producto {
    private String productDisplayName;
    private Double listPrice;
    private String smImage;

    public Producto(String productDisplayName, Double listPrice, String smImage) {
        this.productDisplayName = productDisplayName;
        this.listPrice = listPrice;
        this.smImage = smImage;
    }

    public void setProductDisplayName(String productDisplayName) {
        this.productDisplayName = productDisplayName;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public void setSmImage(String smImage) {
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
