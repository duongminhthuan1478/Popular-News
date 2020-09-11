package com.example.android.popularnews.models;

import com.example.android.popularnews.Utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Article {

    //name website
    private String source = "VN Express";

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("link")
    @Expose
    private String url ;

    private String urlToImage= "";

    @SerializedName("pubDate")
    @Expose
    private String publishedAt;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Utils.stripHtml(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        if(urlToImage.length() == 0) {
            String imgRegex = "(?i)<img[^>]+?src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";

            Pattern p = Pattern.compile(imgRegex);
            Matcher m = p.matcher(this.description);

            while (m.find()) {
                String imgSrc = m.group(1);
                return imgSrc;
            }
        }
        return this.urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Article(String source, String title, String description, String url, String publishedAt) {
        this.source = source;
        this.title = title;
        this.description = description;
        this.url = url;
        this.publishedAt = publishedAt;
    }
}
