package com.vorane.gymassistant;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.RoutineHolder> {
    Context context;
    private int[] resid = {R.drawable.diet, R.drawable.sups, R.drawable.weights2, R.drawable.timer,R.drawable.calculator};
    private int[] titles = {R.string.diet, R.string.suplements, R.string.exercises, R.string.timer,R.string.Calculator};
    private int[] resid_color = new int[]{R.color.biceps, R.color.chest, R.color.back, R.color.legs, R.color.chest};


    public HelpAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RoutineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.help_layout, parent, false);

        return new RoutineHolder(v);
    }

    @Override
    public void onBindViewHolder(RoutineHolder holder, int position) {
        try {
            Bitmap b = BitmapFactory.decodeResource(context.getResources() ,resid[position]);
            holder.image.setImageBitmap(b);
        }catch (OutOfMemoryError outOfMemoryError){

        }

        //holder.image.setBackgroundResource(resid[position]);
        //Bitmap b = decoderesource(context.getResources(), resid[position]);

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
            image = (ImageView) itemView.findViewById(R.id.help_image);
            tvTitle = (TextView) itemView.findViewById(R.id.help_title);
        }
    }
    private static Bitmap decoderesource(Resources res ,int id){
        Bitmap bitmap  = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        for(options.inSampleSize = 1; options.inSampleSize<=32;options.inSampleSize++){
            try {
                bitmap = BitmapFactory.decodeResource(res,id,options);
            }catch (OutOfMemoryError outOfMemoryError){

            }

        }
        return bitmap;
    }
}
