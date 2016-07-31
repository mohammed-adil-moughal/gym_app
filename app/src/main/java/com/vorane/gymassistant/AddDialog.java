package com.vorane.gymassistant;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by hp on 4/16/2016.
 */
public class AddDialog extends android.support.v4.app.DialogFragment {
    ScrollView scrollView;
    CoordinatorLayout coordinatorLayout;
    LinearLayout linear2;
    int id = 20;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getTag());
        scrollView = new ScrollView(getBaseContext());
        coordinatorLayout = new CoordinatorLayout(getBaseContext());
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        ScrollView.LayoutParams params2 = new ScrollView.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params2.setMargins(0, 0, 0, 60);
        coordinatorLayout.setLayoutParams(params);
        coordinatorLayout.setMinimumHeight(500);
        scrollView.setLayoutParams(params2);
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.light_blue_grey));
        setupmainlinear();
        linear2.addView(getLinear(id + 1));
        id += 5;

        scrollView.addView(linear2);
        coordinatorLayout.addView(scrollView);
        coordinatorLayout.addView(bottom());

        builder.setView(coordinatorLayout);
        return builder.create();
    }

    private void setupmainlinear() {
        linear2 = new LinearLayout(getBaseContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        linear2.setLayoutParams(params);
        linear2.setOrientation(LinearLayout.VERTICAL);
        linear2.addView(routineEt());


    }


    private LinearLayout bottom() {
        LinearLayout linearLayout = new LinearLayout(getBaseContext());
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.gravity = Gravity.END | Gravity.BOTTOM;
//        params.setMargins(16, 16, 16, 16);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button one = getbutton(5, "Add Set");
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear2.addView(getLinear(id + 1));
                id += 5;
            }
        });
        linearLayout.addView(one);
        linearLayout.addView(divider());
        Button two = getbutton(6, "Save");
        linearLayout.addView(two);
        return linearLayout;
    }

    private Button getbutton(int id, String text) {
        Button button = new Button(getBaseContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 80, 0.45f);
        params.setMargins(0, 0, 0, 0);
        button.setLayoutParams(params);
        button.setId(id);
        button.setText(text);
        return button;
    }

    private TextInputLayout routineEt() {
        TextInputLayout inputLayout = new TextInputLayout(getBaseContext());
        TextInputLayout.LayoutParams params = new TextInputLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMargins(10, 5, 10, 5);
        inputLayout.setLayoutParams(params);
        inputLayout.setBackgroundColor(ContextCompat.getColor(getBaseContext(), android.R.color.white));
        EditText etname = new EditText(getBaseContext());
        etname.setId(Integer.valueOf(123));
        etname.setHint("Workout Name");
        inputLayout.addView(etname);
        return inputLayout;
    }


    private LinearLayout getLinear(int id) {
        LinearLayout linearLayout = new LinearLayout(getBaseContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMargins(10, 5, 10, 5);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(workEt(id + 1, "Reps"));
        linearLayout.addView(workEt2(id + 2, "Weight"));
        return linearLayout;
    }

    private View divider() {
        View div = new View(getBaseContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(2, 80);
        params.setMargins(0, 0, 0, 0);
        div.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.divider_color));
        div.setLayoutParams(params);
        return div;
    }

    private EditText workEt(int id, String hint) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 50, 0.5f);
        params.setMargins(0, 0, 5, 0);
        EditText etname = new EditText(getBaseContext());
        etname.setLayoutParams(params);
        etname.setGravity(Gravity.CENTER);
        etname.setId(Integer.valueOf(123));
        etname.setHint(hint);
        etname.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.group_top));
        return etname;
    }

    private EditText workEt2(int id, String hint) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 50, 0.5f);
        params.setMargins(0, 0, 0, 0);
        EditText etname = new EditText(getBaseContext());
        etname.setLayoutParams(params);
        etname.setGravity(Gravity.CENTER);
        etname.setId(Integer.valueOf(123));
        etname.setHint(hint);
        etname.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.group_top));
        return etname;
    }

    public Context getBaseContext() {
        return getActivity();
    }
}
