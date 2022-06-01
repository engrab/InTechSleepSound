package com.arapps.sleepsound.relaxandsleep.naturesounds.Utils;

import androidx.multidex.MultiDexApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;

public class MyApplication extends MultiDexApplication {
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        SoundListDataSource.createData(this);
    }
}
