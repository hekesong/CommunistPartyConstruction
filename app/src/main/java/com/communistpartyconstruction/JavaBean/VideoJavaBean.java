package com.communistpartyconstruction.JavaBean;

/**
 * Created by DerryChan on 2016/12/31 0031.
 */

public class VideoJavaBean {
    private String title,cover,link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public VideoJavaBean(String title, String cover, String link) {
        this.title = title;
        this.cover = cover;
        this.link = link;
    }
    public VideoJavaBean(){

    }
}
