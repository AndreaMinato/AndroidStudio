package com.andrea.myapplication.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andrea.myapplication.R;

/**
 * Created by Andrea on 06/07/17.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public View mView;
    private TextView mCategory;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mCategory = (TextView) itemView.findViewById(R.id.categoryTitleTextView);
    }


    public void setCategory(String aCategory) {
        mCategory.setText(aCategory);
    }

}