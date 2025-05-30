package com.arapps.sleepsound.relaxandsleep.naturesounds.Adapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.arapps.sleepsound.relaxandsleep.naturesounds.customView.ObjectAtPositionInterface;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerWrapAdapter extends FragmentStatePagerAdapter implements ObjectAtPositionInterface {
    private final SparseArray<Fragment> mFragmentList = new SparseArray();
    private final List<Fragment> mFragmentList1 = new ArrayList();
    private final FragmentManager mFragmentManager;

    public ViewPagerWrapAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
    }

    public ViewPagerWrapAdapter(FragmentManager fragmentManager, int i) {
        super(fragmentManager, i);
        this.mFragmentManager = fragmentManager;
    }

    public Fragment getItem(int i) {
        return this.mFragmentList1.get(i);
    }

    public int getCount() {
        return this.mFragmentList1.size();
    }

    public void addFragment(Fragment fragment) {
        this.mFragmentList1.add(fragment);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        this.mFragmentList.put(i, fragment);
        return fragment;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        this.mFragmentList.remove(i);
        super.destroyItem(viewGroup, i, obj);
    }

    public Fragment getRegisteredFragment(int i) {
        return this.mFragmentList.get(i);
    }

    public Object getObjectAtPosition(int i) {
        return this.mFragmentList.get(i);
    }
}
