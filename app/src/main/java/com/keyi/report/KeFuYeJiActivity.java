package com.keyi.report;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class KeFuYeJiActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kefuyeji);

        initWebView();
        initWebViewSetting();
        initWebViewClient();
        initWebChromeClient();
    }

    private void initWebChromeClient() {
        webView.setWebChromeClient(new WebChromeClient() {
            private Bitmap bitmap;

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public Bitmap getDefaultVideoPoster() {
                if (bitmap == null) {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_warning_24dp);
                    return bitmap;
                }
                return super.getDefaultVideoPoster();
            }
        });

    }



    private void initWebViewClient() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                super.onReceivedError(view, request, error);
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });

    }

    private void initWebViewSetting() {
        WebSettings webSettings=webView.getSettings();
        webView.requestFocusFromTouch();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.supportMultipleWindows();
        webSettings.setSupportMultipleWindows(true);

        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(webView.getContext().getCacheDir().getAbsolutePath());

        webSettings.setAllowFileAccess(true);
        webSettings.setNeedInitialFocus(true);
        if (Build.VERSION.SDK_INT>=19){
            webSettings.setLoadsImagesAutomatically(true);
        }else {
            webSettings.setLoadsImagesAutomatically(false);
        }
        webSettings.setNeedInitialFocus(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
    }

    private void initWebView() {
        webView= (WebView) findViewById(R.id.web_view);
        progressBar= (ProgressBar) findViewById(R.id.progress_bar);
        String url="http://118brd.keyierp.com/Static/9d.htm";
        webView.loadUrl(url);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
            webView.goBack();
            return true;
        }else {
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
}
