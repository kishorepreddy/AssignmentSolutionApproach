package com.ecomm.test.mvvmecommapp.service.repository;

import com.ecomm.test.mvvmecommapp.service.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface HauteLookCdnService {
    String HTTPS_API_HAUTECDN_URL = "https://www.hautelookcdn.com/mobile-apps/Interview/";

    @GET("catalog.json")
    Call<Products> getProductList();
}
