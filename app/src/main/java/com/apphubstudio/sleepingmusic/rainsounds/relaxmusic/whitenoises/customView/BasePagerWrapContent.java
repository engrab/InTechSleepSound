package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class BasePagerWrapContent extends ViewPager {
    private static final String TAG = BasePagerWrapContent.class.getSimpleName();
    private boolean isAnimHeight;
    private int deHeight = 0;
    private int height = 0;
    private int lHeight;
    private int rHeight;
    private int pos = -1;
    private int wMeasurSpec;

    public BasePagerWrapContent(Context context) {
        super(context);
        init();
    }

    public BasePagerWrapContent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        addOnPageChangeListener(new OnPageChangeListener() {
            public int state;

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (this.state == 0) {
                    BasePagerWrapContent.this.height = 0;
                    String access$100 = BasePagerWrapContent.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onPageSelected:");
                    stringBuilder.append(i);
                    Log.d(access$100, stringBuilder.toString());
                }
            }

            public void onPageScrollStateChanged(int i) {
                this.state = i;
            }
        });
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (pagerAdapter instanceof ListenerObjectPosition) {
            this.height = 0;
            super.setAdapter(pagerAdapter);
            return;
        }
        throw new IllegalArgumentException("WrapContentViewPage requires that PagerAdapter will implement ObjectAtPositionInterface");
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.wMeasurSpec = i;
        int mode = MeasureSpec.getMode(i2);
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            if (this.height == 0) {
                this.deHeight = 0;
                for (mode = 0; mode < getChildCount(); mode++) {
                    View childAt = getChildAt(mode);
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    if (layoutParams != null && layoutParams.isDecor) {
                        int i3 = layoutParams.gravity & 112;
                        Object obj = (i3 == 48 || i3 == 80) ? 1 : null;
                        if (obj != null) {
                            this.deHeight += childAt.getMeasuredHeight();
                        }
                    }
                }
                View viewAtPosition = getViewAtPosition(getCurrentItem());
                if (viewAtPosition != null) {
                    this.height = measureViewHeight(viewAtPosition);
                }
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onMeasure height:");
                stringBuilder.append(this.height);
                stringBuilder.append(" decor:");
                stringBuilder.append(this.deHeight);
                Log.d(str, stringBuilder.toString());
            }
            i2 = ((this.height + this.deHeight) + getPaddingBottom()) + getPaddingTop();
            mode = MeasureSpec.makeMeasureSpec(i2, 1073741824);
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("onMeasure total height:");
            stringBuilder2.append(i2);
            Log.d(str2, stringBuilder2.toString());
            i2 = mode;
        }
        super.onMeasure(i, i2);
    }

    public void onPageScrolled(int i, float f, int i2) {
        StringBuilder stringBuilder;
        super.onPageScrolled(i, f, i2);
        if (this.pos != i) {
            this.pos = i;
            View viewAtPosition = getViewAtPosition(i);
            View viewAtPosition2 = getViewAtPosition(i + 1);
            if (viewAtPosition == null || viewAtPosition2 == null) {
                this.isAnimHeight = false;
            } else {
                this.lHeight = measureViewHeight(viewAtPosition);
                this.rHeight = measureViewHeight(viewAtPosition2);
                this.isAnimHeight = true;
                String str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("onPageScrolled heights left:");
                stringBuilder.append(this.lHeight);
                stringBuilder.append(" right:");
                stringBuilder.append(this.rHeight);
                Log.d(str, stringBuilder.toString());
            }
        }
        if (this.isAnimHeight) {
            i = (int) ((((float) this.lHeight) * (1.0f - f)) + (((float) this.rHeight) * f));
            if (this.height != i) {
                String str2 = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("onPageScrolled height change:");
                stringBuilder.append(i);
                Log.d(str2, stringBuilder.toString());
                this.height = i;
                requestLayout();
                invalidate();
            }
        }
    }

    private int measureViewHeight(View view) {
        view.measure(getChildMeasureSpec(this.wMeasurSpec, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        return view.getMeasuredHeight();
    }

    /* Access modifiers changed, original: protected */
    public View getViewAtPosition(int i) {
        if (getAdapter() != null) {
            Object objectAtPosition = ((ListenerObjectPosition) getAdapter()).getObjectAtPosition(i);
            if (objectAtPosition != null) {
                for (int i2 = 0; i2 < getChildCount(); i2++) {
                    View childAt = getChildAt(i2);
                    if (childAt != null && getAdapter().isViewFromObject(childAt, objectAtPosition)) {
                        return childAt;
                    }
                }
            }
        }
        return null;
    }
}
