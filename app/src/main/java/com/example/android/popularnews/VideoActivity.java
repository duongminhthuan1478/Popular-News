package com.example.android.popularnews;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.Set;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class VideoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        String url = getIntent().getStringExtra("url");

    }
    String urlParams(String url, String params){
        Uri uri = Uri.parse("http://www.chalklit.in/post.html?chapter=V-Maths-Addition%20&%20Subtraction&post=394");
        String server = uri.getAuthority();
        String path = uri.getPath();
        String protocol = uri.getScheme();
        Set<String> args = uri.getQueryParameterNames();
        return uri.getQueryParameter(params);
    }
}