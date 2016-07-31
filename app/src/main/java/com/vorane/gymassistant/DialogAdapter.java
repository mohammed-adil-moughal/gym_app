package com.vorane.gymassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekirapa on 6/12/16.
 */
public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogHolder> {
Context context;
    List<String> routines = new ArrayList<>();
    public DialogAdapter(Context context,List<String> routines){
        this.context = context;
        this.routines = routines;
    }
    @Override
    public DialogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.routine_dialog_layout,parent,false);
        return new DialogHolder(v);
    }

    @Override
    public void onBindViewHolder(DialogHolder holder, int position) {
holder.view.setText(routines.get(position));
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }
    public  void  setList(List<String> routines){
        this.routines = routines;
        notifyDataSetChanged();
    }

    class DialogHolder extends RecyclerView.ViewHolder {
        TextView view;
        public DialogHolder(View itemView) {
            super(itemView);
            view = (TextView) itemView.findViewById(R.id.tv_dialog);
        }
    }
}
