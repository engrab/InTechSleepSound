package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.fragment;

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
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ui.PrivacyPolicyActivityBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ui.BedReminderActivityBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.baseClasses.FragmentBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.Constant;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.ShareUtil;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.SharedPrefsUtils;

public class BaseSettingFragment extends FragmentBase {
    private boolean isEnabled = false;

    private LinearLayout mFragmentSettingBottomAd;
    private ConstraintLayout mClReminder;
    private ConstraintLayout mClFeedback;
    private ConstraintLayout mClPolicy;
    private TextView mBedRemind;
    private TextView mVersion;
    private View root;
@Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        root = layoutInflater.inflate(R.layout.fragment_setting, viewGroup, false);
        InitView();
        return this.root;
    }

    public void InitView() {
        this.mClReminder = this.root.findViewById(R.id.ll_bed_reminder);
        this.mClFeedback = this.root.findViewById(R.id.ll_feedback);
        this.mClPolicy = this.root.findViewById(R.id.ll_privacy_policy);
        this.mVersion = this.root.findViewById(R.id.tv_Rateus);

        mVersion.setOnClickListener(new OnClickListener() {
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
        this.mBedRemind = this.root.findViewById(R.id.tv_bed_reminder_time);
        this.mFragmentSettingBottomAd = this.root.findViewById(R.id.fragment_setting_bottom_ad);
        this.mClReminder.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseSettingFragment settingFragment = BaseSettingFragment.this;
                settingFragment.startActivity(new Intent(settingFragment.getActivity(), BedReminderActivityBase.class));
            }
        });
        this.mClPolicy.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseSettingFragment settingFragment = BaseSettingFragment.this;
                settingFragment.startActivity(new Intent(settingFragment.getActivity(), PrivacyPolicyActivityBase.class));
            }
        });
        this.mClFeedback.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ShareUtil.feedback(BaseSettingFragment.this.getActivity(), BaseSettingFragment.this.getResources().getString(R.string.email));
            }
        });
    }
@Override
    public void onResume() {
        super.onResume();
        UpdateUi();
    }

    public void UpdateUi() {
        this.isEnabled = SharedPrefsUtils.getBooleanPreference(getActivity(), Constant.KEY_ALARM_STATE, false);
        CharSequence stringPreference = SharedPrefsUtils.getStringPreference(getActivity(), Constant.KEY_ALARM_TIME);
        if (stringPreference == null) {
            stringPreference = "";
        }
        if (this.isEnabled) {
            this.mBedRemind.setText(stringPreference);
        } else {
            this.mBedRemind.setText(R.string.bedtime_reminder_off);
        }
    }
}
