package com.arapps.sleepsound.relaxandsleep.naturesounds.Activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.State;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import java.util.List;
import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector.Action;

public class CustomSelectActivity extends BaseActivity implements OnClickListener {
    private Guideline mGuidelineH5;
    private Guideline mGuidelineH71;
    private LinearLayout mLlClose;
    private RecyclerView mRcvSelectedSound;
    private TextView mTvSaveCustom;
    private SlimAdapter slimAdapter;
    private List<SoundModel> soundModels;

    class VideoDecoration extends ItemDecoration {
        VideoDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            LayoutManager layoutManager = recyclerView.getLayoutManager();
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (layoutManager instanceof GridLayoutManager) {
                int i = childAdapterPosition % 2;
                if (i == 0) {
                    rect.set(0, 0, DisplayUtil.dpToPx(4.0f), DisplayUtil.dpToPx(16.0f));
                } else {
                    rect.set(DisplayUtil.dpToPx(4.0f), 0, 0, DisplayUtil.dpToPx(16.0f));
                }
                int itemCount = state.getItemCount();
                if (itemCount > 0 && childAdapterPosition == itemCount - 1) {
                    if (i == 0) {
                        rect.set(0, 0, DisplayUtil.dpToPx(4.0f), DisplayUtil.dpToPx(60.0f));
                    } else {
                        rect.set(DisplayUtil.dpToPx(4.0f), 0, 0, DisplayUtil.dpToPx(60.0f));
                    }
                }
            } else if (layoutManager instanceof LinearLayoutManager) {
                rect.set(0, 0, 0, DisplayUtil.dpToPx(10.0f));
            }
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.custom_selected_activity);
        InitView();
        DisplayUtil.hideActionBar(this);
    }

    private void InitView() {
        this.mGuidelineH5 = findViewById(R.id.guideline_h_5);
        this.mGuidelineH71 = findViewById(R.id.guideline_h_71);
        this.mRcvSelectedSound = findViewById(R.id.rcv_selected_sound);
        this.mTvSaveCustom = findViewById(R.id.tv_save_custom);
        this.mLlClose = findViewById(R.id.ll_close);
        this.mTvSaveCustom.setOnClickListener(this);
        this.mLlClose.setOnClickListener(this);
        this.mRcvSelectedSound.addItemDecoration(new VideoDecoration());
    }
@Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_close) {
            finish();
        } else if (id == R.id.tv_save_custom) {
            startActivity(new Intent(this, SaveCustomActivity.class));
            finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SetUpList();
    }

    public void SetUpList() {
        this.mRcvSelectedSound.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        this.slimAdapter = SlimAdapter.create().register(R.layout.model_rcv_selected_sound, new SlimInjector<SoundModel>() {
            public void onInject(final SoundModel soundModel, final IViewInjector iViewInjector) {
                iViewInjector.image(R.id.iv_sound_icon, soundModel.getIconResId());
                iViewInjector.text(R.id.tv_sound_name, soundModel.getStr_name());
                iViewInjector.clicked(R.id.iv_delete_sound, new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(CustomSelectActivity.this, SoundPlayerService.class);
                        intent.setAction(SoundPlayerService.ACTION_CMD);
                        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_RELEASE);
                        intent.putExtra(SoundPlayerService.SOUND_ID, soundModel.getSoundId());
                        CustomSelectActivity.this.startService(intent);
                        CustomSelectActivity.this.soundModels.remove(soundModel);
                        CustomSelectActivity.this.slimAdapter.updateData(CustomSelectActivity.this.soundModels);
                        CustomSelectActivity.this.slimAdapter.notifyDataSetChanged();
                        if (CustomSelectActivity.this.soundModels.size() == 0) {
                            CustomSelectActivity.this.finish();
                        }
                    }
                });
                iViewInjector.with(R.id.seek_bar_sound_volume, new Action<AppCompatSeekBar>() {
                    public void action(AppCompatSeekBar appCompatSeekBar) {
                        appCompatSeekBar.setMax(100);
                        appCompatSeekBar.setProgress(soundModel.getVolume());
                        appCompatSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }

                            public void onProgressChanged(final SeekBar seekBar, int i, boolean z) {
                                iViewInjector.with(R.id.seek_bar_sound_volume, new Action<AppCompatSeekBar>() {
                                    public void action(AppCompatSeekBar appCompatSeekBar) {
                                        appCompatSeekBar.setProgress(seekBar.getProgress());
                                        SoundPlayerManager.getInstance(CustomSelectActivity.this).setVolume(soundModel, (float) appCompatSeekBar.getProgress());
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append(soundModel.getSoundId());
                                        stringBuilder.append(" ");
                                        stringBuilder.append(appCompatSeekBar.getProgress());
                                        Log.e("VOLUME", stringBuilder.toString());
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }).enableDiff().attachTo(mRcvSelectedSound);
        this.soundModels = SoundPlayerManager.getInstance(this).getPlayingSoundItem();
        this.slimAdapter.updateData(this.soundModels);
    }
}
