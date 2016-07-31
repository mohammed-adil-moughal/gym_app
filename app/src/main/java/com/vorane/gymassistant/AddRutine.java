package com.vorane.gymassistant;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by hp on 4/13/2016.
 */
public class AddRutine extends AppCompatActivity implements View.OnClickListener {
    ScrollView scrollView;
    CoordinatorLayout coordinatorLayout;
    LinearLayout linear2;
    int id = 20;
    private SQLiteHandler handler;
    private List<EditText> editTextList = new ArrayList<EditText>();
    private String group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new SQLiteHandler(getBaseContext());
        scrollView = new ScrollView(this);
        coordinatorLayout = new CoordinatorLayout(this);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        ScrollView.LayoutParams params2 = new ScrollView.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        coordinatorLayout.setLayoutParams(params);
        scrollView.setLayoutParams(params2);
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.light_blue_grey));
        setupmainlinear();
        linear2.addView(setupLinear());
        scrollView.addView(linear2);
        coordinatorLayout.addView(scrollView);
        coordinatorLayout.addView(addfab());
        group = getIntent().getStringExtra("Title");
        setContentView(coordinatorLayout);


    }

    private void setupmainlinear() {
        linear2 = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        linear2.setLayoutParams(params);
        linear2.setOrientation(LinearLayout.VERTICAL);
        linear2.addView(addToolar());
        linear2.addView(routineEt());


    }

    private LinearLayout setupLinear() {
        LinearLayout linear = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        linear.setLayoutParams(params);
        linear.setOrientation(LinearLayout.VERTICAL);
        setupWOrkoutLayout(id, linear);
        //cardView.addView(linear);
        id += 4;
        return linear;
    }

    private Toolbar addToolar() {

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), android.R.color.white));
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Add New Routine");
        toolbar.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.dark_blue_grey));
        toolbar.setPopupTheme(R.style.Overlay);
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        return toolbar;
    }

    private FloatingActionButton addfab() {
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        params.gravity = Gravity.END | Gravity.BOTTOM;
        params.setMargins(16, 16, 16, 16);
        FloatingActionButton fab = new FloatingActionButton(this);
        fab.setId(Integer.valueOf(10));
        fab.setLayoutParams(params);
        fab.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_action_save));
        fab.setOnClickListener(this);
        return fab;

    }

    private TextInputLayout routineEt() {
        TextInputLayout inputLayout = new TextInputLayout(this);
        TextInputLayout.LayoutParams params = new TextInputLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMargins(10, 5, 10, 5);
        inputLayout.setLayoutParams(params);
        inputLayout.setBackgroundColor(ContextCompat.getColor(getBaseContext(), android.R.color.white));
        EditText etname = new EditText(this);
        etname.setId(Integer.valueOf(123));
        etname.setHint("Routine Name");
        inputLayout.addView(etname);
        return inputLayout;
    }

    private void setupWOrkoutLayout(int id, LinearLayout linear) {
        linear.addView(mainEt(id, "Workout eg. Barbell curl"));
        linear.addView(getLinear(id));
        linear.addView(deleteBt(id + 2));
        linear.addView(divider());
    }

    private EditText mainEt(int id, String hint) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, 50);
        params.setMargins(10, 5, 10, 5);
        EditText etname = new EditText(this);
        etname.setLayoutParams(params);
        etname.setId(id);
        etname.setHint(hint);
        etname.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.group_top));
        editTextList.add(etname);

        return etname;
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, 1);
        params.setMargins(10, 5, 10, 5);
        div.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.divider_color));
        div.setLayoutParams(params);
        return div;
    }

    private ImageView deleteBt(int id) {
        ImageView bt = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
        params.setMargins(400, 3, 10, 3);
        bt.setLayoutParams(params);
        bt.setId(id);
        bt.setScaleType(ImageView.ScaleType.CENTER_CROP);
        bt.setBackgroundDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_delete));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return bt;
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
        editTextList.add(etname);
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
        editTextList.add(etname);
        return etname;
    }

    private boolean isok() {
        boolean ok = true;
        for (EditText editText : editTextList) {
            if (editText.getText().toString().isEmpty()) {
                ok = false;
            }
        }
        return ok;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 10:
                handler = new SQLiteHandler(getBaseContext());
                EditText rname = (EditText) findViewById(Integer.valueOf(123));
                String name = rname.getText().toString();
                if (!name.isEmpty()) {
                    if (isok()) {
                        String weight = new SessionManager(getBaseContext()).weight();
                        handler.addRoutine(name, group, "Comment");
                        for (int i = 0; i < editTextList.size(); i += 3) {
                            String wname = editTextList.get(i).getText().toString();
                            String rep = editTextList.get(i + 1).getText().toString() + " Sets X " + editTextList.get(i + 2).getText().toString() + " Reps";
                            handler.addWorkout(wname, name, rep);
                            Toast.makeText(getBaseContext(), "Routine added", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getBaseContext(), "Enter all workout details", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Enter the Routine name", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.routine_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_routine) {
            linear2.addView(setupLinear());
        }
        return super.onOptionsItemSelected(item);
    }
}
