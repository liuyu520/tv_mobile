package com.bean.news;

/**
 * Created by huangweii on 2016/3/22.<br>
 * 新闻列表
 */
public class NewsListItem {
    private String title;
    /***
     * 新闻的发布时间
     */
    private String date;
    private String newsType;
    /***
     * 新闻详情的接口地址
     */
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    /***
     * 新闻详情的接口地址
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /***
     * 新闻详情的接口地址
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
