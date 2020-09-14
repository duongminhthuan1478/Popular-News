package com.example.android.popularnews;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.android.popularnews.Utils.ApiCall;
import com.example.android.popularnews.Utils.GetJsonAPI;
import com.example.android.popularnews.Utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class VideoActivity extends AppCompatActivity {


    private String url;
    private VideoView videoView;
    private MediaController mediaController;
    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().hide();
        url = getIntent().getStringExtra("url");
        pos = getIntent().getIntExtra("pos",0);
        videoView = findViewById(R.id.video_view);
        // Set the media controller buttons
        if (this.mediaController == null) {
            this.mediaController = new MediaController(this);
            // Set MediaController for VideoView
            this.videoView.setMediaController(mediaController);
        }

        videoView.setVideoPath(url);
        videoView.start();
        videoView.seekTo(pos);
    }


}