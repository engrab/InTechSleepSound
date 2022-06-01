package com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.SettingsFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingViewModel extends ViewModel {
    private MutableLiveData<String> mText = new MutableLiveData();

    public SettingViewModel() {
        this.mText.setValue("This is setting fragment_setting");
    }

    public LiveData<String> getText() {
        return this.mText;
    }
}
