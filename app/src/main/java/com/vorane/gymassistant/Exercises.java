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
public class Exercises extends AppCompatActivity {
    private static final String KEY_STRING = "EXERCISE";
    Toolbar toolbar;
    RecyclerView recyclerView;
    private ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises);
        setuptoolbar();
        setupRecycler();
    }

    private void setuptoolbar() {
        toolbar = (Toolbar) findViewById(R.id.exercises_toolbar);
        toolbar.setTitle("Sample Exercises");
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.exercises_recycler);
        adapter = new ExerciseAdapter(getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent specific_intent = new Intent(getBaseContext(), SpecificExercise.class);
                switch (position) {
                    case 0:
                        CharSequence[] chest = {"Flat Bench Press",
                                "Incline Bench Press",
                                "Decline Bench Press",
                                "Incline Dumbell Press",
                                "Decline Dumbell Press",
                                "Flat Dumbell Press",
                                "Dumbell Pullover",
                                "Dumbell Flyes"};
                        specific_intent.putExtra(KEY_STRING, chest);
                        specific_intent.putExtra("Title", "Chest");
                        startActivity(specific_intent);
                        //chest
                        break;
                    case 1:
                        CharSequence[] biceps = {"Barbell Curls",
                                "Cable Curl",
                                "Dumbbell Curl",
                                "Dumbbell Incline Curl",
                                "Machine Curl"};
                        specific_intent.putExtra(KEY_STRING, biceps);
                        specific_intent.putExtra("Title", "Biceps");
                        startActivity(specific_intent);
                        //biceps
                        break;
                    case 2:
                        CharSequence[] back = {"Barbell Dead lifts",
                                "Power cleans",
                                "Chin Ups",
                                "Behind The Neck Pull ups",
                                "Lat Pull Down",
                                "Dumbell Rows",
                                "Bent Over Rows"};
                        specific_intent.putExtra(KEY_STRING, back);
                        specific_intent.putExtra("Title", "Back");
                        startActivity(specific_intent);
                        //back
                        break;
                    case 3:
                        CharSequence[] triceps = {"Close-Grip Bench Press",
                                "Lying French Press",
                                "Barbell Overhead Extensions",
                                "Dumbbell Overhead Extensions",
                                "Bench Dips",
                                "Tricep Dips"};
                        specific_intent.putExtra(KEY_STRING, triceps);
                        specific_intent.putExtra("Title", "Triceps");
                        startActivity(specific_intent);
                        //tri
                        break;
                    case 4:
                        CharSequence[] shoulders = {"Barbell Shoulder Press",
                                "Military Press",
                                "Dumbbell Shoulder Press",
                                "Dumbell Lateral Raise",
                                "Push Press",
                                "Barbell Shrug",
                                "Trap bar Shrug"};
                        specific_intent.putExtra(KEY_STRING, shoulders);
                        specific_intent.putExtra("Title", "Shoulders");
                        startActivity(specific_intent);
                        //sho
                        break;
                    case 5:
                        CharSequence[] legs = {"Barbell Back Squat",
                                "Barbell Front Squat",
                                "Barbell Lunge",
                                "Hack Squat",
                                "Barbell Calf Raises",
                                "Seated Leg Press",
                                "Leg Curls"};
                        specific_intent.putExtra(KEY_STRING, legs);
                        specific_intent.putExtra("Title", "Legs");
                        startActivity(specific_intent);
                        //leg
                        break;
                    case 6:
                        CharSequence[] abs = {"Crunch",
                                "Knee Raises",
                                "Twisting Crunch"};
                        specific_intent.putExtra(KEY_STRING, abs);
                        specific_intent.putExtra("Title", "Abs");
                        startActivity(specific_intent);
                        //abs
                        break;
                    case 7:
                        CharSequence[] cardio = {};
                        specific_intent.putExtra(KEY_STRING, cardio);
                        specific_intent.putExtra("Title", "Cardio");
                        startActivity(specific_intent);
                        //card
                        break;
                }

            }
        }));
    }
}
