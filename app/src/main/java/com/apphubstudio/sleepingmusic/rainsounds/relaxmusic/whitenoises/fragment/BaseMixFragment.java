package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ui.PlayActivityBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.adapter.AdapterViewPager;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.baseClasses.FragmentBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.SoundList;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.HelperSaveData;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelMixCategory;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelMix;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.services.SoundPlayerManager;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.services.SoundPlayerService;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.DisplayUtil;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.TimeUtils;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.List;

public class BaseMixFragment extends FragmentBase {
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                int intExtra = intent.getIntExtra(SoundPlayerService.UPDATE_PLAY_STATE, 0);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(intExtra);
                stringBuilder.append("");
                Log.e("PLAYER_STATE", stringBuilder.toString());
                if (intExtra == 0) {
                    BaseMixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
                    BaseMixFragment.this.mMiniPlayer.setVisibility(View.GONE);
                } else if (intExtra == 1) {
                    BaseMixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_pause);
                    if (BaseMixFragment.this.mMiniPlayer.getVisibility() == View.INVISIBLE || BaseMixFragment.this.mMiniPlayer.getVisibility() == View.GONE) {
                        BaseMixFragment.this.mMiniPlayer.setVisibility(View.VISIBLE);
                    }
                } else if (intExtra == 2) {
                    BaseMixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
                }
                ModelMix modelMix = SoundPlayerManager.getInstance(BaseMixFragment.this.getActivity()).getMixItem();
                String str = "MIX_MAIN";
                if (modelMix != null) {
                    BaseMixFragment.this.mIvCover.setImageResource(modelMix.getmMixSoundCover().getmCoverResId1());
                    BaseMixFragment.this.mSoundName.setText(modelMix.getmName());
                    Log.e(str, "OK");
                    return;
                }
                Log.e(str, "NULL");
            }
        }
    };
    private final BroadcastReceiver broadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                final long longExtra = intent.getLongExtra(SoundPlayerService.UPDATE_TIME, -1);
                if (longExtra > 0) {
                    BaseMixFragment.this.mPlayTime.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            try {
                                BaseMixFragment.this.mPlayTime.setText(TimeUtils.convertMillisecondsToString(longExtra));
                            } catch (Exception unused) {
                            }
                        }
                    }, 1000);
                    return;
                }
                BaseMixFragment.this.mPlayTime.setVisibility(View.VISIBLE);
            }
        }
    };
    private LinearLayoutManager linearLayoutManager;
    private ImageView mIvClose;
    private ImageView mIvCover;
    private ImageView mPlayControlIv;
    private LinearLayout mLlControl;
    private RelativeLayout mMiniPlayer;
    private TextView mPlayTime;
    private RecyclerView mRcvMixSoundType;
    private TextView mSoundName;
    private SlimAdapter mSlimAdapter;
    private ViewPager mViewPager;
    private AdapterViewPager mPagerAdapter;
    StringBuilder mStrBuilder;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_mix, viewGroup, false);
        this.mRcvMixSoundType = inflate.findViewById(R.id.rcv_mix_sound_type);
        this.mViewPager = inflate.findViewById(R.id.vp_mix_sound);
        this.mIvCover = inflate.findViewById(R.id.cover_image);
        this.mSoundName = inflate.findViewById(R.id.sound_name);
        this.mPlayTime = inflate.findViewById(R.id.play_time);
        this.mPlayControlIv = inflate.findViewById(R.id.play_control_iv);
        this.mIvClose = inflate.findViewById(R.id.close_iv);
        this.mLlControl = inflate.findViewById(R.id.ll_control);
        this.mMiniPlayer = inflate.findViewById(R.id.mini_player);
        this.mIvClose.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseMixFragment.this.mMiniPlayer.setVisibility(View.GONE);
                Intent intent = new Intent(BaseMixFragment.this.getActivity(), SoundPlayerService.class);
                String str = SoundPlayerService.ACTION_CMD;
                intent.setAction(str);
                String str2 = SoundPlayerService.CMD_NAME;
                intent.putExtra(str2, SoundPlayerService.CMD_PAUSE_ALL);
                BaseMixFragment.this.getActivity().startService(intent);
                BaseMixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
                Intent intent2 = new Intent(BaseMixFragment.this.getActivity(), SoundPlayerService.class);
                intent2.setAction(str);
                intent2.putExtra(str2, SoundPlayerService.CMD_NOTIFICATION_CLOSE);
                BaseMixFragment.this.getActivity().startService(intent);
            }
        });
        this.mPlayControlIv.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int playerState = SoundPlayerManager.getInstance(BaseMixFragment.this.getActivity()).getPlayerState();
                String str = SoundPlayerService.CMD_NAME;
                String str2 = SoundPlayerService.ACTION_CMD;
                Intent intent;
                if (playerState == 2) {
                    intent = new Intent(BaseMixFragment.this.getActivity(), SoundPlayerService.class);
                    intent.setAction(str2);
                    intent.putExtra(str, SoundPlayerService.CMD_RESUME_ALL);
                    BaseMixFragment.this.getActivity().startService(intent);
                    BaseMixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_pause);
                } else if (SoundPlayerManager.getInstance(BaseMixFragment.this.getActivity()).getPlayerState() == 1) {
                    intent = new Intent(BaseMixFragment.this.getActivity(), SoundPlayerService.class);
                    intent.setAction(str2);
                    intent.putExtra(str, SoundPlayerService.CMD_PAUSE_ALL);
                    BaseMixFragment.this.getActivity().startService(intent);
                    BaseMixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
                }
            }
        });
        this.mMiniPlayer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseMixFragment mixFragment = BaseMixFragment.this;
                mixFragment.startActivity(new Intent(mixFragment.getActivity(), PlayActivityBase.class));
            }
        });
        SetupRecycleView();
        setUpViewPager();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver, new IntentFilter(SoundPlayerService.ACTION_UPDATE_PLAY_STATE));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver1, new IntentFilter(SoundPlayerService.ACTION_UPDATE_TIME));
        return inflate;
    }

    public void setUpViewPager() {
        this.mPagerAdapter = new AdapterViewPager(getActivity().getSupportFragmentManager());
        this.mPagerAdapter.addFragment(new BaseMixListFragment(0));
        List customMixList = HelperSaveData.getCustomMixList(getActivity());
        int i;
        if (customMixList == null || customMixList.size() <= 0) {
            for (i = 2; i < SoundList.numerTab; i++) {
                this.mPagerAdapter.addFragment(new BaseMixListFragment(i));
            }
        } else {
            for (i = 1; i < SoundList.numerTab; i++) {
                this.mPagerAdapter.addFragment(new BaseMixListFragment(i));
            }
        }


        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                for (int i2 = 0; i2 < BaseMixFragment.this.mSlimAdapter.getData().size(); i2++) {
                    List customMixList = HelperSaveData.getCustomMixList(BaseMixFragment.this.getActivity());
                    if (customMixList == null || customMixList.size() <= 0) {
                        ((ModelMixCategory) BaseMixFragment.this.mSlimAdapter.getData().get(i2)).setOk(i2 == i);
                    } else
                        ((ModelMixCategory) BaseMixFragment.this.mSlimAdapter.getData().get(i2)).setOk(((ModelMixCategory) BaseMixFragment.this.mSlimAdapter.getData().get(i2)).getCategoryId() == i);
                }
                BaseMixFragment.this.mSlimAdapter.notifyDataSetChanged();
                BaseMixFragment.this.linearLayoutManager.scrollToPositionWithOffset(i, DisplayUtil.getScreenWidth(BaseMixFragment.this.getActivity()) / 2);
            }
        });
        this.mViewPager.setOffscreenPageLimit(7);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver1);
    }

    @Override
    public void onResume() {
        super.onResume();
        int playerState = SoundPlayerManager.getInstance(getActivity()).getPlayerState();
        mStrBuilder = new StringBuilder();
        mStrBuilder.append(playerState);
        mStrBuilder.append("");
        Log.e("PLAYER_STATE", mStrBuilder.toString());
        if (playerState == 1) {
            this.mPlayControlIv.setImageResource(R.drawable.vector_ic_pause);
        } else if (playerState == 2) {
            this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
        } else if (playerState == 3) {
            this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
        }
        ModelMix modelMix = SoundPlayerManager.getInstance(getActivity()).getMixItem();
        String str = "MIX_MAIN";
        if (modelMix != null) {
            if (this.mMiniPlayer.getVisibility() == View.INVISIBLE || this.mMiniPlayer.getVisibility() == View.GONE) {
                this.mMiniPlayer.setVisibility(View.VISIBLE);
            }
            this.mIvCover.setImageResource(modelMix.getmMixSoundCover().getmCoverResId1());
            this.mSoundName.setText(modelMix.getmName());
            Log.e(str, "OK");
        } else {
            Log.e(str, "NULL");
        }
        if (SoundPlayerManager.getInstance(getActivity()).getSizePlayer() == 0 || SoundPlayerManager.getInstance(getActivity()).getMixItem() == null) {
            this.mMiniPlayer.setVisibility(View.GONE);
        }
    }
    public void SetupRecycleView() {
        this.linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        this.mRcvMixSoundType.setLayoutManager(this.linearLayoutManager);
        this.mSlimAdapter = SlimAdapter.create().register(R.layout.model_rcv_mix_sound_type, new SlimInjector<ModelMixCategory>() {
            public void onInject(final ModelMixCategory modelMixCategory, IViewInjector iViewInjector) {
                iViewInjector.text(R.id.tv_mix_sound_type, modelMixCategory.getCategoryName());
                if (modelMixCategory.isOk()) {
                    iViewInjector.background(R.id.tv_mix_sound_type, R.drawable.shape_mix_sound_type_tv_selected_bg);
                } else {
                    iViewInjector.background(R.id.tv_mix_sound_type, R.drawable.shape_mix_sound_type_tv_unselected_bg);
                }
                iViewInjector.clicked(R.id.tv_mix_sound_type, new OnClickListener() {
                    public void onClick(View view) {
                        for (int i = 0; i < BaseMixFragment.this.mSlimAdapter.getData().size(); i++) {
                            ((ModelMixCategory) BaseMixFragment.this.mSlimAdapter.getData().get(i)).setOk(((ModelMixCategory) BaseMixFragment.this.mSlimAdapter.getData().get(i)).getCategoryId() == modelMixCategory.getCategoryId());
                        }
                        BaseMixFragment.this.mSlimAdapter.notifyDataSetChanged();
                        BaseMixFragment.this.mViewPager.setCurrentItem(modelMixCategory.getCategoryId(), true);
                    }
                });
            }
        }).enableDiff().attachTo(mRcvMixSoundType);
        this.mSlimAdapter.updateData(SoundList.getListMixCategoryItem(getActivity()));
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
