package com.arapps.sleepsound.relaxandsleep.naturesounds.Activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import com.facebook.drawee.view.SimpleDraweeView;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SaveDataHelper;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixCoverModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.Utils;
import java.util.ArrayList;
import java.util.List;
import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector.Action;

public class SaveCustomActivity extends BaseActivity implements OnClickListener {
    private LinearLayoutManager linearLayoutManager;
    private AppCompatImageView mCloseView;
    private RecyclerView mMixSoundCoverRcv;
    private TextView mMixSoundCoverTv;
    private TextView mMixSoundNameTv;
    private AppCompatEditText mMixSoundNameEt;
    private RelativeLayout mSaveCustomBgLayout;
    private AppCompatImageView mSelectIv;
    private MixCoverModel mixCoverModelClick = SoundListDataSource.getMixCoverItemById(0);
    private SlimAdapter slimAdapter;

    class VideoDecoration extends ItemDecoration {
        VideoDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            recyclerView.getLayoutManager();
            recyclerView.getChildAdapterPosition(view);
            rect.set(DisplayUtil.dpToPx(12.0f), DisplayUtil.dpToPx(10.0f), DisplayUtil.dpToPx(12.0f), DisplayUtil.dpToPx(10.0f));
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView(R.layout.save_custom_activity);
        InitView();
        DisplayUtil.hideActionBar(this);
    }

    private void InitView() {
        this.mCloseView = findViewById(R.id.close_view);
        this.mCloseView.setOnClickListener(this);
        this.mMixSoundNameTv = findViewById(R.id.mix_sound_name_tv);
        this.mMixSoundNameEt = findViewById(R.id.mix_sound_name_et);
        this.mMixSoundCoverTv = findViewById(R.id.mix_sound_cover_tv);
        this.mMixSoundCoverRcv = findViewById(R.id.mix_sound_cover_rcv);
        this.mSelectIv = findViewById(R.id.select_iv);
        this.mSaveCustomBgLayout = findViewById(R.id.save_custom_bg_layout);
        this.mSaveCustomBgLayout.setOnClickListener(this);
        SetupRecycleView();
    }
@Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.close_view) {
            finish();
        } else if (id == R.id.save_custom_bg_layout) {
            SaveCustomMix();
        }
    }

    public void SetupRecycleView() {
        this.linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.mMixSoundCoverRcv.setLayoutManager(this.linearLayoutManager);
        this.mMixSoundCoverRcv.addItemDecoration(new VideoDecoration());
        this.slimAdapter = SlimAdapter.create().register(R.layout.model_save_custom_mix_sound_cover, new SlimInjector<MixCoverModel>() {
            public void onInject(final MixCoverModel mixCoverModel, IViewInjector iViewInjector) {
                iViewInjector.with(R.id.mix_sound_cover_iv, new Action<SimpleDraweeView>() {
                    public void action(SimpleDraweeView simpleDraweeView) {
                        simpleDraweeView.setImageURI(Utils.getUriToResource(SaveCustomActivity.this, mixCoverModel.getCoverResId()));
                    }
                });
                iViewInjector.clicked(R.id.mix_sound_cover_iv, new OnClickListener() {
                    public void onClick(View view) {
                        SaveCustomActivity.this.mixCoverModelClick = mixCoverModel;
                        SaveCustomActivity.this.slimAdapter.notifyDataSetChanged();
                    }
                });
                if (SaveCustomActivity.this.mixCoverModelClick == null || SaveCustomActivity.this.mixCoverModelClick.getId() != mixCoverModel.getId()) {
                    iViewInjector.visibility(R.id.mix_sound_cover_select_iv, 4);
                } else {
                    iViewInjector.visibility(R.id.mix_sound_cover_select_iv, 0);
                }
            }
        }).enableDiff().attachTo(mMixSoundCoverRcv);
        this.slimAdapter.updateData(SoundListDataSource.getCustomCoverList(this));
    }

    public void SaveCustomMix() {
        List customMixList = SaveDataHelper.getCustomMixList(this);
        String str = "MIX_CUSTOM";
        if (customMixList != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(customMixList.size());
            stringBuilder.append("");
            Log.e(str, stringBuilder.toString());
        } else {
            Log.e(str, "NULL");
            customMixList = new ArrayList();
        }
        if (customMixList.size() < 1000) {
            MixModel mixModel = new MixModel();
            mixModel.setmCategory(1);
            mixModel.setName(this.mMixSoundNameEt.getText().toString());
            mixModel.setMixSoundCover(this.mixCoverModelClick);
            mixModel.setSoundList(SoundPlayerManager.getInstance(this).getPlayingSoundItem());
            mixModel.setMixSoundId(customMixList.size());
            Toast.makeText(this, R.string.save_successfully, 0).show();
            SaveDataHelper.addCustomMixInJSONArray(this, mixModel);
            SoundListDataSource.createData(this);
            finish();
            return;
        }
        Toast.makeText(this, "You have reach max save custom mix", 0).show();
        finish();
    }
}
