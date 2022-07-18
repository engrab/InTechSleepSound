package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.State;

import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ui.DeleteCustomMixActivityBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.ui.PlayActivityBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.baseClasses.FragmentBase;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper.SoundList;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelMix;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.services.SoundPlayerService;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.DisplayUtil;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector.Action;

public class BaseMixListFragment extends FragmentBase {
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
        }
    };
    private int mCategoryId = 0;
    private TextView mNoItemText;
    private RecyclerView mRvListMix;
    private List<ModelMix> mMixList = new ArrayList();
    private SlimAdapter mSlimAdapter;

    public BaseMixListFragment(){

    }

    public List<ModelMix> filter(int i) {
        if (i == 0) {
            return SoundList.getListMixItem(getActivity());
        }
        ArrayList arrayList = new ArrayList();
        for (ModelMix modelMix : SoundList.getListMixItem(getActivity())) {
            if (modelMix.getmCategoryPos() == i) {
                arrayList.add(modelMix);
            }
        }
        return arrayList;
    }
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        SetUpList();
    }
    public BaseMixListFragment(int i) {
        this.mCategoryId = i;
        switch (this.mCategoryId) {

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
                this.mMixList = SoundList.getListMixItem(getActivity());
                return;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_mix_list, viewGroup, false);
        this.mRvListMix = inflate.findViewById(R.id.rv_list_mix);
        this.mNoItemText = inflate.findViewById(R.id.no_item);
        return inflate;
    }



    public void SetUpList() {
        this.mRvListMix.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        this.mRvListMix.addItemDecoration(new VideoDecoration());

        this.mSlimAdapter = SlimAdapter.create().register(R.layout.model_mix, new SlimInjector<ModelMix>() {
            public void onInject(final ModelMix modelMix, IViewInjector iViewInjector) {
                iViewInjector.text(R.id.title_text, modelMix.getmName());
                iViewInjector.with(R.id.cover_image, new Action<ImageView>() {
                    public void action(ImageView simpleDraweeView) {
                        if (BaseMixListFragment.this.getActivity()!=null){

                            simpleDraweeView.setImageURI(Utils.getUriToResource(BaseMixListFragment.this.getActivity(), modelMix.getmMixSoundCover().getmCoverResId1()));
                        }
                    }
                });
                iViewInjector.invisible(R.id.ads_icon);
                if (modelMix.getmCategoryPos() == 1) {
                    iViewInjector.visibility(R.id.mix_sound_tag_tv, 0);
                    iViewInjector.longClicked(R.id.cover_container, new OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            Intent intent = new Intent(BaseMixListFragment.this.getActivity(), DeleteCustomMixActivityBase.class);
                            intent.putExtra(SoundPlayerService.MIX_ID, modelMix.getmMixSoundId());
                            BaseMixListFragment.this.startActivity(intent);
                            return true;
                        }
                    });
                } else {
                    iViewInjector.visibility(R.id.mix_sound_tag_tv, 8);
                }
                iViewInjector.clicked(R.id.cover_container, new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(BaseMixListFragment.this.getActivity(), SoundPlayerService.class);
                        intent.setAction(SoundPlayerService.ACTION_CMD);
                        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY_MIX);
                        int mixSoundId = modelMix.getmMixSoundId();
                        String str = SoundPlayerService.MIX_ID;
                        intent.putExtra(str, mixSoundId);
                        BaseMixListFragment.this.getActivity().startService(intent);
                        intent = new Intent(BaseMixListFragment.this.getActivity(), PlayActivityBase.class);
                        intent.putExtra(str, modelMix.getmMixSoundId());
                        BaseMixListFragment.this.startActivity(intent);
                    }
                });
            }
        }).enableDiff().attachTo(mRvListMix);
        this.mSlimAdapter.updateData(this.mMixList);
    }
@Override
    public void onResume() {
        super.onResume();
        this.mMixList = filter(this.mCategoryId);
        this.mSlimAdapter.updateData(this.mMixList);
        this.mSlimAdapter.notifyDataSetChanged();
        if (this.mMixList.size() == 0) {
            this.mNoItemText.setVisibility(View.VISIBLE);
        } else {
            this.mNoItemText.setVisibility(View.GONE);
        }
    }

    class VideoDecoration extends ItemDecoration {
        VideoDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            LayoutManager layoutManager = recyclerView.getLayoutManager();
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (layoutManager instanceof GridLayoutManager) {
                if (childAdapterPosition % 2 == 0) {
                    rect.set(0, 0, DisplayUtil.dpToPx(4.0f), DisplayUtil.dpToPx(16.0f));
                } else {
                    rect.set(DisplayUtil.dpToPx(4.0f), 0, 0, DisplayUtil.dpToPx(16.0f));
                }
            } else if (layoutManager instanceof LinearLayoutManager) {
                rect.set(0, 0, 0, DisplayUtil.dpToPx(16.0f));
            }
            int itemCount = state.getItemCount();
            if (itemCount > 0 && childAdapterPosition == itemCount - 1) {
                if (childAdapterPosition % 2 == 0) {
                    rect.set(0, 0, DisplayUtil.dpToPx(4.0f), DisplayUtil.dpToPx(60.0f));
                } else {
                    rect.set(DisplayUtil.dpToPx(4.0f), 0, 0, DisplayUtil.dpToPx(60.0f));
                }
            }
        }
    }
}
