package com.vorane.gymassistant;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hp on 3/15/2016.
 */
public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineHolder> {
    Context context;
    private int[] resid = {R.drawable.bicep_image, R.drawable.chest_image, R.drawable.shoulder_training, R.drawable.legs_image, R.drawable.back_image};
    private int[] titles = {R.string.biceps, R.string.chest, R.string.shoulder, R.string.legs, R.string.back};
    private int[] resid_color = {R.color.biceps, R.color.chest, R.color.back, R.color.legs, R.color.chest};


    public RoutineAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RoutineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.routine_layout, parent, false);

        return new RoutineHolder(v);
    }

    @Override
    public void onBindViewHolder(RoutineHolder holder, int position) {
        holder.image.setBackgroundResource(resid[position]);
        holder.tvTitle.setText(titles[position]);
        holder.tvTitle.setBackgroundColor(ContextCompat.getColor(context, resid_color[position]));
    }

    @Override
    public int getItemCount() {
        return resid.length;
    }

    class RoutineHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvTitle;

        public RoutineHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.routine_image);
            tvTitle = (TextView) itemView.findViewById(R.id.routine_title);
        }
    }
}
