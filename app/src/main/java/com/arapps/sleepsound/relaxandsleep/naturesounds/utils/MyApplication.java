package com.arapps.sleepsound.relaxandsleep.naturesounds.utils;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import com.arapps.sleepsound.relaxandsleep.naturesounds.ads.OpenAds;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.arapps.sleepsound.relaxandsleep.naturesounds.helper.SoundList;
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
