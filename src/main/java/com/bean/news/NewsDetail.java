package com.bean.news;

/**
 * Created by huangweii on 2016/3/22.<br>
 * 新闻详情
 */
public class NewsDetail {
    private int id;
    private String newsDetailsTitle;
    private String newsDetailsUrl;
    private String newsDetailsBody;
    private String newsDetailsAuthor;
    private String newsDetailsCreateDate;
    private int newsType;

    public NewsDetail() {
    }

    public String getNewsDetailsTitle() {
        return newsDetailsTitle;
    }

    public void setNewsDetailsTitle(String newsDetailsTitle) {
        this.newsDetailsTitle = newsDetailsTitle;
    }

    public String getNewsDetailsUrl() {
        return newsDetailsUrl;
    }

    public void setNewsDetailsUrl(String newsDetailsUrl) {
        this.newsDetailsUrl = newsDetailsUrl;
    }

    public String getNewsDetailsBody() {
        return newsDetailsBody;
    }

    public void setNewsDetailsBody(String newsDetailsBody) {
        this.newsDetailsBody = newsDetailsBody;
    }

    public String getNewsDetailsAuthor() {
        return newsDetailsAuthor;
    }

    public void setNewsDetailsAuthor(String newsDetailsAuthor) {
        this.newsDetailsAuthor = newsDetailsAuthor;
    }

    public String getNewsDetailsCreateDate() {
        return newsDetailsCreateDate;
    }

    public void setNewsDetailsCreateDate(String newsDetailsCreateDate) {
        this.newsDetailsCreateDate = newsDetailsCreateDate;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
