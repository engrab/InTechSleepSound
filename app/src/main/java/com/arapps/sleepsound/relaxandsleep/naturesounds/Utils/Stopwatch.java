package com.arapps.sleepsound.relaxandsleep.naturesounds.Utils;

import android.util.Log;

public class Stopwatch {
    private String name;
    private long startTime = 0;
    private long stopTime = 0;

    private Stopwatch(String str) {
        restart();
        this.name = str;
    }

    private long lap() {
        long j = this.stopTime;
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        return j - this.startTime;
    }

    private void logLap(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(Thread.currentThread().getName());
        stringBuilder.append(" #");
        stringBuilder.append(Thread.currentThread().getId());
        stringBuilder.append("] STOPWATCH \"");
        stringBuilder.append(this.name);
        stringBuilder.append("\"");
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str);
        stringBuilder3.append(" (in ");
        stringBuilder3.append(lap());
        stringBuilder3.append("ms)");
        Log.e(stringBuilder2, stringBuilder3.toString());
    }

    private void restart() {
        this.startTime = System.currentTimeMillis();
        this.stopTime = 0;
    }

    public static Stopwatch start(String str) {
        return new Stopwatch(str);
    }

    public void logNewLap(String str) {
        logLap(str);
        restart();
    }

    public void stop() {
        this.stopTime = System.currentTimeMillis();
    }
}
