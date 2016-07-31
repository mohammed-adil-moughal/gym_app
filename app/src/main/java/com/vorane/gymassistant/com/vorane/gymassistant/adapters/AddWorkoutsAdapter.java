package com.vorane.gymassistant.com.vorane.gymassistant.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vorane.gymassistant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 4/18/2016.
 */
public class AddWorkoutsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_NAME = 1;
    private static final int TYPE_REP = 2;
    Context context;
    JSONArray array;

    public AddWorkoutsAdapter(Context con, JSONArray array) {
        this.context = con;
        this.array = array;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_NAME;
        } else {
            return TYPE_REP;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NAME) {
            View v = LayoutInflater.from(context).inflate(R.layout.workout_name, parent, false);
            return new WNameHolder(v);
        } else {
            View v = LayoutInflater.from(context).inflate(R.layout.reps, parent, false);
            return new WRepsHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NAME) {
            WNameHolder nameholder = (WNameHolder) holder;
            try {
                JSONObject obj = array.getJSONObject(position);
                nameholder.tvname.setText(obj.getString("Name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            WRepsHolder repsHolder = (WRepsHolder) holder;
            try {
                JSONObject obj = array.getJSONObject(position);
                repsHolder.treps.setText(obj.getString("Reps"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return array.length();
    }

    class WNameHolder extends RecyclerView.ViewHolder {
        TextView tvname;

        public WNameHolder(View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.tv_wname);
        }
    }

    class WRepsHolder extends RecyclerView.ViewHolder {
        TextView treps;

        public WRepsHolder(View itemView) {
            super(itemView);
            treps = (TextView) itemView.findViewById(R.id.tv_reps);
        }
    }
}
