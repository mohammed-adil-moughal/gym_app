package com.vorane.gymassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp on 3/16/2016.
 */
public class Today extends Fragment {
    private View v;

    public Today() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.today, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
