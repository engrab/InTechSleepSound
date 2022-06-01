package com.arapps.sleepsound.relaxandsleep.naturesounds.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Adapter.ViewPagerAdapter;
import com.arapps.sleepsound.relaxandsleep.naturesounds.BaseFragment.BaseActivity;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.MixFragment.MixFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.SettingsFragment.SettingFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Fragment.SoundFragment.SoundFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.AdsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.customView.NoScrollViewPager;

public class MainActivity extends BaseActivity {

    private MenuItem prevMenuItem;
    ViewPagerAdapter viewPagerAdapter;
    BottomNavigationView bottomNavigationView;
    NoScrollViewPager noScrollViewPager;
    StringBuilder stringBuilder;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);


        RelativeLayout relativeLayout = findViewById(R.id.ads_lays);
        AdView adView = findViewById(R.id.main_medium2);

        AdsUtils.ShowBanner(MainActivity.this, relativeLayout);
        bottomNavigationView = findViewById(R.id.nav_view);
        noScrollViewPager = findViewById(R.id.nav_host_fragment);
        DisplayUtil.hideActionBar(this);
        stringBuilder = new StringBuilder();
        stringBuilder.append(SoundListDataSource.getListSoundItem(this).size());
        String str = "";
        stringBuilder.append(str);
        Log.e("getListSoundItem", stringBuilder.toString());
        stringBuilder.append(SoundListDataSource.getListMixCoverItem(this).size());
        stringBuilder.append(str);
        Log.e("getListMixCoverItem", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(SoundListDataSource.getListMixItem(this).size());
        stringBuilder.append(str);
        Log.e("getListMixItem", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(SoundListDataSource.getListMixCategoryItem(this).size());
        stringBuilder.append(str);
        Log.e("getListMixCategoryItem", stringBuilder.toString());
        this.viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        this.viewPagerAdapter.addFragment(new MixFragment());
        this.viewPagerAdapter.addFragment(new SoundFragment());
        this.viewPagerAdapter.addFragment(new SettingFragment());
        noScrollViewPager.setAdapter(this.viewPagerAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_mix:
                        AdsUtils.ShowInterstitial(MainActivity.this);
                        noScrollViewPager.setCurrentItem(0, true);
                        break;
                    case R.id.navigation_setting:
                        noScrollViewPager.setCurrentItem(2, true);
                        break;
                    case R.id.navigation_sound:
                        AdsUtils.ShowInterstitial(MainActivity.this);
                        noScrollViewPager.setCurrentItem(1, true);
                        break;
                }
                return false;
            }
        });
        bottomNavigationView.setOnNavigationItemReselectedListener(new OnNavigationItemReselectedListener() {
            public void onNavigationItemReselected(MenuItem menuItem) {
            }
        });
        noScrollViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (MainActivity.this.prevMenuItem != null) {
                    MainActivity.this.prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(i).setChecked(true);
                MainActivity.this.prevMenuItem = bottomNavigationView.getMenu().getItem(i);
            }
        });
        noScrollViewPager.setOffscreenPageLimit(3);

        if (getIntent() != null && getIntent().getBooleanExtra(SoundPlayerService.ACTION_OPEN_FROM_NOTIFI, false)) {
            startActivity(new Intent(this, PlayActivity.class));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
