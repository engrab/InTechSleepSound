package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model;

import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.ArrayUtils;
import java.util.ArrayList;
import java.util.List;

public class ModelMix {
    private int mCategoryPos;
    private ModelMixCover mMixSoundCover;
    private int mMixSoundId;
    private String mName;
    private List<ModelSound> mSoundList;

    public ModelMix() {
        this.mSoundList = new ArrayList();
    }

    public ModelMix(ModelMix modelMix) {
        this.mMixSoundId = modelMix.mMixSoundId;
        this.mName = modelMix.mName;
        this.mMixSoundCover = modelMix.mMixSoundCover;
        this.mCategoryPos = modelMix.mCategoryPos;
        this.mSoundList = ArrayUtils.copy(modelMix.mSoundList);
    }

    public ModelMix(int i, int i2, String str, ModelMixCover modelMixCover, List<ModelSound> list) {
        this.mMixSoundId = i;
        this.mCategoryPos = i2;
        this.mName = str;
        this.mMixSoundCover = modelMixCover;
        this.mSoundList = list;
    }

    public void copy(ModelMix modelMix) {
        this.mMixSoundId = modelMix.mMixSoundId;
        this.mCategoryPos = modelMix.mCategoryPos;
        this.mName = modelMix.mName;
        this.mMixSoundCover = modelMix.mMixSoundCover;
        this.mSoundList = modelMix.mSoundList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MixSoundModel{mixSoundId=");
        stringBuilder.append(this.mMixSoundId);
        stringBuilder.append(", name='");
        stringBuilder.append(this.mName);
        stringBuilder.append('\'');
        stringBuilder.append(", mixSoundCover=");
        stringBuilder.append(this.mMixSoundCover);
        stringBuilder.append(", category=");
        stringBuilder.append(this.mCategoryPos);
        stringBuilder.append(", soundList=");
        stringBuilder.append(this.mSoundList);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public int getmMixSoundId() {
        return this.mMixSoundId;
    }

    public void setmMixSoundId(int i) {
        this.mMixSoundId = i;
    }

    public String getmName() {
        return this.mName;
    }

    public void setmName(String str) {
        this.mName = str;
    }

    public ModelMixCover getmMixSoundCover() {
        return this.mMixSoundCover;
    }

    public void setmMixSoundCover(ModelMixCover modelMixCover) {
        this.mMixSoundCover = modelMixCover;
    }

    public int getmCategoryPos() {
        return this.mCategoryPos;
    }

    public void setmCategoryPos(int i) {
        this.mCategoryPos = i;
    }

    public List<ModelSound> getmSoundList() {
        return this.mSoundList;
    }

    public void setmSoundList(List<ModelSound> list) {
        this.mSoundList = list;
    }
}
