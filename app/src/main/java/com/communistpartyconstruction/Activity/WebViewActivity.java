package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent=getIntent();
        String contentUrl = intent.getStringExtra("contenturl");
        String text_title = intent.getStringExtra("title");
        webView = (WebView) findViewById(webview);
        title = (TextView) findViewById(R.id.webview_title_tv);
        if (text_title != null&& !text_title.equals("")){
            title.setText(text_title);
        }
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new HelloWebViewClient());
        webView.loadUrl(contentUrl);
        back = (LinearLayout) findViewById(R.id.webview_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Log.e("hekesong",contentUrl);
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
