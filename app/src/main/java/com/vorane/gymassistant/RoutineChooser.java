package com.vorane.gymassistant;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RoutineChooser extends DialogFragment {
    List<String> groups = new ArrayList<>();
    List<String> routines = new ArrayList<>();
    RecyclerView recycler;
    Spinner spinner;
    private  SQLiteHandler handler;
    private  DialogAdapter adapter;
    private String mGroup = "BICEPS";
    String day;

    public RoutineChooser() {

    }

    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        day = getArguments().getString("Day", "MON");

        Log.d("Day", day);
        View v = getActivity().getLayoutInflater().inflate(
                R.layout.routine_dialog, null);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        setupSpinner();
        handler = new SQLiteHandler(getContext());

        routines = handler.getRoutinesList(mGroup);
        adapter = new DialogAdapter(getContext(),routines);
        recycler = (RecyclerView) v.findViewById(R.id.dialog_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                handler.addSchedule(routines.get(position),day,mGroup);
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent("Added"));
                close();
            }
        }));

        builder.setView(v);
        builder.setNegativeButton("Cancel", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        day = getArguments().getString("Day", "MON");


    }

    private void close() {
        this.dismiss();
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onActivityCreated(arg0);

    }

    public interface choseRoutine {
        void chooseroutine(String routine,String group);
    }
    private void setupSpinner() {
        groups.add("BICEPS");
        groups.add("CHEST");
        groups.add("SHOULDER");
        groups.add("LEGS");
        groups.add("BACK");
        groups.add("ABDOMINALS");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, groups);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setPrompt("Select a Client");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mGroup = parent.getItemAtPosition(position).toString();
                routines = handler.getRoutinesList(mGroup);
                adapter.setList(routines);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    }
