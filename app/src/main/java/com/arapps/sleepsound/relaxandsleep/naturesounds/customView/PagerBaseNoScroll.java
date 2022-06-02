package com.arapps.sleepsound.relaxandsleep.naturesounds.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class PagerBaseNoScroll extends ViewPager {
    private boolean isTouch = false;

    public PagerBaseNoScroll(Context context) {
        super(context);
    }

    public PagerBaseNoScroll(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.isTouch && super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.isTouch && super.onInterceptTouchEvent(motionEvent);
    }

    public void setPagingEnabled(boolean z) {
        this.isTouch = z;
    }

    public boolean canScrollHorizontally(int i) {
        if (this.isTouch) {
            return super.canScrollHorizontally(i);
        }
        return false;
    }
}
