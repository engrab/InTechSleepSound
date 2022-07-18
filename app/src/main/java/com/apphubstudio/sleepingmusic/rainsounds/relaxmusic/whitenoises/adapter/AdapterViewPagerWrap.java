package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.customView.ListenerObjectPosition;
import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerWrap extends FragmentStatePagerAdapter implements ListenerObjectPosition {
    private final SparseArray<Fragment> sparseArray = new SparseArray();
    private final List<Fragment> list = new ArrayList();
    private final FragmentManager mFragmentManager;

    public AdapterViewPagerWrap(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
    }

    public AdapterViewPagerWrap(FragmentManager fragmentManager, int i) {
        super(fragmentManager, i);
        this.mFragmentManager = fragmentManager;
    }

    public Fragment getItem(int i) {
        return this.list.get(i);
    }

    public int getCount() {
        return this.list.size();
    }

    public void addFragment(Fragment fragment) {
        this.list.add(fragment);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        this.sparseArray.put(i, fragment);
        return fragment;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        this.sparseArray.remove(i);
        super.destroyItem(viewGroup, i, obj);
    }

    public Fragment getRegisteredFragment(int i) {
        return this.sparseArray.get(i);
    }

    public Object getObjectAtPosition(int i) {
        return this.sparseArray.get(i);
    }
}
