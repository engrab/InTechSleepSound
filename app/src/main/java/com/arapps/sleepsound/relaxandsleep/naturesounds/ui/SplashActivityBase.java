package com.arapps.sleepsound.relaxandsleep.naturesounds.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.arapps.sleepsound.relaxandsleep.naturesounds.baseClasses.ActivityBase;
import com.arapps.sleepsound.relaxandsleep.naturesounds.helper.SoundList;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.AdsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.DisplayUtil;

public class SplashActivityBase extends ActivityBase {
    ImageView imgMoon;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.splash_activity);
        AdsUtils.LoadInterstitial(SplashActivityBase.this);
        this.imgMoon = findViewById(R.id.imageView);
        this.imgMoon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slideup));
        DisplayUtil.hideActionBar(this);
        SoundList.createData(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {

                SplashActivityBase.this.startActivity(new Intent(SplashActivityBase.this, MainActivityBase.class));
                AdsUtils.ShowInterstitial(SplashActivityBase.this);
                SplashActivityBase.this.finish();

            }
        }, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
