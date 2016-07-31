package com.vorane.gymassistant;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by hp on 4/13/2016.
 */
public class AddWorkout extends AppCompatActivity implements View.OnClickListener {
    ScrollView scrollView;
    CoordinatorLayout coordinatorLayout;
    LinearLayout linear2;
    int id = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scrollView = new ScrollView(this);
        coordinatorLayout = new CoordinatorLayout(this);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        ScrollView.LayoutParams params2 = new ScrollView.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params2.setMargins(0,0,0,60);
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

        setContentView(coordinatorLayout);

    }

    private void setupmainlinear() {
        linear2 = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        linear2.setLayoutParams(params);
        linear2.setOrientation(LinearLayout.VERTICAL);
        //linear2.addView(addToolar());
        linear2.addView(routineEt());


    }

    private Toolbar addToolar() {

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), android.R.color.white));
        toolbar.setTitle("Add New Workout");
        toolbar.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.dark_blue_grey));
        toolbar.setPopupTheme(R.style.Overlay);
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private LinearLayout bottom() {
        LinearLayout linearLayout = new LinearLayout(this);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.gravity = Gravity.END | Gravity.BOTTOM;
//        params.setMargins(16, 16, 16, 16);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(getbutton(5, "Add Set"));
        linearLayout.addView(divider());
        linearLayout.addView(getbutton(6, "Save"));
        return linearLayout;
    }

    private Button getbutton(int id, String text) {
        Button button = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 0.45f);
        params.setMargins(0,0,0,0);
        button.setLayoutParams(params);
        button.setId(id);
        button.setText(text);
        return button;
    }

    private TextInputLayout routineEt() {
        TextInputLayout inputLayout = new TextInputLayout(this);
        TextInputLayout.LayoutParams params = new TextInputLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMargins(10, 5, 10, 5);
        inputLayout.setLayoutParams(params);
        inputLayout.setBackgroundColor(ContextCompat.getColor(getBaseContext(), android.R.color.white));
        EditText etname = new EditText(this);
        etname.setId(Integer.valueOf(123));
        etname.setHint("Workout Name");
        inputLayout.addView(etname);
        return inputLayout;
    }


    private LinearLayout getLinear(int id) {
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMargins(10, 5, 10, 5);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(workEt(id + 1, "Sets"));
        linearLayout.addView(workEt2(id + 2, "Reps"));
        return linearLayout;
    }

    private View divider() {
        View div = new View(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(2, MATCH_PARENT);
        params.setMargins(0, 0, 0, 0);
        div.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.divider_color));
        div.setLayoutParams(params);
        return div;
    }

    private EditText workEt(int id, String hint) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 50, 0.5f);
        params.setMargins(0, 0, 5, 0);
        EditText etname = new EditText(this);
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
        EditText etname = new EditText(this);
        etname.setLayoutParams(params);
        etname.setGravity(Gravity.CENTER);
        etname.setId(Integer.valueOf(123));
        etname.setHint(hint);
        etname.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.group_top));
        return etname;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.workout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_workout) {
            linear2.addView(getLinear(id + 1));
            id += 5;
        }
        return super.onOptionsItemSelected(item);
    }
}
