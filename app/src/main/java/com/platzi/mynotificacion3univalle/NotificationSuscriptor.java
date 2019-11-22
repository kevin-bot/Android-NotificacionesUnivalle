package com.platzi.mynotificacion3univalle;

import android.app.Application;

import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationSuscriptor extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic("noticias_meteo_ferrolterra");
    }

}
