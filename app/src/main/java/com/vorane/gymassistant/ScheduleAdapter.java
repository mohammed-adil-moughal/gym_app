package com.vorane.gymassistant;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ekirapa
 */
public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    SQLiteHandler handler;
    boolean noTask = false;
    Context context;
    JSONArray array;
    List<Integer> clicked = new ArrayList<>();

    public ScheduleAdapter(Context context, JSONArray array, List<Integer> clicked) {
        this.context = context;
        this.array = array;
        this.clicked = clicked;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.schedule_layout, parent, false);
            return new ScheduleHolder(v);
        } else {
            View v = LayoutInflater.from(context).inflate(R.layout.no_routine, parent, false);
            return new NoRoutineHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            ScheduleHolder holder = (ScheduleHolder) myholder;
            RecyclerView recyclerView = holder.recycler;
            if (clicked.size()>0 && clicked.get(position) == 1) {
                holder.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.font_blue_grey));
            }else{
                holder.cardView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
            }
            try {
                JSONObject obj = array.getJSONObject(position);
                String name = obj.getString("Name");
                String group = obj.getString("Group2");
                holder.title.setText(group + " :" + name);
                recyclerView.setLayoutManager(new CustomLinearLayoutManager(context));
                recyclerView.setAdapter(new WorkoutsAdapter(context, new SQLiteHandler(context).getWorkouts(name)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            NoRoutineHolder holder = (NoRoutineHolder) myholder;
            holder.tv_title.setText("No Routine added on this day");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (array.length() == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }

    }

    public void addClicked(int position) {
        if (clicked.get(position) == 1) {
            clicked.add(position, 0);
        } else {
            clicked.add(position, 1);
        }
        notifyItemChanged(position);
    }

    public void clearClicked() {
        int mSize = clicked.size();
        for (int i = 0; i < mSize; i++) {
            clicked.add(i, 0);
        }
        notifyDataSetChanged();
    }
public void setClicked(List<Integer> clicked){
    this.clicked = clicked;
    notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        if (array.length() > 0) {
            noTask = false;
            return array.length();
        } else {
            noTask = true;
            return 1;
        }
    }

    public void setArray(JSONArray newarray) {
        this.array = newarray;
        notifyDataSetChanged();
    }

    class ScheduleHolder extends RecyclerView.ViewHolder {
        TextView title;
        LinearLayout cardView;
        RecyclerView recycler;

        public ScheduleHolder(View itemView) {
            super(itemView);
            cardView = (LinearLayout) itemView.findViewById(R.id.schedule_card);
            title = (TextView) itemView.findViewById(R.id.tv_routines_title);
            recycler = (RecyclerView) itemView.findViewById(R.id.workout_recycler);
        }
    }

    class NoRoutineHolder extends RecyclerView.ViewHolder {
        TextView tv_title;

        public NoRoutineHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_noRoutine);
        }
    }
}
