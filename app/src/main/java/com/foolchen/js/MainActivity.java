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
        mWebSettings.setTextZoom(100);// 默认文字缩放比为100%(该属性仅api14及以上可使用)

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

        //        mWebView.loadUrl("file:///android_asset/test2.html");
        mWebView.loadUrl("http://a.xcar.com.cn/interface/6.0/bbs_detail.php?tid=21530314&p=1&type=0&deviceType=android&themeType=white&version=6.0.3B26&network=wifi");
    }

    @OnClick(R.id.button_decline)
    public void decline() {
        //mWebView.loadUrl("javascript:changeFontSize('12px')");
        changeFontSize(12);
    }

    @OnClick(R.id.button_reset)
    public void reset() {
        //mWebView.loadUrl("javascript:changeFontSize('16px')");
        changeFontSize(16);
    }

    @OnClick(R.id.button_rise)
    public void rise() {
        //mWebView.loadUrl("javascript:changeFontSize('20px')");
        changeFontSize(20);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    private void changeFontSize(int size) {
        String script = "javascript:(function changeFontSize(fontSize){\n" +
                "            var d=document.getElementsByTagName(\"div\");\n" +
                "            for(i=0;i<d.length;i++) {\n" +
                "                var divNode = d[i];\n" +
                "                if(divNode.className=='p_line')\n" +
                "                    divNode.style.fontSize=\'" + size + "px\';\n" +
                "            }\n" +
                "        })()";
        mWebView.loadUrl(script);
    }

}
