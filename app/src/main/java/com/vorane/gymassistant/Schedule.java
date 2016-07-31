package com.vorane.gymassistant;

import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Schedule extends Fragment implements View.OnClickListener {
    TextView sun, mon, tue, wed, thu, fri, sat, previous;
    ScheduleAdapter adapter;
    JSONArray routinesList = new JSONArray();
    SQLiteHandler handler;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    private View v;
    private String mDay;
    private boolean isLong = false;
    int mToday;
    Toolbar bottomToolbar;
    private List<Integer> clicked;
    private int mShortAnimationDuration;
    private int mDayColor = R.color.light_blue_grey;

    public Schedule() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.schedule, container, false);
        mShortAnimationDuration = getContext().getResources().getInteger(android.R.integer.config_mediumAnimTime);
        handler = new SQLiteHandler(getContext());
        tvs();
        ImageView delete = (ImageView) v.findViewById(R.id.menu_delete);
        ImageView edit = (ImageView) v.findViewById(R.id.menu_edit);
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);


        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        mToday = cal.get(Calendar.DAY_OF_WEEK);
        settoday(mToday);
        previous.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));

        setupBottomToolbar();
        setuprecycler();
        fab = (FloatingActionButton) v.findViewById(R.id.schedule_fab);
        fab.setOnClickListener(this);


        return v;
    }

    private void setuprecycler() {

        adapter = new ScheduleAdapter(getContext(), routinesList, clicked);
        getClicked();
        recyclerView = (RecyclerView) v.findViewById(R.id.schedule_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener2(getContext(), new RecyclerItemClickListener2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isLong && adapter.getItemViewType(position) == 1) {
                    showBottom(position);
                }
            }

            @Override
            public void onLOngItemClick(View v, int position) {
                if (adapter.getItemViewType(position) == 1) {
                    showBottom(position);
                }

            }
        }, recyclerView));

    }

    private void getClicked() {
        clicked = new ArrayList<>();
        for (int i = 0; i < routinesList.length(); i++) {
            clicked.add(0);
        }
        adapter.setClicked(clicked);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                routinesList = handler.getSchedule(mDay);
                adapter.setArray(routinesList);
                getClicked();
            }
        };
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, new IntentFilter("Added"));
    }

    private void tvs() {
        sun = (TextView) v.findViewById(R.id.sun);
        mon = (TextView) v.findViewById(R.id.mon);
        tue = (TextView) v.findViewById(R.id.tue);
        wed = (TextView) v.findViewById(R.id.wed);
        thu = (TextView) v.findViewById(R.id.thu);
        fri = (TextView) v.findViewById(R.id.fri);
        sat = (TextView) v.findViewById(R.id.sat);
        sun.setOnClickListener(this);
        mon.setOnClickListener(this);
        tue.setOnClickListener(this);
        wed.setOnClickListener(this);
        thu.setOnClickListener(this);
        fri.setOnClickListener(this);
        sat.setOnClickListener(this);
    }

    private void setupBottomToolbar() {
        bottomToolbar = (Toolbar) v.findViewById(R.id.bottom_toolbar);
        bottomToolbar.setNavigationIcon(R.mipmap.cancel);
        bottomToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeBottom();
            }
        });
        v.findViewById(R.id.menu_edit).setOnClickListener(this);
        v.findViewById(R.id.menu_delete).setOnClickListener(this);
    }

    private void showBottom(int position) {
        if (bottomToolbar.getVisibility() != View.VISIBLE) {
            bottomToolbar.setAlpha(0f);
            bottomToolbar.setVisibility(View.VISIBLE);
            bottomToolbar.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(null);
            fab.animate().alpha(0f).setDuration(mShortAnimationDuration).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    fab.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            isLong = true;

        }
        adapter.addClicked(position);
    }

    private void closeBottom() {
        isLong = false;
        adapter.clearClicked();
        bottomToolbar.setVisibility(View.GONE);
        fab.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void settoday(int day) {
        switch (day) {
            case Calendar.SUNDAY:
                previous = sun;
                routinesList = handler.getSchedule("SUN");
                mDay = "SUN";
                break;
            case Calendar.MONDAY:
                previous = mon;
                routinesList = handler.getSchedule("Mon");
                mDay = "Mon";
                break;
            case Calendar.TUESDAY:
                previous = tue;
                routinesList = handler.getSchedule("Tue");
                mDay = "Tue";
                break;
            case Calendar.WEDNESDAY:
                previous = wed;
                routinesList = handler.getSchedule("Wed");
                mDay = "Wed";
                break;
            case Calendar.THURSDAY:
                previous = thu;
                routinesList = handler.getSchedule("Thu");
                mDay = "Thu";
                break;
            case Calendar.FRIDAY:
                previous = fri;
                routinesList = handler.getSchedule("Fri");
                mDay = "Fri";
                break;
            case Calendar.SATURDAY:
                previous = sat;
                routinesList = handler.getSchedule("Sat");
                mDay = "Sat";
                break;
            case R.id.menu_delete:
                break;
            case R.id.menu_edit:
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sun:
                previous.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                sun.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));
                previous = sun;
                routinesList = handler.getSchedule("SUN");
                adapter.setArray(routinesList);
                mDay = "SUN";
                getClicked();
                break;
            case R.id.mon:
                previous.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                mon.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));
                previous = mon;
                routinesList = handler.getSchedule("Mon");
                adapter.setArray(routinesList);
                mDay = "Mon";
                getClicked();
                break;
            case R.id.tue:
                previous.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                tue.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));
                previous = tue;
                routinesList = handler.getSchedule("Tue");
                adapter.setArray(routinesList);
                mDay = "Tue";
                getClicked();
                break;
            case R.id.wed:
                previous.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                wed.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));
                previous = wed;
                routinesList = handler.getSchedule("Wed");
                adapter.setArray(routinesList);
                mDay = "Wed";
                getClicked();
                break;
            case R.id.thu:
                previous.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                thu.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));
                previous = thu;
                routinesList = handler.getSchedule("Thu");
                adapter.setArray(routinesList);
                mDay = "Thu";
                getClicked();
                break;
            case R.id.fri:
                previous.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                fri.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));
                previous = fri;
                routinesList = handler.getSchedule("Fri");
                adapter.setArray(routinesList);
                mDay = "Fri";
                getClicked();
                break;
            case R.id.sat:
                previous.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                sat.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));
                previous = sat;
                routinesList = handler.getSchedule("Sat");
                adapter.setArray(routinesList);
                mDay = "Sat";
                getClicked();
                break;
            case R.id.schedule_fab:
                RoutineChooser dialog = new RoutineChooser();
                Bundle bundle = new Bundle();
                bundle.putString("Day", mDay);
                dialog.setArguments(bundle);
                dialog.show(getChildFragmentManager(), "Pick Routine");
                break;
        }
    }

}
