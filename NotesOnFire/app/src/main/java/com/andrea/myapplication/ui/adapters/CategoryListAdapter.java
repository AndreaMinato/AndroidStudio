package com.andrea.myapplication.ui.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.andrea.myapplication.data.model.Category;
import com.andrea.myapplication.data.model.Note;
import com.andrea.myapplication.ui.holders.CategoryViewHolder;
import com.andrea.myapplication.ui.holders.NoteViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by Andrea on 04/07/17.
 */

public class CategoryListAdapter extends FirebaseRecyclerAdapter<Category, CategoryViewHolder> {

    Context mContext;

    public interface ICategoryAdapter {
        void onCategoryClick(String categoryId);

        void onCategoryLongClick(String categoryID);
    }

    ICategoryAdapter mICategoryAdapter = new ICategoryAdapter() {
        @Override
        public void onCategoryClick(String categoryId) {

        }

        @Override
        public void onCategoryLongClick(String categoryID) {

        }
    };

    public CategoryListAdapter(Class<Category> modelClass, @LayoutRes int modelLayout, Class<CategoryViewHolder> viewHolderClass, Query query, Context aContext) {
        super(modelClass, modelLayout, viewHolderClass, query);

        mContext = aContext;
        if (mContext instanceof ICategoryAdapter) {
            mICategoryAdapter = (ICategoryAdapter) mContext;
        }
    }

    @Override
    protected void populateViewHolder(CategoryViewHolder viewHolder, final Category aCategory, int position) {
        viewHolder.setCategory(aCategory.getCategoryName());


        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mICategoryAdapter.onCategoryClick(aCategory.getCategoryId());
            }
        });

        viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mICategoryAdapter.onCategoryLongClick(aCategory.getCategoryId());
                return true;
            }
        });
    }
}
