package com.communistpartyconstruction.JavaBean;

/**
 * Created by hekesong on 2016/12/13.
 * 用来封装主页党建要闻的item数据
 */

public class PartyBuildingNews {
    private String title,imageurl,browse,share,time,contenturl;

    public String getTitle() {
        return title;
    }

    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShare() {
        return share;

    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getImageurl() {
        return imageurl;

    }

    public String getBrowse() {
        return browse;
    }

    public void setBrowse(String browse) {
        this.browse = browse;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "PartyBuildingNews{" +
                "browse='" + browse + '\'' +
                ", title='" + title + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", share='" + share + '\'' +
                ", time='" + time + '\'' +
                ", contenturl='" + contenturl + '\'' +
                '}';
    }

    public void setTitle(String title) {

        this.title = title;
    }
}
