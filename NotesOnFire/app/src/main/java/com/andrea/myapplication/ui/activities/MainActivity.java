package com.andrea.myapplication.ui.activities;


import android.content.Intent;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.andrea.myapplication.R;
import com.andrea.myapplication.data.model.User;
import com.andrea.myapplication.ui.adapters.CategoryListAdapter;
import com.andrea.myapplication.ui.adapters.NoteListAdapter;
import com.andrea.myapplication.ui.dialogs.CategoryDialog;
import com.andrea.myapplication.ui.fragments.NoteDetailFragment;
import com.andrea.myapplication.ui.fragments.NoteListFragment;
import com.andrea.myapplication.utils.Constants;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements NoteListFragment.INoteList, NoteListAdapter.INoteAdapter, NoteDetailFragment.INoteDetail, CategoryListAdapter.ICategoryAdapter {

    private static final String NOTES = "notes";
    private static final String DETAIL = "detail";
    private static final int REQUEST_IMAGE_CAPTURE = 500;
    public static final int SKETCH_ACTIVITY = 600;
    private boolean mHasDetail = false;

    Uri imageUri = null;
    Uri photoUri = null;
    private static final String TAG = "MainActivity";

    private static final int SIGN_IN_REQUEST_CODE = 100;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    User mCurrentUser;


    private MediaRecorder mediaRecorder;
    private Timer recTimer;
    private boolean isRecording;
    private String audioOutputPath = " ";
    private Snackbar timeProgressSnackbar;

    String mImagePath;

    Toolbar toolbar;

    Drawer mDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mHasDetail = getResources().getBoolean(R.bool.isMasterDetail);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            login();
        } else {
            mCurrentUser = new User(mFirebaseUser);
            if (getSupportFragmentManager().findFragmentByTag(NOTES) == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listFragmentContainer, NoteListFragment.getInstance(mCurrentUser.getUserId()), NOTES)
                        .commit();
            }
            if (mHasDetail) {
                getSupportFragmentManager().beginTransaction().replace(R.id.detailFragmentContainer, NoteDetailFragment.getInstance(mCurrentUser.getUserId()), DETAIL).commit();
            }
            initNavigationDrawer(savedInstanceState);
        }


    }

    @Override
    public void newNote() {
        if (mHasDetail) {
            getSupportFragmentManager().beginTransaction().replace(R.id.detailFragmentContainer, NoteDetailFragment.getInstance(mCurrentUser.getUserId()), DETAIL).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.listFragmentContainer, NoteDetailFragment.getInstance(mCurrentUser.getUserId()), DETAIL).addToBackStack(DETAIL).commit();
        }
    }

    @Override
    public void onNoteClick(String noteId) {
        if (mHasDetail) {
            getSupportFragmentManager().beginTransaction().replace(R.id.detailFragmentContainer, NoteDetailFragment.getInstance(mCurrentUser.getUserId(), noteId), DETAIL).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.listFragmentContainer, NoteDetailFragment.getInstance(mCurrentUser.getUserId(), noteId), DETAIL).addToBackStack(DETAIL).commit();
        }
    }

    @Override
    public void onNoteLongClick(String noteId) {
        DatabaseReference vReference = FirebaseDatabase.getInstance().getReference().child(mCurrentUser.getUserId()).child(Constants.NOTES_TREE).child(noteId);

        vReference.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError aDatabaseError, DatabaseReference aDatabaseReference) {
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onCategoryClick(String categoryId) {
        NoteDetailFragment vFragment = (NoteDetailFragment) getSupportFragmentManager().findFragmentByTag(DETAIL);
        if (vFragment != null)
            vFragment.setCategory(categoryId);
    }

    @Override
    public void onCategoryLongClick(String categoryID) {
        DatabaseReference vReference = FirebaseDatabase.getInstance().getReference().child(mCurrentUser.getUserId()).child(Constants.CATEGORIES_TREE).child(categoryID);

        vReference.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError aDatabaseError, DatabaseReference aDatabaseReference) {
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //region Drawer
    private void initNavigationDrawer(Bundle savedInstanceState) {
//        if (mCurrentUser == null) {
//            mCurrentUser = User.anonymous();
//        }
        IProfile vProfile = new ProfileDrawerItem()
                .withName(mCurrentUser.getUserName())
                .withIcon(mCurrentUser.getPhotoUrl())
                .withEmail(mCurrentUser.getEmailAddress())
                .withIdentifier(Constants.PROFILE);
        AccountHeader vHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.drawer_background)
                .addProfiles(vProfile)
                .build();

        mDrawer = new DrawerBuilder()
                .withAccountHeader(vHeader)
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName("Notes")
                                .withIcon(GoogleMaterial.Icon.gmd_view_list)
                                .withIdentifier(Constants.NOTES)
                                .withBadge("50+"),

                        new PrimaryDrawerItem()
                                .withName("Categories")
                                .withIcon(GoogleMaterial.Icon.gmd_folder)
                                .withIdentifier(Constants.CATEGORIES)
                                .withBadge("5"),
                        new PrimaryDrawerItem()
                                .withName("Settings")
                                .withIcon(GoogleMaterial.Icon.gmd_settings)
                                .withIdentifier(Constants.SETTINGS),
                        new PrimaryDrawerItem()
                                .withName("Logout")
                                .withIcon(GoogleMaterial.Icon.gmd_exit_to_app)
                                .withIdentifier(Constants.LOGOUT)
                )
                .addStickyDrawerItems(
                        new PrimaryDrawerItem()
                                .withName("Delete")
                                .withIcon(GoogleMaterial.Icon.gmd_delete)
                                .withIdentifier(Constants.DELETE)
                                .withIconColor(Color.RED)
                                .withTextColor(Color.RED)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem instanceof Nameable) {
                            String name = ((Nameable) drawerItem).getName().getText(MainActivity.this);
                            toolbar.setTitle(name);
                        }
                        if (drawerItem != null) {
                            onTouchDrawer((int) drawerItem.getIdentifier());
                        }
                        return false;
                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();


    }

    private void onTouchDrawer(int aIdentifier) {
        switch (aIdentifier) {
            case Constants.NOTES:
                break;
            case Constants.CATEGORIES:
                break;
            case Constants.SETTINGS:
                break;
            case Constants.LOGOUT:
                logout();
                break;
            case Constants.DELETE:
                deleteAccount();
                break;
        }
    }
    //endregion

    //region Account
    private void login() {
        startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                        .build(),
                SIGN_IN_REQUEST_CODE);
    }

    private void deleteAccount() {
        AuthUI.getInstance().delete(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> aTask) {
                if (aTask.isSuccessful()) {
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, R.string.deletion_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void logout() {
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> aTask) {
                        if (aTask.isSuccessful()) {
                            login();
                            //finish();
                        } else {
                            Toast.makeText(MainActivity.this, R.string.logout_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //endregion

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            IdpResponse vResponse = IdpResponse.fromResultIntent(data);


            if (resultCode == ResultCodes.OK) {
                return;
            } else {
                if (vResponse == null) {
                    showSnackBar(R.string.sign_in_cancelled);
                    return;
                }

                if (vResponse.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackBar(R.string.error_no_network);
                    return;
                }

                if (vResponse.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackBar(R.string.general_error);
                    return;
                }
            }
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            if (data != null) {
                imageUri = data.getData();
            }
            if (imageUri == null && photoUri != null)
                imageUri = photoUri;


            NoteDetailFragment vFragment = (NoteDetailFragment) getSupportFragmentManager().findFragmentByTag(DETAIL);
            if (vFragment != null)
                vFragment.uploadFileToCLoud(mImagePath, Constants.TYPE_IMAGE);
        }

        if (requestCode == SKETCH_ACTIVITY && resultCode == RESULT_OK) {


            Uri vUri = data.getData();
            if (vUri != null) {
                NoteDetailFragment vFragment = (NoteDetailFragment) getSupportFragmentManager().findFragmentByTag(DETAIL);
                if (vFragment != null)
                    vFragment.uploadFileToCLoud(vUri.getPath(), Constants.TYPE_SKETCH);

            }
        }

    }


    protected void showSnackBar(int resourceId) {
        Snackbar.make(findViewById(R.id.toolbar), resourceId, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showCateory() {
//        CategoryDialog vDialog = CategoryDialog.getInstance();
//        vDialog.show(getSupportFragmentManager(), "CATDIAL");

        FragmentManager fm = getSupportFragmentManager();
        CategoryDialog vDialog = CategoryDialog.getInstance(mCurrentUser.getUserId());
        vDialog.show(fm, "CATDIAL");

    }

    @Override
    public void noteSaved() {
        if (!mHasDetail) {
            getSupportFragmentManager().beginTransaction().replace(R.id.listFragmentContainer, NoteListFragment.getInstance(mCurrentUser.getUserId()), NOTES).commit();
        }
    }


    @Override
    public void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException aE) {
                aE.printStackTrace();
            }

            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this,
                        "com.andrea.myapplication.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name

        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        mImagePath = image.getAbsolutePath();
        return image;
    }


    @Override
    public void takeSketch() {
        Intent vIntent = new Intent(this, SketchActivity.class);
        startActivityForResult(vIntent, SKETCH_ACTIVITY);
    }

    @Override
    public void takeAudio() {
        if (isRecording) {
            stopRecording();
        } else {
            startRecording();
        }
    }

    @Override
    public void onBackPressed() {
        if (isRecording) {
            Toast.makeText(this, "Concludi la registrazione prima di uscire dalla nota", Toast.LENGTH_SHORT).show();

        } else {
            super.onBackPressed();
        }
    }

    private void startRecording() {

        mediaRecorder = setupRecorder();
        if (mediaRecorder != null) {
            try {
                mediaRecorder.prepare();
            } catch (IOException e) {
                Log.d("AUDIO", "prepare() failed");
                e.printStackTrace();
            }
            mediaRecorder.start();
            isRecording = true;

            timeProgressSnackbar = Snackbar.make(findViewById(R.id.toolbar), "", Snackbar.LENGTH_INDEFINITE);
            timeProgressSnackbar.show();
            recTimer = new Timer();
            recTimer.schedule(createTimerTask(), 1000, 1000);
        }
    }

    private void stopRecording() {
        isRecording = false;
        mediaRecorder.stop();
        mediaRecorder.reset();
        mediaRecorder.release();
        mediaRecorder = null;
        recTimer.cancel();

        if (timeProgressSnackbar != null) {
            timeProgressSnackbar.dismiss();
        }
        NoteDetailFragment vFragment = (NoteDetailFragment) getSupportFragmentManager().findFragmentByTag(DETAIL);
        if (vFragment != null)
            vFragment.uploadFileToCLoud(audioOutputPath, Constants.TYPE_AUDIO);
    }

    private MediaRecorder setupRecorder() {
        MediaRecorder recorder = new MediaRecorder();
        isRecording = false;
        audioOutputPath = getExternalFilesDir("Audios") + "/" + (new Date()).getTime() + ".3gp";
        Log.d("Setup recorder", "Path: " + audioOutputPath);

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setOutputFile(audioOutputPath);
        return recorder;
    }


    private TimerTask createTimerTask() {
        final Handler handler = new Handler();
        return new TimerTask() {
            private Date data = new Date(0);
            private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

            @Override
            public void run() {
                data.setTime(data.getTime() + 1000);
                handler.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     timeProgressSnackbar.setText("recording - " + sdf.format(data));
                                 }
                             }

                );
            }
        };
    }

    @Override
    public void uploaded(boolean success) {
        if (success)
            Toast.makeText(this, "Upload effettuato con successo", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Upload Fallito", Toast.LENGTH_SHORT).show();
    }
}
