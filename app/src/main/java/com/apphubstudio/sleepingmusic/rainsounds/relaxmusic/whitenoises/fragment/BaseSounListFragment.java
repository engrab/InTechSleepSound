package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;

import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.baseClasses.FragmentBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.SoundList;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelSound;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.services.SoundPlayerManager;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.services.SoundPlayerService;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.DisplayUtil;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector.Action;

import java.util.ArrayList;
import java.util.List;

public class BaseSounListFragment extends FragmentBase {
    int MAX_ITEM = 9;
    SparseArray<ModelSound> mSparseArray = new SparseArray();
    private int mCategoryId = 0;
    private RecyclerView mRecyclerViewMixList;
    private List<ModelSound> mListItem = new ArrayList();
    private SlimAdapter mSlimAdapter;

    public static BaseSounListFragment getInstance(int i) {
        BaseSounListFragment sounListFragment = new BaseSounListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("category", i);
        sounListFragment.setArguments(bundle);
        return sounListFragment;
    }

    private List<ModelSound> filter(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = this.MAX_ITEM * i; i2 < (i + 1) * this.MAX_ITEM; i2++) {
            if (i2 < SoundList.getListSoundItem(getActivity()).size()) {
                arrayList.add(SoundList.getListSoundItem(getActivity()).get(i2));
            }
        }
        return arrayList;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.model_vp_mix_sound, viewGroup, false);
        this.mRecyclerViewMixList = inflate.findViewById(R.id.rcv_mix_sound);
        this.mRecyclerViewMixList.addItemDecoration(new VideoDecoration());
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getArguments() != null) {
            this.mCategoryId = getArguments().getInt("category");
            int i = this.mCategoryId;
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    this.mListItem = filter(i);
                    break;
                default:
                    this.mListItem = SoundList.getListSoundItem(getActivity());
                    break;
            }
        }
        setUpList();
    }

    public void onResume() {
        super.onResume();
        List<ModelSound> playingModelSound = SoundPlayerManager.getInstance(getActivity()).getPlayingSoundItem();
        this.mSparseArray.clear();
        for (ModelSound modelSound : playingModelSound) {
            this.mSparseArray.put(modelSound.getmSoundId(), modelSound);
        }
        this.mSlimAdapter.notifyDataSetChanged();
    }

    public void setUpList() {
        this.mSlimAdapter = SlimAdapter.create().register(R.layout.model_sounds_gridview, new SlimInjector<ModelSound>() {
            public void onInject(final ModelSound modelSound, final IViewInjector iViewInjector) {
                iViewInjector.text(R.id.sound_name, modelSound.getmStrName());
                iViewInjector.image(R.id.sound_icon_iv, modelSound.getmResId());
                iViewInjector.invisible(R.id.iv_sound_download);
                iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                    public void action(SeekBar appCompatSeekBar) {
                        appCompatSeekBar.setMax(100);
                        appCompatSeekBar.setProgress(modelSound.getmVol());
                        appCompatSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }

                            public void onProgressChanged(final SeekBar seekBar, int i, boolean z) {
                                iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                                    public void action(SeekBar appCompatSeekBar) {
                                        appCompatSeekBar.setProgress(seekBar.getProgress());
                                        SoundPlayerManager.getInstance(BaseSounListFragment.this.getActivity()).setVolume(modelSound, (float) appCompatSeekBar.getProgress());
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append(modelSound.getmSoundId());
                                        stringBuilder.append(" ");
                                        stringBuilder.append(appCompatSeekBar.getProgress());
                                        Log.e("VOLUME", stringBuilder.toString());
                                    }
                                });
                            }
                        });
                    }
                });
                iViewInjector.clicked(R.id.sound_icon_bg_layout, new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(BaseSounListFragment.this.getActivity(), SoundPlayerService.class);
                        intent.setAction(SoundPlayerService.ACTION_CMD);
                        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY);
                        intent.putExtra(SoundPlayerService.SOUND_ID, modelSound.getmSoundId());
                        BaseSounListFragment.this.getActivity().startService(intent);
                        iViewInjector.with(R.id.sound_seekbar, new Action<AppCompatSeekBar>() {
                            public void action(AppCompatSeekBar appCompatSeekBar) {
                                appCompatSeekBar.setMax(100);
                                appCompatSeekBar.setProgress(modelSound.getmVol());
                            }
                        });
                        if (!SoundPlayerManager.getInstance(BaseSounListFragment.this.getActivity()).isMaxPlayerStart()) {
                            iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                                public void action(SeekBar appCompatSeekBar) {
                                    if (appCompatSeekBar.getVisibility() == View.VISIBLE) {
                                        appCompatSeekBar.setVisibility(View.INVISIBLE);
                                        iViewInjector.background(R.id.sound_icon_bg_layout, R.drawable.shape_sound_icon_unselete_bg);
                                        return;
                                    }
                                    appCompatSeekBar.setVisibility(View.VISIBLE);
                                    iViewInjector.background(R.id.sound_icon_bg_layout, R.drawable.shape_sound_icon_seleted_bg);
                                }
                            });
                        } else if (SoundPlayerManager.getInstance(BaseSounListFragment.this.getActivity()).getSizePlayer() == 8) {
                            Toast.makeText(BaseSounListFragment.this.getActivity(), String.format(BaseSounListFragment.this.getActivity().getString(R.string.max_select_toast), "8"), Toast.LENGTH_SHORT).show();
                        }
                        if (SoundPlayerManager.getInstance(BaseSounListFragment.this.getActivity()).getSizePlayer() == 8 && SoundPlayerManager.getInstance(BaseSounListFragment.this.getActivity()).getSoundPlayerById(modelSound.getmSoundId()) != null) {
                            iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                                public void action(SeekBar appCompatSeekBar) {
                                    if (appCompatSeekBar.getVisibility() == View.VISIBLE) {
                                        appCompatSeekBar.setVisibility(View.INVISIBLE);
                                        iViewInjector.background(R.id.sound_icon_bg_layout, R.drawable.shape_sound_icon_unselete_bg);
                                        return;
                                    }
                                    appCompatSeekBar.setVisibility(View.VISIBLE);
                                    iViewInjector.background(R.id.sound_icon_bg_layout, R.drawable.shape_sound_icon_seleted_bg);
                                }
                            });
                        }
                        SoundPlayerManager.getInstance(BaseSounListFragment.this.getActivity()).getMixItem();
                    }
                });
                if (SoundPlayerManager.getInstance(BaseSounListFragment.this.getActivity()).getSoundPlayerById(modelSound.getmSoundId()) != null) {
                    iViewInjector.visibility(R.id.sound_seekbar, 0);
                    iViewInjector.background(R.id.sound_icon_bg_layout, R.drawable.shape_sound_icon_seleted_bg);
                    return;
                }
                iViewInjector.visibility(R.id.sound_seekbar, 4);
                iViewInjector.background(R.id.sound_icon_bg_layout, R.drawable.shape_sound_icon_unselete_bg);
            }
        }).enableDiff().attachTo(mRecyclerViewMixList);
        this.mSlimAdapter.updateData(this.mListItem);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                BaseSounListFragment.this.mSlimAdapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    class VideoDecoration extends ItemDecoration {
        VideoDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            recyclerView.getLayoutManager();
            recyclerView.getChildAdapterPosition(view);
            rect.set(DisplayUtil.dpToPx(12.0f), DisplayUtil.dpToPx(5.0f), DisplayUtil.dpToPx(12.0f), DisplayUtil.dpToPx(5.0f));
        }
    }
}
