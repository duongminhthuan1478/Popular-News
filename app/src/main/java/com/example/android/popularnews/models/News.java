package com.example.android.popularnews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class News {

    @SerializedName("item")
    @Expose
    private List<Article> articles;

}
