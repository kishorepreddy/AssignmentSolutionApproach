package com.ecomm.test.mvvmecommapp.service.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("division")
    public String division;
    @SerializedName("department")
    public String department;
    @SerializedName("brand")
    public String brand;
    @SerializedName("name")
    public String name;

    @Override
    public String toString() {
        return "[Product]{" +
                "division='" + division + '\'' +
                ", department='" + department + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", images=" + images +
                '}';
    }

    @SerializedName("images")
    public images images;

    public Product() {
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public images getImages() {
        return images;
    }

    public void setImages(images images) {
        this.images = images;
    }

    public Product(String division, String department, String brand, String name, images images) {
        this.division = division;
        this.department = department;
        this.brand = brand;
        this.name = name;
        this.images = images;
    }

}
