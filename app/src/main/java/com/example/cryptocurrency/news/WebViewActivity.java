package com.example.cryptocurrency.news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptocurrency.R;


public class WebViewActivity extends AppCompatActivity implements CustomWebChromeClient.ProgressListener {

    private WebView mWebView;
    private ProgressBar mProgressBar;
     Toolbar mToolbar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mWebView = this.findViewById(R.id.webView);
        mProgressBar = findViewById(R.id.progressBar);

        mProgressBar.setMax(100);
        mWebView.setWebChromeClient(new CustomWebChromeClient(this));
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);

            }
        });
        mWebView.loadUrl(url);
        mProgressBar.setProgress(0);
    }



    @Override
    public void onUpdateProgress(int progressValue) {
        mProgressBar.setProgress(progressValue);
        if (progressValue == 100) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }




}
