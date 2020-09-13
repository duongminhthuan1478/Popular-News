package com.example.android.popularnews.Adapter.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularnews.R;

import androidx.recyclerview.widget.RecyclerView;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    public TextView title, description, author, published, source, time;
    public ImageView imgView, play;
    public ProgressBar progressBar;
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
    }
}