package com.vorane.gymassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 4/12/2016.
 */
public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.WorkOutHolder> {
    Context context;
    JSONArray array;
    public WorkoutsAdapter(Context con,JSONArray array) {
        this.context = con;
        this.array = array;
    }

    @Override
    public WorkOutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.workout_layout,parent,false);
        return new WorkOutHolder(v);
    }

    @Override
    public void onBindViewHolder(WorkOutHolder holder, int position) {
        try {
            JSONObject obj = array.getJSONObject(position);
            holder.name.setText(obj.getString("Name"));
            holder.reps.setText(obj.getString("Reps"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return array.length();
    }

    class WorkOutHolder extends RecyclerView.ViewHolder {
        TextView name, reps;

        public WorkOutHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_workout_name);
            reps = (TextView) itemView.findViewById(R.id.tv_workout_reps);
        }
    }
}
