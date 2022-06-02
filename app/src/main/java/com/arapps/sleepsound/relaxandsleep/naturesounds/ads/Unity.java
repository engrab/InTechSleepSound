package com.arapps.sleepsound.relaxandsleep.naturesounds.ads;

import android.app.Activity;
import android.util.Log;

import com.unity3d.mediation.IInitializationListener;
import com.unity3d.mediation.IInterstitialAdLoadListener;
import com.unity3d.mediation.IInterstitialAdShowListener;
import com.unity3d.mediation.IRewardedAdLoadListener;
import com.unity3d.mediation.InitializationConfiguration;
import com.unity3d.mediation.InterstitialAd;
import com.unity3d.mediation.RewardedAd;
import com.unity3d.mediation.UnityMediation;
import com.unity3d.mediation.errors.LoadError;
import com.unity3d.mediation.errors.SdkInitializationError;
import com.unity3d.mediation.errors.ShowError;

public class Unity {

    private static String GameID = "abcd";
    private static final String mInterstitialAdUnitId = "Interstitial_Android";
    private static InterstitialAd mInterstitialAd;
    private static  boolean isLoaded=false;


    // for rewarded video
    private static String REWARDED_AD_UNIT_ID = "Rewarded_Android";
    private static RewardedAd rewardedAd;

    public static boolean isRewardedLoaded = false;


    public static void initializeSdk(Activity activity) {

        InitializationConfiguration configuration = InitializationConfiguration.builder().setGameId(GameID)
                .setInitializationListener(new IInitializationListener() {
                    @Override
                    public void onInitializationComplete() {
                        loadInterstitial(activity);
                        loadRewardedVideo(activity);
                        Log.d("naveed", "Unity Mediation is successfully initialized.");
                    }

                    @Override
                    public void onInitializationFailed(SdkInitializationError errorCode, String msg) {
                        System.out.println("Unity Mediation Failed to Initialize : " + msg);
                    }
                }).build();
        UnityMediation.initialize(configuration);

    }

    public static boolean isAdLoaded() {
//        if (interstitialAd != null) {
//            return true;
//        } else {
//            return false;
//        }

        return isLoaded;
    }


    public static void loadInterstitial(Activity activity) {
        mInterstitialAd = new InterstitialAd(activity, mInterstitialAdUnitId);
        final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
            @Override
            public void onInterstitialLoaded(InterstitialAd ad) {
                isLoaded=true;
            }

            @Override
            public void onInterstitialFailedLoad(InterstitialAd ad, LoadError error, String msg) {
                isLoaded=false;
            }
        };

        mInterstitialAd.load(loadListener);

    }

    public static void showInterstitial(Activity activity) {
        final IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
            @Override
            public void onInterstitialShowed(InterstitialAd interstitialAd) {
            }

            @Override
            public void onInterstitialClicked(InterstitialAd interstitialAd) {
            }

            @Override
            public void onInterstitialClosed(InterstitialAd interstitialAd) {
                loadInterstitial(activity);
            }

            @Override
            public void onInterstitialFailedShow(InterstitialAd interstitialAd, ShowError error, String msg) {

            }
        };

        if (mInterstitialAd != null) {
            mInterstitialAd.show(showListener);
        } else {
            loadInterstitial(activity);
        }
    }

    public static void showInterstitial(Activity activity, final IInterstitialAdShowListener showListener) {

        if (mInterstitialAd != null) {
            mInterstitialAd.show(showListener);
            isLoaded=false;
        } else {
            loadInterstitial(activity);
        }
    }


    public static void loadRewardedVideo(Activity activity) {
        // Instantiate a new rewarded ad object:
        rewardedAd = new RewardedAd(activity, REWARDED_AD_UNIT_ID);
        final IRewardedAdLoadListener loadListener = new IRewardedAdLoadListener() {
            @Override
            public void onRewardedLoaded(RewardedAd ad) {
                isRewardedLoaded = true;
            }

            @Override
            public void onRewardedFailedLoad(RewardedAd ad, LoadError error, String msg) {
                isRewardedLoaded = false;
            }
        };

        rewardedAd.load(loadListener);

    }

    public static boolean isIsRewardedLoaded() {
        return isRewardedLoaded;
    }

    public static RewardedAd getRewardedAd() {
        if (rewardedAd != null) {
            return rewardedAd;
        } else {
            return null;
        }
    }
}
