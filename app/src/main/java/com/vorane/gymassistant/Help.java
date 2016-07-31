package com.vorane.gymassistant;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by hp on 3/15/2016.
 */
public class Help extends Fragment {
    private View v;
    private RecyclerView recyclerView;
    private HelpAdapter adapter;

    public Help() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.help, container, false);
        setupRecycler();
        return v;
    }

    private void setupRecycler() {
        int SPAN_SIZE = 2;
        adapter = new HelpAdapter(getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.help_recycler);

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
                switch (position) {
                    case 0:
                        Intent diet_intent = new Intent(getContext(), Diet.class);
                        startActivity(diet_intent);
                        break;
                    case 1:
                        Intent sups_intent = new Intent(getContext() , Sups.class);
                        startActivity(sups_intent);
                        break;
                    case 2:
                        Intent ex_intent = new Intent(getContext(), Exercises.class);
                        startActivity(ex_intent);
                        break;
                    case 3:
                        Intent timer_in = new Intent(getContext(),Timer.class);
                        startActivity(timer_in);
                        break;
                    case 4:
                        Intent calc=new Intent (getContext(), calculator.class);
                        startActivity(calc);
                        break;
                }
            }
        }));
    }
}
