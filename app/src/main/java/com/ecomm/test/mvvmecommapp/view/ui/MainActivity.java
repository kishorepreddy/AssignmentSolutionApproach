package com.ecomm.test.mvvmecommapp.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ecomm.test.mvvmecommapp.R;
import com.ecomm.test.mvvmecommapp.service.model.Product;
import com.ecomm.test.mvvmecommapp.view.adapter.ProductAdapter;
import com.ecomm.test.mvvmecommapp.view.callback.ProductClickCallback;
import com.ecomm.test.mvvmecommapp.viewmodel.ProductListViewModel;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private ProductListViewModel prod_list_ViewModel;
    private ProductAdapter prod_adapt;

    public void getProduct() {
        swipeRefresh.setRefreshing(true);
        prod_list_ViewModel.getProductListObservable().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> prodList) {
                swipeRefresh.setRefreshing(false);
                prod_adapt.setProductList(prodList);
                prepareRecyclerView(prodList);
            }
        });
    }

    private final ProductClickCallback productClickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Log.e("ProductClickCallback", product.toString());
                show(product);
            }
        }
    };

    private void prepareRecyclerView(List<Product> blogList) {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(prod_adapt);
        prod_adapt.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefresh = findViewById(R.id.swiperefresh);
        mRecyclerView = findViewById(R.id.prodRecyclerView);
        prod_adapt = new ProductAdapter(productClickCallback);
        prod_list_ViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        getProduct();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProduct();
            }
        });
    }

    public void show(Product product) {
        Intent intent = new Intent(getBaseContext(), ProductDetails.class);
        intent.putExtra("PRODUCT_IMG_ID", product.getImages().getThumbnail());
        startActivity(intent);
    }
}
