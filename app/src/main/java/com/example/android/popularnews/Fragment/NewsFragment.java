package com.example.android.popularnews.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.android.popularnews.Adapter.ArticleAdapter;
import com.example.android.popularnews.Adapter.Holder.ArticleViewHolder;
import com.example.android.popularnews.MainActivity;
import com.example.android.popularnews.R;
import com.example.android.popularnews.Utils.ApiCall;
import com.example.android.popularnews.DetailArticleActivity;
import com.example.android.popularnews.Utils.GetJsonAPI;
import com.example.android.popularnews.Utils.Utils;

import com.example.android.popularnews.Utils.XMLDOMParser;
import com.example.android.popularnews.models.Article;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class NewsFragment extends Fragment implements ArticleAdapter.ArticleAdapterInterface {
    Context context;
    String url = "";
    TextView Loading;
    private RecyclerView mArticleRecyclerView;
    private List<Article> articles = new ArrayList();
    private ArticleAdapter articleAdapter;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            context = getActivity();
        } catch (NullPointerException e) {
        }
        return inflater.inflate(R.layout.fragment_news, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mArticleRecyclerView = (RecyclerView) view.findViewById(R.id.article_recyclerView);
        mArticleRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mArticleRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mArticleRecyclerView.setNestedScrollingEnabled(false);
        mArticleRecyclerView.setHasFixedSize(true);

        articleAdapter = new ArticleAdapter(articles, this);
        mArticleRecyclerView.setAdapter(articleAdapter);
        articleAdapter.notifyDataSetChanged();
        Loading = view.findViewById(R.id.loading_text_view);
    }

    @Override
    public void onResume() {
        getData();
        loadJsonData();
        super.onResume();
    }

    void getData() {
        Bundle arg = getArguments();
        if (arg != null) {
            url = arg.getString("url");
        }
    }

    public void loadJsonData() {
        GetJsonAPI
                .setUrl(url)
                .addHeader("Content-Type", "application/xml")
                .get(new ApiCall.AsyncApiCall() {
                    @Override
                    public void onSuccess(long resTime, String result) {
                        try {
                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder builder = factory.newDocumentBuilder();
                            InputSource is = new InputSource(new StringReader(result));
                            Document doc = builder.parse(is);
                            NodeList nList = doc.getElementsByTagName("item");
                            XMLDOMParser parser = new XMLDOMParser();
                            for (int i = 0; i < nList.getLength(); i++) {
                                Element element = (Element) nList.item(i);
                                String category = element.getElementsByTagName("category").item(0).getTextContent();
                                String title = element.getElementsByTagName("title").item(0).getTextContent();
                                String description = element.getElementsByTagName("description").item(0).getTextContent();
                                String url = element.getElementsByTagName("link").item(0).getTextContent();
                                String publishedAt = element.getElementsByTagName("pubDate").item(0).getTextContent();
                                String img = element.getElementsByTagName("media:content").item(0).getAttributes().getNamedItem("url").getNodeValue();
                                String content = element.getElementsByTagName("content:encoded").item(0).getTextContent();
                                articles.add(new Article(category, title, description, url, publishedAt, img, content));
                            }
                            articleAdapter.notifyDataSetChanged();
                            Loading.setVisibility(View.INVISIBLE);
                        } catch (Exception e) {
                            Loading.setText("Lấy dữ liệu thất bại");
                        }
                    }

                    @Override
                    public void onFail(int responeCode, String mess) {

                    }
                });
    }


    @Override
    public void onArticleBind(final ArticleViewHolder holder, final List<Article> Articles, final int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = Articles.get(position).getContent();
                Intent intent = new Intent(getActivity(), DetailArticleActivity.class);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });
        Article model = articles.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(this)
                .load(model.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imgView);
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.source.setText(model.getSource());
        holder.time.setText(model.getPublishedAt());
        holder.author.setText("");
        holder.published.setText( model.getPublishedAt());
    }
}