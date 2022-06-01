package com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment;

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
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import java.util.ArrayList;
import java.util.List;
import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector.Action;

public class SounListFragment extends BaseFragment {
    int MAX_ITEM = 9;
    private int category = 0;
    private RecyclerView mRvListMix;
    private List<SoundModel> mixItems = new ArrayList();
    private SlimAdapter slimAdapter;
    SparseArray<SoundModel> soundItemSparseArray = new SparseArray();

    class VideoDecoration extends ItemDecoration {
        VideoDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            recyclerView.getLayoutManager();
            recyclerView.getChildAdapterPosition(view);
            rect.set(DisplayUtil.dpToPx(12.0f), DisplayUtil.dpToPx(5.0f), DisplayUtil.dpToPx(12.0f), DisplayUtil.dpToPx(5.0f));
        }
    }

    public static SounListFragment getInstance(int i) {
        SounListFragment sounListFragment = new SounListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("category", i);
        sounListFragment.setArguments(bundle);
        return sounListFragment;
    }

    private List<SoundModel> filter(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = this.MAX_ITEM * i; i2 < (i + 1) * this.MAX_ITEM; i2++) {
            if (i2 < SoundListDataSource.getListSoundItem(getActivity()).size()) {
                arrayList.add(SoundListDataSource.getListSoundItem(getActivity()).get(i2));
            }
        }
        return arrayList;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.model_vp_mix_sound, viewGroup, false);
        this.mRvListMix = inflate.findViewById(R.id.rcv_mix_sound);
        this.mRvListMix.addItemDecoration(new VideoDecoration());
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getArguments() != null) {
            this.category = getArguments().getInt("category");
            int i = this.category;
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
                    this.mixItems = filter(i);
                    break;
                default:
                    this.mixItems = SoundListDataSource.getListSoundItem(getActivity());
                    break;
            }
        }
        setUpList();
    }

    public void onResume() {
        super.onResume();
        List<SoundModel> playingSoundModel = SoundPlayerManager.getInstance(getActivity()).getPlayingSoundItem();
        this.soundItemSparseArray.clear();
        for (SoundModel soundModel : playingSoundModel) {
            this.soundItemSparseArray.put(soundModel.getSoundId(), soundModel);
        }
        this.slimAdapter.notifyDataSetChanged();
    }

    public void setUpList() {
        this.slimAdapter = SlimAdapter.create().register(R.layout.model_sounds_gridview, new SlimInjector<SoundModel>() {
            public void onInject(final SoundModel soundModel, final IViewInjector iViewInjector) {
                iViewInjector.text(R.id.sound_name, soundModel.getStr_name());
                iViewInjector.image(R.id.sound_icon_iv, soundModel.getIconResId());
                iViewInjector.invisible(R.id.iv_sound_download);
                iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                    public void action(SeekBar appCompatSeekBar) {
                        appCompatSeekBar.setMax(100);
                        appCompatSeekBar.setProgress(soundModel.getVolume());
                        appCompatSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }

                            public void onProgressChanged(final SeekBar seekBar, int i, boolean z) {
                                iViewInjector.with(R.id.sound_seekbar, new Action<SeekBar>() {
                                    public void action(SeekBar appCompatSeekBar) {
                                        appCompatSeekBar.setProgress(seekBar.getProgress());
                                        SoundPlayerManager.getInstance(SounListFragment.this.getActivity()).setVolume(soundModel, (float) appCompatSeekBar.getProgress());
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
                iViewInjector.clicked(R.id.sound_icon_bg_layout, new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(SounListFragment.this.getActivity(), SoundPlayerService.class);
                        intent.setAction(SoundPlayerService.ACTION_CMD);
                        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY);
                        intent.putExtra(SoundPlayerService.SOUND_ID, soundModel.getSoundId());
                        SounListFragment.this.getActivity().startService(intent);
                        iViewInjector.with(R.id.sound_seekbar, new Action<AppCompatSeekBar>() {
                            public void action(AppCompatSeekBar appCompatSeekBar) {
                                appCompatSeekBar.setMax(100);
                                appCompatSeekBar.setProgress(soundModel.getVolume());
                            }
                        });
                        if (!SoundPlayerManager.getInstance(SounListFragment.this.getActivity()).isMaxPlayerStart()) {
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
                        } else if (SoundPlayerManager.getInstance(SounListFragment.this.getActivity()).getSizePlayer() == 8) {
                            Toast.makeText(SounListFragment.this.getActivity(), String.format(SounListFragment.this.getActivity().getString(R.string.max_select_toast), "8"), Toast.LENGTH_SHORT).show();
                        }
                        if (SoundPlayerManager.getInstance(SounListFragment.this.getActivity()).getSizePlayer() == 8 && SoundPlayerManager.getInstance(SounListFragment.this.getActivity()).getSoundPlayerById(soundModel.getSoundId()) != null) {
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
                        SoundPlayerManager.getInstance(SounListFragment.this.getActivity()).getMixItem();
                    }
                });
                if (SoundPlayerManager.getInstance(SounListFragment.this.getActivity()).getSoundPlayerById(soundModel.getSoundId()) != null) {
                    iViewInjector.visibility(R.id.sound_seekbar, 0);
                    iViewInjector.background(R.id.sound_icon_bg_layout, R.drawable.shape_sound_icon_seleted_bg);
                    return;
                }
                iViewInjector.visibility(R.id.sound_seekbar, 4);
                iViewInjector.background(R.id.sound_icon_bg_layout, R.drawable.shape_sound_icon_unselete_bg);
            }
        }).enableDiff().attachTo(mRvListMix);
        this.slimAdapter.updateData(this.mixItems);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SounListFragment.this.slimAdapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}
