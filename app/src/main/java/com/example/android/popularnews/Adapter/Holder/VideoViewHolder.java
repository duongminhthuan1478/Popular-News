package com.example.android.popularnews.Adapter.Holder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.android.popularnews.R;

import androidx.recyclerview.widget.RecyclerView;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    public TextView title, description, author, published, source, time;
    public ImageView imgView, play;
    public ProgressBar progressBar;
    public VideoView videoView;
    public LinearLayout mediaControllerLayout;
    public ImageButton prev,next,fulls,playpause;
    public VideoViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        author = itemView.findViewById(R.id.author);
        published = itemView.findViewById(R.id.publishedAt);
        source = itemView.findViewById(R.id.source);
        time = itemView.findViewById(R.id.time);
        imgView = itemView.findViewById(R.id.img_thumb);
        play = imgView.findViewById(R.id.play);
        progressBar = itemView.findViewById(R.id.progress_load_photo);
        videoView = itemView.findViewById(R.id.video_view);
        mediaControllerLayout = itemView.findViewById(R.id.media_controller);
        prev = itemView.findViewById(R.id.prev);
        next = itemView.findViewById(R.id.next);
        fulls= itemView.findViewById(R.id.fulls);
        playpause= itemView.findViewById(R.id.play_pause);
    }
}