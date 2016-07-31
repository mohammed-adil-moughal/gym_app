package com.vorane.gymassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 3/20/2016.
 */
public class SupsAdapter extends RecyclerView.Adapter<SupsAdapter.SupsHolder> {
    Context context;
    int[] titles = {R.string.bcaa_title, R.string.creatine_title, R.string.proteins_title, R.string.acid_title, R.string.glutamine_title, R.string.multivits_title};
    int[] content = {R.string.bcaa, R.string.creatine, R.string.proteins, R.string.acid, R.string.glutamine, R.string.multivits};

    public SupsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SupsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sups_layout, parent, false);
        return new SupsHolder(v);
    }

    @Override
    public void onBindViewHolder(SupsHolder holder, int position) {
        holder.title.setText(context.getString(titles[position]));
        holder.content.setText(context.getString(content[position]));

    }

    @Override
    public int getItemCount() {
        return content.length;
    }

    class SupsHolder extends RecyclerView.ViewHolder {
        TextView title, content;

        public SupsHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
