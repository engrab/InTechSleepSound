package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.adapter.AdapterViewPagerWrap;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ads.AdsUtils;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ads.Unity;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.baseClasses.ActivityBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.fragment.BaseSoundListCustomFragment;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.HelperSaveData;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.SoundList;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelMix;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelSound;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.services.SoundPlayerManager;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.services.SoundPlayerService;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.DisplayUtil;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.customView.BasePagerWrapContent;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.unity3d.mediation.IInterstitialAdShowListener;
import com.unity3d.mediation.errors.ShowError;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector.Action;

import java.util.ArrayList;
import java.util.List;

import io.github.kshitij_jain.indicatorview.IndicatorView;

public class MixCustomActivityBase extends ActivityBase implements OnClickListener {
    private LinearLayoutManager linearLayoutManager;
    private IndicatorView mCircleIndicatorView;
    private LinearLayout mCustomSoundsCancelLayout;
    private RecyclerView mCustomSoundsRcv;
    private LinearLayout mCustomSoundsResetLayout;
    private AppCompatImageView mCustomSoundsSaveView;
    private BasePagerWrapContent mCustomSoundsSvp;
    private LinearLayout mRootView;
    private ModelMix modelMix;
    private ModelMix modelMix1;
    private SlimAdapter slimAdapter;
    private List<ModelSound> soundList = new ArrayList();
    private AdapterViewPagerWrap viewPagerAdapter;
    int adcount = 1;
    private InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.mix_custom_activity);
        InitView();
        DisplayUtil.hideActionBar(this);
        this.modelMix = SoundPlayerManager.getInstance(this).getMixItem();
        this.soundList = SoundPlayerManager.getInstance(this).getPlayingSoundItem();
        SetUpViewPager();
        SetupList();
        this.modelMix1 = this.modelMix;
    }

    private void InitView() {
        this.mCustomSoundsRcv = findViewById(R.id.custom_sounds_rcv);
        this.mCustomSoundsSvp = findViewById(R.id.custom_sounds_svp);
        this.mCircleIndicatorView = findViewById(R.id.circle_indicator_view);
        this.mCustomSoundsCancelLayout = findViewById(R.id.custom_sounds_cancel_layout);
        this.mCustomSoundsCancelLayout.setOnClickListener(this);
        this.mCustomSoundsSaveView = findViewById(R.id.custom_sounds_save_view);
        this.mCustomSoundsSaveView.setOnClickListener(this);
        this.mCustomSoundsResetLayout = findViewById(R.id.custom_sounds_reset_layout);
        this.mCustomSoundsResetLayout.setOnClickListener(this);
        this.mRootView = findViewById(R.id.root_view);
    }

    public void SetUpViewPager() {
        this.mCustomSoundsSvp.setOffscreenPageLimit(10);
        this.viewPagerAdapter = new AdapterViewPagerWrap(getSupportFragmentManager(), -1);
        this.viewPagerAdapter.addFragment(new BaseSoundListCustomFragment(0));
        this.viewPagerAdapter.addFragment(new BaseSoundListCustomFragment(1));
        this.viewPagerAdapter.addFragment(new BaseSoundListCustomFragment(2));
        this.viewPagerAdapter.addFragment(new BaseSoundListCustomFragment(3));
        this.viewPagerAdapter.addFragment(new BaseSoundListCustomFragment(4));
        this.viewPagerAdapter.addFragment(new BaseSoundListCustomFragment(5));
        this.viewPagerAdapter.addFragment(new BaseSoundListCustomFragment(6));
        this.mCustomSoundsSvp.setAdapter(this.viewPagerAdapter);
        this.mCustomSoundsSvp.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                MixCustomActivityBase.this.mCircleIndicatorView.setCurrentPage(i);
            }
        });
        this.mCircleIndicatorView.setPageIndicators(this.viewPagerAdapter.getCount());
        this.mCircleIndicatorView.setActiveIndicatorColor(R.color.white);
        this.mCircleIndicatorView.setActiveIndicatorColor(R.color.white_20);
    }

    public void SetupList() {
        this.linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.mCustomSoundsRcv.setLayoutManager(this.linearLayoutManager);
        this.slimAdapter = SlimAdapter.create().register(R.layout.model_rcv_mix_custom_sound, new SlimInjector<ModelSound>() {
            public void onInject(final ModelSound modelSound, IViewInjector iViewInjector) {
                iViewInjector.text(R.id.sound_name, modelSound.getmStrName());
                iViewInjector.image(R.id.sound_icon_iv, modelSound.getmResId());
                iViewInjector.clicked(R.id.sound_delete_iv, new OnClickListener() {
                    public void onClick(View view) {

                        if (adcount % 2 == 0) {




                            mInterstitialAd = AdsUtils.getInterstitial();
                            if (mInterstitialAd != null) {//admob
                                mInterstitialAd.show(MixCustomActivityBase.this);
                                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        super.onAdDismissedFullScreenContent();
                                        Toast.makeText(MixCustomActivityBase.this, modelSound.getmStrName(), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MixCustomActivityBase.this, SoundPlayerService.class);
                                        intent.setAction(SoundPlayerService.ACTION_CMD);
                                        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_RELEASE);
                                        intent.putExtra(SoundPlayerService.SOUND_ID, modelSound.getmSoundId());
                                        MixCustomActivityBase.this.startService(intent);
                                        AdsUtils.loadInterstitial(MixCustomActivityBase.this);
                                    }
                                });
                            } else if (Unity.isAdLoaded()) {//unity fb
                                Unity.showInterstitial(MixCustomActivityBase.this, new IInterstitialAdShowListener() {
                                    @Override
                                    public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                    }

                                    @Override
                                    public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                    }

                                    @Override
                                    public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {
                                        Toast.makeText(MixCustomActivityBase.this, modelSound.getmStrName(), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MixCustomActivityBase.this, SoundPlayerService.class);
                                        intent.setAction(SoundPlayerService.ACTION_CMD);
                                        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_RELEASE);
                                        intent.putExtra(SoundPlayerService.SOUND_ID, modelSound.getmSoundId());
                                        MixCustomActivityBase.this.startService(intent);
                                        Unity.loadInterstitial(MixCustomActivityBase.this);//load unity ads
                                    }

                                    @Override
                                    public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                                    }

                                });
                                AdsUtils.loadInterstitial(MixCustomActivityBase.this);//load admob ad
                            } else {
                                Toast.makeText(MixCustomActivityBase.this, modelSound.getmStrName(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MixCustomActivityBase.this, SoundPlayerService.class);
                                intent.setAction(SoundPlayerService.ACTION_CMD);
                                intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_RELEASE);
                                intent.putExtra(SoundPlayerService.SOUND_ID, modelSound.getmSoundId());
                                MixCustomActivityBase.this.startService(intent);
                                AdsUtils.loadInterstitial(MixCustomActivityBase.this);//load admob ad
                                Unity.loadInterstitial(MixCustomActivityBase.this);//load unity ads
                            }


                        } else {
                            Toast.makeText(MixCustomActivityBase.this, modelSound.getmStrName(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MixCustomActivityBase.this, SoundPlayerService.class);
                            intent.setAction(SoundPlayerService.ACTION_CMD);
                            intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_RELEASE);
                            intent.putExtra(SoundPlayerService.SOUND_ID, modelSound.getmSoundId());
                            MixCustomActivityBase.this.startService(intent);
                        }
                        MixCustomActivityBase.this.soundList.remove(modelSound);
                        MixCustomActivityBase.this.slimAdapter.updateData(MixCustomActivityBase.this.soundList);
                        MixCustomActivityBase.this.slimAdapter.notifyDataSetChanged();
                        adcount++;


                    }
                });
                iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                    public void action(SeekBar seekBar) {
                        seekBar.setMax(100);
                        seekBar.setProgress(modelSound.getmVol());
                    }
                });
                iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                    public void action(final SeekBar seekBar) {
                        seekBar.setOnTouchListener(new OnTouchListener() {
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                int action = motionEvent.getAction();
                                if (action == 0) {
                                    MixCustomActivityBase.this.mCustomSoundsRcv.setClickable(false);
                                } else if (action != 1) {
                                    return false;
                                }
                                MixCustomActivityBase.this.mCustomSoundsRcv.setClickable(true);
                                return true;
                            }
                        });
                        seekBar.setMax(100);
                        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }

                            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                                seekBar.setProgress(seekBar.getProgress());
                                SoundPlayerManager.getInstance(MixCustomActivityBase.this).setVolume(modelSound, (float) seekBar.getProgress());
                            }
                        });
                    }
                });
            }
        }).enableDiff().attachTo(mCustomSoundsRcv);
        this.slimAdapter.updateData(this.soundList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_sounds_cancel_layout:
                SoundPlayerManager.getInstance(this).removeAllPlayer();
                SoundPlayerManager.getInstance(this).setMixItem(null);

                Intent intent = new Intent(this, SoundPlayerService.class);
                intent.setAction(SoundPlayerService.ACTION_CMD);
                intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY_MIX);
                intent.putExtra(SoundPlayerService.MIX_ID, this.modelMix1.getmMixSoundId());
                startService(intent);
                finish();
                return;
            case R.id.custom_sounds_reset_layout:
                Reset();
                return;
            case R.id.custom_sounds_save_view:
                this.modelMix = SoundPlayerManager.getInstance(this).getMixItem();
                ModelMix modelMix = this.modelMix;
                if (modelMix == null) {
                    Reset();
                    Toast.makeText(this, "Reset mix", Toast.LENGTH_SHORT).show();
                    return;
                }
                modelMix.setmSoundList(SoundPlayerManager.getInstance(this).getPlayingSoundItem());
                Toast.makeText(this, R.string.save_successfully, Toast.LENGTH_SHORT).show();
                HelperSaveData.addCustomMixInJSONArray(this, this.modelMix);
                SoundList.createData(this);
                finish();
                return;
            default:
                return;
        }
    }

    public void Reset() {
        this.modelMix = SoundPlayerManager.getInstance(this).getMixItem();
        HelperSaveData.removeCustomMixInJSONArray(this, this.modelMix1);
        SoundList.createData(this);
        this.modelMix = SoundList.getMixItemById(this.modelMix1.getmMixSoundId());
        this.soundList = this.modelMix.getmSoundList();
        this.slimAdapter.updateData(this.soundList);
        this.slimAdapter.notifyDataSetChanged();
        SoundPlayerManager.getInstance(this).removeAllPlayer();
        SoundPlayerManager.getInstance(this).setMixItem(null);
        Intent intent = new Intent(this, SoundPlayerService.class);
        intent.setAction(SoundPlayerService.ACTION_CMD);
        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY_MIX);
        intent.putExtra(SoundPlayerService.MIX_ID, this.modelMix.getmMixSoundId());
        startService(intent);
    }

    public void AddSoundItem(ModelSound modelSound) {
        if (!SoundPlayerManager.getInstance(this).isMaxPlayerStart() && !IsPlaying(modelSound)) {
            Intent intent = new Intent(this, SoundPlayerService.class);
            intent.setAction(SoundPlayerService.ACTION_CMD);
            intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY);
            intent.putExtra(SoundPlayerService.SOUND_ID, modelSound.getmSoundId());
            startService(intent);
            this.soundList.add(modelSound);
            this.slimAdapter.updateData(this.soundList);
            this.slimAdapter.notifyDataSetChanged();
            this.mCustomSoundsRcv.scrollToPosition(this.soundList.size() - 1);
        } else if (SoundPlayerManager.getInstance(this).isMaxPlayerStart()) {
            Toast.makeText(this, String.format(getString(R.string.max_select_toast), "8"), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean IsPlaying(ModelSound modelSound) {
        boolean z = false;
        for (ModelSound soundId : this.soundList) {
            if (soundId.getmSoundId() == modelSound.getmSoundId()) {
                z = true;
            }
        }
        return z;
    }
}
