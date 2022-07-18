package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SwipeCustomRefresh extends SwipeRefreshLayout {
    private boolean isdisabled;
    private float mPrevX;
    private int mTouchslop;

    public SwipeCustomRefresh(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTouchslop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void disableInterceptTouchEvent(boolean z) {
        this.isdisabled = z;
        getParent().requestDisallowInterceptTouchEvent(z);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mPrevX = MotionEvent.obtain(motionEvent).getX();
        } else if (action == 2 && (this.isdisabled || Math.abs(motionEvent.getX() - this.mPrevX) > ((float) this.mTouchslop))) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
