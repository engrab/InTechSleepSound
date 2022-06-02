package com.arapps.sleepsound.relaxandsleep.naturesounds.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import java.util.List;

public class ShareUtil {
    public static void share(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TEXT", str2);
        intent.setFlags(268435456);
        context.startActivity(Intent.createChooser(intent, str));
    }

    public static void feedback(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mailto:");
        stringBuilder.append(str);
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(stringBuilder.toString()));
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
            Toast.makeText(context, "No email application", 0).show();
        } else {
            context.startActivity(intent);
        }
    }

    public static void shareLinkApp(Context context) {
        String packageName = context.getPackageName();
        StringBuilder stringBuilder;
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            stringBuilder = new StringBuilder();
            stringBuilder.append("market://details?id=");
            stringBuilder.append(packageName);
            packageName = stringBuilder.toString();
        } catch (Exception unused) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("https://play.google.com/store/apps/details?id=");
            stringBuilder.append(packageName);
            packageName = stringBuilder.toString();
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(packageName));
        intent.addFlags(268959744);
        context.startActivity(intent);
    }
}
