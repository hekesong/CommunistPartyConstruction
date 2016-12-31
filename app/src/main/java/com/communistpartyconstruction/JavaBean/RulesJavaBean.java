package com.communistpartyconstruction.JavaBean;

/**
 * Created by DerryChan on 2016/12/31 0031.
 */

public class RulesJavaBean {
    private String title,content,url;
    private int titleLength;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RulesJavaBean(){

    }

    public int getTitleLength() {
        return titleLength;
    }

    public void setTitleLength(int titleLength) {
        this.titleLength = titleLength;
    }

    public RulesJavaBean(String title, String content,int titleLength,String url){
        this.title = title;
        this.content = content;
        this.titleLength = titleLength;
        this.url = url;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
