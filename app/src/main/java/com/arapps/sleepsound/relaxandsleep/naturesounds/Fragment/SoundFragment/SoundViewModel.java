package com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.SoundFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SoundViewModel extends ViewModel {
    private MutableLiveData<String> mText = new MutableLiveData();

    public SoundViewModel() {
        this.mText.setValue("This is sound fragment_setting");
    }

    public LiveData<String> getText() {
        return this.mText;
    }
}
