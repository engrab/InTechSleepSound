package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class AdapterViewPager extends FragmentStatePagerAdapter {
    private final SparseArray<Fragment> sparseArray = new SparseArray();
    private final List<Fragment> list = new ArrayList();

    public AdapterViewPager(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public AdapterViewPager(FragmentManager fragmentManager, int i) {
        super(fragmentManager, i);
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
}
