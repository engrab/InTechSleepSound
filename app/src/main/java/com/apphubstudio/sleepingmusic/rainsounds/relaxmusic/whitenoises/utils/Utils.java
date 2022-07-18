package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;

public class Utils {
    public static final Uri getUriToResource(Context context, int i) throws NotFoundException {
        Resources resources = context.getResources();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("android.resource://");
        stringBuilder.append(resources.getResourcePackageName(i));
        stringBuilder.append('/');
        stringBuilder.append(resources.getResourceTypeName(i));
        stringBuilder.append('/');
        stringBuilder.append(resources.getResourceEntryName(i));
        return Uri.parse(stringBuilder.toString());
    }
}
