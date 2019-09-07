package com.ecomm.test.mvvmecommapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.ecomm.test.mvvmecommapp.service.model.Product;
import com.ecomm.test.mvvmecommapp.service.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private final LiveData<Product> productObservable;
    private final String product_img_id;

    public ProductViewModel(@NonNull Application application,
                            final String product_img_id) {
        super(application);
        this.product_img_id = product_img_id;
        productObservable = ProductRepository.getInstance().getProducDetails(this.product_img_id);
    }

    public LiveData<Product> getObservableProduct() {
        return productObservable;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;

        private final String product_img_id;

        public Factory(@NonNull Application application, String product_img_id) {
            this.application = application;
            this.product_img_id = product_img_id;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ProductViewModel(application, product_img_id);
        }
    }
}
