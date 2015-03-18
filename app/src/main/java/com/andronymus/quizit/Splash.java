package com.andronymus.quizit;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import static android.view.Window.FEATURE_NO_TITLE;


public class Splash extends ActionBarActivity {
    //duration of the splash screen
    private static final long SPLASH_SCREEN = 5000;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSplash);

        //requestWindowFeature(FEATURE_NO_TITLE);

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN);

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Intent mainIntent = new Intent().setClass(Splash.this, MainActivity.class);

            startActivity(mainIntent);
            finish();

        }
    };

}
