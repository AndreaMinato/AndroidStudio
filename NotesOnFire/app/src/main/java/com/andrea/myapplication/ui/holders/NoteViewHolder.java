package com.andrea.myapplication.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andrea.myapplication.R;

/**
 * Created by Andrea on 04/07/17.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {
    public View mView;
    private TextView mTxtTitle;
    private TextView mTxtContent;

    public NoteViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mTxtTitle = (TextView) itemView.findViewById(R.id.titleTextView);
        mTxtContent = (TextView) itemView.findViewById(R.id.contentTextView);
    }


    public void setTxtTitle(String aTxtTitle) {
        mTxtTitle.setText(aTxtTitle);
    }

    public void setTxtContent(String aContent) {
        mTxtContent.setText(aContent);
    }
}