package com.andronymus.quizit;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.ImageButton;

/**
 * Created by VLad on 18/03/2015.
 */
public class Sonidos {
    private MediaPlayer mediaPlayer;
    private ImageButton imageButton;

    public void sonidoClick(Context context){
        mediaPlayer = MediaPlayer.create(context, R.raw.qsoundclick);
        mediaPlayer.start();
    }
}
