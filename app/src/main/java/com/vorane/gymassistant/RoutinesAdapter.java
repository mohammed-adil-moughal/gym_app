package com.vorane.gymassistant;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 3/20/2016.
 */
public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.RoutinesHolder> {
    Context context;
    JSONArray array;
    Routines fragment;

    public RoutinesAdapter(Context context, JSONArray array, Routines fragment) {
        this.context = context;
        this.array = array;
        this.fragment = fragment;
    }

    @Override
    public RoutinesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.routines_layout, parent, false);
        return new RoutinesHolder(v);
    }

    @Override
    public void onBindViewHolder(RoutinesHolder holder, final int position) {

        RecyclerView recyclerView = holder.recycler;
        try {
            JSONObject obj = array.getJSONObject(position);
            String name = obj.getString("Name");
            holder.title.setText(name);
            recyclerView.setLayoutManager(new CustomLinearLayoutManager(context));
            recyclerView.setAdapter(new WorkoutsAdapter(context, new SQLiteHandler(context).getWorkouts(name)));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return array.length();
    }

    class RoutinesHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView recycler;

        public RoutinesHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_routines_title);
            recycler = (RecyclerView) itemView.findViewById(R.id.workout_recycler);
        }
    }
}
