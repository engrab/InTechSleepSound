package com.arapps.sleepsound.relaxandsleep.naturesounds.Model;

public class MixCoverModel {
    private int bigCoverResId;
    private int coverResId;
    private int id;

    public MixCoverModel(int i, int i2, int i3) {
        this.id = i;
        this.coverResId = i2;
        this.bigCoverResId = i3;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MixSoundCover{id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", coverResId=");
        stringBuilder.append(this.coverResId);
        stringBuilder.append(", bigCoverResId=");
        stringBuilder.append(this.bigCoverResId);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getCoverResId() {
        return this.coverResId;
    }

    public void setCoverResId(int i) {
        this.coverResId = i;
    }

    public int getBigCoverResId() {
        return this.bigCoverResId;
    }

    public void setBigCoverResId(int i) {
        this.bigCoverResId = i;
    }
}
