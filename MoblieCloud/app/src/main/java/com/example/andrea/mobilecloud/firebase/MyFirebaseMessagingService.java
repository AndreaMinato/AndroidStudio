package com.example.andrea.mobilecloud.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.util.Log;

import com.example.andrea.mobilecloud.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by andreaits on 12/01/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessagingServ";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(remoteMessage.getNotification().getTag())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(NOTIFICATION_SERVICE);


        Log.d(TAG, "onMessageReceived: " + remoteMessage.getNotification());

        int id = remoteMessage.hashCode();
        notificationManager.notify(id, notification);

    }
}
