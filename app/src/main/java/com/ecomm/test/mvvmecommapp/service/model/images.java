package com.ecomm.test.mvvmecommapp.service.model;

import com.google.gson.annotations.SerializedName;

public class images {
    @SerializedName("thumbnail")
    public String thumbnail;

    @Override
    public String toString() {
        return "[images]{" +
                "thumbnail='" + thumbnail + '\'' +
                '}';
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public images(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
