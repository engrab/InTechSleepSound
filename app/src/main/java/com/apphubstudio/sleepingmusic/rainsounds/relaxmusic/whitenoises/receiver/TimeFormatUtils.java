package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.receiver;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFormatUtils {

    public static long m18492a(int i, int i2) {
        Date parse;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(m18496b());
            stringBuilder.append(" ");
            stringBuilder.append(i);
            stringBuilder.append(":");
            stringBuilder.append(i2);
            stringBuilder.append(":0");
            parse = simpleDateFormat.parse(stringBuilder.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ParseException = ");
            stringBuilder2.append(e.toString());
            Log.e("ERR", stringBuilder2.toString());
            parse = null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);
        return instance.getTimeInMillis();
    }

    public static String m18496b() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    public static String m18494a(int i) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        instance.add(5, i);
        return simpleDateFormat.format(instance.getTime());
    }

    public static long m18493a(String str) {
        Date parse;
        try {
            parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ParseException = ");
            stringBuilder.append(e.toString());
            Log.e("ERR", stringBuilder.toString());
            parse = null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);
        return instance.getTimeInMillis();
    }

    public static String m18495a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public static String m18499b(long j) {
        return new SimpleDateFormat("HH:mm").format(new Date(j));
    }

    public static String m18497b(int i) {
        int i2 = i % 60;
        i /= 60;
        int i3 = i / 60;
        i %= 60;
        String str = ":";
        String str2 = "0";
        String valueOf;
        String valueOf2;
        if (i3 > 0) {
            String valueOf3;
            StringBuilder stringBuilder;
            if (i3 >= 10) {
                valueOf3 = String.valueOf(i3);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(i3);
                valueOf3 = stringBuilder.toString();
            }
            if (i >= 10) {
                valueOf = String.valueOf(i);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(i);
                valueOf = stringBuilder.toString();
            }
            if (i2 >= 10) {
                valueOf2 = String.valueOf(i2);
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append(i2);
                valueOf2 = stringBuilder2.toString();
            }
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(valueOf3);
            stringBuilder3.append(str);
            stringBuilder3.append(valueOf);
            stringBuilder3.append(str);
            stringBuilder3.append(valueOf2);
            return stringBuilder3.toString();
        }
        StringBuilder stringBuilder4;
        if (i >= 10) {
            valueOf = String.valueOf(i);
        } else {
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append(str2);
            stringBuilder4.append(i);
            valueOf = stringBuilder4.toString();
        }
        if (i2 >= 10) {
            valueOf2 = String.valueOf(i2);
        } else {
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append(str2);
            stringBuilder4.append(i2);
            valueOf2 = stringBuilder4.toString();
        }
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append(valueOf);
        stringBuilder4.append(str);
        stringBuilder4.append(valueOf2);
        return stringBuilder4.toString();
    }

    public static String m18498b(int i, int i2) {
        String valueOf;
        String valueOf2;
        String str = "0";
        if (i >= 10) {
            valueOf = String.valueOf(i);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(i);
            valueOf = stringBuilder.toString();
        }
        if (i2 >= 10) {
            valueOf2 = String.valueOf(i2);
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(i2);
            valueOf2 = stringBuilder2.toString();
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(valueOf);
        stringBuilder3.append(":");
        stringBuilder3.append(valueOf2);
        return stringBuilder3.toString();
    }
}
