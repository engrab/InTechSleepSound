package com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment;

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

import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.DeleteCustomMixActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Activity.PlayActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.Utils;
import java.util.ArrayList;
import java.util.List;
import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector.Action;

public class MixListFragment extends BaseFragment {
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
        }
    };
    private int category = 0;
    private TextView mNoItemText;
    private RecyclerView mRvListMix;
    private List<MixModel> mixModels = new ArrayList();
    private SlimAdapter slimAdapter;

    public MixListFragment(){

    }

    public List<MixModel> filter(int i) {
        if (i == 0) {
            return SoundListDataSource.getListMixItem(getActivity());
        }
        ArrayList arrayList = new ArrayList();
        for (MixModel mixModel : SoundListDataSource.getListMixItem(getActivity())) {
            if (mixModel.getmCategory() == i) {
                arrayList.add(mixModel);
            }
        }
        return arrayList;
    }
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        SetUpList();
    }
    public MixListFragment(int i) {
        this.category = i;
        switch (this.category) {
            case 1:
                this.mixModels = filter(i);
                return;
            case 2:
                this.mixModels = filter(i);
                return;
            case 3:
                this.mixModels = filter(i);
                return;
            case 4:
                this.mixModels = filter(i);
                return;
            case 5:
                this.mixModels = filter(i);
                return;
            case 6:
                this.mixModels = filter(i);
                return;
            case 7:
                this.mixModels = filter(i);
                return;
            case 8:
                this.mixModels = filter(i);
                return;
            case 9:
                this.mixModels = filter(i);
                return;
            case 10:
                this.mixModels = filter(i);
                return;
            default:
                this.mixModels = SoundListDataSource.getListMixItem(getActivity());
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

        this.slimAdapter = SlimAdapter.create().register(R.layout.model_mix, new SlimInjector<MixModel>() {
            public void onInject(final MixModel mixModel, IViewInjector iViewInjector) {
                iViewInjector.text(R.id.title_text, mixModel.getName());
                iViewInjector.with(R.id.cover_image, new Action<ImageView>() {
                    public void action(ImageView simpleDraweeView) {
                        if (MixListFragment.this.getActivity()!=null){

                            simpleDraweeView.setImageURI(Utils.getUriToResource(MixListFragment.this.getActivity(), mixModel.getMixSoundCover().getCoverResId()));
                        }
                    }
                });
                iViewInjector.invisible(R.id.ads_icon);
                if (mixModel.getmCategory() == 1) {
                    iViewInjector.visibility(R.id.mix_sound_tag_tv, 0);
                    iViewInjector.longClicked(R.id.cover_container, new OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            Intent intent = new Intent(MixListFragment.this.getActivity(), DeleteCustomMixActivity.class);
                            intent.putExtra(SoundPlayerService.MIX_ID, mixModel.getMixSoundId());
                            MixListFragment.this.startActivity(intent);
                            return true;
                        }
                    });
                } else {
                    iViewInjector.visibility(R.id.mix_sound_tag_tv, 8);
                }
                iViewInjector.clicked(R.id.cover_container, new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(MixListFragment.this.getActivity(), SoundPlayerService.class);
                        intent.setAction(SoundPlayerService.ACTION_CMD);
                        intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_PLAY_MIX);
                        int mixSoundId = mixModel.getMixSoundId();
                        String str = SoundPlayerService.MIX_ID;
                        intent.putExtra(str, mixSoundId);
                        MixListFragment.this.getActivity().startService(intent);
                        intent = new Intent(MixListFragment.this.getActivity(), PlayActivity.class);
                        intent.putExtra(str, mixModel.getMixSoundId());
                        MixListFragment.this.startActivity(intent);
                    }
                });
            }
        }).enableDiff().attachTo(mRvListMix);
        this.slimAdapter.updateData(this.mixModels);
    }
@Override
    public void onResume() {
        super.onResume();
        this.mixModels = filter(this.category);
        this.slimAdapter.updateData(this.mixModels);
        this.slimAdapter.notifyDataSetChanged();
        if (this.mixModels.size() == 0) {
            this.mNoItemText.setVisibility(0);
        } else {
            this.mNoItemText.setVisibility(8);
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
