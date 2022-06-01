package com.arapps.sleepsound.relaxandsleep.naturesounds.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import com.facebook.drawee.view.SimpleDraweeView;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SaveDataHelper;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.Utils;

public class DeleteCustomMixActivity extends BaseActivity implements OnClickListener {
    private Intent intent;
    private ConstraintLayout mClRoot;
    private Guideline mGlH14;
    private Guideline mGlH8;
    private Guideline mGlH86;
    private Guideline mGlH92;
    private Group mGroupGoPremium;
    private Group mGroupMix;
    private Group mGroupSingleSound;
    private AppCompatImageView mIvClose;
    private AppCompatImageView mIvIconPremium;
    private AppCompatImageView mIvIconWatch;
    private AppCompatImageView mIvSoundIcon;
    private SimpleDraweeView mSivMixCover;
    private TextView mTvHint;
    private TextView mTvMixSoundName;
    private TextView mTvTitle;
    private TextView mTvUnlockAllSounds;
    private TextView mTvWatch;
    private View mViewGoSubscribeBg;
    private View mViewMixNameBg;
    private View mViewSoundIconBg;
    private View mViewWatchBtBg;
    private View mViewWatchBtBgSolid;
    private MixModel mixModel;
    private SoundModel soundModel;

@Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.delete_custom_mix_activity_);
        this.intent = getIntent();
        Intent intent = this.intent;
        if (intent != null) {
            int intExtra = intent.getIntExtra(SoundPlayerService.MIX_ID, -1);
            if (intExtra != -1) {
                this.mixModel = SoundListDataSource.getMixItemById(intExtra);
                InitView();
            } else {
                intExtra = this.intent.getIntExtra(SoundPlayerService.SOUND_ID, -1);
                if (intExtra != -1) {
                    this.soundModel = SoundListDataSource.getSoundItemById(intExtra);
                    InitView();
                }
            }
        } else {
            finish();
        }
        DisplayUtil.hideActionBar(this);
    }

    private void InitView() {
        this.mIvClose = findViewById(R.id.iv_close);
        this.mTvTitle = findViewById(R.id.tv_title);
        this.mSivMixCover = findViewById(R.id.siv_mix_cover);
        this.mViewMixNameBg = findViewById(R.id.view_mix_name_bg);
        this.mTvMixSoundName = findViewById(R.id.tv_mix_sound_name);
        this.mViewSoundIconBg = findViewById(R.id.view_sound_icon_bg);
        this.mIvSoundIcon = findViewById(R.id.iv_sound_icon);
        this.mTvHint = findViewById(R.id.tv_hint);
        this.mViewWatchBtBg = findViewById(R.id.view_watch_bt_bg);
        this.mViewWatchBtBgSolid = findViewById(R.id.view_watch_bt_bg_solid);
        this.mIvIconWatch = findViewById(R.id.iv_icon_watch);
        this.mTvWatch = findViewById(R.id.tv_watch);
        this.mViewGoSubscribeBg = findViewById(R.id.view_go_subscribe_bg);
        this.mIvIconPremium = findViewById(R.id.iv_icon_premium);
        this.mTvUnlockAllSounds = findViewById(R.id.tv_unlock_all_sounds);
        this.mGroupMix = findViewById(R.id.group_mix);
        this.mGroupSingleSound = findViewById(R.id.group_single_sound);
        this.mGroupGoPremium = findViewById(R.id.group_go_premium);
        this.mGlH8 = findViewById(R.id.gl_h_8);
        this.mGlH92 = findViewById(R.id.gl_h_92);
        this.mGlH14 = findViewById(R.id.gl_h_14);
        this.mGlH86 = findViewById(R.id.gl_h_86);
        this.mIvClose.setOnClickListener(this);
        this.mViewWatchBtBgSolid.setOnClickListener(this);
        this.mViewGoSubscribeBg.setOnClickListener(this);
        this.mClRoot = findViewById(R.id.cl_root);
        MixModel mixModel = this.mixModel;
        if (mixModel != null) {
            this.mSivMixCover.setImageURI(Utils.getUriToResource(this, mixModel.getMixSoundCover().getCoverResId()));
            this.mTvMixSoundName.setText(this.mixModel.getName());
            this.mGroupSingleSound.setVisibility(8);
            this.mGroupMix.setVisibility(0);
            return;
        }
        SoundModel soundModel = this.soundModel;
        if (soundModel != null) {
            this.mIvSoundIcon.setImageResource(soundModel.getIconResId());
            this.mGroupSingleSound.setVisibility(0);
            this.mGroupMix.setVisibility(8);
        }
    }
@Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_close) {
            finish();
        } else if (id != R.id.view_go_subscribe_bg && id == R.id.view_watch_bt_bg_solid) {
            DeleteMix();
        }
    }

    private void DeleteMix() {
        SaveDataHelper.removeCustomMixInJSONArray(this, this.mixModel);
        SoundListDataSource.createData(this);
        Toast.makeText(this, R.string.delete_success, 0).show();
        finish();
    }
}
