package com.example.maksim_zakharenka.broowerandroidapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    public static final String URL_HOST = "https://broower.github.io/";
    public static final int FADE_OUT_MILLIS = 750;

    private View mSplashBackgroundView;
    private View mSplashView;
    private WebView mWebView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initWebView();
    }

    private void initViews() {
        Utils.setStatusBarColor(this, R.color.status_bar_color);

        mSplashView = findViewById(R.id.splash_view);
        mSplashBackgroundView = findViewById(R.id.splash_background_view);
        mWebView = findViewById(R.id.web_view);

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                if (url.startsWith(URL_HOST)) {
                    view.loadUrl(url);

                    return false;
                } else {
                    super.shouldOverrideUrlLoading(view, url);
                }

                return true;
            }

            public void onPageFinished(final WebView view, final String url) {
                fadeOutLogos();
            }
        });

        mWebView.loadUrl(URL_HOST);
    }

    private void fadeOutLogos() {
        final Animation fadeOut = getFadeOutAnimation();
        fadeOut.setAnimationListener(getLogosAnimationListener());

        mSplashView.startAnimation(fadeOut);
    }

    private void fadeOutBackground() {
        final Animation fadeOut = getFadeOutAnimation();

        fadeOut.setAnimationListener(getBackgroundAnimationListener());

        mSplashBackgroundView.startAnimation(fadeOut);
    }

    private Animation getFadeOutAnimation() {
        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(FADE_OUT_MILLIS);

        return fadeOut;
    }

    private Animation.AnimationListener getBackgroundAnimationListener() {
        return new SimpleAnimationListener() {

            public void onAnimationEnd(final Animation animation) {
                mSplashBackgroundView.setVisibility(View.GONE);
            }
        };
    }

    private Animation.AnimationListener getLogosAnimationListener() {
        return new SimpleAnimationListener() {

            public void onAnimationEnd(final Animation animation) {
                mSplashView.setVisibility(View.GONE);

                fadeOutBackground();
            }
        };
    }
}
