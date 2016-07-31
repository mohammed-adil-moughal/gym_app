package com.vorane.gymassistant;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by hp on 3/20/2016.
 */
public class Diet extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet);
        setupToolbar();
        setupViewPager();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.diet_toolbar);
        toolbar.setTitle("Diet");
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.diet_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.diet_tabs);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle b = new Bundle();
        b.putString("Title", getString(R.string.massheader));
        b.putString("Content", getString(R.string.massdetails));
        DietFrag diet1 = new DietFrag();
        diet1.setArguments(b);

        adapter.addFragment(diet1, getString(R.string.addmass));

        Bundle b2 = new Bundle();
        b2.putString("Title", getString(R.string.lossheader));
        b2.putString("Content", getString(R.string.weightlossdiet));
        DietFrag diet2 = new DietFrag();
        diet2.setArguments(b2);
        adapter.addFragment(diet2, getString(R.string.weightloss));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
