package com.arapps.sleepsound.relaxandsleep.naturesounds.Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.work.WorkRequest;

import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.AdsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.SharedPrefsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.TimeUtils;
import com.google.android.gms.ads.AdView;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends BaseActivity implements OnClickListener {
    private final List<View> animList = new ArrayList();
    private final List<AnimatorSet> animatorSets = new ArrayList();
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                final long longExtra = intent.getLongExtra(SoundPlayerService.UPDATE_TIME, -1);
                if (longExtra > 0) {
                    PlayActivity.this.mPlaySetTimeTv.setVisibility(8);
                    PlayActivity.this.mPlayCountTimeTv.setVisibility(0);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            try {
                                PlayActivity.this.mPlayCountTimeTv.setText(TimeUtils.convertMillisecondsToString(longExtra));
                            } catch (Exception unused) {
                            }
                        }
                    }, 1000);
                    return;
                }
                PlayActivity.this.mPlaySetTimeTv.setVisibility(0);
                PlayActivity.this.mPlayCountTimeTv.setVisibility(8);
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
                    PlayActivity.this.mPlayControlView.setImageResource(R.drawable.vector_ic_play);
                    PlayActivity.this.PauseAnim();
                } else if (intExtra == 1) {
                    PlayActivity.this.mPlayControlView.setImageResource(R.drawable.vector_ic_pause);
                    PlayActivity.this.PlayAnim();
                } else if (intExtra == 2) {
                    PlayActivity.this.mPlayControlView.setImageResource(R.drawable.vector_ic_play);
                    PlayActivity.this.PauseAnim();
                }
            }
        }
    };
    private Intent intent;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayout mActivityPlayBottomAd;
    private AppCompatImageView mCloseView;

    private AppCompatImageView mMoreView;
    private AppCompatImageView mPlayBgIv;
    private AppCompatImageView mPlayControlView;

    private View mMixSoundCoverAnimView1;
    private View mMixSoundCoverAnimView2;
    private View mMixSoundCoverAnimView3;
    private TextView mPlayCountTimeTv;
    private TextView mPlaySetTimeTv;
    private TextView mSoundName;
    private TextView mTvSetTimerHint;
    private RecyclerView mSoundRcv;
    private RelativeLayout mPlayView;
    private RelativeLayout mToolbar;
    private final int mixId = 0;
    private MixModel mixModel;
    private View mViewSetTimer;
    private SlimAdapter slimAdapter;

    static class VideoDecoration extends ItemDecoration {
        VideoDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            recyclerView.getLayoutManager();
            recyclerView.getChildAdapterPosition(view);
            rect.set(DisplayUtil.dpToPx(2.0f), DisplayUtil.dpToPx(2.0f), DisplayUtil.dpToPx(2.0f), DisplayUtil.dpToPx(2.0f));
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.play_activity);

        RelativeLayout relativeLayout = findViewById(R.id.ads_lays);
        AdView adView = findViewById(R.id.main_medium2);

        AdsUtils.ShowBanner(PlayActivity.this, relativeLayout);
        this.mixModel = SoundPlayerManager.getInstance(this).getMixItem();
        if (this.mixModel == null) {
            finish();
        }
        InitView();
        DisplayUtil.hideActionBar(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.broadcastReceiver, new IntentFilter(SoundPlayerService.ACTION_UPDATE_TIME));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.broadcastReceiver1, new IntentFilter(SoundPlayerService.ACTION_UPDATE_PLAY_STATE));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void InitView() {
        mMoreView = findViewById(R.id.more_view);
        mMoreView.setOnClickListener(this);
        mPlayBgIv = findViewById(R.id.play_bg_iv);
        mCloseView = findViewById(R.id.close_view);
        mCloseView.setOnClickListener(this);
        mSoundName = findViewById(R.id.sound_name);
        mSoundName.setOnClickListener(this);
        mToolbar = findViewById(R.id.toolbar);
        mMixSoundCoverAnimView1 = findViewById(R.id.mix_sound_cover_anim_view1);
        mMixSoundCoverAnimView2 = findViewById(R.id.mix_sound_cover_anim_view2);
        mMixSoundCoverAnimView3 = findViewById(R.id.mix_sound_cover_anim_view3);
        mViewSetTimer = findViewById(R.id.view_set_timer);
        mViewSetTimer.setOnClickListener(this);
        mPlayCountTimeTv = findViewById(R.id.play_count_time_tv);
        mPlaySetTimeTv = findViewById(R.id.play_set_time_tv);
        mTvSetTimerHint = findViewById(R.id.tv_set_timer_hint);
        mPlayView = findViewById(R.id.play_view);
        mSoundRcv = findViewById(R.id.sound_rcv);
        mSoundRcv.setOnClickListener(this);
        mPlayControlView = findViewById(R.id.play_control_view);
        mPlayControlView.setOnClickListener(this);
        mActivityPlayBottomAd = findViewById(R.id.activity_play_bottom_ad);
        mActivityPlayBottomAd.setOnClickListener(this);
        animList.add(findViewById(R.id.mix_sound_cover_anim_view1));
        animList.add(findViewById(R.id.mix_sound_cover_anim_view2));
        animList.add(findViewById(R.id.mix_sound_cover_anim_view3));
        LayoutParams layoutParams = findViewById(R.id.play_view).getLayoutParams();
        int m18448a = DisplayUtil.m18448a(this, 70.0f, 41.0f);
        layoutParams.width = m18448a;
        layoutParams.height = m18448a;
        this.mSoundRcv.addItemDecoration(new VideoDecoration());
        this.slimAdapter = SlimAdapter.create().register(R.layout.model_sounds_play, new SlimInjector<SoundModel>() {
            public void onInject(SoundModel soundModel, IViewInjector iViewInjector) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(soundModel.getVolume());
                stringBuilder.append("%");
                iViewInjector.text(R.id.sound_volume_tv, stringBuilder.toString());
                iViewInjector.image(R.id.sound_icon_iv, soundModel.getIconResId());
                iViewInjector.invisible(R.id.sound_count_tv);
                iViewInjector.clicked(R.id.sound_play_layout, new OnClickListener() {
                    public void onClick(View view) {
                        PlayActivity.this.startActivity(new Intent(PlayActivity.this, MixCustomActivity.class));
                    }
                });
            }
        }).register(R.layout.model_sounds_play, new SlimInjector<String>() {
            public void onInject(String str, IViewInjector iViewInjector) {
                iViewInjector.text(R.id.sound_volume_tv, "Edit");
                iViewInjector.image(R.id.sound_icon_iv, R.drawable.vector_ic_edit);
                iViewInjector.visible(R.id.sound_count_tv);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(PlayActivity.this.mixModel.getSoundList().size());
                stringBuilder.append("");
                iViewInjector.text(R.id.sound_count_tv, stringBuilder.toString());
                iViewInjector.clicked(R.id.sound_play_layout, new OnClickListener() {
                    public void onClick(View view) {
                        PlayActivity.this.startActivity(new Intent(PlayActivity.this, MixCustomActivity.class));
                    }
                });
            }
        }).enableDiff().attachTo(mSoundRcv);
        this.mActivityPlayBottomAd = findViewById(R.id.activity_play_bottom_ad);
        CreateAnim();
        PlayAnim();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_view:
                finish();
                return;
            case R.id.more_view:
                startActivity(new Intent(this, AdjustMixActivity.class));
                return;
            case R.id.play_control_view:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(SoundPlayerManager.getInstance(this).getPlayerState());
                stringBuilder.append("");
                Log.e("STATE", stringBuilder.toString());
                int playerState = SoundPlayerManager.getInstance(this).getPlayerState();
                String str = SoundPlayerService.CMD_NAME;
                String str2 = SoundPlayerService.ACTION_CMD;
                if (playerState == 2) {
                    this.intent = new Intent(this, SoundPlayerService.class);
                    this.intent.setAction(str2);
                    this.intent.putExtra(str, SoundPlayerService.CMD_RESUME_ALL);
                    startService(this.intent);
                    this.mPlayControlView.setImageResource(R.drawable.vector_ic_pause);
                    return;
                } else if (SoundPlayerManager.getInstance(this).getPlayerState() == 1) {
                    this.intent = new Intent(this, SoundPlayerService.class);
                    this.intent.setAction(str2);
                    this.intent.putExtra(str, SoundPlayerService.CMD_PAUSE_ALL);
                    startService(this.intent);
                    this.mPlayControlView.setImageResource(R.drawable.vector_ic_play);
                    return;
                } else {
                    return;
                }
            case R.id.view_set_timer:
                this.intent = new Intent(this, SetTimeActivity.class);
                startActivity(this.intent);
                return;
            default:
                return;
        }
    }


    private void CreateAnim() {
        for (int i = 0; i < this.animList.size(); i++) {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.animList.get(i), "alpha", 0.3f, 0.0f);
            ofFloat.setRepeatCount(-1);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.animList.get(i), "scaleX", 1.0f, 1.3f);
            ofFloat2.setRepeatCount(-1);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.animList.get(i), "scaleY", 1.0f, 1.3f);
            ofFloat3.setRepeatCount(-1);
            animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
            animatorSet.setInterpolator(null);
            animatorSet.setStartDelay(i * 2000);
            animatorSet.setDuration(6000);
            this.animatorSets.add(animatorSet);
        }
        float screenWidth = (float) DisplayUtil.getScreenWidth(this);
        this.mPlayBgIv.getLayoutParams().width = (int) (1.3f * screenWidth);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.mPlayBgIv, "translationX", (float) ((int) (0.15f * screenWidth)), (float) ((int) (screenWidth * -0.15f)));
        ofFloat4.setInterpolator(null);
        ofFloat4.setDuration(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
        ofFloat4.setRepeatCount(-1);
        ofFloat4.setRepeatMode(2);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(ofFloat4);
        this.animatorSets.add(animatorSet2);
    }

    public void PlayAnim() {
        for (AnimatorSet animatorSet : this.animatorSets) {
            if (VERSION.SDK_INT >= 19) {
                if (animatorSet.isPaused()) {
                    animatorSet.resume();
                } else if (!animatorSet.isRunning()) {
                    animatorSet.start();
                }
            } else if (!animatorSet.isRunning()) {
                animatorSet.start();
            }
        }
    }

    public void PauseAnim() {
        for (AnimatorSet animatorSet : this.animatorSets) {
            if (VERSION.SDK_INT >= 19) {
                animatorSet.pause();
            } else {
                animatorSet.cancel();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        UpdateUI();
    }


    public void UpdateUI() {
        this.mixModel = SoundPlayerManager.getInstance(this).getMixItem();
        MixModel mixModel = this.mixModel;
        if (mixModel != null) {
            this.mixModel = SoundListDataSource.getMixItemById(mixModel.getMixSoundId());
            if (this.mixModel.getSoundList().size() >= 6) {
                this.linearLayoutManager = new GridLayoutManager(this, 5, 1, false);
            } else if (this.mixModel.getSoundList().size() < 1) {
                this.linearLayoutManager = new GridLayoutManager(this, 1, 1, false);
            } else {
                this.linearLayoutManager = new GridLayoutManager(this, this.mixModel.getSoundList().size() + 1, 1, false);
            }
            this.mSoundRcv.setLayoutManager(this.linearLayoutManager);
            this.mPlayBgIv.setImageResource(this.mixModel.getMixSoundCover().getBigCoverResId());
            this.mSoundName.setText(this.mixModel.getName());
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.mixModel.getSoundList());
            arrayList.add("Edit");
            this.slimAdapter.updateData(arrayList);
            this.slimAdapter.notifyDataSetChanged();
        }
        if (SoundPlayerManager.getInstance(this).getPlayerState() == 2) {
            this.mPlayControlView.setImageResource(R.drawable.vector_ic_play);
        } else if (SoundPlayerManager.getInstance(this).getPlayerState() == 1) {
            this.mPlayControlView.setImageResource(R.drawable.vector_ic_pause);
        }
        long longPreference = SharedPrefsUtils.getLongPreference(this, Constant.KEY_PLAY_TIME, 1140000);
        if (longPreference > 0) {
            this.mPlaySetTimeTv.setVisibility(8);
            this.mPlayCountTimeTv.setVisibility(0);
            this.mPlayCountTimeTv.setText(TimeUtils.convertMillisecondsToString(longPreference));
            return;
        }
        this.mPlaySetTimeTv.setVisibility(0);
        this.mPlayCountTimeTv.setVisibility(8);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.broadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.broadcastReceiver1);
    }
}
