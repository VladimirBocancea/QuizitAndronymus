package com.andronymus.quizit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // TODO Externalizar en una clase
        /*String fontPath="fonts/Roboto-Light.ttf";
        String fontPath1="fonts/days.ttf";
        EditText et= (EditText) findViewById(R.id.userNameEditText);
        EditText et1 = (EditText) findViewById(R.id.passWordEditText);
        TextView txt = (TextView) findViewById(R.id.loginButton);
        TextView txt1 = (TextView) findViewById(R.id.signUpButton);
        Typeface tf= Typeface.createFromAsset(getAssets(), fontPath);
        Typeface tf1= Typeface.createFromAsset(getAssets(), fontPath1);
        txt.setTypeface(tf);
        txt1.setTypeface(tf);
        et.setTypeface(tf1);
        et1.setTypeface(tf1);*/

    }

    public void ShowSignUpActivity(View view) {
        Intent signup = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(signup);
    }

    public void LoginIntoParse(View view) {
        EditText userName = (EditText) findViewById(R.id.userNameEditText);
        EditText passWord = (EditText) findViewById(R.id.passWordEditText);

        ParseUser.logInInBackground(String.valueOf(userName.getText()), String.valueOf(passWord.getText()), new
                LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Intent main = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(main);
                            Toast toast = Toast.makeText(getApplicationContext(), "Login OK", Toast.LENGTH_SHORT);
                            toast.show();
                            finish();

                        } else {
                            // Signup failed.
                            Toast toast = Toast.makeText(getApplicationContext(), "Login Failded", Toast.LENGTH_SHORT);
                            toast.show();

                        }
                    }
                });
    }




}