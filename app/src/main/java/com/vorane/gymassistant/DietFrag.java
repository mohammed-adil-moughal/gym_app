package com.vorane.gymassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 3/20/2016.
 */
public class DietFrag extends Fragment {
    View v;
    TextView title, content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.diet_layout, container, false);
        Bundle b = getArguments();

        title = (TextView) v.findViewById(R.id.diet1_title);
        content = (TextView) v.findViewById(R.id.diet1_content);
        title.setText(b.getString("Title"));
        content.setText(b.getString("Content"));
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
