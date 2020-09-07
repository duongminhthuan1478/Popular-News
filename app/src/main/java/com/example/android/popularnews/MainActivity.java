package com.example.android.popularnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android.popularnews.Adapter.ArticleAdapter;

import com.example.android.popularnews.Interface.IListItemClickListener;
import com.example.android.popularnews.Utils.ConstantAPI;
import com.example.android.popularnews.Utils.Utils;
import com.example.android.popularnews.api.ApiClient;
import com.example.android.popularnews.api.ApiInterface;
import com.example.android.popularnews.models.Article;
import com.example.android.popularnews.models.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IListItemClickListener{

    private RecyclerView mArticleRecyclerView;
    private List<Article> articles = new ArrayList();
    private ArticleAdapter articleAdapter;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArticleRecyclerView = (RecyclerView)  findViewById(R.id.article_recyclerView);
        mArticleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mArticleRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mArticleRecyclerView.setNestedScrollingEnabled(false);
        mArticleRecyclerView.setHasFixedSize(true);
        loadJsonData();

    }


    public void loadJsonData() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String country = Utils.getCountry();

        Call<News> call = apiInterface.getNews(country, ConstantAPI.API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                 if(response.isSuccessful() && response.body().getArticles() != null) {
                     articles.clear();
                     articles = response.body().getArticles();
                     articleAdapter = new ArticleAdapter(articles, MainActivity.this,MainActivity.this);
                     mArticleRecyclerView.setAdapter(articleAdapter);
                     articleAdapter.notifyDataSetChanged();
                 }  else {
                     Toast.makeText(MainActivity.this, "No Response", Toast.LENGTH_LONG).show();
                 }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}