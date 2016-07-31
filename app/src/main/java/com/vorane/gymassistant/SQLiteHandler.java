package com.vorane.gymassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 3/16/2016.
 */
public class SQLiteHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DB_GymAssistant";
    private static final String TABLE_ROUTINES = "TB_Routines";
    private static final String TABLE_WORKOUTS = "TB_Workouts";
    private static final String TABLE_SCHEDULE = "TB_Schedule";
    private static final String KEY_ID = "Id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_GROUP = "Group2";
    private static final String KEY_REPS = "Reps";
    private static final String KEY_COMMENT = "Comment";
    private static final String KEY_DAY = "Day";
    private final Context context;

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createRoutinesTable() {
        SQLiteDatabase db = getWritableDatabase();
        String QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_ROUTINES + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + KEY_NAME
                + " TEXT ," + KEY_GROUP + " TEXT ," + KEY_COMMENT + " TEXT )";
        db.execSQL(QUERY);
        db.close();

    }

    private void createWorkoutsTable() {
        SQLiteDatabase db = getWritableDatabase();
        String QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_WORKOUTS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + KEY_NAME
                + " TEXT ," + KEY_REPS + " TEXT ," + KEY_GROUP + " TEXT )";
        db.execSQL(QUERY);
        db.close();

    }

    private void createSchedule() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_SCHEDULE + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + KEY_NAME
                + " TEXT ," + KEY_DAY + " TEXT ," + KEY_GROUP + " TEXT )";
        db.execSQL(query);
        db.close();
    }

    public void addWorkout(String name, String group, String reps) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_GROUP, group);
        values.put(KEY_REPS, reps);
        db.insert(TABLE_WORKOUTS, null, values);
        db.close();
    }

    public void addRoutine(String name, String group, String comment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_GROUP, group);
        values.put(KEY_COMMENT, comment);
        db.insert(TABLE_ROUTINES, null, values);
        db.close();
    }

    public void addSchedule(String routine, String day, String group) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, routine);
        values.put(KEY_DAY, day);
        values.put(KEY_GROUP, group);
        db.insert(TABLE_SCHEDULE, null, values);
        db.close();
    }

    private void preWriteDB() {
        SQLiteDatabase db = getWritableDatabase();
        String groups[] = {"CHEST", "BICEPS", "SHOULDER", "LEGS", "BACK"};
        int[] titles = {R.string.biceps, R.string.chest, R.string.shoulder, R.string.legs, R.string.back};
        String colors[] = {"Flat Bench Press", "Inclined Bench Press", "Decline Bench Press", "Flat Dumbell Press"};

        for (int i = 0; i < 5; i++) {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, "Sample Routine");
            values.put(KEY_GROUP, context.getString(titles[i]));
            values.put(KEY_COMMENT, "Comment");
            db.insert(TABLE_ROUTINES, null, values);
        }

        for (int i = 0; i < 4; i++) {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, colors[i]);
            values.put(KEY_GROUP, "Sample Routine");
            values.put(KEY_REPS, "4 Sets X 10 Reps");
            db.insert(TABLE_WORKOUTS, null, values);
        }
        db.close();
    }

    public void createDB() {
        createRoutinesTable();
        createWorkoutsTable();
        createSchedule();
        preWriteDB();
    }

public List<String> getRoutinesList(String group){
    List<String> routines = new ArrayList<>();
    String selectQuery = "SELECT  * FROM " + TABLE_ROUTINES + " WHERE " + KEY_GROUP + " ='" + group + "'";
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);
    if(cursor.moveToNext()){
        do {
            routines.add(cursor.getString(1));
        }while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return  routines;

}
    public JSONArray getRoutines(String group) {
        JSONArray routines_array = new JSONArray();
        String selectQuery = "SELECT  * FROM " + TABLE_ROUTINES + " WHERE " + KEY_GROUP + " ='" + group + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            // Move to first row
            if (cursor.moveToFirst()) {
                int i = 0;
                do {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put(KEY_NAME, cursor.getString(1));
                        obj.put(KEY_GROUP, cursor.getString(2));
                        obj.put(KEY_COMMENT, cursor.getString(3));

                        routines_array.put(i, obj);
                        i++;
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (android.database.sqlite.SQLiteException e) {
            return routines_array;
        }
        return routines_array;
    }

    public JSONArray getWorkouts(String group) {
        JSONArray array = new JSONArray();
        String selectQuery = "SELECT  * FROM " + TABLE_WORKOUTS + " WHERE " + KEY_GROUP + "='" + group + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                int i = 0;
                do {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put(KEY_NAME, cursor.getString(1));
                        obj.put(KEY_REPS, cursor.getString(2));

                        array.put(i, obj);
                        i++;
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (android.database.sqlite.SQLiteException e) {

            return array;
        }

        return array;
    }

    public JSONArray getSchedule(String day) {
        JSONArray array = new JSONArray();
        int i = 0;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_SCHEDULE + " WHERE " + KEY_DAY + "='" + day + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                JSONObject obj = new JSONObject();
                try {
                    obj.put(KEY_NAME, cursor.getString(1));
                    obj.put(KEY_GROUP, cursor.getString(3));

                    array.put(i, obj);
                    i++;
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return array;
    }
}
