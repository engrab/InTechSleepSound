package com.arapps.sleepsound.relaxandsleep.naturesounds.Utils;

import android.content.Context;
import android.util.SparseArray;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ArrayUtils {
    public static List<SoundModel> copy(List<SoundModel> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (SoundModel soundModel : list) {
            arrayList.add(new SoundModel(soundModel));
        }
        return arrayList;
    }

    public static <C> List<C> asList(SparseArray<C> sparseArray) {
        if (sparseArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(sparseArray.valueAt(i));
        }
        return arrayList;
    }

    public static void saveDayCheck(Context context, boolean[] zArr) {
        String str = "";
        for (int i = 0; i < zArr.length; i++) {
            StringBuilder stringBuilder;
            if (i < zArr.length - 1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(zArr[i]);
                stringBuilder.append(";");
                str = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(zArr[i]);
                str = stringBuilder.toString();
            }
        }
        SharedPrefsUtils.setStringPreference(context, Constant.KEY_ALARM_DAY, str);
    }

    public static boolean[] parseDayCheck(Context context) {
        boolean[] zArr = new boolean[7];
        String stringPreference = SharedPrefsUtils.getStringPreference(context, Constant.KEY_ALARM_DAY);
        if (stringPreference == null) {
            stringPreference = "";
        }
        String[] split = stringPreference.split(";");
        int i = 0;
        if (split.length == 7) {
            while (i < 7) {
                zArr[i] = Boolean.parseBoolean(split[i]);
                i++;
            }
        } else {
            for (int i2 = 0; i2 < 7; i2++) {
                zArr[i2] = false;
            }
        }
        return zArr;
    }

    public static boolean isDayRemind(Context context) {
        if (parseDayCheck(context)[Calendar.getInstance().get(7) - 1]) {
            return true;
        }
        return false;
    }
}
