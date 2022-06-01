package com.arapps.sleepsound.relaxandsleep.naturesounds.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    static final SimpleDateFormat sBaseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static final SimpleDateFormat sDateFormat = new SimpleDateFormat("dd MM yyyy");
    static final SimpleDateFormat sLogDateFormat = new SimpleDateFormat("HH:mm:ss-dd-MM-yyyy");
    static final SimpleDateFormat sTimeFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy");

    public static synchronized String getDateFormat(String str) {
        String str2;
        synchronized (CommonUtils.class) {
            str2 = "";
            try {
                str2 = sDateFormat.format(sBaseFormat.parse(str));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public static synchronized String getDateTimeFormat(String str) {
        String str2;
        synchronized (CommonUtils.class) {
            str2 = "";
            try {
                str2 = sTimeFormat.format(sBaseFormat.parse(str));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public static synchronized String getDateForLog() {
        String format;
        synchronized (CommonUtils.class) {
            format = sLogDateFormat.format(new Date());
        }
        return format;
    }

    public static synchronized String getTimeFromMiliSecond(int i) {
        String stringBuilder;
        synchronized (CommonUtils.class) {
            String stringBuilder2;
            StringBuilder stringBuilder3;
            int i2 = (i / 1000) / 60;
            i = (i / 1000) % 60;
            StringBuilder stringBuilder4;
            if (i2 < 10) {
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("0");
                stringBuilder4.append(i2);
                stringBuilder2 = stringBuilder4.toString();
            } else {
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("");
                stringBuilder4.append(i2);
                stringBuilder2 = stringBuilder4.toString();
            }
            if (i < 10) {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("0");
                stringBuilder3.append(i);
                stringBuilder = stringBuilder3.toString();
            } else {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("");
                stringBuilder3.append(i);
                stringBuilder = stringBuilder3.toString();
            }
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("");
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append(":");
            stringBuilder3.append(stringBuilder);
            stringBuilder = stringBuilder3.toString();
        }
        return stringBuilder;
    }
}
