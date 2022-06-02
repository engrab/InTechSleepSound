package com.arapps.sleepsound.relaxandsleep.naturesounds.fragment;

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
import com.arapps.sleepsound.relaxandsleep.naturesounds.adapter.AdapterViewPagerWrap;
import com.arapps.sleepsound.relaxandsleep.naturesounds.baseClasses.FragmentBase;
import com.arapps.sleepsound.relaxandsleep.naturesounds.customView.BasePagerWrapContent;
import com.arapps.sleepsound.relaxandsleep.naturesounds.helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.services.SoundPlayerManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.ui.CustomSelectActivityBase;
import com.arapps.sleepsound.relaxandsleep.naturesounds.ui.SetTimeActivityBase;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.SharedPrefsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.TimeUtils;

import io.github.kshitij_jain.indicatorview.IndicatorView;

public class BaseSoundFragment extends FragmentBase {
    private AppCompatImageView mCustomPlayIv;
    private final BroadcastReceiver broadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                int intExtra = intent.getIntExtra(SoundPlayerService.UPDATE_PLAY_STATE, 0);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(intExtra);
                stringBuilder.append("");
                Log.e("PLAYER_STATE", stringBuilder.toString());
                if (intExtra == 0) {
                    BaseSoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_play);
                } else if (intExtra == 1) {
                    BaseSoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_pause);
                } else if (intExtra == 2) {
                    BaseSoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_play);
                }
            }
        }
    };
    private BasePagerWrapContent mFragmentCustomVp;
    private Guideline mGuidelineV33;
    private Guideline mGuidelineV67;
    private LinearLayout mSetTimeLayout;
    private TextView mSoundCountTv;
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                int intExtra = intent.getIntExtra(SoundPlayerService.UPDATE_SOUND_SELECTED, 0);
                TextView access$100 = BaseSoundFragment.this.mSoundCountTv;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(intExtra);
                stringBuilder.append("");
                access$100.setText(stringBuilder.toString());
                if (SoundPlayerManager.getInstance(BaseSoundFragment.this.getActivity()).getPlayerState() == 2) {
                    BaseSoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_play);
                } else if (SoundPlayerManager.getInstance(BaseSoundFragment.this.getActivity()).getPlayerState() == 1) {
                    BaseSoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_pause);
                }
            }
        }
    };
    private LinearLayout mCustomPlayLayout;
    private ConstraintLayout mCustomSaveView;
    private LinearLayout mFragmentCustomBottomAd;
    private LinearLayout mTitle;
    private IndicatorView mFragmentCustomIndicator;
    private AppCompatImageView mSelectIv;
    private TextView mTvSelect;
    private AdapterViewPagerWrap viewPagerAdapter;
    private TextView mPlayCountTimeTv;
    private final BroadcastReceiver broadcastReceiver3 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                long longExtra = intent.getLongExtra(SoundPlayerService.UPDATE_TIME, -1);
                if (longExtra > 0) {
                    BaseSoundFragment.this.mPlayCountTimeTv.setVisibility(View.VISIBLE);
                    BaseSoundFragment.this.mPlayCountTimeTv.setText(TimeUtils.convertMillisecondsToString(longExtra));
                    return;
                }
                long longPreference = SharedPrefsUtils.getLongPreference(BaseSoundFragment.this.getActivity(), Constant.KEY_PLAY_TIME, 1140000);
                BaseSoundFragment.this.mPlayCountTimeTv.setText(TimeUtils.convertMillisecondsToString(longPreference));
                if (longPreference == -1) {
                    BaseSoundFragment.this.mPlayCountTimeTv.setText(R.string.count_time_zero);
                }
            }
        }
    };
    private TextView mTvAppName;

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        SetUpViewPager();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sound, viewGroup, false);
        this.mCustomPlayIv = inflate.findViewById(R.id.custom_play_iv);
        this.mCustomPlayLayout = inflate.findViewById(R.id.custom_play_layout);
        this.mTvSelect = inflate.findViewById(R.id.tv_select);
        this.mTitle = inflate.findViewById(R.id.title);
        this.mPlayCountTimeTv = inflate.findViewById(R.id.play_count_time_tv);
        this.mTvAppName = inflate.findViewById(R.id.tv_app_name);
        this.mSetTimeLayout = inflate.findViewById(R.id.set_time_layout);
        this.mFragmentCustomBottomAd = inflate.findViewById(R.id.fragment_custom_bottom_ad);
        this.mSoundCountTv = inflate.findViewById(R.id.sound_count_tv);
        this.mSelectIv = inflate.findViewById(R.id.select_iv);
        this.mCustomSaveView = inflate.findViewById(R.id.custom_save_view);
        this.mFragmentCustomVp = inflate.findViewById(R.id.fragment_custom_vp);
        this.mGuidelineV33 = inflate.findViewById(R.id.guideline_v_33);
        this.mGuidelineV67 = inflate.findViewById(R.id.guideline_v_67);
        this.mFragmentCustomIndicator = inflate.findViewById(R.id.fragment_custom_indicator);
        this.mSetTimeLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseSoundFragment soundFragment = BaseSoundFragment.this;
                soundFragment.startActivity(new Intent(soundFragment.getActivity(), SetTimeActivityBase.class));
            }
        });
        this.mCustomPlayLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int playerState = SoundPlayerManager.getInstance(BaseSoundFragment.this.getActivity()).getPlayerState();
                String str = SoundPlayerService.CMD_NAME;
                String str2 = SoundPlayerService.ACTION_CMD;
                Intent intent;
                if (playerState == 2) {
                    intent = new Intent(BaseSoundFragment.this.getActivity(), SoundPlayerService.class);
                    intent.setAction(str2);
                    intent.putExtra(str, SoundPlayerService.CMD_RESUME_ALL);
                    BaseSoundFragment.this.getActivity().startService(intent);
                    BaseSoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_pause);
                } else if (SoundPlayerManager.getInstance(BaseSoundFragment.this.getActivity()).getPlayerState() == 1) {
                    intent = new Intent(BaseSoundFragment.this.getActivity(), SoundPlayerService.class);
                    intent.setAction(str2);
                    intent.putExtra(str, SoundPlayerService.CMD_PAUSE_ALL);
                    BaseSoundFragment.this.getActivity().startService(intent);
                    BaseSoundFragment.this.mCustomPlayIv.setImageResource(R.drawable.vector_ic_play);
                }
            }
        });
        this.mCustomSaveView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SoundPlayerManager.getInstance(BaseSoundFragment.this.getActivity()).getSizePlayer() > 0) {
                    BaseSoundFragment soundFragment = BaseSoundFragment.this;
                    soundFragment.startActivity(new Intent(soundFragment.getActivity(), CustomSelectActivityBase.class));
                    return;
                }
                Toast.makeText(BaseSoundFragment.this.getActivity(), "Choose a sound to create your custom", 0).show();
            }
        });
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver3, new IntentFilter(SoundPlayerService.ACTION_UPDATE_TIME));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver, new IntentFilter(SoundPlayerService.ACTION_UPDATE_SOUND_SELECTED));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver1, new IntentFilter(SoundPlayerService.ACTION_UPDATE_PLAY_STATE));
        return inflate;
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

    public void SetUpViewPager() {
        this.mFragmentCustomVp.setOffscreenPageLimit(7);
        this.viewPagerAdapter = new AdapterViewPagerWrap(getActivity().getSupportFragmentManager());
        this.viewPagerAdapter.addFragment(BaseSounListFragment.getInstance(0));
        this.viewPagerAdapter.addFragment(BaseSounListFragment.getInstance(1));
        this.viewPagerAdapter.addFragment(BaseSounListFragment.getInstance(2));
        this.viewPagerAdapter.addFragment(BaseSounListFragment.getInstance(3));
        this.viewPagerAdapter.addFragment(BaseSounListFragment.getInstance(4));
//        this.viewPagerAdapter.addFragment(SounListFragment.getInstance(5));
//        this.viewPagerAdapter.addFragment(SounListFragment.getInstance(6));
        this.mFragmentCustomVp.setAdapter(this.viewPagerAdapter);
        this.mFragmentCustomVp.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                BaseSoundFragment.this.mFragmentCustomIndicator.setCurrentPage(i);
            }
        });
        this.mFragmentCustomIndicator.setPageIndicators(this.viewPagerAdapter.getCount());
        this.mFragmentCustomIndicator.setActiveIndicatorColor(R.color.white);
        this.mFragmentCustomIndicator.setActiveIndicatorColor(R.color.white_20);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver1);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver3);
    }
}
