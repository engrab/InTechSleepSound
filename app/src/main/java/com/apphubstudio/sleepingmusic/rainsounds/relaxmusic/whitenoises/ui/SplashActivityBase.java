package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.baseClasses.ActivityBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.SoundList;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.DisplayUtil;

public class SplashActivityBase extends ActivityBase {
    ImageView imgMoon;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.splash_activity);

        this.imgMoon = findViewById(R.id.imageView);
        this.imgMoon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slideup));
        DisplayUtil.hideActionBar(this);
        SoundList.createData(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {

                SplashActivityBase.this.startActivity(new Intent(SplashActivityBase.this, MainActivityBase.class));

                SplashActivityBase.this.finish();

            }
        }, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
