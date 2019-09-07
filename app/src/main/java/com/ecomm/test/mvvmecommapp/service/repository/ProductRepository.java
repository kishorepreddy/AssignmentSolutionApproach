package com.ecomm.test.mvvmecommapp.service.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecomm.test.mvvmecommapp.service.model.Product;
import com.ecomm.test.mvvmecommapp.service.model.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRepository {
    //! Access to REST API
    private HauteLookCdnService hauteCdnService;
    //! Repository
    private static ProductRepository hauteCdnRepository;
    //! List of products
    MutableLiveData<List<Product>> data = new MutableLiveData<>();
    //! Specific Product
    MutableLiveData<Product> product = new MutableLiveData<>();

    private ProductRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HauteLookCdnService.HTTPS_API_HAUTECDN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        hauteCdnService = retrofit.create(HauteLookCdnService.class);
    }

    public synchronized static ProductRepository getInstance() {
        if (hauteCdnRepository == null) {
            if (hauteCdnRepository == null) {
                hauteCdnRepository = new ProductRepository();
            }
        }
        return hauteCdnRepository;
    }

    /*get All products from Cloud*/
    public MutableLiveData<List<Product>> getProductList() {
        Call<Products> call = hauteCdnService.getProductList();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                Products mProductWrapper = response.body();
                Log.e("JSON Response", response.body().toString());
                if (mProductWrapper != null && mProductWrapper.getProducts() != null) {
                    for(Product model : mProductWrapper.getProducts()) {
                        System.out.println(model.toString());
                        Log.e("Product", model.toString());
                    }
                    data.setValue(mProductWrapper.getProducts());
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Log.e("JSON Failure", t.getMessage());
            }
        });
        return data;
    }

    /*Get the only product required*/
    public LiveData<Product> getProducDetails(String ID) {
        boolean found = false;
        Log.e("Number of Items Found", String.valueOf(data.getValue().size()));
        for(int index = 0; index < data.getValue().size(); index++)
        {
            /*Compare Product Image URL*/
            Log.e("Present in DB", data.getValue().get(index).getImages().getThumbnail());
            Log.e("Compared Against", ID);

            if(ID.equals(data.getValue().get(index).getImages().getThumbnail()))
            {
                product.setValue(data.getValue().get(index));
                Log.e("ProductFound", "PRODUCT:" + data.getValue().get(index).toString());
                found = true;
            }
        }
        if(found == false)
        {
            Log.e("ProductNotFound", "PRODUCT:" + "0");
        }
        return product;
    }
}
