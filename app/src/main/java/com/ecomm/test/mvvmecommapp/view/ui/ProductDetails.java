package com.ecomm.test.mvvmecommapp.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecomm.test.mvvmecommapp.R;
import com.ecomm.test.mvvmecommapp.service.model.Product;
import com.ecomm.test.mvvmecommapp.viewmodel.ProductViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProductDetails extends AppCompatActivity {
    /*ViewModel specific to ViewModel*/
    private ProductViewModel prod_ViewModel;

    @Nullable
    @BindView(R.id.imageView)
    public ImageView imageView;

    @Nullable
    @BindView(R.id.name)
    public TextView name;

    @Nullable
    @BindView(R.id.department)
    public TextView department;

    @Nullable
    @BindView(R.id.brand)
    public TextView brand;

    @Nullable
    @BindView(R.id.product_division)
    public TextView product_division;

    //! Product ID
    String prod_img_id;

    public void UpdateView(Product mProd)
    {
        Log.e("UpdateView", mProd.toString());
        if (mProd.getImages() != null) {
            Glide.with(this)
                    .load(mProd.getImages().getThumbnail())
                    .into(imageView);
        }

        if (mProd.getName() != null) {
            name.setText(mProd.getName());
        }

        if (mProd.getBrand() != null) {
            brand.setText(mProd.getBrand());
        }

        if (mProd.getDivision() != null) {
            product_division.setText(mProd.getDivision());
        }

        if (mProd.getDepartment()!= null) {
            department.setText(mProd.getDepartment());
        }
    }

    public void getProduct() {
        prod_ViewModel.getObservableProduct().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(@Nullable Product product) {
                if (product != null) {
                    UpdateView(product);
                }
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        ButterKnife.bind(this);

        /*Get the Product Requested*/
        Intent intent = getIntent();
        prod_img_id = intent.getStringExtra("PRODUCT_IMG_ID");
        Log.e("ProductDetails", "PRODUCT_IMG_ID:" + prod_img_id);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        ProductViewModel.Factory factory = new ProductViewModel.Factory(
                this.getApplication(), prod_img_id);

        prod_ViewModel = ViewModelProviders.of(this, factory)
                .get(ProductViewModel.class);

        getProduct();
    }
}
