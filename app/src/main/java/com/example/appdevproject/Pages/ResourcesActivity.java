package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.appdevproject.R;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        WebView webView = findViewById(R.id.youtubeView1);
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XYkwa1D1AC4?si=gJlETCARQGmjD2ZM\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        webView.loadData(video, "text/html","utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        WebView webView2 = findViewById(R.id.youtubeView2);
        String video2 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/PvEUj_mRfIo?si=e2rHDwWE6r2ygspo\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        webView2.loadData(video2, "text/html","utf-8");
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setWebChromeClient(new WebChromeClient());

        WebView webView3 = findViewById(R.id.webView1);

        WebSettings webSettings1 = webView3.getSettings();
        webSettings1.setJavaScriptEnabled(true);

        webView3.loadUrl("https://www.nerdwallet.com/article/finance/how-to-budget");

    }
}