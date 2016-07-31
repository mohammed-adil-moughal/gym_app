package com.vorane.gymassistant;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by hp on 3/20/2016.
 */
public class NewRoutine extends AppCompatActivity {
    int id = 1;
    LinearLayout main;
    private SupsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_routine);
        setuptoolbar();
        main = (LinearLayout) findViewById(R.id.main_linear);
       main.addView(mainEt(1,"Reps"));
        main.invalidate();
    }

    private void setuptoolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.add_toolbar);
        toolbar.setTitle("Add New Routine");
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private LinearLayout getView(int id) {
        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setId(id);
        linearLayout.addView(mainEt(id + 1, "Exercise eg. barbell curl"));
        linearLayout.addView(getLinear(id + 2));
        linearLayout.addView(divider());
        return linearLayout;
    }

    private EditText mainEt(int id, String hint) {
        EditText et = new EditText(this);
        et.setId(id);
        et.setHeight(WRAP_CONTENT);
        et.setWidth(MATCH_PARENT);
        et.setPadding(5, 5, 5, 5);
        et.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.group_top));
        et.setHint("Exercise eg. barbell curl");
        return et;
    }

    private LinearLayout getLinear(int id) {
        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(mainEt(id + 1, "Reps"));
        linearLayout.addView(mainEt(id + 2, "Weight"));
        return linearLayout;
    }

    private View divider() {
        View div = new View(this);
        div.setMinimumHeight(2);
        div.setMinimumWidth(MATCH_PARENT);
        div.setPadding(5, 5, 5, 5);
        div.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.divider_color));
        return div;
    }
}
