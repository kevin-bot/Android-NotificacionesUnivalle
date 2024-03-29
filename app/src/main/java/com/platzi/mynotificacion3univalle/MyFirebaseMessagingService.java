package com.platzi.mynotificacion3univalle;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private NotificationManager notificationManager;
    private static final String ADMIN_CHANNEL_ID ="admin_channel";

    @Override
    public void onNewToken(String s) {

        /*En este metodo recibimos el token del dispositivo.
        * LO necesitamos si vamos a comunicarnos con el dispositivo directamente*/

        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);

        /*A partir de qui hacer lo que reramos con el token como enviarlo al sevidor para gurdarlo en una B.DD
            Nosotros no haremos nada con el token porque no nos vamos a comunicar con un solo dispositivo
        */
    }

/*
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent notificacionIntent=new Intent(this,MainActivity.class);

        notificacionIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        final PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificacionIntent,PendingIntent.FLAG_ONE_SHOT);

       notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

       //configuramos la notificacion para android oreo o superior

        int notificationId= new Random().nextInt(6000);

        //creamos la notificacion en si

        Uri defaultSuonUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder= new NotificationCompat.Builder(this,ADMIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_message_black_24dp)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("message"))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSound(defaultSuonUri);
        NotificationManager notificationManager=(NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId,notificationBuilder.build());

        //Log.e("FIREBASE", remoteMessage.getNotification().getBody());
    }


   /* @RequiresApi(api = Build.VERSION_CODES.ECLAIR_0_1)
    private void setupChannels(){
        CharSequence admindChanelName = getString(R.string.common_google_play_services_notification_channel_name);
        String adminChannelDescription = getString(R.string.fcm_fallback_notification_channel_label);

        NotificationChannel admindChannel;
        admindChannel= new NotificationChannel(ADMINCHANNEL_ID,admindChanelName,NotificationManager.IMPORTANCE_LOW);
        admindChannel.setDescription(adminChannelDescription);
        admindChannel.enableLights(true);


        //adminChannel=new NotificationChannel(ADMINCHANNEL_ID,admindChanelName,NotificationManager.IMPORTANCE_LOW);
    }*/

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // En este método recibimos el mensaje
        Intent notificationIntent;
       /* if(MainActivity.){
            // Qué hacemos si la aplicación está en primer plano
            notificationIntent = new Intent(this, MainActivity.class);
        }else{ */
            // Qué hacemos si la aplicación está en background
            notificationIntent = new Intent(this,MainActivity.class);
       // }
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_ONE_SHOT);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Configuramos la notificación para Android Oreo o superior
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupChannels();
        }
        int notificationId = new Random().nextInt(60000);
        // Creamos la notificación en si
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_message_black_24dp)  //a resource for your custom small icon
                .setContentTitle(remoteMessage.getData().get("title")) //the "title" value you sent in your notification
                .setContentText(remoteMessage.getData().get("message")) //ditto
                .setAutoCancel(true)  //dismisses the notification on click
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels(){
        //CharSequence adminChannelName = getString(R.string.notifications_admin_channel_name);
        CharSequence adminChannelName = getString(R.string.common_google_play_services_notification_channel_name);
        //String adminChannelDescription = getString(R.string.notifications_admin_channel_description);
        String adminChannelDescription = getString(R.string.common_google_play_services_notification_channel_name);
        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_LOW);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }
    }

}
