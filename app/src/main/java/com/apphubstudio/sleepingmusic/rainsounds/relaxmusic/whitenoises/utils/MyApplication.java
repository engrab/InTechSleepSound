package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ads.OpenAds;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.SoundList;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApplication extends MultiDexApplication {
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        SoundList.createData(this);
        MobileAds.initialize(MyApplication.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        new OpenAds(MyApplication.this);
    }
}
