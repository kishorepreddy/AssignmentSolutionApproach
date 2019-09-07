package com.ecomm.test.mvvmecommapp.view.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class ParentViewHolder extends RecyclerView.ViewHolder
{
    private int mCurrentPosition;

    public ParentViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }
    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
