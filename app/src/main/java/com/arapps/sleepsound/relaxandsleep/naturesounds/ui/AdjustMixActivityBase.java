package com.arapps.sleepsound.relaxandsleep.naturesounds.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.work.WorkRequest;

import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.ads.AdsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.baseClasses.ActivityBase;
import com.arapps.sleepsound.relaxandsleep.naturesounds.helper.SoundList;
import com.arapps.sleepsound.relaxandsleep.naturesounds.model.ModelMix;
import com.arapps.sleepsound.relaxandsleep.naturesounds.services.SoundPlayerManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.Utils;
import com.google.android.gms.ads.AdView;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector.Action;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdjustMixActivityBase extends ActivityBase implements OnClickListener {
    private final List<View> animlist = new ArrayList();
    private final List<AnimatorSet> animatorSetList = new ArrayList();
    private final BroadcastReceiver broadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(SoundPlayerService.RESULT_CODE, 0) == 1) {
                int intExtra = intent.getIntExtra(SoundPlayerService.UPDATE_PLAY_STATE, 0);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(intExtra);
                stringBuilder.append("");
                Log.e("PLAYER_STATE", stringBuilder.toString());
                if (intExtra == 0) {
                    AdjustMixActivityBase.this.mMixSoundControlIv.setImageResource(R.drawable.vector_ic_play);
                    AdjustMixActivityBase.this.PauseAnim();
                } else if (intExtra == 1) {
                    AdjustMixActivityBase.this.mMixSoundControlIv.setImageResource(R.drawable.vector_ic_pause);
                    AdjustMixActivityBase.this.PlayAnim();
                    AdjustMixActivityBase.this.mSlimAdapter.notifyDataSetChanged();
                } else if (intExtra == 2) {
                    AdjustMixActivityBase.this.mMixSoundControlIv.setImageResource(R.drawable.vector_ic_play);
                    AdjustMixActivityBase.this.PauseAnim();
                    AdjustMixActivityBase.this.PlayAnim();
                    try {
                        AdjustMixActivityBase.this.mSlimAdapter.notifyDataSetChanged();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    };
    private final int category = 0;
    private AppCompatImageView mMixSoundControlIv;
    private FrameLayout mMixSoundControlLayout;
    private View mMixSoundCoverAnimView1;
    private View mMixSoundCoverAnimView2;
    private View mMixSoundCoverAnimView3;
    private CircleImageView mMixSoundCoverIv;
    private TextView mMixSoundNameTv;
    private RecyclerView mMixSoundRcv;
    private SlimAdapter mSlimAdapter;
    private ImageView mPlayBgIv;
    private RelativeLayout m_RelPlayView;
    private NestedScrollView mScrollView;
    private Toolbar mToolbar;
    ModelMix mModelMix;
    private List<ModelMix> modelMixes = new ArrayList();

    static class VideoDecoration extends ItemDecoration {
        VideoDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            LayoutManager layoutManager = recyclerView.getLayoutManager();
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (layoutManager instanceof GridLayoutManager) {
                if (childAdapterPosition % 2 == 0) {
                    rect.set(0, 0, DisplayUtil.dpToPx(10.0f), DisplayUtil.dpToPx(16.0f));
                } else {
                    rect.set(DisplayUtil.dpToPx(10.0f), 0, 0, DisplayUtil.dpToPx(16.0f));
                }
            } else if (layoutManager instanceof LinearLayoutManager) {
                rect.set(0, 0, 0, DisplayUtil.dpToPx(16.0f));
            }
        }
    }

    public List<ModelMix> filter(int i) {
        ArrayList arrayList = new ArrayList();
        for (ModelMix modelMix : SoundList.getListMixItem(this)) {
            if (modelMix.getmCategoryPos() == i) {
                arrayList.add(modelMix);
            }
        }
        return arrayList;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.adjust_mix_activity);

        AdsUtils.showBanner(AdjustMixActivityBase.this, findViewById(R.id.llAds));


        this.mModelMix = SoundPlayerManager.getInstance(this).getMixItem();
        InitView();
        DisplayUtil.hideActionBar(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.broadcastReceiver1, new IntentFilter(SoundPlayerService.ACTION_UPDATE_PLAY_STATE));
    }

    public void SetUpList() {
        this.mMixSoundRcv.setNestedScrollingEnabled(false);
        this.mMixSoundRcv.setFocusable(false);
        this.mMixSoundRcv.setLayoutManager(new GridLayoutManager(this, 2));
        this.mMixSoundRcv.addItemDecoration(new VideoDecoration());
        this.mSlimAdapter = SlimAdapter.create().register(R.layout.model_mix, new SlimInjector<ModelMix>() {
            public void onInject(final ModelMix modelMix, IViewInjector iViewInjector) {
                iViewInjector.text(R.id.title_text, modelMix.getmName());
                iViewInjector.with(R.id.cover_image, new Action<ImageView>() {
                    public void action(ImageView simpleDraweeView) {
                        simpleDraweeView.setImageURI(Utils.getUriToResource(AdjustMixActivityBase.this, modelMix.getmMixSoundCover().getmCoverResId1()));
                    }
                });
                iViewInjector.invisible(R.id.ads_icon);
                iViewInjector.clicked(R.id.cover_container, new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(AdjustMixActivityBase.this, SoundPlayerService.class);
                        intent.setAction(SoundPlayerService.ACTION_CMD);
                        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY_MIX);
                        intent.putExtra(SoundPlayerService.MIX_ID, modelMix.getmMixSoundId());
                        AdjustMixActivityBase.this.startService(intent);
                        AdjustMixActivityBase.this.mModelMix = modelMix;
                        AdjustMixActivityBase.this.UpdateUI();
                        AdjustMixActivityBase.this.mMixSoundControlIv.setImageResource(R.drawable.vector_ic_pause);
                    }
                });
                if (SoundPlayerManager.getInstance(AdjustMixActivityBase.this).getPlayerState() == 2) {
                    iViewInjector.visibility(R.id.view_play_state_bg, 0);
                    iViewInjector.image(R.id.iv_play_state, R.drawable.vector_ic_play);
                } else if (AdjustMixActivityBase.this.mModelMix.getmMixSoundId() == modelMix.getmMixSoundId()) {
                    iViewInjector.visibility(R.id.view_play_state_bg, 0);
                    iViewInjector.image(R.id.iv_play_state, R.drawable.vector_ic_pause);
                } else {
                    iViewInjector.visibility(R.id.view_play_state_bg, 0);
                    iViewInjector.image(R.id.iv_play_state, R.drawable.vector_ic_play);
                }
            }
        }).enableDiff().attachTo(mMixSoundRcv);
        this.modelMixes = SoundList.getListMixItem(this);
        this.mSlimAdapter.updateData(this.modelMixes);
    }

    private void InitView() {
        mPlayBgIv = findViewById(R.id.play_bg_iv);
        mPlayBgIv.setOnClickListener(this);
        mMixSoundCoverAnimView1 = findViewById(R.id.mix_sound_cover_anim_view1);
        mMixSoundCoverAnimView1.setOnClickListener(this);
        mMixSoundCoverAnimView2 = findViewById(R.id.mix_sound_cover_anim_view2);
        mMixSoundCoverAnimView2.setOnClickListener(this);
        mMixSoundCoverAnimView3 = findViewById(R.id.mix_sound_cover_anim_view3);
        mMixSoundCoverAnimView3.setOnClickListener(this);
        mMixSoundCoverIv = findViewById(R.id.mix_sound_cover_iv);
        mMixSoundCoverIv.setOnClickListener(this);
        mMixSoundControlIv = findViewById(R.id.mix_sound_control_iv);
        mMixSoundControlLayout = findViewById(R.id.mix_sound_control_layout);
        mMixSoundControlLayout.setOnClickListener(this);
        m_RelPlayView = findViewById(R.id.play_view);
        m_RelPlayView.setOnClickListener(this);
        mMixSoundNameTv = findViewById(R.id.mix_sound_name_tv);
        mMixSoundNameTv.setOnClickListener(this);
        mMixSoundRcv = findViewById(R.id.mix_sound_rcv);
        mMixSoundRcv.setOnClickListener(this);
        mScrollView = findViewById(R.id.scroll_view);
        mScrollView.setOnClickListener(this);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setOnClickListener(this);
        mToolbar.setNavigationOnClickListener(view -> AdjustMixActivityBase.this.finish());
        this.animlist.add(findViewById(R.id.mix_sound_cover_anim_view1));
        this.animlist.add(findViewById(R.id.mix_sound_cover_anim_view2));
        this.animlist.add(findViewById(R.id.mix_sound_cover_anim_view3));
        LayoutParams layoutParams = findViewById(R.id.play_view).getLayoutParams();
        int m18448a = DisplayUtil.m18448a(this, 70.0f, 41.0f);
        layoutParams.width = m18448a;
        layoutParams.height = m18448a;
        SetUpList();
        CreateAnim();
        PlayAnim();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.mix_sound_control_layout) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SoundPlayerManager.getInstance(this).getPlayerState());
            stringBuilder.append("");
            Log.e("STATE", stringBuilder.toString());
            int playerState = SoundPlayerManager.getInstance(this).getPlayerState();
            String str = SoundPlayerService.CMD_NAME;
            String str2 = SoundPlayerService.ACTION_CMD;
            Intent intent;
            if (playerState == 2) {
                intent = new Intent(this, SoundPlayerService.class);
                intent.setAction(str2);
                intent.putExtra(str, SoundPlayerService.CMD_RESUME_ALL);
                startService(intent);
                this.mMixSoundControlIv.setImageResource(R.drawable.vector_ic_pause);
            } else if (SoundPlayerManager.getInstance(this).getPlayerState() == 1) {
                intent = new Intent(this, SoundPlayerService.class);
                intent.setAction(str2);
                intent.putExtra(str, SoundPlayerService.CMD_PAUSE_ALL);
                if (VERSION.SDK_INT >= 26) {
                    startForegroundService(intent);
                } else {
                    startService(intent);
                }
                this.mMixSoundControlIv.setImageResource(R.drawable.vector_ic_play);
            }
            this.mSlimAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        UpdateUI();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void UpdateUI() {
        if (SoundPlayerManager.getInstance(this).getPlayerState() == 2) {
            this.mMixSoundControlIv.setImageResource(R.drawable.vector_ic_play);
        } else if (SoundPlayerManager.getInstance(this).getPlayerState() == 1) {
            this.mMixSoundControlIv.setImageResource(R.drawable.vector_ic_pause);
        }
        this.mSlimAdapter.notifyDataSetChanged();
        this.mMixSoundNameTv.setText(this.mModelMix.getmName());
        this.mMixSoundCoverIv.setImageResource(this.mModelMix.getmMixSoundCover().getmCoverResId1());
        this.mPlayBgIv.setImageResource(this.mModelMix.getmMixSoundCover().getmCoverResId());
        this.mSlimAdapter.notifyDataSetChanged();
    }

    public void PauseAnim() {
        for (AnimatorSet animatorSet : this.animatorSetList) {
            if (VERSION.SDK_INT >= 19) {
                animatorSet.pause();
            } else {
                animatorSet.cancel();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.broadcastReceiver1);
    }

    public void PlayAnim() {
        for (AnimatorSet animatorSet : this.animatorSetList) {
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

    private void CreateAnim() {
        for (int i = 0; i < this.animlist.size(); i++) {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.animlist.get(i), "alpha", 0.3f, 0.0f);
            ofFloat.setRepeatCount(-1);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.animlist.get(i), "scaleX", 1.0f, 1.3f);
            ofFloat2.setRepeatCount(-1);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.animlist.get(i), "scaleY", 1.0f, 1.3f);
            ofFloat3.setRepeatCount(-1);
            animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
            animatorSet.setInterpolator(null);
            animatorSet.setStartDelay(i * 2000);
            animatorSet.setDuration(6000);
            this.animatorSetList.add(animatorSet);
        }
        float screenWidth = (float) DisplayUtil.getScreenWidth(this);
        this.mPlayBgIv.getLayoutParams().width = (int) (1.3f * screenWidth);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.mPlayBgIv, "translationX", (float) ((int) (0.15f * screenWidth)), (float) ((int) (screenWidth * -0.15f)));
        ofFloat4.setInterpolator(null);
        ofFloat4.setDuration(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
        ofFloat4.setRepeatCount(-1);
        ofFloat4.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(ofFloat4);
        this.animatorSetList.add(animatorSet2);
    }


}
