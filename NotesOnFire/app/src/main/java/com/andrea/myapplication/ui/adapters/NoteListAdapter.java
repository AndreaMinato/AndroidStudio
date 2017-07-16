package com.andrea.myapplication.ui.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;

import com.andrea.myapplication.data.model.Note;
import com.andrea.myapplication.ui.holders.NoteViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by Andrea on 04/07/17.
 */

public class NoteListAdapter extends FirebaseRecyclerAdapter<Note, NoteViewHolder> {

    Context mContext;

    public interface INoteAdapter {
        void onNoteClick(String noteId);

        void onNoteLongClick(String noteId);
    }

    INoteAdapter mINoteAdapter = new INoteAdapter() {
        @Override
        public void onNoteClick(String noteId) {

        }

        @Override
        public void onNoteLongClick(String noteId) {

        }
    };

    public NoteListAdapter(Class<Note> modelClass, @LayoutRes int modelLayout, Class<NoteViewHolder> viewHolderClass, Query query, Context aContext) {
        super(modelClass, modelLayout, viewHolderClass, query);

        mContext = aContext;
        if (mContext instanceof INoteAdapter) {
            mINoteAdapter = (INoteAdapter) mContext;
        }
    }

    @Override
    protected void populateViewHolder(NoteViewHolder viewHolder, final Note aNote, int position) {
        viewHolder.setTxtTitle(aNote.getTitle());
        viewHolder.setTxtContent(aNote.getContent());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mINoteAdapter.onNoteClick(aNote.getNoteId());
            }
        });

        viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mINoteAdapter.onNoteLongClick(aNote.getNoteId());
                return true;
            }
        });
    }
}
