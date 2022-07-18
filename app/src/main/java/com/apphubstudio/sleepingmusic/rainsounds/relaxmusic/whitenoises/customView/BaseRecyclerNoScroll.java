package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;

public class BaseRecyclerNoScroll extends RecyclerView {
    public BaseRecyclerNoScroll(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return motionEvent.getAction() != 2 ? super.dispatchTouchEvent(motionEvent) : false;
    }
}
