package com.vorane.gymassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener2 implements OnItemTouchListener {
    private OnItemClickListener mListener;

    GestureDetector mGestureDetector;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onLOngItemClick(View v, int position);
    }

    public RecyclerItemClickListener2(Context context,
                                      OnItemClickListener listener, final RecyclerView recyclerView) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        super.onLongPress(e);
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

                        if (childView != null && mListener != null) {
                            mListener.onLOngItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                        }
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null
                && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView,
                    view.getChildAdapterPosition(childView));
            //mListener.onItemClick(childView, view.getChildPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTouchEvent(RecyclerView arg0, MotionEvent arg1) {
        // TODO Auto-generated method stub

    }

}
