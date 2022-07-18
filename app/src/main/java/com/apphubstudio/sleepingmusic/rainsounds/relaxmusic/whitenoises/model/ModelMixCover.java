package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model;

public class ModelMixCover {
    private int mCoverResId;
    private int mCoverResId1;
    private int mId;

    public ModelMixCover(int i, int i2, int i3) {
        this.mId = i;
        this.mCoverResId1 = i2;
        this.mCoverResId = i3;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MixSoundCover{id=");
        stringBuilder.append(this.mId);
        stringBuilder.append(", coverResId=");
        stringBuilder.append(this.mCoverResId1);
        stringBuilder.append(", bigCoverResId=");
        stringBuilder.append(this.mCoverResId);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public int getmId() {
        return this.mId;
    }

    public void setmId(int i) {
        this.mId = i;
    }

    public int getmCoverResId1() {
        return this.mCoverResId1;
    }

    public void setmCoverResId1(int i) {
        this.mCoverResId1 = i;
    }

    public int getmCoverResId() {
        return this.mCoverResId;
    }

    public void setmCoverResId(int i) {
        this.mCoverResId = i;
    }
}
