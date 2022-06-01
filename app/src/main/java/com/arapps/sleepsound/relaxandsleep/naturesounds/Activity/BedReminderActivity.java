package com.arapps.sleepsound.relaxandsleep.naturesounds.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Receiver.AlarmUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.ArrayUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.SharedPrefsUtils;

public class BedReminderActivity extends BaseActivity implements OnClickListener {
    private boolean[] dayChecked = new boolean[7];
    private TextView mBedRemindDay1;
    private TextView mBedRemindDay2;
    private TextView mBedRemindDay3;
    private TextView mBedRemindDay4;
    private TextView mBedRemindDay5;
    private TextView mBedRemindDay6;
    private TextView mBedRemindDay7;
    private AppCompatImageView mBedRemindSelect;
    private LinearLayout mBedRemindSetTimeLayout;
    private AppCompatImageView mBedRemindSetTimeView;
    private SwitchCompat mBedRemindSwitch;
    private TextView mBedReminderTimeTv;
    private TextView mSetRepeatTv;
    private Toolbar mToolbar;
    private final TextView[] textViews = new TextView[7];
    TextView[] textViewArr;
    int i = 0;

@Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.bed_reminder_activity_);
        this.dayChecked = ArrayUtils.parseDayCheck(this);
        InitView();
        DisplayUtil.hideActionBar(this);
    }

    private void InitView() {
        this.mToolbar = findViewById(R.id.toolbar);
        this.mBedRemindSwitch = findViewById(R.id.bed_remind_switch);
        this.mBedRemindSetTimeView = findViewById(R.id.bed_remind_set_time_view);
        this.mBedReminderTimeTv = findViewById(R.id.bed_reminder_time_tv);
        this.mBedRemindSetTimeLayout = findViewById(R.id.bed_remind_set_time_layout);
        this.mSetRepeatTv = findViewById(R.id.set_repeat_tv);
        this.mBedRemindDay1 = findViewById(R.id.bed_remind_day_1);
        this.mBedRemindDay2 = findViewById(R.id.bed_remind_day_2);
        this.mBedRemindDay3 = findViewById(R.id.bed_remind_day_3);
        this.mBedRemindDay4 = findViewById(R.id.bed_remind_day_4);
        this.mBedRemindDay5 = findViewById(R.id.bed_remind_day_5);
        this.mBedRemindDay6 = findViewById(R.id.bed_remind_day_6);
        this.mBedRemindDay7 = findViewById(R.id.bed_remind_day_7);
        this.mBedRemindSelect = findViewById(R.id.bed_remind_select);
        this.mBedRemindSetTimeView.setOnClickListener(this);
        this.mBedRemindSelect.setOnClickListener(this);

        this.mBedRemindSwitch.setChecked(SharedPrefsUtils.getBooleanPreference(this, Constant.KEY_ALARM_STATE, false));
        this.mBedRemindSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            }
        });
        textViewArr = this.textViews;
        textViewArr[0] = this.mBedRemindDay1;
        textViewArr[1] = this.mBedRemindDay2;
        textViewArr[2] = this.mBedRemindDay3;
        textViewArr[3] = this.mBedRemindDay4;
        textViewArr[4] = this.mBedRemindDay5;
        textViewArr[5] = this.mBedRemindDay6;
        textViewArr[6] = this.mBedRemindDay7;

        for(int i=0;i <textViewArr.length;i++)
         {
         textViewArr = this.textViews;
            if (i <textViewArr.length) {
                if (this.dayChecked[i]) {
                    textViewArr[i].setBackground(BedReminderActivity.this.getResources().getDrawable(R.drawable.shape_day_selected_bg));
                } else {
                    textViewArr[i].setBackground(BedReminderActivity.this.getResources().getDrawable(R.drawable.shape_day_unselect_bg));
                }
                int finalI = i;
                this.textViews[i].setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (!BedReminderActivity.this.mBedRemindSwitch.isChecked()) {
                            return;
                        }
                        if (BedReminderActivity.this.dayChecked[finalI]) {
                            BedReminderActivity.this.textViews[finalI].setBackground(BedReminderActivity.this.getResources().getDrawable(R.drawable.shape_day_unselect_bg));
                            BedReminderActivity.this.dayChecked[finalI] = false;
                            return;
                        }
                        BedReminderActivity.this.textViews[finalI].setBackground(BedReminderActivity.this.getResources().getDrawable(R.drawable.shape_day_selected_bg));
                        BedReminderActivity.this.dayChecked[finalI] = true;
                    }
                });
//                i++;
            } else {
                this.mToolbar.setNavigationOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        BedReminderActivity.this.finish();
                    }
                });
                return;
            }
        }
    }
@Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bed_remind_select) {
            ArrayUtils.saveDayCheck(this, this.dayChecked);
            boolean isChecked = this.mBedRemindSwitch.isChecked();
            String str = Constant.KEY_ALARM_STATE;
            if (isChecked) {
                SharedPrefsUtils.setBooleanPreference(this, str, true);
                AlarmUtils.nextAlarm(this);
            } else {
                SharedPrefsUtils.setBooleanPreference(this, str, false);
                AlarmUtils.cancelBedAlarm(this);
            }
            Toast.makeText(this, R.string.set_success, 0).show();
            finish();
        } else if (id == R.id.bed_remind_set_time_view && this.mBedRemindSwitch.isChecked()) {
            startActivity(new Intent(this, SetBedTimeActivity.class));
        }
    }
}
