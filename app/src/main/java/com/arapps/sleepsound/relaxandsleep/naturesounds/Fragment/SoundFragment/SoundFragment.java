package com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.SoundFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.CustomSelectActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.SetTimeActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Adapter.ViewPagerWrapAdapter;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.customView.WrapContentViewPager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.SounListFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.SharedPrefsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.TimeUtils;
import io.github.kshitij_jain.indicatorview.IndicatorView;

public class SoundFragment extends BaseFragment {
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                int intExtra = intent.getIntExtra(SoundPlayerService.UPDATE_SOUND_SELECTED, 0);
                TextView access$100 = SoundFragment.this.mSoundCountTv;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(intExtra);
                stringBuilder.append("");
                access$100.setText(stringBuilder.toString());
                if (SoundPlayerManager.getInstance(SoundFragment.this.getActivity()).getPlayerState() == 2) {
                    SoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_play);
                } else if (SoundPlayerManager.getInstance(SoundFragment.this.getActivity()).getPlayerState() == 1) {
                    SoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_pause);
                }
            }
        }
    };
    private final BroadcastReceiver broadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                int intExtra = intent.getIntExtra(SoundPlayerService.UPDATE_PLAY_STATE, 0);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(intExtra);
                stringBuilder.append("");
                Log.e("PLAYER_STATE", stringBuilder.toString());
                if (intExtra == 0) {
                    SoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_play);
                } else if (intExtra == 1) {
                    SoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_pause);
                } else if (intExtra == 2) {
                    SoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_play);
                }
            }
        }
    };
    private final BroadcastReceiver broadcastReceiver3 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                long longExtra = intent.getLongExtra(SoundPlayerService.UPDATE_TIME, -1);
                if (longExtra > 0) {
                    SoundFragment.this.mPlayCountTimeTv.setVisibility(0);
                    SoundFragment.this.mPlayCountTimeTv.setText(TimeUtils.convertMillisecondsToString(longExtra));
                    return;
                }
                long longPreference = SharedPrefsUtils.getLongPreference(SoundFragment.this.getActivity(), Constant.KEY_PLAY_TIME, 1140000);
                SoundFragment.this.mPlayCountTimeTv.setText(TimeUtils.convertMillisecondsToString(longPreference));
                if (longPreference == -1) {
                    SoundFragment.this.mPlayCountTimeTv.setText(R.string.count_time_zero);
                }
            }
        }
    };
    private AppCompatImageView mCustomPlayIv;
    private LinearLayout mCustomPlayLayout;
    private ConstraintLayout mCustomSaveView;
    private LinearLayout mFragmentCustomBottomAd;
    private LinearLayout mTitle;
    private IndicatorView mFragmentCustomIndicator;
    private WrapContentViewPager mFragmentCustomVp;
    private Guideline mGuidelineV33;
    private Guideline mGuidelineV67;
    private AppCompatImageView mSelectIv;
    private LinearLayout mSetTimeLayout;
    private TextView mSoundCountTv;
    private TextView mPlayCountTimeTv;
    private TextView mTvAppName;
    private TextView mTvSelect;
    private SoundViewModel soundViewModel;
    private ViewPagerWrapAdapter viewPagerAdapter;
@Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sound, viewGroup, false);
        this.mTvAppName = inflate.findViewById(R.id.tv_app_name);
        this.mTitle = inflate.findViewById(R.id.title);
        this.mPlayCountTimeTv = inflate.findViewById(R.id.play_count_time_tv);
        this.mSetTimeLayout = inflate.findViewById(R.id.set_time_layout);
        this.mCustomPlayIv = inflate.findViewById(R.id.custom_play_iv);
        this.mCustomPlayLayout = inflate.findViewById(R.id.custom_play_layout);
        this.mGuidelineV33 = inflate.findViewById(R.id.guideline_v_33);
        this.mGuidelineV67 = inflate.findViewById(R.id.guideline_v_67);
        this.mTvSelect = inflate.findViewById(R.id.tv_select);
        this.mSoundCountTv = inflate.findViewById(R.id.sound_count_tv);
        this.mSelectIv = inflate.findViewById(R.id.select_iv);
        this.mCustomSaveView = inflate.findViewById(R.id.custom_save_view);
        this.mFragmentCustomVp = inflate.findViewById(R.id.fragment_custom_vp);
        this.mFragmentCustomIndicator = inflate.findViewById(R.id.fragment_custom_indicator);
        this.mFragmentCustomBottomAd = inflate.findViewById(R.id.fragment_custom_bottom_ad);
        this.mSetTimeLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SoundFragment soundFragment = SoundFragment.this;
                soundFragment.startActivity(new Intent(soundFragment.getActivity(), SetTimeActivity.class));
            }
        });
        this.mCustomPlayLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int playerState = SoundPlayerManager.getInstance(SoundFragment.this.getActivity()).getPlayerState();
                String str = SoundPlayerService.CMD_NAME;
                String str2 = SoundPlayerService.ACTION_CMD;
                Intent intent;
                if (playerState == 2) {
                    intent = new Intent(SoundFragment.this.getActivity(), SoundPlayerService.class);
                    intent.setAction(str2);
                    intent.putExtra(str, SoundPlayerService.CMD_RESUME_ALL);
                    SoundFragment.this.getActivity().startService(intent);
                    SoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_pause);
                } else if (SoundPlayerManager.getInstance(SoundFragment.this.getActivity()).getPlayerState() == 1) {
                    intent = new Intent(SoundFragment.this.getActivity(), SoundPlayerService.class);
                    intent.setAction(str2);
                    intent.putExtra(str, SoundPlayerService.CMD_PAUSE_ALL);
                    SoundFragment.this.getActivity().startService(intent);
                    SoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_play);
                }
            }
        });
        this.mCustomSaveView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SoundPlayerManager.getInstance(SoundFragment.this.getActivity()).getSizePlayer() > 0) {
                    SoundFragment soundFragment = SoundFragment.this;
                    soundFragment.startActivity(new Intent(soundFragment.getActivity(), CustomSelectActivity.class));
                    return;
                }
                Toast.makeText(SoundFragment.this.getActivity(), "Choose a sound to create your custom", 0).show();
            }
        });
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver3, new IntentFilter(SoundPlayerService.ACTION_UPDATE_TIME));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver, new IntentFilter(SoundPlayerService.ACTION_UPDATE_SOUND_SELECTED));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver1, new IntentFilter(SoundPlayerService.ACTION_UPDATE_PLAY_STATE));
        return inflate;
    }
@Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        SetUpViewPager();
    }

    public void SetUpViewPager() {
        this.mFragmentCustomVp.setOffscreenPageLimit(7);
        this.viewPagerAdapter = new ViewPagerWrapAdapter(getActivity().getSupportFragmentManager());
        this.viewPagerAdapter.addFragment(SounListFragment.getInstance(0));
        this.viewPagerAdapter.addFragment(SounListFragment.getInstance(1));
        this.viewPagerAdapter.addFragment(SounListFragment.getInstance(2));
        this.viewPagerAdapter.addFragment(SounListFragment.getInstance(3));
        this.viewPagerAdapter.addFragment(SounListFragment.getInstance(4));
//        this.viewPagerAdapter.addFragment(SounListFragment.getInstance(5));
//        this.viewPagerAdapter.addFragment(SounListFragment.getInstance(6));
        this.mFragmentCustomVp.setAdapter(this.viewPagerAdapter);
        this.mFragmentCustomVp.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                SoundFragment.this.mFragmentCustomIndicator.setCurrentPage(i);
            }
        });
        this.mFragmentCustomIndicator.setPageIndicators(this.viewPagerAdapter.getCount());
        this.mFragmentCustomIndicator.setActiveIndicatorColor(R.color.white);
        this.mFragmentCustomIndicator.setActiveIndicatorColor(R.color.white_20);
    }
@Override
    public void onResume() {
        super.onResume();
        if (SoundPlayerManager.getInstance(getActivity()).getPlayerState() == 2) {
            this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_play);
        } else if (SoundPlayerManager.getInstance(getActivity()).getPlayerState() == 1) {
            this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_pause);
        }
        long longPreference = SharedPrefsUtils.getLongPreference(getActivity(), Constant.KEY_PLAY_TIME, 1140000);
        this.mPlayCountTimeTv.setText(TimeUtils.convertMillisecondsToString(longPreference));
        if (longPreference == -1) {
            this.mPlayCountTimeTv.setText(R.string.count_time_zero);
        }
        TextView textView = this.mSoundCountTv;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SoundPlayerManager.getInstance(getActivity()).getSizePlayer());
        stringBuilder.append("");
        textView.setText(stringBuilder.toString());
    }
@Override
    public void onPause() {
        super.onPause();
    }
@Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver1);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver3);
    }
}
