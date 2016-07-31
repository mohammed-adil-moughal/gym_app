package com.vorane.gymassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hp on 3/16/2016.
 */
public class SplashScreen extends AppCompatActivity {
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!new SessionManager(getBaseContext()).isLoggedIn()){
            new SQLiteHandler(getBaseContext()).createDB();
            new SessionManager(getBaseContext()).setLogin(true);
        }
        Intent intent = new Intent(getBaseContext(),
                Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
