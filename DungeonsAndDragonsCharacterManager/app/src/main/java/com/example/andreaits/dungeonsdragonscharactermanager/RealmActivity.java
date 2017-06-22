package com.example.andreaits.dungeonsdragonscharactermanager;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmActivity extends AppCompatActivity {
    private static final String TAG = "RealmActivity";

    protected Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(getApplicationContext());

        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .name("db.realm")
                .build());

        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
