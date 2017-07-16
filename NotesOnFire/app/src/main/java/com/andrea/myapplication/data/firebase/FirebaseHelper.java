package com.andrea.myapplication.data.firebase;

import com.andrea.myapplication.data.model.Category;
import com.andrea.myapplication.data.model.Note;
import com.andrea.myapplication.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Andrea on 03/07/17.
 */

public class FirebaseHelper {

    public static FirebaseUser getFirebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public static DatabaseReference getUserDataBaseReference(String userUUID) {
        return FirebaseDatabase.getInstance().getReference(userUUID);
    }

    public static void putNote(DatabaseReference aReference, Note aNote) {

        aReference.child(Constants.NOTES_TREE).child(aNote.getNoteId()).setValue(aNote);

    }

    public static void putCategory(DatabaseReference aReference, Category aCategory) {

        aReference.child(Constants.NOTES_TREE).child(aCategory.getCategoryId()).setValue(aCategory);

    }

}
