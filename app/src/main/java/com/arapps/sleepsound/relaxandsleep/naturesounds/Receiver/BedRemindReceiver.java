package com.arapps.sleepsound.relaxandsleep.naturesounds.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.ArrayUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.SharedPrefsUtils;

public class BedRemindReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onReceive = ");
        stringBuilder.append(action);
        String str = "BedRemindReceiver";
        Log.e(str, stringBuilder.toString());
        if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.intent.action.TIME_SET".equals(action) || "android.intent.action.TIMEZONE_CHANGED".equals(action) || SoundPlayerService.ACTION_UPDATE_BED.equals(action)) {
            long longExtra = intent.getLongExtra(Constant.EXTRA_TIME_REMIND, 0);
            boolean booleanPreference = SharedPrefsUtils.getBooleanPreference(context, Constant.KEY_ALARM_STATE, false);
            boolean isDayRemind = ArrayUtils.isDayRemind(context);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("remindTime = ");
            stringBuilder2.append(longExtra);
            stringBuilder2.append(" isOpen=");
            stringBuilder2.append(booleanPreference);
            Log.e(str, stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("System.currentTimeMillis() = ");
            stringBuilder2.append(System.currentTimeMillis());
            Log.e(str, stringBuilder2.toString());
            if (Math.abs(longExtra - System.currentTimeMillis()) < 600000 && booleanPreference && isDayRemind) {
                AlarmUtils.createBedNotification(context, longExtra);
            }
            if (booleanPreference) {
                AlarmUtils.nextAlarm(context);
            }
        }
    }
}
