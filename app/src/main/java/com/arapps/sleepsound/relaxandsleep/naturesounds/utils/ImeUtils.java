package com.arapps.sleepsound.relaxandsleep.naturesounds.utils;

import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Method;

public class ImeUtils {
    private ImeUtils() {
    }

    public static void showIme(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        try {
            Method method = InputMethodManager.class.getMethod("showSoftInputUnchecked", new Class[]{Integer.TYPE, ResultReceiver.class});
            method.setAccessible(true);
            method.invoke(inputMethodManager, new Object[]{Integer.valueOf(0), null});
        } catch (Exception unused) {
        }
    }

    public static void hideIme(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
