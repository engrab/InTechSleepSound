package com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.MixFragment;

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

import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.PlayActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Adapter.ViewPagerAdapter;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.MixListFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SaveDataHelper;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixCategoryModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.TimeUtils;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.List;

public class MixFragment extends BaseFragment {
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                int intExtra = intent.getIntExtra(SoundPlayerService.UPDATE_PLAY_STATE, 0);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(intExtra);
                stringBuilder.append("");
                Log.e("PLAYER_STATE", stringBuilder.toString());
                if (intExtra == 0) {
                    MixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
                    MixFragment.this.mMiniPlayer.setVisibility(8);
                } else if (intExtra == 1) {
                    MixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_pause);
                    if (MixFragment.this.mMiniPlayer.getVisibility() == 4 || MixFragment.this.mMiniPlayer.getVisibility() == 8) {
                        MixFragment.this.mMiniPlayer.setVisibility(0);
                    }
                } else if (intExtra == 2) {
                    MixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
                }
                MixModel mixModel = SoundPlayerManager.getInstance(MixFragment.this.getActivity()).getMixItem();
                String str = "MIX_MAIN";
                if (mixModel != null) {
                    MixFragment.this.mCover_Image.setImageResource(mixModel.getMixSoundCover().getCoverResId());
                    MixFragment.this.m_txtSoundName.setText(mixModel.getName());
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
                    MixFragment.this.mPlayTime.setVisibility(0);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            try {
                                MixFragment.this.mPlayTime.setText(TimeUtils.convertMillisecondsToString(longExtra));
                            } catch (Exception unused) {
                            }
                        }
                    }, 1000);
                    return;
                }
                MixFragment.this.mPlayTime.setVisibility(0);
            }
        }
    };
    private LinearLayoutManager linearLayoutManager;
    private ImageView mImg_CloseIv;
    private ImageView mCover_Image;
    private ImageView mPlayControlIv;
    private LinearLayout mLlControl;
    private RelativeLayout mMiniPlayer;
    private TextView mPlayTime;
    private RecyclerView mRcvMixSoundType;
    private TextView m_txtSoundName;
    private MixViewModel mixViewModel;
    private SlimAdapter mSlimAdapter;
    private ViewPager mixviewPager;
    private ViewPagerAdapter viewPagerAdapter;
    StringBuilder stringBuilder;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_mix, viewGroup, false);
        this.mRcvMixSoundType = inflate.findViewById(R.id.rcv_mix_sound_type);
        this.mixviewPager = inflate.findViewById(R.id.vp_mix_sound);
        this.mCover_Image = inflate.findViewById(R.id.cover_image);
        this.m_txtSoundName = inflate.findViewById(R.id.sound_name);
        this.mPlayTime = inflate.findViewById(R.id.play_time);
        this.mPlayControlIv = inflate.findViewById(R.id.play_control_iv);
        this.mImg_CloseIv = inflate.findViewById(R.id.close_iv);
        this.mLlControl = inflate.findViewById(R.id.ll_control);
        this.mMiniPlayer = inflate.findViewById(R.id.mini_player);
        this.mImg_CloseIv.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MixFragment.this.mMiniPlayer.setVisibility(8);
                Intent intent = new Intent(MixFragment.this.getActivity(), SoundPlayerService.class);
                String str = SoundPlayerService.ACTION_CMD;
                intent.setAction(str);
                String str2 = SoundPlayerService.CMD_NAME;
                intent.putExtra(str2, SoundPlayerService.CMD_PAUSE_ALL);
                MixFragment.this.getActivity().startService(intent);
                MixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
                Intent intent2 = new Intent(MixFragment.this.getActivity(), SoundPlayerService.class);
                intent2.setAction(str);
                intent2.putExtra(str2, SoundPlayerService.CMD_NOTIFICATION_CLOSE);
                MixFragment.this.getActivity().startService(intent);
            }
        });
        this.mPlayControlIv.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int playerState = SoundPlayerManager.getInstance(MixFragment.this.getActivity()).getPlayerState();
                String str = SoundPlayerService.CMD_NAME;
                String str2 = SoundPlayerService.ACTION_CMD;
                Intent intent;
                if (playerState == 2) {
                    intent = new Intent(MixFragment.this.getActivity(), SoundPlayerService.class);
                    intent.setAction(str2);
                    intent.putExtra(str, SoundPlayerService.CMD_RESUME_ALL);
                    MixFragment.this.getActivity().startService(intent);
                    MixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_pause);
                } else if (SoundPlayerManager.getInstance(MixFragment.this.getActivity()).getPlayerState() == 1) {
                    intent = new Intent(MixFragment.this.getActivity(), SoundPlayerService.class);
                    intent.setAction(str2);
                    intent.putExtra(str, SoundPlayerService.CMD_PAUSE_ALL);
                    MixFragment.this.getActivity().startService(intent);
                    MixFragment.this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
                }
            }
        });
        this.mMiniPlayer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MixFragment mixFragment = MixFragment.this;
                mixFragment.startActivity(new Intent(mixFragment.getActivity(), PlayActivity.class));
            }
        });
        SetupRecycleView();
        setUpViewPager();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver, new IntentFilter(SoundPlayerService.ACTION_UPDATE_PLAY_STATE));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver1, new IntentFilter(SoundPlayerService.ACTION_UPDATE_TIME));
        return inflate;
    }

    public void setUpViewPager() {
        this.viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        this.viewPagerAdapter.addFragment(new MixListFragment(0));
        List customMixList = SaveDataHelper.getCustomMixList(getActivity());
        int i;
        if (customMixList == null || customMixList.size() <= 0) {
            for (i = 2; i < SoundListDataSource.numerTab; i++) {
                this.viewPagerAdapter.addFragment(new MixListFragment(i));
            }
        } else {
            for (i = 1; i < SoundListDataSource.numerTab; i++) {
                this.viewPagerAdapter.addFragment(new MixListFragment(i));
            }
        }


        this.mixviewPager.setAdapter(this.viewPagerAdapter);
        this.mixviewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                for (int i2 = 0; i2 < MixFragment.this.mSlimAdapter.getData().size(); i2++) {
                    List customMixList = SaveDataHelper.getCustomMixList(MixFragment.this.getActivity());
                    if (customMixList == null || customMixList.size() <= 0) {
                        ((MixCategoryModel) MixFragment.this.mSlimAdapter.getData().get(i2)).setChecked(i2 == i);
                    } else
                        ((MixCategoryModel) MixFragment.this.mSlimAdapter.getData().get(i2)).setChecked(((MixCategoryModel) MixFragment.this.mSlimAdapter.getData().get(i2)).getId() == i);
                }
                MixFragment.this.mSlimAdapter.notifyDataSetChanged();
                MixFragment.this.linearLayoutManager.scrollToPositionWithOffset(i, DisplayUtil.getScreenWidth(MixFragment.this.getActivity()) / 2);
            }
        });
        this.mixviewPager.setOffscreenPageLimit(7);
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
        stringBuilder = new StringBuilder();
        stringBuilder.append(playerState);
        stringBuilder.append("");
        Log.e("PLAYER_STATE", stringBuilder.toString());
        if (playerState == 1) {
            this.mPlayControlIv.setImageResource(R.drawable.vector_ic_pause);
        } else if (playerState == 2) {
            this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
        } else if (playerState == 3) {
            this.mPlayControlIv.setImageResource(R.drawable.vector_ic_play);
        }
        MixModel mixModel = SoundPlayerManager.getInstance(getActivity()).getMixItem();
        String str = "MIX_MAIN";
        if (mixModel != null) {
            if (this.mMiniPlayer.getVisibility() == 4 || this.mMiniPlayer.getVisibility() == 8) {
                this.mMiniPlayer.setVisibility(0);
            }
            this.mCover_Image.setImageResource(mixModel.getMixSoundCover().getCoverResId());
            this.m_txtSoundName.setText(mixModel.getName());
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
        this.mSlimAdapter = SlimAdapter.create().register(R.layout.model_rcv_mix_sound_type, new SlimInjector<MixCategoryModel>() {
            public void onInject(final MixCategoryModel mixCategoryModel, IViewInjector iViewInjector) {
                iViewInjector.text(R.id.tv_mix_sound_type, mixCategoryModel.getName());
                if (mixCategoryModel.isChecked()) {
                    iViewInjector.background(R.id.tv_mix_sound_type, R.drawable.shape_mix_sound_type_tv_selected_bg);
                } else {
                    iViewInjector.background(R.id.tv_mix_sound_type, R.drawable.shape_mix_sound_type_tv_unselected_bg);
                }
                iViewInjector.clicked(R.id.tv_mix_sound_type, new OnClickListener() {
                    public void onClick(View view) {
                        for (int i = 0; i < MixFragment.this.mSlimAdapter.getData().size(); i++) {
                            ((MixCategoryModel) MixFragment.this.mSlimAdapter.getData().get(i)).setChecked(((MixCategoryModel) MixFragment.this.mSlimAdapter.getData().get(i)).getId() == mixCategoryModel.getId());
                        }
                        MixFragment.this.mSlimAdapter.notifyDataSetChanged();
                        MixFragment.this.mixviewPager.setCurrentItem(mixCategoryModel.getId(), true);
                    }
                });
            }
        }).enableDiff().attachTo(mRcvMixSoundType);
        this.mSlimAdapter.updateData(SoundListDataSource.getListMixCategoryItem(getActivity()));
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
