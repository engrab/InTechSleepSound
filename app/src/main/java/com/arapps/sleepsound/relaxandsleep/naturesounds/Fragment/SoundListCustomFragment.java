package com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;

import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.MixCustomActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

public class SoundListCustomFragment extends BaseFragment {
    int MAX_ITEM = 9;
    private int category = 0;
    private RecyclerView mRvListMix;
    private List<SoundModel> mixItems = new ArrayList();
    private SlimAdapter slimAdapter;


    public List<SoundModel> filter(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = this.MAX_ITEM * i; i2 < (i + 1) * this.MAX_ITEM; i2++) {
            if (i2 < SoundListDataSource.getListSoundItem(getActivity()).size()) {
                arrayList.add(SoundListDataSource.getListSoundItem(getActivity()).get(i2));
            }
        }
        return arrayList;
    }

    public void SetUpList() {
        this.mRvListMix.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        this.slimAdapter = SlimAdapter.create().register(R.layout.model_sounds_mix_custom_gridview, new SlimInjector<SoundModel>() {
            public void onInject(final SoundModel soundModel, IViewInjector iViewInjector) {
                iViewInjector.text((int) R.id.sound_name, soundModel.getStr_name());
                iViewInjector.image((int) R.id.sound_icon_iv, soundModel.getIconResId());
                iViewInjector.invisible(R.id.iv_sound_download);
                iViewInjector.clicked(R.id.sound_icon_bg_layout, new OnClickListener() {
                    public void onClick(View view) {
                        if (SoundListCustomFragment.this.getActivity() != null) {
                            ((MixCustomActivity) SoundListCustomFragment.this.getActivity()).AddSoundItem(soundModel);
                        }
                    }
                });
            }
        }).enableDiff().attachTo(mRvListMix);
        this.slimAdapter.updateData(this.mixItems);
    }

    public SoundListCustomFragment(int i) {
        this.category = i;
        switch (this.category) {
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
                return;
            default:
                this.mixItems = SoundListDataSource.getListSoundItem(getActivity());
                return;
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.model_vp_mix_sound, viewGroup, false);
        this.mRvListMix = (RecyclerView) inflate.findViewById(R.id.rcv_mix_sound);
        this.mRvListMix.addItemDecoration(new VideoDecoration());
        return inflate;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        SetUpList();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class VideoDecoration extends ItemDecoration {
        VideoDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            recyclerView.getLayoutManager();
            recyclerView.getChildAdapterPosition(view);
            rect.set(DisplayUtil.dpToPx(15.0f), DisplayUtil.dpToPx(7.0f), DisplayUtil.dpToPx(15.0f), DisplayUtil.dpToPx(7.0f));
        }
    }


}
