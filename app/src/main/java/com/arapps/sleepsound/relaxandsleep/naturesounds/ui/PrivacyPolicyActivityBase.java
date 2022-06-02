package com.arapps.sleepsound.relaxandsleep.naturesounds.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.baseClasses.ActivityBase;
import com.arapps.sleepsound.relaxandsleep.naturesounds.utils.DisplayUtil;

public class PrivacyPolicyActivityBase extends ActivityBase {
    private WebView mAdConsentWebview;
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayUtil.setFullScreenActivity(this);
        setContentView((int) R.layout.ad_policy_activity);
        InitView();
        DisplayUtil.hideActionBar(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void InitView() {
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.mAdConsentWebview = (WebView) findViewById(R.id.ad_consent_webview);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mToolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PrivacyPolicyActivityBase.this.finish();
            }
        });
        this.mAdConsentWebview.getSettings().setJavaScriptEnabled(true);
        this.mAdConsentWebview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return super.shouldOverrideUrlLoading(webView, str);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                PrivacyPolicyActivityBase.this.mProgressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(webView, str, bitmap);
            }

            public void onPageFinished(WebView webView, String str) {
                PrivacyPolicyActivityBase.this.mProgressBar.setVisibility(View.GONE);
                super.onPageFinished(webView, str);
            }
        });
        this.mAdConsentWebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    PrivacyPolicyActivityBase.this.mProgressBar.setVisibility(View.GONE);
                    return;
                }
                PrivacyPolicyActivityBase.this.mProgressBar.setVisibility(View.VISIBLE);
                PrivacyPolicyActivityBase.this.mProgressBar.setProgress(i);
            }
        });
        this.mAdConsentWebview.loadUrl(getResources().getString(R.string.link_policy));
    }

    @Override
    public void onPause() {
        super.onPause();
        WebView webView = this.mAdConsentWebview;
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        try {
            if (this.mAdConsentWebview != null) {
                this.mAdConsentWebview.removeAllViews();
                this.mAdConsentWebview.setTag(null);
//                this.mAdConsentWebview.clearCache(true);
//                this.mAdConsentWebview.clearHistory();
                this.mAdConsentWebview.destroy();
                this.mAdConsentWebview = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        WebView webView = this.mAdConsentWebview;
        if (webView != null) {
            webView.onResume();
        }
    }


}
