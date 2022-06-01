package com.arapps.sleepsound.relaxandsleep.naturesounds.Utils;

public class TimeUtils {
    public static String convertMillisecondsToString(long j) {
        int i = (int) (j / 1000);
        int i2 = i / 60;
        i %= 60;
        if (i2 >= 60) {
            return String.format("%d:%02d:%02d", new Object[]{Integer.valueOf(i2 / 60), Integer.valueOf(i2 % 60), Integer.valueOf(i)});
        }
        return String.format("%d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)});
    }
}
