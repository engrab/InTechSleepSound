package com.arapps.sleepsound.relaxandsleep.naturesounds.utils;

import androidx.multidex.MultiDexApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.arapps.sleepsound.relaxandsleep.naturesounds.helper.SoundList;

public class MyApplication extends MultiDexApplication {
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        SoundList.createData(this);
    }
}
