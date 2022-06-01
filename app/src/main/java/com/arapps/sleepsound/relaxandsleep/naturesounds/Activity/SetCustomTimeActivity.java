package com.arapps.sleepsound.relaxandsleep.naturesounds.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import com.shawnlin.numberpicker.NumberPicker;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.SharedPrefsUtils;

public class SetCustomTimeActivity extends BaseActivity implements OnClickListener {
    private Intent intent;
    private LinearLayout mCancelLayout;
    private TextView mCustomTv;
    private NumberPicker mPicker1;
    private AppCompatImageView mSaveView;
    private NumberPicker mSetTimeNpHour;

@Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.set_custom_time_activity);
        InitView();
        DisplayUtil.hideActionBar(this);
    }

    private void InitView() {
        this.mCustomTv = findViewById(R.id.custom_tv);
        this.mCustomTv.setOnClickListener(this);
        this.mSetTimeNpHour = findViewById(R.id.set_time_np_hour);
        this.mSetTimeNpHour.setOnClickListener(this);
        this.mPicker1 = findViewById(R.id.set_time_np_minute);
        this.mPicker1.setOnClickListener(this);
        this.mCancelLayout = findViewById(R.id.cancel_layout);
        this.mCancelLayout.setOnClickListener(this);
        this.mSaveView = findViewById(R.id.save_view);
        this.mSaveView.setOnClickListener(this);
        this.mSetTimeNpHour.setDisplayedValues(getResources().getStringArray(R.array.hour_display));
        this.mPicker1.setDisplayedValues(getResources().getStringArray(R.array.minute_display));
        this.mSetTimeNpHour.setDisplayedValues(getResources().getStringArray(R.array.hour_display));
        this.mPicker1.setDisplayedValues(getResources().getStringArray(R.array.minute_display));
    }
@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_layout:
                finish();
                return;
            case R.id.save_view:
                SharedPrefsUtils.setLongPreference(this, Constant.KEY_PLAY_TIME, (((this.mSetTimeNpHour.getValue() * 60) + this.mPicker1.getValue()) * 60) * 1000);
                this.intent = new Intent(this, SoundPlayerService.class);
                this.intent.setAction(SoundPlayerService.ACTION_CMD);
                this.intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_UPDATE_TIME);
                startService(this.intent);
                finish();
                return;
            default:
                return;
        }
    }
}
