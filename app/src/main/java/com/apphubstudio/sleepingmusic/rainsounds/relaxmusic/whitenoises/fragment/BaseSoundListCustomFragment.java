package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.fragment;

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

import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ui.MixCustomActivityBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.baseClasses.FragmentBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.SoundList;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelSound;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.DisplayUtil;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

public class BaseSoundListCustomFragment extends FragmentBase {
    int mMaxTime = 9;
    private int mCategoryId = 0;
    private RecyclerView mRecyclerViewMixList;
    private List<ModelSound> mMixList = new ArrayList();
    private SlimAdapter mSlimAdapter;


    public void SetUpList() {
        this.mRecyclerViewMixList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        this.mSlimAdapter = SlimAdapter.create().register(R.layout.model_sounds_mix_custom_gridview, new SlimInjector<ModelSound>() {
            public void onInject(final ModelSound modelSound, IViewInjector iViewInjector) {
                iViewInjector.text((int) R.id.sound_name, modelSound.getmStrName());
                iViewInjector.image((int) R.id.sound_icon_iv, modelSound.getmResId());
                iViewInjector.invisible(R.id.iv_sound_download);
                iViewInjector.clicked(R.id.sound_icon_bg_layout, new OnClickListener() {
                    public void onClick(View view) {
                        if (BaseSoundListCustomFragment.this.getActivity() != null) {
                            ((MixCustomActivityBase) BaseSoundListCustomFragment.this.getActivity()).AddSoundItem(modelSound);
                        }
                    }
                });
            }
        }).enableDiff().attachTo(mRecyclerViewMixList);
        this.mSlimAdapter.updateData(this.mMixList);
    }

    public List<ModelSound> filter(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = this.mMaxTime * i; i2 < (i + 1) * this.mMaxTime; i2++) {
            if (i2 < SoundList.getListSoundItem(getActivity()).size()) {
                arrayList.add(SoundList.getListSoundItem(getActivity()).get(i2));
            }
        }
        return arrayList;
    }

    public BaseSoundListCustomFragment(int i) {
        this.mCategoryId = i;
        switch (this.mCategoryId) {
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
                this.mMixList = filter(i);
                return;
            default:
                this.mMixList = SoundList.getListSoundItem(getActivity());
                return;
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.model_vp_mix_sound, viewGroup, false);
        this.mRecyclerViewMixList = (RecyclerView) inflate.findViewById(R.id.rcv_mix_sound);
        this.mRecyclerViewMixList.addItemDecoration(new VideoDecoration());
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
