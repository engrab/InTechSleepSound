package com.arapps.sleepsound.relaxandsleep.naturesounds.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat.Builder;
import androidx.core.app.NotificationManagerCompat;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.MainActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.SplashActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.TimeUtils;

public class NotificationHandler {
    private static final String CHANNEL_ID = "com.arapps.sleepsound.relaxandsleep.naturesounds.Notification";
    private static Builder builder = null;
    public static final int notificationID = 1127;

    private static void createChannel(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        NotificationChannel notificationChannel = null;
        if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ID, "Sleep sound playback", 2);
        }
        if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel.setDescription("Sleep sound playback controls");
        }
        if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel.setShowBadge(false);
        }
        if (notificationChannel != null) {
            if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel.setLockscreenVisibility(1);
            }
        }
        if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public static void buildNotification(SoundPlayerService soundPlayerService) {
        String str = "NOTIFICATION";
        if (soundPlayerService == null) {
            Log.e(str, "NULL");
            return;
        }
        Log.e(str, "OK");
        RemoteViews remoteViews = new RemoteViews(soundPlayerService.getPackageName(), R.layout.notification_sound_timing_layout);
        Intent intent = new Intent(soundPlayerService, MainActivity.class);
        intent.putExtra(SoundPlayerService.ACTION_OPEN_FROM_NOTIFI, true);
        intent.addFlags(67108864);
        PendingIntent activity = PendingIntent.getActivity(soundPlayerService, 0, intent, 134217728);
        if (VERSION.SDK_INT >= 26) {
            createChannel(soundPlayerService);
        }
        builder = new Builder(soundPlayerService, CHANNEL_ID).setCustomContentView(remoteViews).setVisibility(1);
        remoteViews.setOnClickPendingIntent(R.id.sound_notification_layout, activity);
        if (soundPlayerService.getSoundPlayerManager().getMixItem() != null) {
            remoteViews.setTextViewText(R.id.sound_name, soundPlayerService.getSoundPlayerManager().getMixItem().getName());
        }
        long timeToStopSound = soundPlayerService.getTimeToStopSound();
        if (timeToStopSound > 0) {
            remoteViews.setTextViewText(R.id.count_down_tv, TimeUtils.convertMillisecondsToString(timeToStopSound));
        } else {
            remoteViews.setTextViewText(R.id.count_down_tv, soundPlayerService.getResources().getString(R.string.timer));
        }
        if (soundPlayerService.getSoundPlayerManager().getPlayerState() == 1) {
            builder.setSmallIcon(R.drawable.notification_icon);
            builder.setOngoing(true);
            remoteViews.setImageViewResource(R.id.play_control_iv_1, R.drawable.vector_ic_pause);
        } else {
            builder.setSmallIcon(R.drawable.notification_icon);
            builder.setOngoing(false);
            remoteViews.setImageViewResource(R.id.play_control_iv_1, R.drawable.vector_ic_play);
        }
        controls(remoteViews, soundPlayerService);
        NotificationManagerCompat.from(soundPlayerService).notify(notificationID, builder.build());
    }

    private static void controls(RemoteViews remoteViews, SoundPlayerService soundPlayerService) {
        Intent intent = new Intent(soundPlayerService, SoundPlayerService.class);
        intent.setAction(SoundPlayerService.ACTION_CMD);
        String str = SoundPlayerService.CMD_NAME;
        intent.putExtra(str, SoundPlayerService.CMD_NOTIFICATION_PLAY);
        PendingIntent service = PendingIntent.getService(soundPlayerService, 0, intent, 0);
        Intent intent2 = new Intent(soundPlayerService, SoundPlayerService.class);
        intent2.setAction("ACTION_CMD_1");
        intent2.putExtra(str, SoundPlayerService.CMD_NOTIFICATION_CLOSE);
        PendingIntent service2 = PendingIntent.getService(soundPlayerService, 0, intent2, 0);
        remoteViews.setOnClickPendingIntent(R.id.play_control_iv_1, service);
        remoteViews.setOnClickPendingIntent(R.id.close_iv_1, service2);
    }

    private static PendingIntent onButtonNotificationClick(int i, Context context) {
        Intent intent = new Intent(SoundPlayerService.ACTION_CMD);
        intent.putExtra(SoundPlayerService.CMD_NAME, i);
        return PendingIntent.getBroadcast(context, i, intent, 0);
    }

    public static void removeNotification(Context context) {
        String str = "REMOVE";
        Log.e(str, str);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(notificationID);
        }
    }

    public static void buildBedNotification(Context context) {
        String str = "NOTIFICATION";
        if (context == null) {
            Log.e(str, "NULL");
            return;
        }
        Log.e(str, "OK");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_bed_layout);
        Intent intent = new Intent(context, SplashActivity.class);
        intent.addFlags(67108864);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
        if (VERSION.SDK_INT >= 26) {
            createChannel(context);
        }
        Builder visibility = new Builder(context, CHANNEL_ID).setWhen(System.currentTimeMillis()).setCategory("android.intent.category.DEFAULT").setPriority(2).setShowWhen(true).setAutoCancel(true).setContent(remoteViews).setSmallIcon(R.drawable.notification_icon).setVisibility(1);
        visibility.setSound(RingtoneManager.getDefaultUri(2));
        visibility.setContentIntent(activity);
        NotificationManagerCompat.from(context).notify(notificationID, visibility.build());
    }

    public static void update(SoundPlayerService soundPlayerService) {
        String str = "NOTIFICATION";
        if (soundPlayerService == null || builder == null) {
            Log.e(str, "NULL");
            return;
        }
        Log.e(str, "OK");
        RemoteViews remoteViews = new RemoteViews(soundPlayerService.getPackageName(), R.layout.notification_sound_timing_layout);
        Intent intent = new Intent(soundPlayerService, MainActivity.class);
        intent.putExtra(SoundPlayerService.ACTION_OPEN_FROM_NOTIFI, true);
        intent.addFlags(67108864);
        PendingIntent activity = PendingIntent.getActivity(soundPlayerService, 0, intent, 134217728);
        if (VERSION.SDK_INT >= 26) {
            createChannel(soundPlayerService);
        }
        remoteViews.setOnClickPendingIntent(R.id.sound_notification_layout, activity);
        if (soundPlayerService.getSoundPlayerManager().getMixItem() != null) {
            remoteViews.setTextViewText(R.id.sound_name, soundPlayerService.getSoundPlayerManager().getMixItem().getName());
        }
        long timeToStopSound = soundPlayerService.getTimeToStopSound();
        if (timeToStopSound > 0) {
            remoteViews.setTextViewText(R.id.count_down_tv, TimeUtils.convertMillisecondsToString(timeToStopSound));
        } else {
            remoteViews.setTextViewText(R.id.count_down_tv, soundPlayerService.getResources().getString(R.string.timer));
        }
        if (soundPlayerService.getSoundPlayerManager().getPlayerState() == 1) {
            builder.setSmallIcon(R.drawable.notification_icon);
            builder.setOngoing(true);
            remoteViews.setImageViewResource(R.id.play_control_iv_1, R.drawable.vector_ic_pause);
        } else {
            builder.setSmallIcon(R.drawable.notification_icon);
            builder.setOngoing(false);
            remoteViews.setImageViewResource(R.id.play_control_iv_1, R.drawable.vector_ic_play);
        }
        builder.setContent(remoteViews);
        controls(remoteViews, soundPlayerService);
        NotificationManagerCompat.from(soundPlayerService).notify(notificationID, builder.build());
    }
}
