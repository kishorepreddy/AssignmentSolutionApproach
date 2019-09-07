package com.ecomm.test.mvvmecommapp.view.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecomm.test.mvvmecommapp.R;
import com.ecomm.test.mvvmecommapp.service.model.Product;
import com.ecomm.test.mvvmecommapp.view.callback.ProductClickCallback;

import java.util.List;
import java.util.Objects;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProductAdapter extends RecyclerView.Adapter<ParentViewHolder> {

    List<Product> productList;

    @Nullable
    private final ProductClickCallback productClickCallback;

    public ProductAdapter(@Nullable ProductClickCallback productClickCallback) {
        this.productClickCallback = productClickCallback;
    }

    public void setProductList(final List<Product> productList) {
        if (this.productList == null) {
            this.productList = productList;
            notifyItemRangeInserted(0, productList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProductAdapter.this.productList.size();
                }

                @Override
                public int getNewListSize() {
                    return productList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProductAdapter.this.productList.get(oldItemPosition).images ==
                            productList.get(newItemPosition).images;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Product product = productList.get(newItemPosition);
                    Product old = productList.get(oldItemPosition);
                    return Objects.equals(product.images, old.images);
                }
            });
            this.productList = productList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ParentViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public class ProductViewHolder extends ParentViewHolder
    {
        ImageView ivThumbnail;
        TextView prodname;
        TextView division;
        TextView department;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            prodname = itemView.findViewById(R.id.prodname);
            division = itemView.findViewById(R.id.division);
            department = itemView.findViewById(R.id.department);
        }

        protected void clear() {
            ivThumbnail.setImageDrawable(null);
            prodname.setText("");
            division.setText("");
            department.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final Product mProd = productList.get(position);

            if (mProd.getImages() != null) {
                Glide.with(itemView.getContext())
                        .load(mProd.getImages().getThumbnail())
                        .into(ivThumbnail);
            }

            if (mProd.getName() != null) {
                prodname.setText(mProd.getName());
            }

            if (mProd.getDivision() != null) {
                division.setText(mProd.getDivision());
            }

            if (mProd.getDepartment()!= null) {
                department.setText(mProd.getDepartment());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mProd != null) {
                        try {
                            productClickCallback.onClick(mProd);
                        } catch (Exception e) {
                            Log.e(TAG, "onClick: Listener Not configured");
                            Log.e(TAG, e.getMessage());
                        }
                    }
                }
            });
        }
    }
}
