package com.arapps.sleepsound.relaxandsleep.naturesounds.ads;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdsUtils {

    static InterstitialAd mInterstitialAd;

    public static InterstitialAd getInterstitial() {
        return mInterstitialAd;
    }

    public static void loadInterstitial(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, context.getResources().getString(R.string.interstitial_id), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                mInterstitialAd = null;
            }
        });
    }

    public static AdView showBanner(Context context, LinearLayout linearLayout) {
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(context.getResources().getString(R.string.banner_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        linearLayout.removeAllViews();
        linearLayout.addView(adView);
        return adView;
    }

}
