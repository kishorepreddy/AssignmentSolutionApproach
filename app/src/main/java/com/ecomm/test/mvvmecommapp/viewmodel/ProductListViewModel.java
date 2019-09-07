package com.ecomm.test.mvvmecommapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ecomm.test.mvvmecommapp.service.model.Product;
import com.ecomm.test.mvvmecommapp.service.repository.ProductRepository;

import java.util.List;

public class ProductListViewModel extends AndroidViewModel {
    private final LiveData<List<Product>> productListObservable;

    public ProductListViewModel(Application application) {
        super(application);
        productListObservable = ProductRepository.getInstance().getProductList();
    }

    public LiveData<List<Product>> getProductListObservable() {
        return productListObservable;
    }
}
