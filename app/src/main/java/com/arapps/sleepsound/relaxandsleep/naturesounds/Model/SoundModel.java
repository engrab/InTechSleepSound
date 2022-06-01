package com.arapps.sleepsound.relaxandsleep.naturesounds.Model;

public class SoundModel {
    private int volume;
    private int iconResId;
    private int soundId;
    private String fileName;
    private String str_name;

    public SoundModel() {
        this.volume = 50;
    }

    public SoundModel(SoundModel soundModel) {
        if (soundModel != null) {
            this.soundId = soundModel.soundId;
            this.str_name = soundModel.str_name;
            this.iconResId = soundModel.iconResId;
            this.volume = soundModel.volume;
            this.fileName = soundModel.fileName;
        }
    }

    public SoundModel(int i, String str, String str2, int i2, int i3) {
        this.soundId = i;
        this.str_name = str;
        this.fileName = str2;
        this.iconResId = i2;
        this.volume = i3;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SoundModel{soundId=");
        stringBuilder.append(this.soundId);
        stringBuilder.append(", name='");
        stringBuilder.append(this.str_name);
        stringBuilder.append('\'');
        stringBuilder.append(", iconResId=");
        stringBuilder.append(this.iconResId);
        stringBuilder.append(", volume=");
        stringBuilder.append(this.volume);
        stringBuilder.append(", fileName='");
        stringBuilder.append(this.fileName);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void update(SoundModel soundModel) {
        if (soundModel != null) {
            this.soundId = soundModel.soundId;
            this.str_name = soundModel.str_name;
            this.iconResId = soundModel.iconResId;
            this.volume = soundModel.volume;
            this.fileName = soundModel.fileName;
        }
    }

    public int getSoundId() {
        return this.soundId;
    }

    public void setSoundId(int i) {
        this.soundId = i;
    }

    public String getStr_name() {
        return this.str_name;
    }

    public void setStr_name(String str) {
        this.str_name = str;
    }

    public int getIconResId() {
        return this.iconResId;
    }

    public void setIconResId(int i) {
        this.iconResId = i;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int i) {
        this.volume = i;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }
}
