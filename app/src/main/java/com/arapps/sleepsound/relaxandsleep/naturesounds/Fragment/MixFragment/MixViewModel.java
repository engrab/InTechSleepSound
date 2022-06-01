package com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.MixFragment;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixCategoryModel;
import java.util.List;

public class MixViewModel extends ViewModel {
    private MutableLiveData<List<MixCategoryModel>> listMutableLiveData;
    private MutableLiveData<String> mText = new MutableLiveData();

    public MixViewModel() {
        this.mText.setValue("This is mix fragment_setting");
    }

    public LiveData<String> getText() {
        return this.mText;
    }

    public LiveData<List<MixCategoryModel>> getListCategoryData(Context context) {
        this.listMutableLiveData.setValue(SoundListDataSource.getListMixCategoryItem(context));
        return this.listMutableLiveData;
    }
}
