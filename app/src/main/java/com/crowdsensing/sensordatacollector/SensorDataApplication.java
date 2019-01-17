package com.crowdsensing.sensordatacollector;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class SensorDataApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
