package com.arapps.sleepsound.relaxandsleep.naturesounds.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.LayoutParams;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

public class WrapContentViewPager extends ViewPager {
    private static final String TAG = WrapContentViewPager.class.getSimpleName();
    private boolean animateHeight;
    private int decorHeight = 0;
    private int height = 0;
    private int leftHeight;
    private int rightHeight;
    private int scrollingPosition = -1;
    private int widthMeasuredSpec;

    public WrapContentViewPager(Context context) {
        super(context);
        init();
    }

    public WrapContentViewPager(Context context, AttributeSet attributeSet) {
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
                    WrapContentViewPager.this.height = 0;
                    String access$100 = WrapContentViewPager.TAG;
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
        if (pagerAdapter instanceof ObjectAtPositionInterface) {
            this.height = 0;
            super.setAdapter(pagerAdapter);
            return;
        }
        throw new IllegalArgumentException("WrapContentViewPage requires that PagerAdapter will implement ObjectAtPositionInterface");
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.widthMeasuredSpec = i;
        int mode = MeasureSpec.getMode(i2);
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            if (this.height == 0) {
                this.decorHeight = 0;
                for (mode = 0; mode < getChildCount(); mode++) {
                    View childAt = getChildAt(mode);
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    if (layoutParams != null && layoutParams.isDecor) {
                        int i3 = layoutParams.gravity & 112;
                        Object obj = (i3 == 48 || i3 == 80) ? 1 : null;
                        if (obj != null) {
                            this.decorHeight += childAt.getMeasuredHeight();
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
                stringBuilder.append(this.decorHeight);
                Log.d(str, stringBuilder.toString());
            }
            i2 = ((this.height + this.decorHeight) + getPaddingBottom()) + getPaddingTop();
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
        if (this.scrollingPosition != i) {
            this.scrollingPosition = i;
            View viewAtPosition = getViewAtPosition(i);
            View viewAtPosition2 = getViewAtPosition(i + 1);
            if (viewAtPosition == null || viewAtPosition2 == null) {
                this.animateHeight = false;
            } else {
                this.leftHeight = measureViewHeight(viewAtPosition);
                this.rightHeight = measureViewHeight(viewAtPosition2);
                this.animateHeight = true;
                String str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("onPageScrolled heights left:");
                stringBuilder.append(this.leftHeight);
                stringBuilder.append(" right:");
                stringBuilder.append(this.rightHeight);
                Log.d(str, stringBuilder.toString());
            }
        }
        if (this.animateHeight) {
            i = (int) ((((float) this.leftHeight) * (1.0f - f)) + (((float) this.rightHeight) * f));
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
        view.measure(getChildMeasureSpec(this.widthMeasuredSpec, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), MeasureSpec.makeMeasureSpec(0, 0));
        return view.getMeasuredHeight();
    }

    /* Access modifiers changed, original: protected */
    public View getViewAtPosition(int i) {
        if (getAdapter() != null) {
            Object objectAtPosition = ((ObjectAtPositionInterface) getAdapter()).getObjectAtPosition(i);
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
