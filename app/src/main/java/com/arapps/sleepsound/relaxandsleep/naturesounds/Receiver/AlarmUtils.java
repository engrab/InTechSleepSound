package com.arapps.sleepsound.relaxandsleep.naturesounds.Receiver;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.SplashActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.SharedPrefsUtils;
import java.util.Calendar;

public class AlarmUtils {
    public static void nextAlarm(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                AlarmUtils.findNextAlarm(context);
                Log.e("SET_", "DONE");
            }
        }).start();
    }

    public static void findNextAlarm(Context context) {
        cancelBedAlarm(context);
        findAlarm(context);
    }

    public static void finalSetAlarm(Context context, long j) {
        cancelBedAlarm(context);
        setBedAlarm(context, j);
    }

    public static void findAlarm(Context context) {
        setBedAlarm(context, getConfigTime(context, 15000));
    }

    public static void setBedAlarm(Context context, long j) {
        int i = VERSION.SDK_INT;
        String str = Constant.EXTRA_TIME_REMIND;
        if (i >= 21) {
            long timeInMillis = j - Calendar.getInstance().getTimeInMillis();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("remindInterval = ");
            stringBuilder.append(timeInMillis);
            Log.e("remindInterval", stringBuilder.toString());
            if (timeInMillis > 0) {
                ComponentName componentName = new ComponentName(context, RemindService.class);
                PersistableBundle persistableBundle = new PersistableBundle();
                persistableBundle.putLong(str, j);
                JobInfo build = new Builder(10, componentName).setMinimumLatency(timeInMillis).setExtras(persistableBundle).build();
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
                if (jobScheduler != null) {
                    jobScheduler.schedule(build);
                    return;
                }
                return;
            }
            return;
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent();
        intent.setAction(SoundPlayerService.ACTION_UPDATE_BED);
        if (alarmManager != null && j > 0) {
            intent.putExtra(str, j);
            alarmManager.set(0, j, PendingIntent.getBroadcast(context, 0, intent, 134217728));
            Log.e("LOG_TIME", TimeFormatUtils.m18495a(j));
        }
    }

    public static void cancelBedAlarm(Context context) {
        Log.e("CANCEL", "DONE");
        if (VERSION.SDK_INT >= 21) {
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
            if (jobScheduler != null) {
                jobScheduler.cancelAll();
            }
            return;
        }
        Intent intent = new Intent();
        intent.setAction(SoundPlayerService.ACTION_UPDATE_BED);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager != null) {
            alarmManager.cancel(broadcast);
        }
    }

    public static void createBedNotification(Context context, long j) {
        String m18499b = TimeFormatUtils.m18499b(j);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_bed_layout);
        remoteViews.setTextViewText(R.id.bed_time, m18499b);
        remoteViews.setTextViewText(R.id.bed_remind_tv, context.getResources().getString(R.string.bedtime_reminder));
        remoteViews.setTextViewText(R.id.bed_remind_hint_tv, context.getResources().getString(R.string.it_s_time_for_bed));
        Intent intent = new Intent(context, SplashActivity.class);
        intent.putExtra("extra_bedremind", 1);
        intent.setPackage(context.getPackageName());
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
        NotificationUtils notificationUtils = new NotificationUtils(context);
        notificationUtils.getNotificationManager().notify(2021, notificationUtils.createNotification(remoteViews, activity));
    }

    public static void removeBedNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(2021);
        }
    }

    public static long getConfigTime(Context context, int i) {
        System.currentTimeMillis();
        String stringPreference = SharedPrefsUtils.getStringPreference(context, Constant.KEY_ALARM_TIME);
        if (stringPreference == null) {
            stringPreference = "21:00";
        }
        String[] split = stringPreference.split(":");
        i = Integer.parseInt(split[0]);
        int parseInt = Integer.parseInt(split[1]);
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.set(11, i);
        instance2.set(12, parseInt);
        if (instance2.getTimeInMillis() - instance.getTimeInMillis() > 0) {
            return instance2.getTimeInMillis();
        }
        instance2.add(5, 1);
        return instance2.getTimeInMillis();
    }
}
