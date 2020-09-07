package com.example.android.popularnews.Adapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.android.popularnews.Interface.IListItemClickListener;
import com.example.android.popularnews.R;
import com.example.android.popularnews.Utils.Utils;
import com.example.android.popularnews.models.Article;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>{

    private static final String TAG = ArticleAdapter.class.getSimpleName();
    private List<Article> articles;
    private Context context;
    final private IListItemClickListener mOnClickListener;

    public ArticleAdapter(List<Article> articleList, Context context, IListItemClickListener listener) {
        this.articles = articleList;
        this.context = context;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, description, author, published, source, time;
        ImageView imgView;
        ProgressBar progressBar;



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
            itemView.setOnClickListener(this);

        }

        void bind(int position) {
            Article model = articles.get(position);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(Utils.getRandomDrawbleColor());
            requestOptions.error(Utils.getRandomDrawbleColor());
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
            requestOptions.centerCrop();

            Glide.with(context)
                    .load(model.getUrlToImage())
                    .apply(requestOptions)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgView);
            title.setText(model.getTitle());
            description.setText(model.getDescription());
            source.setText(model.getSource().getName());
            time.setText("\u2022" + Utils.DateToTimeFormat(model.getPublishedAt()));
            author.setText(model.getAuthor());
            published.setText(model.getPublishedAt());

        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
