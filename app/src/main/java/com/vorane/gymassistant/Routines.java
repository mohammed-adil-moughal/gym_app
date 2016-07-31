package com.vorane.gymassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hp on 3/20/2016.
 */
public class Routines extends AppCompatActivity {
    String title;
    private RoutinesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routines);
        title = getString(getIntent().getIntExtra("Title", R.string.routine));

        setuptoolbar();
        setupRecycler();
    }

    private void setuptoolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.routines_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setupRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.routines_recycler);
        adapter = new RoutinesAdapter(getBaseContext(), new SQLiteHandler(getBaseContext()).getRoutines(title),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener2(getBaseContext(), new RecyclerItemClickListener2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLOngItemClick(View v, int position) {

            }
        }, recyclerView));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.routine_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_routine) {
            Intent in = new Intent(getBaseContext(), AddRutine.class);
            in.putExtra("Title", title);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }

}
