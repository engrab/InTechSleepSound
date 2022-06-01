package com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.SettingsFragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.PrivacyPolicyActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.BedReminderActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.ShareUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.SharedPrefsUtils;

public class SettingFragment extends BaseFragment {
    private boolean alarmState = false;

    private LinearLayout mFragmentSettingBottomAd;
    private ConstraintLayout mLlBedReminder;
    private ConstraintLayout mLlFeedback;
    private ConstraintLayout mLlPrivacyPolicy;
    private TextView mTvBedReminderTime;

    private TextView mTvVersion;
    private View root;
@Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.root = layoutInflater.inflate(R.layout.fragment_setting, viewGroup, false);
        InitView();
        return this.root;
    }

    public void InitView() {
        this.mLlBedReminder = this.root.findViewById(R.id.ll_bed_reminder);
        this.mLlFeedback = this.root.findViewById(R.id.ll_feedback);
        this.mLlPrivacyPolicy = this.root.findViewById(R.id.ll_privacy_policy);
        this.mTvVersion = this.root.findViewById(R.id.tv_Rateus);

        mTvVersion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getActivity().getPackageName())));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getActivity().getPackageName())));
                }
            }
        });
        this.mTvBedReminderTime = this.root.findViewById(R.id.tv_bed_reminder_time);
        this.mFragmentSettingBottomAd = this.root.findViewById(R.id.fragment_setting_bottom_ad);
        this.mLlBedReminder.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SettingFragment settingFragment = SettingFragment.this;
                settingFragment.startActivity(new Intent(settingFragment.getActivity(), BedReminderActivity.class));
            }
        });
        this.mLlPrivacyPolicy.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SettingFragment settingFragment = SettingFragment.this;
                settingFragment.startActivity(new Intent(settingFragment.getActivity(), PrivacyPolicyActivity.class));
            }
        });
        this.mLlFeedback.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ShareUtil.feedback(SettingFragment.this.getActivity(), SettingFragment.this.getResources().getString(R.string.email));
            }
        });
    }
@Override
    public void onResume() {
        super.onResume();
        UpdateUi();
    }

    public void UpdateUi() {
        this.alarmState = SharedPrefsUtils.getBooleanPreference(getActivity(), Constant.KEY_ALARM_STATE, false);
        CharSequence stringPreference = SharedPrefsUtils.getStringPreference(getActivity(), Constant.KEY_ALARM_TIME);
        if (stringPreference == null) {
            stringPreference = "";
        }
        if (this.alarmState) {
            this.mTvBedReminderTime.setText(stringPreference);
        } else {
            this.mTvBedReminderTime.setText(R.string.bedtime_reminder_off);
        }
    }
}
