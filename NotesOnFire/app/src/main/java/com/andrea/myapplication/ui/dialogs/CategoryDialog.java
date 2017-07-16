package com.andrea.myapplication.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.andrea.myapplication.R;
import com.andrea.myapplication.data.model.Category;
import com.andrea.myapplication.data.model.Note;
import com.andrea.myapplication.ui.adapters.CategoryListAdapter;
import com.andrea.myapplication.ui.adapters.NoteListAdapter;
import com.andrea.myapplication.ui.fragments.NoteListFragment;
import com.andrea.myapplication.ui.holders.CategoryViewHolder;
import com.andrea.myapplication.ui.holders.NoteViewHolder;
import com.andrea.myapplication.utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import static com.andrea.myapplication.ui.fragments.NoteListFragment.USERID;

/**
 * Created by Andrea on 06/07/17.
 */

public class CategoryDialog extends DialogFragment {

    private static final String TITLE = "Category";
    private static final String TEXT = "Choose Your Category";
    private static final String DIALOG = "dialog1";
    private String userId;
    private DatabaseReference mDatabaseReference;
    private CategoryListAdapter mAdapter;

    public interface IOnCategorySelected {
        void onCategorySelected(String str);
    }

    IOnCategorySelected listener = new IOnCategorySelected() {
        @Override
        public void onCategorySelected(String categoryId) {

        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.category_list_dialog, container);

        RecyclerView categoryListView = (RecyclerView) vView.findViewById(R.id.categoriesRecyclerView);
        categoryListView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle vBundle = getArguments();
        if (vBundle != null)
            userId = vBundle.getString(USERID);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(userId).child(Constants.CATEGORIES_TREE);


        mAdapter = new CategoryListAdapter(Category.class, R.layout.category_cell, CategoryViewHolder.class, mDatabaseReference, getContext());

        categoryListView.setAdapter(mAdapter);

        final EditText vText = (EditText) vView.findViewById(R.id.txtAddCategory);

        Button vButton = (Button) vView.findViewById(R.id.btnAddCategory);
        vButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(vText.getText().toString())) {
                    Category vCategory = new Category(vText.getText().toString());
                    DatabaseReference vReference = mDatabaseReference.child(vCategory.getCategoryId());
                    vReference.setValue(vCategory);
                }
            }
        });

        return vView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnCategorySelected) {
            listener = (IOnCategorySelected) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;

        mAdapter.cleanup();
    }


    public static CategoryDialog getInstance(String aUserId) {
        CategoryDialog vFragment = new CategoryDialog();
        Bundle bundle = new Bundle();
        bundle.putString(USERID, aUserId);
        vFragment.setArguments(bundle);
        return vFragment;
    }

    public static CategoryDialog getInstance(String[] text) {

        CategoryDialog dialog = new CategoryDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArray(TEXT, text);
        dialog.setArguments(bundle);
        return dialog;
    }

}
