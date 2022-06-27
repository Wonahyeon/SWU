package com.cookandroid.swu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;

public class PillSearch extends AppCompatActivity {
    ImageView back,camera;
    EditText edtSearch;
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pill);
        back = findViewById(R.id.back);
        camera = findViewById(R.id.camera);
        edtSearch = findViewById(R.id.edtSearch);
        web = findViewById(R.id.webView);
        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.setImageResource(R.drawable.search);
            }
        });
        web.setWebViewClient(new PillWebViewClient());
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl("https://m.search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" + edtSearch.getText().toString());
            }
        });
    }

    private class PillWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}