package com.andrea.firebaseintegration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.andrea.firebaseintegration.model.User;
import com.andrea.firebaseintegration.utils.Constants;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST_CODE = 100;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    User mCurrentUser;

    Toolbar toolbar;

    Drawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            login();
        } else {
            mCurrentUser = new User(mFirebaseUser.getDisplayName(), mFirebaseUser.getPhotoUrl().toString(), mFirebaseUser.getEmail());
        }
        initNavigationDrawer(savedInstanceState);
    }


    private void initNavigationDrawer(Bundle savedInstanceState) {
        if (mCurrentUser == null) {
            mCurrentUser = new User("Anonymouse", "", "test@test.it");
        }
        IProfile vProfile = new ProfileDrawerItem()
                .withName(mCurrentUser.getUserName())
                .withIcon(mCurrentUser.getPhotoUrl())
                .withEmail(mCurrentUser.getEmailAddress())
                .withIdentifier(Constants.PROFILE);
        AccountHeader vHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.header)
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
                    login();
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
                        } else {
                            Toast.makeText(MainActivity.this, R.string.logout_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
    }

    protected void showSnackBar(int resourceId) {
        Snackbar.make(findViewById(R.id.toolbar), resourceId, Snackbar.LENGTH_LONG).show();
    }
}
