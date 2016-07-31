package com.vorane.gymassistant;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

/**
 * Created by hp on 3/15/2016.
 */
public class Home extends AppCompatActivity {
    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setToolbar();
       setupViewPager();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setTitle(R.string.app_name);
        // toolbar.setNavigationIcon(R.drawable.ic_action_menu);
    }

    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Routine(), getString(R.string.routine));
        adapter.addFragment(new Schedule(), getString(R.string.schedule));
        adapter.addFragment(new Help(), getString(R.string.help));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}
