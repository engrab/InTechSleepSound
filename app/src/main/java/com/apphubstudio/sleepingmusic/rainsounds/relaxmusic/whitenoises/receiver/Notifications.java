package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.receiver;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.RemoteViews;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;

public class Notifications extends ContextWrapper {
    private NotificationManager notificationManager;

    public Notifications(Context context) {
        super(context);
        if (VERSION.SDK_INT >= 26) {
            String str = "";
            NotificationChannel notificationChannel = new NotificationChannel(str, context.getResources().getString(R.string.app_name), 2);
            notificationChannel.setLockscreenVisibility(1);
            getNotificationManager().createNotificationChannel(notificationChannel);
            notificationChannel = new NotificationChannel(str, context.getResources().getString(R.string.app_name), 4);
            notificationChannel.setLockscreenVisibility(1);
            getNotificationManager().createNotificationChannel(notificationChannel);
        }
    }

    public Notification createNotificationMax(RemoteViews remoteViews, PendingIntent pendingIntent) {
        Builder builder;
        if (VERSION.SDK_INT >= 26) {
            builder = new Builder(getApplicationContext(), "");
        } else {
            builder = new Builder(getApplicationContext());
        }
        try {
            return builder.setSmallIcon(R.drawable.notification_icon).setContentIntent(pendingIntent).setContent(remoteViews).setPriority(2).setOngoing(true).setAutoCancel(false).build();
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Exception ");
            stringBuilder.append(e.toString());
            Log.e("Exception", stringBuilder.toString());
            return null;
        }
    }

    public Notification createNotification(RemoteViews remoteViews, PendingIntent pendingIntent) {
        Builder builder;
        if (VERSION.SDK_INT >= 26) {
            builder = new Builder(getApplicationContext(), "");
        } else {
            builder = new Builder(getApplicationContext());
        }
        return builder.setSmallIcon(R.drawable.notification_icon).setContentIntent(pendingIntent).setContent(remoteViews).setPriority(2).setOngoing(false).setAutoCancel(true).setDefaults(3).build();
    }

    public NotificationManager getNotificationManager() {
        if (this.notificationManager == null) {
            this.notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return this.notificationManager;
    }
}
