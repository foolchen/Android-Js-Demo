package com.foolchen.js;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @InjectView(R.id.web_view) WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i(TAG, "finish : " + url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.i(TAG, "errorCode : " + errorCode);
            }
        });

        mWebView.loadUrl("file:///android_asset/test.html");
    }

    @OnClick(R.id.button_decline)
    public void decline() {
        mWebView.loadUrl("javascript:changeFontSize2('12px')");
    }

    @OnClick(R.id.button_reset)
    public void reset() {
        mWebView.loadUrl("javascript:changeFontSize2('16px')");
    }

    @OnClick(R.id.button_rise)
    public void rise() {
        mWebView.loadUrl("javascript:changeFontSize2('20px')");
    }
}
