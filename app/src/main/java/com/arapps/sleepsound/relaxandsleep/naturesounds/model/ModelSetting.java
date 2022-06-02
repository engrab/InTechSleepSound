package com.arapps.sleepsound.relaxandsleep.naturesounds.model;

public class ModelSetting {
    int mId;
    String mSubTitle;
    String mTitle;

    public ModelSetting(int i, String str, String str2) {
        this.mId = i;
        this.mTitle = str;
        this.mSubTitle = str2;
    }

    public int getmId() {
        return this.mId;
    }

    public void setmId(int i) {
        this.mId = i;
    }

    public String getmTitle() {
        return this.mTitle;
    }

    public void setmTitle(String str) {
        this.mTitle = str;
    }

    public String getmSubTitle() {
        return this.mSubTitle;
    }

    public void setmSubTitle(String str) {
        this.mSubTitle = str;
    }
}
