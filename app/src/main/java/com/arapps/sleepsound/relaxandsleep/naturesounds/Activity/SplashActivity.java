package com.arapps.sleepsound.relaxandsleep.naturesounds.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.AdsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;

public class SplashActivity extends BaseActivity {
    ImageView imgMoon;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.splash_activity);
        AdsUtils.LoadInterstitial(SplashActivity.this);
        this.imgMoon = findViewById(R.id.imageView);
        this.imgMoon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slideup));
        DisplayUtil.hideActionBar(this);
        SoundListDataSource.createData(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {

                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                AdsUtils.ShowInterstitial(SplashActivity.this);
                SplashActivity.this.finish();

            }
        }, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
