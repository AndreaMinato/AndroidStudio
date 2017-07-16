package com.andrea.calcolomedico;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.andrea.calcolomedico.fragments.AtFragment;
import com.andrea.calcolomedico.fragments.CppFragment;
import com.andrea.calcolomedico.utils.Constants;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private static final String CPPFRAGMENT = "CPPFRAGMENT";
    private static final String ATFRAGMENT = "ATFRAGMENT";
    private static final String TOOL = "TOOL";
    private Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Drawer mDrawer;
    AccountHeader mHeader;

    CppFragment mCppFragment;
    AtFragment mAtFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        unbinder = ButterKnife.bind(this);

        if(savedInstanceState!=null)
            toolbar.setTitle(savedInstanceState.getString(TOOL));

        initDrawer(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TOOL, toolbar.getTitle().toString());
    }

    private void initDrawer(Bundle savedInstanceState) {

            IProfile profile = new ProfileDrawerItem()
                    .withName("Azienda Ospedaliera")
                    .withIcon(R.drawable.account);

            mHeader = new AccountHeaderBuilder()
                    .withActivity(this)
                    .withHeaderBackground(R.drawable.drawer_background)
                    .addProfiles(profile)
                    .withAlternativeProfileHeaderSwitching(true)
                    .build();
            mDrawer = new DrawerBuilder()
                    .withActivity(this)
                    .withToolbar(toolbar)
                    .withAccountHeader(mHeader, true)
                    .withActionBarDrawerToggle(true)
                    .addDrawerItems(
                            new PrimaryDrawerItem()
                                    .withName(R.string.CPP)
                                    .withIcon(GoogleMaterial.Icon.gmd_view_list)
                                    .withIdentifier(Constants.CCP),
                            new PrimaryDrawerItem()
                                    .withName(R.string.AT)
                                    .withIcon(GoogleMaterial.Icon.gmd_folder)
                                    .withIdentifier(Constants.AT)
                    ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            if (drawerItem != null) {
                                onTouchItem((int) drawerItem.getIdentifier());
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
                    .withFireOnInitialOnClick(true)
                    .withSavedInstance(savedInstanceState)
                    .build();



    }

    private void onTouchItem(int identifier) {
        switch (identifier) {
            case Constants.CCP: {
                onCPPItemClicked();
            }
            break;
            case Constants.AT: {
                onATTItemClicked();
            }
            break;
            default: {
            }
        }
    }

    private void onATTItemClicked() {
        toolbar.setTitle(R.string.anti_trombina_3);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mAtFragment = (AtFragment) fragmentManager.findFragmentByTag(ATFRAGMENT);
        if (mAtFragment == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            mAtFragment = new AtFragment();
            fragmentTransaction.replace(R.id.container, mAtFragment, ATFRAGMENT);
            fragmentTransaction.commit();
        }
    }

    private void onCPPItemClicked() {
        toolbar.setTitle(R.string.concentrati_di_complesso_protrombinico);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mCppFragment = (CppFragment) fragmentManager.findFragmentByTag(CPPFRAGMENT);
        if (mCppFragment == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            mCppFragment = new CppFragment();
            fragmentTransaction.replace(R.id.container, mCppFragment, CPPFRAGMENT);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

