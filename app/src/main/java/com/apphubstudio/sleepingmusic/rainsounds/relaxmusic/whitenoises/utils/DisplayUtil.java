package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.AppBarLayout.Behavior;
import com.google.android.material.appbar.AppBarLayout.LayoutParams;

public class DisplayUtil {
    private static DisplayMetrics getDisplayMetrics(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        return identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
    }

    public static int getActionBarHeight(Context context) {
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(16843499, typedValue, true) ? TypedValue.complexToDimensionPixelSize(typedValue.data, resources.getDisplayMetrics()) : 0;
    }

    public static int dpToPx(float f) {
        return (int) (f * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int i) {
        return (int) (((float) i) / Resources.getSystem().getDisplayMetrics().density);
    }

    public static void marginStatusBar(Activity activity, View view, int i) {
        if (VERSION.SDK_INT >= 21) {
            int statusBarHeight = getStatusBarHeight(activity);
            activity.getWindow().setStatusBarColor(i);
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    public static void hideActionBar(AppCompatActivity appCompatActivity) {
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().hide();
        }
    }

    public static void showActionBar(AppCompatActivity appCompatActivity) {
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().show();
        }
    }

    public static void setFullScreenActivity(AppCompatActivity appCompatActivity) {
        appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(1280);
        if (VERSION.SDK_INT >= 21) {
            appCompatActivity.getWindow().setStatusBarColor(1140850688);
        } else if (VERSION.SDK_INT >= 19) {
            appCompatActivity.getWindow().setFlags(67108864, 67108864);
        }
    }

    public static void marginStatusBar(Activity activity, View view) {
        if (VERSION.SDK_INT >= 21) {
            view.setPadding(0, getStatusBarHeight(activity), 0, 0);
        }
    }

    public static void resetMarginStatusBar(Activity activity, View view) {
        if (VERSION.SDK_INT >= 21) {
            view.setPadding(0, 0, 0, 0);
        }
    }

    public static void setStatusColor(Activity activity, int i) {
        if (VERSION.SDK_INT >= 21) {
            if (i == 0) {
                activity.getWindow().setStatusBarColor(1140850688);
            } else {
                activity.getWindow().setStatusBarColor(i);
            }
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        }
    }

    public void turnOffToolbarScrolling(Toolbar toolbar, AppBarLayout appBarLayout) {
        LayoutParams layoutParams = (LayoutParams) toolbar.getLayoutParams();
        layoutParams.setScrollFlags(0);
        toolbar.setLayoutParams(layoutParams);
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        layoutParams2.setBehavior(null);
        appBarLayout.setLayoutParams(layoutParams2);
    }

    public void turnOnToolbarScrolling(Toolbar toolbar, AppBarLayout appBarLayout) {
        LayoutParams layoutParams = (LayoutParams) toolbar.getLayoutParams();
        layoutParams.setScrollFlags(5);
        toolbar.setLayoutParams(layoutParams);
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        layoutParams2.setBehavior(new Behavior());
        appBarLayout.setLayoutParams(layoutParams2);
    }

    public static int m18447a(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int m18448a(Context context, float f, float f2) {
        int screenWidth = getScreenWidth(context);
        int screenHeight = getScreenHeight(context);
        double d = (double) (((float) screenWidth) * f);
        Double.isNaN(d);
        Double.isNaN(d);
        int i = (int) (d * 0.01d);
        d = (double) (((float) screenHeight) * f2);
        Double.isNaN(d);
        Double.isNaN(d);
        screenHeight = (int) (d * 0.01d);
        return screenHeight < i ? screenHeight : i;
    }
}
