package com.example.adsa;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ADSAApplication extends Application {

    HashMap<String, String> userValues;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public HashMap<String, String> getData() {
        return userValues;
    }

    public void setData(HashMap<String, String> userValues) {
        this.userValues = userValues;
    }

}
