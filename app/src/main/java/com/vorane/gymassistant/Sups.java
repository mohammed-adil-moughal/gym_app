package com.vorane.gymassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by hp on 3/20/2016.
 */
public class Sups extends AppCompatActivity {
    private SupsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sups);
        setuptoolbar();
        setupRecycler();
    }
    private void setuptoolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.sups_toolbar);
        toolbar.setTitle("Food Supplements");
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private  void  setupRecycler(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sups_recycler);
        adapter = new SupsAdapter(getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(adapter);
    }
}
