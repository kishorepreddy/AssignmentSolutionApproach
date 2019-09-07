package com.ecomm.test.mvvmecommapp.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class Products {

    @SerializedName("products")
    public List<Product> products;

    @Override
    public String toString() {
        return "[Products]{" +
                "products=" + products +
                '}';
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
