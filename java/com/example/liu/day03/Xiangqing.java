package com.example.liu.day03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Xiangqing extends AppCompatActivity {

    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        initView();

        String url = getIntent().getStringExtra("url");
        mWeb.loadUrl(url);
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
    }
}
