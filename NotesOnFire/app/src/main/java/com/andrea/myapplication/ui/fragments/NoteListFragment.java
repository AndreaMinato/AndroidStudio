package com.andrea.myapplication.ui.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andrea.myapplication.R;
import com.andrea.myapplication.data.model.Note;
import com.andrea.myapplication.ui.adapters.NoteListAdapter;
import com.andrea.myapplication.ui.holders.NoteViewHolder;
import com.andrea.myapplication.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class NoteListFragment extends Fragment {

    public static final String USERID = "USERID";
    NoteListAdapter mAdapter;
    String userId = "";

    DatabaseReference mDatabaseReference;


    public interface INoteList {
        void newNote();
    }

    private INoteList mINoteList = new INoteList() {
        @Override
        public void newNote() {

        }
    };

    public NoteListFragment() {
    }

    public static NoteListFragment getInstance() {
        return new NoteListFragment();
    }


    public static NoteListFragment getInstance(String aUUID) {
        NoteListFragment vFragment = new NoteListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERID, aUUID);
        vFragment.setArguments(bundle);
        return vFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.note_list, container, false);

        RecyclerView noteListView = (RecyclerView) vView.findViewById(R.id.notesRecyclerView);
        noteListView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle vBundle = getArguments();
        if (vBundle != null)
            userId = vBundle.getString(USERID);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(userId).child(Constants.NOTES_TREE);


        mAdapter = new NoteListAdapter(Note.class, R.layout.note_cell, NoteViewHolder.class, mDatabaseReference, getContext());

        noteListView.setAdapter(mAdapter);

        FloatingActionButton vButton = (FloatingActionButton) vView.findViewById(R.id.btnAddNote);
        vButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mINoteList.newNote();
            }
        });

        return vView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof INoteList) {
            mINoteList = (INoteList) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mINoteList = null;

        mAdapter.cleanup();
    }



}
