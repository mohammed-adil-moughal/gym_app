package com.vorane.gymassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 3/17/2016.
 */
public class SpecificAdapter extends RecyclerView.Adapter<SpecificAdapter.ExerciseHolder> {
    private final Context context;
    CharSequence[] val ;

    public SpecificAdapter(Context context, CharSequence[] val) {
        this.context = context;
        this.val = val;
    }

    @Override
    public ExerciseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.exercises_layout, parent, false);
        return new ExerciseHolder(v);
    }

    @Override
    public void onBindViewHolder(ExerciseHolder holder, int position) {
        holder.tv.setText(val[position]);
    }

    @Override
    public int getItemCount() {
        return val.length;
    }

    class ExerciseHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ExerciseHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_exercise);
        }
    }
}
