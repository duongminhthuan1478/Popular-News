package com.example.android.popularnews.Fragment;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.android.popularnews.Adapter.ArticleAdapter;
import com.example.android.popularnews.Adapter.Holder.VideoViewHolder;
import com.example.android.popularnews.Adapter.NewsPagerAdapter;
import com.example.android.popularnews.Adapter.VideoAdapter;
import com.example.android.popularnews.CustomView.VerticalViewPager;
import com.example.android.popularnews.MainActivity;
import com.example.android.popularnews.R;
import com.example.android.popularnews.Utils.ApiCall;
import com.example.android.popularnews.Utils.ConstantAPI;
import com.example.android.popularnews.Utils.GetJsonAPI;
import com.example.android.popularnews.Utils.Utils;
import com.example.android.popularnews.Utils.XMLDOMParser;
import com.example.android.popularnews.VideoActivity;
import com.example.android.popularnews.models.Article;
import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class VideoFragment extends Fragment implements VideoAdapter.VideoAdapterInterface {
    Context context;
    String url = "https://baotintuc.vn/video.rss";
    TextView Loading;
    private RecyclerView mArticleRecyclerView;
    private List<Article> articles = new ArrayList();
    private VideoAdapter articleAdapter;
    private String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.context = (MainActivity) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mArticleRecyclerView = (RecyclerView) view.findViewById(R.id.article_recyclerView);
        mArticleRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mArticleRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mArticleRecyclerView.setNestedScrollingEnabled(false);
        mArticleRecyclerView.setHasFixedSize(true);

        articleAdapter = new VideoAdapter(articles, this);
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
                                String title = element.getElementsByTagName("title").item(0).getTextContent();
                                String description = element.getElementsByTagName("description").item(0).getTextContent();
                                String url = element.getElementsByTagName("link").item(0).getTextContent();
                                String publishedAt = element.getElementsByTagName("pubDate").item(0).getTextContent();
                                articles.add(new Article("Báo tin tức", title, description, url, publishedAt, "", ""));
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
    public void onArticleBind(final VideoViewHolder holder, List<Article> Articles, final int position) {
        final Article model = articles.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        holder.playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.videoView.isPlaying()) {
                    holder.videoView.pause();
                    holder.playpause.setImageDrawable(getResources().getDrawable(R.drawable.play));
                } else {
                    holder.playpause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                    holder.videoView.start();
                }

            }
        });
        holder.fulls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (articles.get(position).getVideoUrl().length() != 0) {
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("url", articles.get(position).getVideoUrl());
                    intent.putExtra("pos", holder.videoView.getCurrentPosition());
                    startActivity(intent);
                }
            }
        });
        holder.prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPos = holder.videoView.getCurrentPosition() - 10;
                holder.videoView.seekTo(newPos);
            }
        });
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPos = holder.videoView.getCurrentPosition() + 10;
                holder.videoView.seekTo(newPos);
            }
        });
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(articles.get(position).getVideoUrl().length() == 0 && !articles.get(position).isLoadingVid){
                    articles.get(position).isLoadingVid = true;
                    holder.videoView.bringToFront();
                    holder.mediaControllerLayout.bringToFront();
                    StartVideo(holder.videoView, model.getUrl(), position);
                }
            }
        });
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
        holder.time.setText(Utils.DateFormat(model.getPublishedAt()));
        holder.author.setText("");
        holder.published.setText(model.getPublishedAt());
    }

    void StartVideo(final VideoView videoView, String url, final int pos) {
        GetJsonAPI.setUrl(url).addHeader("Content-Type", "text/html;  charset=UTF-8").get(new ApiCall.AsyncApiCall() {
            @Override
            public void onSuccess(long resTime, String result) throws ParserConfigurationException {
                org.jsoup.nodes.Document document = (org.jsoup.nodes.Document) Jsoup.parse(result);
                org.jsoup.nodes.Element e = document.select("iframe#divVideo_2").first();
                String videoPath = Utils.urlParams(e.attr("src"), "fileId");
                videoView.setVideoPath(videoPath);
                videoView.start();
                articles.get(pos).setVideoUrl(videoPath);
            }

            @Override
            public void onFail(int responeCode, String mess) {

            }
        });

    }
}