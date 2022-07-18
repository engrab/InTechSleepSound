package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

import com.shawnlin.numberpicker.NumberPicker;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.Constant;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.DisplayUtil;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.SharedPrefsUtils;

public class SetBedTimeActivity extends AppCompatActivity implements OnClickListener {
    private NumberPicker mPicker1;
    private NumberPicker mSetTimeNpHour;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.set_bed_time_activity);
        findViewById(R.id.custom_tv).setOnClickListener(this);
        this.mSetTimeNpHour = findViewById(R.id.set_time_np_hour);
        this.mSetTimeNpHour.setOnClickListener(this);
        this.mPicker1 = findViewById(R.id.set_time_np_minute);
        this.mPicker1.setOnClickListener(this);
        findViewById(R.id.cancel_layout).setOnClickListener(this);
        findViewById(R.id.save_view).setOnClickListener(this);
        String stringPreference = SharedPrefsUtils.getStringPreference(this, Constant.KEY_ALARM_TIME);
        if (stringPreference == null) {
            stringPreference = "21:00";
        }
        String[] split = stringPreference.split(":");
        if (mSetTimeNpHour != null)
            this.mSetTimeNpHour.setDisplayedValues(SetBedTimeActivity.this.getResources().getStringArray(R.array.hour_display));
        this.mPicker1.setDisplayedValues(getResources().getStringArray(R.array.minute_display));
        this.mSetTimeNpHour.setValue(Integer.parseInt(split[0]));
        this.mPicker1.setValue(Integer.parseInt(split[1]));
        DisplayUtil.hideActionBar(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cancel_layout) {
            finish();
        } else if (id == R.id.save_view) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mSetTimeNpHour.getValue());
            stringBuilder.append(":");
            stringBuilder.append(this.mPicker1.getValue());
            SharedPrefsUtils.setStringPreference(this, Constant.KEY_ALARM_TIME, stringBuilder.toString());
            finish();
        }
    }
}
