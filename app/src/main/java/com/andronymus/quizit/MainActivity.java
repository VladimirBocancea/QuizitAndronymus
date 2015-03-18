package com.andronymus.quizit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class MainActivity extends ActionBarActivity {

    DatabaseManager dbm;
    LoadInfo loadInfo;

    Sonidos sonidos;
    // Variables de la notificacion
    NotificationManager nm;
    Notification notif;
    static String ns = Context.NOTIFICATION_SERVICE;

    //Defino los iconos de la notificacion en la barra de notificacion
    int icono_v = R.drawable.qlogo;
    int icono_r = R.drawable.qlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Inicio el servicio de notificaciones accediendo al servicio
        nm = (NotificationManager) getSystemService(ns);
        sonidos = new Sonidos();
        String fontPath2 = "fonts/LeckerliOne-Regular.ttf";

        /*
         * Creation of the Database
         */
        dbm = new DatabaseManager(this);
        loadInfo = new LoadInfo(dbm);


        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
          //  Toast toast = Toast.makeText(getApplicationContext(), "yes logged", Toast.LENGTH_SHORT);
          //  toast.show();
        } else {
            // show the signup or login screen
          //  Toast toast = Toast.makeText(getApplicationContext(), "no logged", Toast.LENGTH_SHORT);
          //  toast.show();
            Intent login = new Intent().setClass(MainActivity.this, LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login);
        }


    }


    public void onClickShare() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Check this new AndronymusQUIZIT!!";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Settings op = new Settings(this, getPreferences(id));
            Intent settings = new Intent().setClass(MainActivity.this, Settings.class);
            startActivity(settings);
            return true;
        }
        if (id == R.id.logout) {
            notificacion(icono_r, "Quizit", "Quizit", "Te has deslogueado");
            nm.notify(1, notif);
            ParseUser.logOut();
            finish();

        }
        if (id == R.id.share) {
            onClickShare();
        }


        return super.onOptionsItemSelected(item);
    }


    public void notificacion(int icon, CharSequence textoEstado, CharSequence titulo, CharSequence texto) {
        // Capturo la hora del evento
        long hora = System.currentTimeMillis();

        // Definimos la accion de la pulsacion sobre la notificacion (esto es opcional)
        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        // Defino la notificacion, icono, texto y hora
        notif = new Notification(icon, textoEstado, hora);
        notif.setLatestEventInfo(context, titulo, texto, contentIntent);

        //Defino que la notificacion sea permamente
        notif.flags = Notification.FLAG_AUTO_CANCEL;
    }


    public void OnClickPlayNormal(View view) {
        sonidos.sonidoClick(this);
        Intent playNormal = new Intent().setClass(MainActivity.this, PlayNormal.class);
        startActivity(playNormal);
    }

    public void onClickAboutUs(View view) {
        sonidos.sonidoClick(this);
        Intent aboutUs = new Intent().setClass(MainActivity.this, AboutUs.class);
        startActivity(aboutUs);

    }

    public void onClickSetting(View view) {
        sonidos.sonidoClick(this);
        Intent settings = new Intent().setClass(MainActivity.this, Settings.class);
        startActivity(settings);

    }

    public void onClickScores(View view) {
        sonidos.sonidoClick(this);
        Intent scores = new Intent().setClass(MainActivity.this, Scores.class);
        startActivity(scores);

    }

    public void onClickHowToPlay(View view) {
        sonidos.sonidoClick(this);
        Intent howToPlay = new Intent().setClass(MainActivity.this, HowToPlay.class);
        startActivity(howToPlay);

    }


}
