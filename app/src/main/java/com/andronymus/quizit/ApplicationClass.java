package com.andronymus.quizit;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by VLad on 30/01/2015.
 */
public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "mrzEikqRTZHkyaM88MorsXuTC40Pf3okScsfzvO7", "Fyf5weBShaUwsG6XTfvYduu09Bh1tVs4x51gCU8P");
    }
}
