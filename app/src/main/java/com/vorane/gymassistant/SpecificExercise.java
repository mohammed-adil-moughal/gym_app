package com.vorane.gymassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by hp on 3/17/2016.
 */
public class SpecificExercise extends AppCompatActivity {
    private static final String KEY_STRING = "EXERCISE";
    Toolbar toolbar;
    RecyclerView recyclerView;
    CharSequence[] vals;
    String title;
    private SpecificAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific);
        Bundle b = getIntent().getExtras();
        vals = b.getCharSequenceArray(KEY_STRING);
        title = b.getString("Title");
        setupToolbar();
        setupRecycler();
    }

    private void setupRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.specific_recycler);
        adapter = new SpecificAdapter(getBaseContext(), vals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CharSequence exercise;
                int[] ids;
                int[] cont_ids;
                exercise = vals[position];
                ids = getids(title);
                cont_ids = getContentIds(title);
                Intent disp_intent = new Intent(getBaseContext(), DisplayExercise.class);
                disp_intent.putExtra("Title", exercise);
                disp_intent.putExtra("Id", ids[position]);
                disp_intent.putExtra("Content", cont_ids[position]);
                startActivity(disp_intent);

            }

        }));
    }

    private int[] getContentIds(String exercise) {
        if (exercise.equals("Chest")) {
            int[] resids = {R.string.flatbench, R.string.inclinebench, R.string.Declinebench, R.string.dbincline, R.string.DBdeclinepress, R.string.DBpress, R.string.dbpullover, R.string.dbflyes};
            return resids;
        } else if (exercise.equals("Biceps")) {
            int[] resids = {R.string.bbcurls, R.string.bbcablecurl, R.string.bbdbcurl, R.string.bbinclinedbcurl, R.string.levercurl};
            return resids;
        } else if (exercise.equals("Back")) {
            int[] resids = {R.string.deadlift, R.string.powerclean, R.string.chinups, R.string.bhneck, R.string.latpulldown, R.string.dbrows, R.string.BBROWS};
            return resids;
        } else if (exercise.equals("Triceps")) {
            int[] resids = {R.string.triclose, R.string.tricrusher, R.string.trioverheadbb, R.string.trioverheadbb, R.string.tribenchdip, R.string.trinormaldip};
            return resids;
        } else if (exercise.equals("Shoulders")) {
            int[] resids = {R.string.sshoulderpress, R.string.smilitarypress, R.string.sshoulderdumbellpress, R.string.slateralraises, R.string.spushpressshoulder, R.string.sbarbellshrug, R.string.strapbarshrug};
            return resids;
        } else if (exercise.equals("Legs")) {
            int[] resids = {R.string.lnormsquat, R.string.lfrsquat, R.string.llunges, R.string.lhacksquat, R.string.lstandingcalfrasies, R.string.lseatedlegpress, R.string.llegcurls, R.string.lhamstringcurls};
            return resids;
        } else if (exercise.equals("Abs")) {
            int[] resids = {R.string.aweightedcrunches, R.string.alegraises, R.string.twistedcrunches};
            return resids;
        }
        return null;
    }

    private int[] getids(String exercise) {

        if (exercise.equals("Chest")) {
            int[] resids = {R.drawable.flatbench, R.drawable.inclinebenchpress, R.drawable.declinebenchpress, R.drawable.dbdeclinebenchpress, R.drawable.dbdeclinebenchpress, R.drawable.dbbenchpress, R.drawable.dbpullover, R.drawable.dbfly};
            return resids;
        } else if (exercise.equals("Biceps")) {
            int[] resids = {R.drawable.bbcurl, R.drawable.cbcurl, R.drawable.dbcurl, R.drawable.dbinclinecurl, R.drawable.machinecurl};
            return resids;
        } else if (exercise.equals("Back")) {
            int[] resids = {R.drawable.bbdeadlift, R.drawable.bbclean, R.drawable.bbchinup, R.drawable.bbrearpullup, R.drawable.bbfrontpulldown, R.drawable.bbbentoverrow, R.drawable.bbbarbelbentrow};
            return resids;
        } else if (exercise.equals("Triceps")) {
            int[] resids = {R.drawable.triclose, R.drawable.tricrusher, R.drawable.trioverheadbb, R.drawable.trioverheadbb, R.drawable.tribenchdip, R.drawable.trinormaldip};
            return resids;
        } else if (exercise.equals("Shoulders")) {
            int[] resids = {R.drawable.sbbshoulderpress, R.drawable.sbbmilitarypress, R.drawable.sbbshoulderpress, R.drawable.sdblateraraise, R.drawable.spushpress, R.drawable.sbbshrugs, R.drawable.sshrugs};
            return resids;
        } else if (exercise.equals("Legs")) {
            int[] resids = {R.drawable.lnormsqaut, R.drawable.lfrontsquat, R.drawable.llunges, R.drawable.lhacksquat, R.drawable.lstandingcalfraises, R.drawable.lseatedlegpress, R.drawable.llegcurl, R.drawable.lbbhamgluteraises};
            return resids;
        } else if (exercise.equals("Abs")) {
            int[] resids = {R.drawable.aweightedcrunch, R.drawable.alegraise, R.drawable.atwistedcrunch};
            return resids;
        }
        return null;
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.specific_toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
