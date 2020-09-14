package com.example.android.popularnews.Adapter.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.android.popularnews.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    public TextView title, description, author, published, source, time;
    public ImageView imgView;
    public ProgressBar progressBar;
    public CardView cardView;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        author = itemView.findViewById(R.id.author);
        published = itemView.findViewById(R.id.publishedAt);
        source = itemView.findViewById(R.id.source);
        time = itemView.findViewById(R.id.time);
        imgView = itemView.findViewById(R.id.img);
        progressBar = itemView.findViewById(R.id.progress_load_photo);
        cardView = itemView.findViewById(R.id.news_item_card_view);
    }
}