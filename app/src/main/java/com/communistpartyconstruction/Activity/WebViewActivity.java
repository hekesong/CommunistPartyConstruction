package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.communistpartyconstruction.R;

import static com.communistpartyconstruction.R.id.webview;

/**
 * Created by hekesong on 2016/12/14.
 */

public class WebViewActivity extends Activity {

    private WebView webView;
    private LinearLayout back;
    private TextView title;
    private ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        String contentUrl = intent.getStringExtra("contenturl");
        String text_title = intent.getStringExtra("title");
        title = (TextView) findViewById(R.id.webview_title_tv);
        webView = (WebView) findViewById(webview);
        pg = (ProgressBar) findViewById(R.id.webview_progressbar);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100){
                    pg.setVisibility(View.GONE);
                } else {
                    pg.setVisibility(View.VISIBLE);
                    pg.setProgress(newProgress);
                }
            }
        });

        if (text_title != null&& !text_title.equals("")){
            title.setText(text_title);
        }
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new HelloWebViewClient());
        //屏幕自适应
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        //页面支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webView.loadUrl(contentUrl);



        back = (LinearLayout) findViewById(R.id.webview_back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //Web视图
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
