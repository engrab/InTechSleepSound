package com.arapps.sleepsound.relaxandsleep.naturesounds.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;

public class NoScrollRecyclerView extends RecyclerView {
    public NoScrollRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return motionEvent.getAction() != 2 ? super.dispatchTouchEvent(motionEvent) : false;
    }
}
