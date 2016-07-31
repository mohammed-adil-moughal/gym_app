package com.vorane.gymassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

/**
 * Created by hp on 3/20/2016.
 */
public class DisplayExercise extends AppCompatActivity {
    CharSequence title;
    ImageView imageView;
    TextView tv_content;
    int resid, content;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_show);
        imageView = (ImageView) findViewById(R.id.img_gif);
        tv_content = (TextView) findViewById(R.id.tv_display);
        Bundle b = getIntent().getExtras();
        title = b.getCharSequence("Title");
        resid = b.getInt("Id");
        content = b.getInt("Content");
        setupToolbar();
        Ion.with(imageView).load("android.resource://com.vorane.gymassistant/" + resid);
        tv_content.setText(getString(content));
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.display_toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
