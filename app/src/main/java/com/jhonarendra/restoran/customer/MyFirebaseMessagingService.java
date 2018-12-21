package com.jhonarendra.restoran.customer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jhonarendra.restoran.customer.activity.MainActivity;

import java.util.Map;

/**
 * Created by Jhonarendra on 11/29/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
//    private int NOTIFICATION_ID=0;
//    private String chanel_id="my_chanel_id";
//
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        if(remoteMessage.getData().isEmpty()){
//            generateNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
//        }
//        else{
//            Map<String,String> data=remoteMessage.getData();
//            String title = data.get("title");
//            String body = data.get("message");
//            generateNotification(body,title);
//        }
//
//    }
//
//    private void generateNotification(String body,String title) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this,chanel_id)
//                .setContentText(body)
//                .setContentTitle(title)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setPriority(android.app.Notification.PRIORITY_HIGH)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());
//    }
}
