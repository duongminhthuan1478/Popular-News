package com.example.android.popularnews.api;

import com.example.android.popularnews.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=f2e472172b23470dba721bd0ecb73475
    @GET("top-headlines")
    Call<News> getNews(
            //
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
}
