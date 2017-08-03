package com.example.bhati.mysquawker.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;

import com.example.bhati.mysquawker.MainActivity;
import com.example.bhati.mysquawker.R;
import com.example.bhati.mysquawker.provider.MySquawkContract;
import com.example.bhati.mysquawker.provider.MySquawkProvider;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by Bhati on 8/2/2017.
 */

public class MySquawkerFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       Map<String,String> hashMap=remoteMessage.getData();
        showNotification(hashMap);
        insertIntoDatabase(hashMap);
    }

    private void insertIntoDatabase(Map<String, String> hashMap) {
        //Yet to be completed
        getContentResolver().insert(MySquawkProvider.SquawkMessages.CONTENT_URI,new ContentValues());
    }

    private void showNotification(Map<String, String> hashMap) {
        String message=hashMap.get(MySquawkContract.COLUMN_MESSAGE);
        String author=hashMap.get(MySquawkContract.COLUMN_AUTHOR);
        if(message.length()>30){
            message=message.substring(0,30);
        }
        NotificationCompat.Builder notificationCompat=new NotificationCompat.Builder(this)
                .setContentTitle(author)
                .setContentText(message)
                .setAutoCancel(true)
                .setColor(ActivityCompat.getColor(this, R.color.colorAccent))
                .setContentIntent(createPendingIntent())
                .setSmallIcon(R.drawable.ic_duck);
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(54,notificationCompat.build());
    }

    private PendingIntent createPendingIntent() {
        Intent i=new Intent(this, MainActivity.class);
        return PendingIntent.getActivity(this,101,i,PendingIntent.FLAG_ONE_SHOT);
    }
}
