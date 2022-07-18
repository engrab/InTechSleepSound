package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model;

public class ModelSound {
    private int mVol;
    private int mResId;
    private int mSoundId;
    private String mFileName;
    private String mStrName;

    public ModelSound() {
        this.mVol = 50;
    }

    public ModelSound(ModelSound modelSound) {
        if (modelSound != null) {
            this.mSoundId = modelSound.mSoundId;
            this.mStrName = modelSound.mStrName;
            this.mResId = modelSound.mResId;
            this.mVol = modelSound.mVol;
            this.mFileName = modelSound.mFileName;
        }
    }

    public ModelSound(int i, String str, String str2, int i2, int i3) {
        this.mSoundId = i;
        this.mStrName = str;
        this.mFileName = str2;
        this.mResId = i2;
        this.mVol = i3;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SoundModel{soundId=");
        stringBuilder.append(this.mSoundId);
        stringBuilder.append(", name='");
        stringBuilder.append(this.mStrName);
        stringBuilder.append('\'');
        stringBuilder.append(", iconResId=");
        stringBuilder.append(this.mResId);
        stringBuilder.append(", volume=");
        stringBuilder.append(this.mVol);
        stringBuilder.append(", fileName='");
        stringBuilder.append(this.mFileName);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void update(ModelSound modelSound) {
        if (modelSound != null) {
            this.mSoundId = modelSound.mSoundId;
            this.mStrName = modelSound.mStrName;
            this.mResId = modelSound.mResId;
            this.mVol = modelSound.mVol;
            this.mFileName = modelSound.mFileName;
        }
    }

    public int getmSoundId() {
        return this.mSoundId;
    }

    public void setmSoundId(int i) {
        this.mSoundId = i;
    }

    public String getmStrName() {
        return this.mStrName;
    }

    public void setmStrName(String str) {
        this.mStrName = str;
    }

    public int getmResId() {
        return this.mResId;
    }

    public void setmResId(int i) {
        this.mResId = i;
    }

    public int getmVol() {
        return this.mVol;
    }

    public void setmVol(int i) {
        this.mVol = i;
    }

    public String getmFileName() {
        return this.mFileName;
    }

    public void setmFileName(String str) {
        this.mFileName = str;
    }
}
