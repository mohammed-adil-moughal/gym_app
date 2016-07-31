package com.vorane.gymassistant;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by hp on 3/15/2016.
 */
public class Routine extends Fragment {
    private View v;
    private RecyclerView recyclerView;
     int[] titles = {R.string.biceps, R.string.chest, R.string.shoulder, R.string.legs, R.string.back};
    private RoutineAdapter adapter;

    public Routine() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.routine, container, false);
        setupRecycler();
        return v;
    }

    private void setupRecycler() {
        int SPAN_SIZE = 2;
        adapter = new RoutineAdapter(getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.routine_recycler);

        //screen size
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            SPAN_SIZE = 3;
        }
        // Medium screen devices landscape orientation
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Display display = ((WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE))
                    .getDefaultDisplay();
            int orientation = display.getRotation();
            if (orientation == Surface.ROTATION_90
                    || orientation == Surface.ROTATION_270) {
                SPAN_SIZE = 3;
            }
        }
        GridLayoutManager manager = new GridLayoutManager(getContext(), SPAN_SIZE);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent in = new Intent(getContext(), Routines.class);
                in.putExtra("Title", titles[position]);
                startActivity(in);

            }
        }));
    }
}
