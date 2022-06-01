package com.arapps.sleepsound.relaxandsleep.naturesounds.Activity;

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

import com.arapps.sleepsound.relaxandsleep.naturesounds.Adapter.ViewPagerWrapAdapter;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.SoundListCustomFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SaveDataHelper;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.AdsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.customView.WrapContentViewPager;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector.Action;

import java.util.ArrayList;
import java.util.List;

import io.github.kshitij_jain.indicatorview.IndicatorView;

public class MixCustomActivity extends BaseActivity implements OnClickListener {
    private LinearLayoutManager linearLayoutManager;
    private IndicatorView mCircleIndicatorView;
    private LinearLayout mCustomSoundsCancelLayout;
    private RecyclerView mCustomSoundsRcv;
    private LinearLayout mCustomSoundsResetLayout;
    private AppCompatImageView mCustomSoundsSaveView;
    private WrapContentViewPager mCustomSoundsSvp;
    private LinearLayout mRootView;
    private MixModel mixModel;
    private MixModel mixModel1;
    private SlimAdapter slimAdapter;
    private List<SoundModel> soundList = new ArrayList();
    private ViewPagerWrapAdapter viewPagerAdapter;
    int adcount = 1;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.mix_custom_activity);
        InitView();
        DisplayUtil.hideActionBar(this);
        this.mixModel = SoundPlayerManager.getInstance(this).getMixItem();
        this.soundList = SoundPlayerManager.getInstance(this).getPlayingSoundItem();
        SetUpViewPager();
        SetupList();
        this.mixModel1 = this.mixModel;
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
        this.viewPagerAdapter = new ViewPagerWrapAdapter(getSupportFragmentManager(), -1);
        this.viewPagerAdapter.addFragment(new SoundListCustomFragment(0));
        this.viewPagerAdapter.addFragment(new SoundListCustomFragment(1));
        this.viewPagerAdapter.addFragment(new SoundListCustomFragment(2));
        this.viewPagerAdapter.addFragment(new SoundListCustomFragment(3));
        this.viewPagerAdapter.addFragment(new SoundListCustomFragment(4));
        this.viewPagerAdapter.addFragment(new SoundListCustomFragment(5));
        this.viewPagerAdapter.addFragment(new SoundListCustomFragment(6));
        this.mCustomSoundsSvp.setAdapter(this.viewPagerAdapter);
        this.mCustomSoundsSvp.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                MixCustomActivity.this.mCircleIndicatorView.setCurrentPage(i);
            }
        });
        this.mCircleIndicatorView.setPageIndicators(this.viewPagerAdapter.getCount());
        this.mCircleIndicatorView.setActiveIndicatorColor(R.color.white);
        this.mCircleIndicatorView.setActiveIndicatorColor(R.color.white_20);
    }

    public void SetupList() {
        this.linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.mCustomSoundsRcv.setLayoutManager(this.linearLayoutManager);
        this.slimAdapter = SlimAdapter.create().register(R.layout.model_rcv_mix_custom_sound, new SlimInjector<SoundModel>() {
            public void onInject(final SoundModel soundModel, IViewInjector iViewInjector) {
                iViewInjector.text(R.id.sound_name, soundModel.getStr_name());
                iViewInjector.image(R.id.sound_icon_iv, soundModel.getIconResId());
                iViewInjector.clicked(R.id.sound_delete_iv, new OnClickListener() {
                    public void onClick(View view) {

                        if (adcount % 2 == 0) {

                            AdsUtils.ShowInterstitial(getApplicationContext());

                            Toast.makeText(MixCustomActivity.this, soundModel.getStr_name(), 0).show();
                            Intent intent = new Intent(MixCustomActivity.this, SoundPlayerService.class);
                            intent.setAction(SoundPlayerService.ACTION_CMD);
                            intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_RELEASE);
                            intent.putExtra(SoundPlayerService.SOUND_ID, soundModel.getSoundId());
                            MixCustomActivity.this.startService(intent);

                        } else {
                            Toast.makeText(MixCustomActivity.this, soundModel.getStr_name(), 0).show();
                            Intent intent = new Intent(MixCustomActivity.this, SoundPlayerService.class);
                            intent.setAction(SoundPlayerService.ACTION_CMD);
                            intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_RELEASE);
                            intent.putExtra(SoundPlayerService.SOUND_ID, soundModel.getSoundId());
                            MixCustomActivity.this.startService(intent);
                        }
                        MixCustomActivity.this.soundList.remove(soundModel);
                        MixCustomActivity.this.slimAdapter.updateData(MixCustomActivity.this.soundList);
                        MixCustomActivity.this.slimAdapter.notifyDataSetChanged();
                        adcount++;


                    }
                });
                iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                    public void action(SeekBar seekBar) {
                        seekBar.setMax(100);
                        seekBar.setProgress(soundModel.getVolume());
                    }
                });
                iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                    public void action(final SeekBar seekBar) {
                        seekBar.setOnTouchListener(new OnTouchListener() {
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                int action = motionEvent.getAction();
                                if (action == 0) {
                                    MixCustomActivity.this.mCustomSoundsRcv.setClickable(false);
                                } else if (action != 1) {
                                    return false;
                                }
                                MixCustomActivity.this.mCustomSoundsRcv.setClickable(true);
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
                                SoundPlayerManager.getInstance(MixCustomActivity.this).setVolume(soundModel, (float) seekBar.getProgress());
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
                intent.putExtra(SoundPlayerService.MIX_ID, this.mixModel1.getMixSoundId());
                startService(intent);
                finish();
                return;
            case R.id.custom_sounds_reset_layout:
                Reset();
                return;
            case R.id.custom_sounds_save_view:
                this.mixModel = SoundPlayerManager.getInstance(this).getMixItem();
                MixModel mixModel = this.mixModel;
                if (mixModel == null) {
                    Reset();
                    Toast.makeText(this, "Reset mix", 0).show();
                    return;
                }
                mixModel.setSoundList(SoundPlayerManager.getInstance(this).getPlayingSoundItem());
                Toast.makeText(this, R.string.save_successfully, 0).show();
                SaveDataHelper.addCustomMixInJSONArray(this, this.mixModel);
                SoundListDataSource.createData(this);
                finish();
                return;
            default:
                return;
        }
    }

    public void Reset() {
        this.mixModel = SoundPlayerManager.getInstance(this).getMixItem();
        SaveDataHelper.removeCustomMixInJSONArray(this, this.mixModel1);
        SoundListDataSource.createData(this);
        this.mixModel = SoundListDataSource.getMixItemById(this.mixModel1.getMixSoundId());
        this.soundList = this.mixModel.getSoundList();
        this.slimAdapter.updateData(this.soundList);
        this.slimAdapter.notifyDataSetChanged();
        SoundPlayerManager.getInstance(this).removeAllPlayer();
        SoundPlayerManager.getInstance(this).setMixItem(null);
        Intent intent = new Intent(this, SoundPlayerService.class);
        intent.setAction(SoundPlayerService.ACTION_CMD);
        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY_MIX);
        intent.putExtra(SoundPlayerService.MIX_ID, this.mixModel.getMixSoundId());
        startService(intent);
    }

    public void AddSoundItem(SoundModel soundModel) {
        if (!SoundPlayerManager.getInstance(this).isMaxPlayerStart() && !IsPlaying(soundModel)) {
            Intent intent = new Intent(this, SoundPlayerService.class);
            intent.setAction(SoundPlayerService.ACTION_CMD);
            intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY);
            intent.putExtra(SoundPlayerService.SOUND_ID, soundModel.getSoundId());
            startService(intent);
            this.soundList.add(soundModel);
            this.slimAdapter.updateData(this.soundList);
            this.slimAdapter.notifyDataSetChanged();
            this.mCustomSoundsRcv.scrollToPosition(this.soundList.size() - 1);
        } else if (SoundPlayerManager.getInstance(this).isMaxPlayerStart()) {
            Toast.makeText(this, String.format(getString(R.string.max_select_toast), "8"), 0).show();
        }
    }

    public boolean IsPlaying(SoundModel soundModel) {
        boolean z = false;
        for (SoundModel soundId : this.soundList) {
            if (soundId.getSoundId() == soundModel.getSoundId()) {
                z = true;
            }
        }
        return z;
    }
}
