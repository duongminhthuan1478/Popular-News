package com.example.android.popularnews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.android.popularnews.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class DetailArticleActivity extends AppCompatActivity {

    private WebView webView;
    private String content;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_article);

        Intent intent = getIntent();
        content = intent.getStringExtra("content");
        webView = findViewById(R.id.webview);
        webView.loadDataWithBaseURL(null,"<style>a{display:none} iframe{display:none} td{font-size: 15px !important; font-family: 'Montserrat', sans-serif;} body{font-size: 20px !important; font-family: 'Montserrat', sans-serif;} img{max-width:100%} </style>"
                + content,"text/html","utf-8",null);
    }

}