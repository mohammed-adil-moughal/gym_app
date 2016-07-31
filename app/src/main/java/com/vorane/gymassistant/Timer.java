package com.vorane.gymassistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * Created by ekirapa on 6/23/16.
 */
public class Timer extends AppCompatActivity implements View.OnClickListener, CircularSeekBar.OnCircularSeekBarChangeListener {
    private FloatingActionButton start, stop;
    TextView tv_minute, tv_seconds;
    private int seconds = 00;
    CircularSeekBar circularSeekBar;
    boolean set = true;
    private CountDownTimer countDownTimer;
    public long startTime1;
    public boolean button = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        circularSeekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar);
        circularSeekBar.setProgress(seconds);
        start = (FloatingActionButton) findViewById(R.id.timer_fabplay);
        stop = (FloatingActionButton) findViewById(R.id.timer_fabstop);
        tv_minute = (TextView) findViewById(R.id.tv_minute);
        tv_seconds = (TextView) findViewById(R.id.tv_seconds);
        tv_seconds.setText(Integer.toString(seconds));

        circularSeekBar.setOnSeekBarChangeListener(this);
        stop.setOnClickListener(this);
        start.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.timer_fabplay) {
            start.setVisibility(View.GONE);
            stop.setVisibility(View.VISIBLE);
            startTimer(startTime1 * 60 * 1000);
        } else if (v.getId() == R.id.timer_fabstop) {
            stop.setVisibility(View.GONE);
            start.setVisibility(View.VISIBLE);
            StopTimer();
        }
    }

    @Override
    public void onProgressChanged(CircularSeekBar circularSeekBar, final int progress, boolean fromUser) {
        tv_minute.setText(Integer.toString(progress));
        startTime1 = Long.valueOf(tv_minute.getText().toString());


    }


    @Override
    public void onStopTrackingTouch(CircularSeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(final CircularSeekBar seekBar) {

    }

    private void StopTimer() {
        countDownTimer.cancel();
        startTime1 = 0;
        tv_minute.setText(String.valueOf(startTime1));
        tv_seconds.setText(String.valueOf(startTime1));
        circularSeekBar.setProgress(0);
    }

    private void startTimer(final long milisecods) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(milisecods, 500) {

            @Override
            public void onTick(long leftTimeInMilliseconds) {

                int barVal = (int) leftTimeInMilliseconds / 60000;
                circularSeekBar.setProgress(barVal);
                tv_minute.setText(String.format("%02d",
                        TimeUnit.MILLISECONDS.toMinutes(leftTimeInMilliseconds), -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(leftTimeInMilliseconds))
                ));

                tv_seconds.setText(String.format("%02d",
                        TimeUnit.MILLISECONDS.toSeconds(leftTimeInMilliseconds) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(leftTimeInMilliseconds))
                ));
                ;
            }

            @Override
            public void onFinish() {

                Intent i = new Intent(Timer.this, timeralert.class);
                startActivity(i);
                stop.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
            }
        }.start();

    }


}

