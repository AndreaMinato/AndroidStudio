package com.andrea.myapplication.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andrea.myapplication.R;
import com.andrea.myapplication.data.model.Category;
import com.andrea.myapplication.data.model.Note;
import com.andrea.myapplication.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class NoteDetailFragment extends Fragment {

    public static final String USERID = "USERID";
    public static final String CATEGORYID = "CATEGORYID";
    private static final int TAKE_PHOTO_CODE = 500;
    EditText txtTitle;
    EditText txtContent;
    TextView txtCategory;

    boolean isRecording = false;


    private Uri outputFileUri;

    Category mCategory;

    DatabaseReference mDatabaseReference;
    Note vNote;

    String noteId;
    String userId;

    FirebaseStorage mStorage;
    StorageReference mBuketReference;
    StorageReference mStorageReference;


    public static final String NOTEID = "NOTEID";

    public interface INoteDetail {

        void noteSaved();

        void showCateory();

        void takePhoto();

        void takeSketch();

        void takeAudio();

        void uploaded(boolean success);
    }

    public void setCategory(String aCategoryId) {
        DatabaseReference vReference = FirebaseDatabase.getInstance().getReference().child(userId).child(Constants.CATEGORIES_TREE).child(aCategoryId);

        vReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot aDataSnapshot) {
                mCategory = aDataSnapshot.getValue(Category.class);
                if (mCategory != null)
                    txtCategory.setText(mCategory.getCategoryName());
            }

            @Override
            public void onCancelled(DatabaseError aDatabaseError) {

            }
        });
    }

    private INoteDetail mINoteDetail = new INoteDetail() {
        @Override
        public void noteSaved() {

        }

        @Override
        public void uploaded(boolean success) {

        }

        @Override
        public void showCateory() {

        }

        @Override
        public void takePhoto() {

        }

        @Override
        public void takeSketch() {

        }

        @Override
        public void takeAudio() {

        }
    };

    public NoteDetailFragment() {

    }


    public static NoteDetailFragment getInstance() {
        return new NoteDetailFragment();
    }


    public static NoteDetailFragment getInstance(String userId, String noteid) {
        NoteDetailFragment vFragment = new NoteDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NOTEID, noteid);
        bundle.putString(USERID, userId);
        vFragment.setArguments(bundle);
        return vFragment;

    }

    public static NoteDetailFragment getInstance(String userId) {
        NoteDetailFragment vFragment = new NoteDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NOTEID, UUID.randomUUID().toString());
        bundle.putString(USERID, userId);
        vFragment.setArguments(bundle);
        return vFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.note_edit, container, false);

        Bundle vBundle = getArguments();

        txtTitle = (EditText) vView.findViewById(R.id.titleEditText);
        txtContent = (EditText) vView.findViewById(R.id.contentEditText);

        txtCategory = (TextView) vView.findViewById(R.id.categoryTextView);

        txtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mINoteDetail.showCateory();
            }
        });

        if (vBundle != null) {
            noteId = vBundle.getString(NOTEID);
            userId = vBundle.getString(USERID);
        }

        if (mCategory != null)
            txtCategory.setText(mCategory.getCategoryName());

        Button vSave = (Button) vView.findViewById(R.id.btnSaveNote);
        vSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatabaseReference != null) {
                    if (vNote == null)
                        vNote = new Note(noteId, txtTitle.getText().toString(), txtContent.getText().toString());
                    if (mCategory != null)
                        vNote.setCategory(mCategory);
                    mDatabaseReference.setValue(vNote);
                    mDatabaseReference.setValue(vNote, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError aDatabaseError, DatabaseReference aDatabaseReference) {
                            if (aDatabaseError == null)
                                mINoteDetail.noteSaved();
                        }
                    });

                }
            }
        });

        Button vImage = (Button) vView.findViewById(R.id.btnImage);
        vImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mINoteDetail.takePhoto();
            }
        });
        Button vAudio = (Button) vView.findViewById(R.id.btnAudio);
        vAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRecording = true;
                mINoteDetail.takeAudio();
            }
        });
        Button vSketch = (Button) vView.findViewById(R.id.btnSketch);
        vSketch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mINoteDetail.takeSketch();
            }
        });

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(userId).child(Constants.NOTES_TREE).child(noteId);

        mStorage = FirebaseStorage.getInstance();

        mBuketReference = mStorage.getReferenceFromUrl(Constants.STORAGE_FIREBASE);

        mStorageReference = mBuketReference.child("/users/" + userId + "/attachments");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot aDataSnapshot) {
                vNote = aDataSnapshot.getValue(Note.class);
                if (vNote != null) {
                    txtTitle.setText(vNote.getTitle());
                    txtContent.setText(vNote.getContent());
                    if (vNote.getCategory() != null)
                        txtCategory.setText(vNote.getCategory().getCategoryName());
                }
            }

            @Override
            public void onCancelled(DatabaseError aDatabaseError) {
                Toast.makeText(getContext(), "Something gone wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        return vView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof INoteDetail) {
            mINoteDetail = (INoteDetail) getActivity();
        }
    }


    @Override
    public void onDetach() {


        mINoteDetail = null;
        super.onDetach();

    }


    public void uploadFileToCLoud(final String filePath, final String fileType) {
        StorageMetadata vMetadata = new StorageMetadata.Builder()
                .setContentType(fileType)
                .build();


        Uri fileToUpload = Uri.fromFile(new File(filePath));

        final String fileName = fileToUpload.getLastPathSegment();

        StorageReference vReference = mStorageReference.child(fileName);

        UploadTask vTask = vReference.putFile(fileToUpload, vMetadata);
        vTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception aE) {
           //     mINoteDetail.uploaded(false);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot aTaskSnapshot) {
                if (fileType.equals(Constants.TYPE_AUDIO)) {
                    vNote.setCloudAudioExists(true);
                    vNote.setAudioPath(filePath);
                    isRecording = false;
                }
                if (fileType.equals(Constants.TYPE_IMAGE)) {
                    vNote.setCloudImageExists(true);
                    vNote.setImagePath(filePath);

                }
                if (fileType.equals(Constants.TYPE_SKETCH)) {
                    vNote.setCloudSketchExists(true);
                    vNote.setSketchPath(filePath);

                }
//                mINoteDetail.uploaded(true);
            }
        });


    }

}
