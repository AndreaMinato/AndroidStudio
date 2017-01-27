package com.example.andreaits.services;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by andreaits on 13/01/17.
 */

public class RandomService extends Service {

    MyBinder myBinder = new MyBinder();
    String[] sentences = {
            "L'hai fattoo!",
            "Non ci credo",
            "Ma allora sei stronzo",
            "Smettila",
            "Perchè stai premendo quell'affare?",
            "Non premere quel coso!",
            "Non chiamare il servizio ti prego",
            "Basta ti scongiuro"
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }


    public String getRandomText() {
        Random random = new Random();

        return sentences[random.nextInt(sentences.length)];
    }

    public class MyBinder extends Binder {
        public RandomService getService() {
            return RandomService.this;
        }
    }
}
