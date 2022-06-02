package com.arapps.sleepsound.relaxandsleep.naturesounds.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.baseClasses.ActivityBase;
import com.arapps.sleepsound.relaxandsleep.naturesounds.helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.SharedPrefsUtils;

public class SetTimeActivityBase extends ActivityBase implements OnClickListener {
    private Intent intent;
    private TextView mSetTimeCustomTv;
    private TextView mSetTimeOffTv;
    private TextView mSetTimeTv1;
    private TextView mSetTimeTv2;
    private TextView mSetTimeTv3;
    private TextView mSetTimeTv4;
    private TextView mSetTimer;
    private LinearLayout mSetTimeCancelLayout;
    private LinearLayout mTimeListLayout;

   @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.set_time_activity);
        InitView();
        DisplayUtil.hideActionBar(this);
    }

    private void InitView() {
        mSetTimer = findViewById(R.id.set_timer);
        mSetTimer.setOnClickListener(this);
        mSetTimeTv1 = findViewById(R.id.set_time_tv1);
        mSetTimeTv1.setOnClickListener(this);
        mSetTimeTv2 = findViewById(R.id.set_time_tv2);
        mSetTimeTv2.setOnClickListener(this);
        mSetTimeTv3 = findViewById(R.id.set_time_tv3);
        mSetTimeTv3.setOnClickListener(this);
        mSetTimeTv4 = findViewById(R.id.set_time_tv4);
        mSetTimeTv4.setOnClickListener(this);
        mSetTimeCustomTv = findViewById(R.id.set_time_custom_tv);
        mSetTimeCustomTv.setOnClickListener(this);
        mSetTimeOffTv = findViewById(R.id.set_time_off_tv);
        mSetTimeOffTv.setOnClickListener(this);
        mTimeListLayout = findViewById(R.id.time_list_layout);
        mTimeListLayout.setOnClickListener(this);
        mSetTimeCancelLayout = findViewById(R.id.set_time_cancel_layout);
        mSetTimeCancelLayout.setOnClickListener(this);
        mSetTimeTv1.setText(getString(R.string.set_time_mins, "15"));
        mSetTimeTv2.setText(getString(R.string.set_time_mins, "30"));
        mSetTimeTv3.setText(getString(R.string.set_time_one_hour));
        mSetTimeTv4.setText(getString(R.string.set_time_hours, "2"));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.time_list_layout) {
            switch (id) {
                case R.id.set_time_cancel_layout:
                    finish();
                    return;
                case R.id.set_time_custom_tv:
                    this.intent = new Intent(this, SetCustomTimeActivityBase.class);
                    startActivity(this.intent);
                    finish();
                    return;
                default:
                    String str = SoundPlayerService.CMD_UPDATE_TIME;
                    String str2 = SoundPlayerService.CMD_NAME;
                    String str3 = SoundPlayerService.ACTION_CMD;
                    String str4 = Constant.KEY_PLAY_TIME;
                    switch (id) {
                        case R.id.set_time_off_tv:
                            SharedPrefsUtils.setLongPreference(this, str4, -1);
                            this.intent = new Intent(this, SoundPlayerService.class);
                            this.intent.setAction(str3);
                            this.intent.putExtra(str2, str);
                            startService(this.intent);
                            finish();
                            return;
                        case R.id.set_time_tv1:
                            SharedPrefsUtils.setLongPreference(this, str4, 900000);
                            this.intent = new Intent(this, SoundPlayerService.class);
                            this.intent.setAction(str3);
                            this.intent.putExtra(str2, str);
                            startService(this.intent);
                            finish();
                            return;
                        case R.id.set_time_tv2:
                            SharedPrefsUtils.setLongPreference(this, str4, 1800000);
                            this.intent = new Intent(this, SoundPlayerService.class);
                            this.intent.setAction(str3);
                            this.intent.putExtra(str2, str);
                            startService(this.intent);
                            finish();
                            return;
                        case R.id.set_time_tv3:
                            SharedPrefsUtils.setLongPreference(this, str4, 3600000);
                            this.intent = new Intent(this, SoundPlayerService.class);
                            this.intent.setAction(str3);
                            this.intent.putExtra(str2, str);
                            startService(this.intent);
                            finish();
                            return;
                        case R.id.set_time_tv4:
                            SharedPrefsUtils.setLongPreference(this, str4, 7200000);
                            this.intent = new Intent(this, SoundPlayerService.class);
                            this.intent.setAction(str3);
                            this.intent.putExtra(str2, str);
                            startService(this.intent);
                            finish();
                            return;
                        default:
                            return;
                    }
            }
        }
    }
}
