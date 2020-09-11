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
import com.example.android.popularnews.Adapter.Holder.ArticleViewHolder;
import com.example.android.popularnews.Interface.IListItemClickListener;
import com.example.android.popularnews.R;
import com.example.android.popularnews.Utils.Utils;
import com.example.android.popularnews.models.Article;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder>{
    ArticleAdapterInterface articleAdapterInterface;
    private static final String TAG = ArticleAdapter.class.getSimpleName();
    private List<Article> articles;


    public ArticleAdapter(List<Article> articleList, ArticleAdapterInterface articleAdapterInterface) {
        this.articles = articleList;
        this.articleAdapterInterface = articleAdapterInterface;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        articleAdapterInterface.onArticleBind(holder,articles,position);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public interface ArticleAdapterInterface {
        void onArticleBind(ArticleViewHolder holder, List<Article> Articles , int position);
    }
}
