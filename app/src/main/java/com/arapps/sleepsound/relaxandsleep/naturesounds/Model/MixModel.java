package com.arapps.sleepsound.relaxandsleep.naturesounds.Model;

import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.ArrayUtils;
import java.util.ArrayList;
import java.util.List;

public class MixModel {
    private int mCategory;
    private MixCoverModel mixSoundCover;
    private int mixSoundId;
    private String name;
    private List<SoundModel> soundList;

    public MixModel() {
        this.soundList = new ArrayList();
    }

    public MixModel(MixModel mixModel) {
        this.mixSoundId = mixModel.mixSoundId;
        this.name = mixModel.name;
        this.mixSoundCover = mixModel.mixSoundCover;
        this.mCategory = mixModel.mCategory;
        this.soundList = ArrayUtils.copy(mixModel.soundList);
    }

    public MixModel(int i, int i2, String str, MixCoverModel mixCoverModel, List<SoundModel> list) {
        this.mixSoundId = i;
        this.mCategory = i2;
        this.name = str;
        this.mixSoundCover = mixCoverModel;
        this.soundList = list;
    }

    public void copy(MixModel mixModel) {
        this.mixSoundId = mixModel.mixSoundId;
        this.mCategory = mixModel.mCategory;
        this.name = mixModel.name;
        this.mixSoundCover = mixModel.mixSoundCover;
        this.soundList = mixModel.soundList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MixSoundModel{mixSoundId=");
        stringBuilder.append(this.mixSoundId);
        stringBuilder.append(", name='");
        stringBuilder.append(this.name);
        stringBuilder.append('\'');
        stringBuilder.append(", mixSoundCover=");
        stringBuilder.append(this.mixSoundCover);
        stringBuilder.append(", category=");
        stringBuilder.append(this.mCategory);
        stringBuilder.append(", soundList=");
        stringBuilder.append(this.soundList);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public int getMixSoundId() {
        return this.mixSoundId;
    }

    public void setMixSoundId(int i) {
        this.mixSoundId = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public MixCoverModel getMixSoundCover() {
        return this.mixSoundCover;
    }

    public void setMixSoundCover(MixCoverModel mixCoverModel) {
        this.mixSoundCover = mixCoverModel;
    }

    public int getmCategory() {
        return this.mCategory;
    }

    public void setmCategory(int i) {
        this.mCategory = i;
    }

    public List<SoundModel> getSoundList() {
        return this.soundList;
    }

    public void setSoundList(List<SoundModel> list) {
        this.soundList = list;
    }
}
