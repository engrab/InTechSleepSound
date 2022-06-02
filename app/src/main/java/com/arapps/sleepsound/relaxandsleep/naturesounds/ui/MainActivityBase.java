package com.arapps.sleepsound.relaxandsleep.naturesounds.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import com.arapps.sleepsound.relaxandsleep.naturesounds.ads.AdsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.ads.Unity;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.arapps.sleepsound.relaxandsleep.naturesounds.adapter.AdapterViewPager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.baseClasses.ActivityBase;
import com.arapps.sleepsound.relaxandsleep.naturesounds.fragment.BaseMixFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.fragment.BaseSettingFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.fragment.BaseSoundFragment;
import com.arapps.sleepsound.relaxandsleep.naturesounds.helper.SoundList;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.services.SoundPlayerService;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.DisplayUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.customView.PagerBaseNoScroll;
import com.unity3d.mediation.IInterstitialAdShowListener;
import com.unity3d.mediation.errors.ShowError;

public class MainActivityBase extends ActivityBase {

    private MenuItem prevMenuItem;
    AdapterViewPager viewPagerAdapter;
    BottomNavigationView bottomNavigationView;
    PagerBaseNoScroll pagerBaseNoScroll;
    StringBuilder stringBuilder;
    private InterstitialAd mInterstitialAd;



    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);


        AdsUtils.loadInterstitial(MainActivityBase.this);
        Unity.initializeSdk(MainActivityBase.this);
        AdsUtils.showBanner(MainActivityBase.this, findViewById(R.id.llAds));

        bottomNavigationView = findViewById(R.id.nav_view);
        pagerBaseNoScroll = findViewById(R.id.nav_host_fragment);
        DisplayUtil.hideActionBar(this);
        stringBuilder = new StringBuilder();
        stringBuilder.append(SoundList.getListSoundItem(this).size());
        String str = "";
        stringBuilder.append(str);
        Log.e("getListSoundItem", stringBuilder.toString());
        stringBuilder.append(SoundList.getListMixCoverItem(this).size());
        stringBuilder.append(str);
        Log.e("getListMixCoverItem", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(SoundList.getListMixItem(this).size());
        stringBuilder.append(str);
        Log.e("getListMixItem", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(SoundList.getListMixCategoryItem(this).size());
        stringBuilder.append(str);
        Log.e("getListMixCategoryItem", stringBuilder.toString());
        this.viewPagerAdapter = new AdapterViewPager(getSupportFragmentManager());
        this.viewPagerAdapter.addFragment(new BaseMixFragment());
        this.viewPagerAdapter.addFragment(new BaseSoundFragment());
        this.viewPagerAdapter.addFragment(new BaseSettingFragment());
        pagerBaseNoScroll.setAdapter(this.viewPagerAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_mix:



                        mInterstitialAd = AdsUtils.getInterstitial();
                        if (mInterstitialAd != null) {//admob
                            mInterstitialAd.show(MainActivityBase.this);
                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    super.onAdDismissedFullScreenContent();
                                    pagerBaseNoScroll.setCurrentItem(0, true);
                                    AdsUtils.loadInterstitial(MainActivityBase.this);
                                }
                            });
                        } else if (Unity.isAdLoaded()) {//unity fb
                            Unity.showInterstitial(MainActivityBase.this, new IInterstitialAdShowListener() {
                                @Override
                                public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {
                                    pagerBaseNoScroll.setCurrentItem(0, true);
                                    Unity.loadInterstitial(MainActivityBase.this);//load unity ads
                                }

                                @Override
                                public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                                }

                            });
                            AdsUtils.loadInterstitial(MainActivityBase.this);//load admob ad
                        } else {
                            pagerBaseNoScroll.setCurrentItem(0, true);
                            AdsUtils.loadInterstitial(MainActivityBase.this);//load admob ad
                            Unity.loadInterstitial(MainActivityBase.this);//load unity ads
                        }

                        break;
                    case R.id.navigation_setting:
                        pagerBaseNoScroll.setCurrentItem(2, true);
                        break;
                    case R.id.navigation_sound:

                        mInterstitialAd = AdsUtils.getInterstitial();
                        if (mInterstitialAd != null) {//admob
                            mInterstitialAd.show(MainActivityBase.this);
                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    super.onAdDismissedFullScreenContent();
                                    pagerBaseNoScroll.setCurrentItem(1, true);
                                    AdsUtils.loadInterstitial(MainActivityBase.this);
                                }
                            });
                        } else if (Unity.isAdLoaded()) {//unity fb
                            Unity.showInterstitial(MainActivityBase.this, new IInterstitialAdShowListener() {
                                @Override
                                public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {
                                    pagerBaseNoScroll.setCurrentItem(1, true);
                                    Unity.loadInterstitial(MainActivityBase.this);//load unity ads
                                }

                                @Override
                                public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                                }

                            });
                            AdsUtils.loadInterstitial(MainActivityBase.this);//load admob ad
                        } else {
                            pagerBaseNoScroll.setCurrentItem(1, true);
                            AdsUtils.loadInterstitial(MainActivityBase.this);//load admob ad
                            Unity.loadInterstitial(MainActivityBase.this);//load unity ads
                        }

                        break;
                }
                return false;
            }
        });
        bottomNavigationView.setOnNavigationItemReselectedListener(new OnNavigationItemReselectedListener() {
            public void onNavigationItemReselected(MenuItem menuItem) {
            }
        });
        pagerBaseNoScroll.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (MainActivityBase.this.prevMenuItem != null) {
                    MainActivityBase.this.prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(i).setChecked(true);
                MainActivityBase.this.prevMenuItem = bottomNavigationView.getMenu().getItem(i);
            }
        });
        pagerBaseNoScroll.setOffscreenPageLimit(3);

        if (getIntent() != null && getIntent().getBooleanExtra(SoundPlayerService.ACTION_OPEN_FROM_NOTIFI, false)) {
            startActivity(new Intent(this, PlayActivityBase.class));
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainActivityBase.this, ExitActivity.class));
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
