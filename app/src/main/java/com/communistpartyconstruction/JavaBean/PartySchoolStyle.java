package com.communistpartyconstruction.JavaBean;

/**
 * Created by hekesong on 2016/12/18.
 */

public class PartySchoolStyle {
    private String red_title,common_title,author,the_number_of_clicks,time,contenturl;
    private boolean isPartySchoolStyle;

    public String getCommon_title() {
        return common_title;
    }

    public boolean isPartySchoolStyle() {
        return isPartySchoolStyle;
    }

    public void setIsPartySchoolStyle(boolean partySchoolStyle) {
        isPartySchoolStyle = partySchoolStyle;
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

    public String getThe_number_of_clicks() {
        return the_number_of_clicks;
    }

    public void setThe_number_of_clicks(String the_number_of_clicks) {
        this.the_number_of_clicks = the_number_of_clicks;
    }

    public String getRed_title() {
        return red_title;

    }

    public void setRed_title(String red_title) {
        this.red_title = red_title;
    }

    public void setCommon_title(String common_title) {
        this.common_title = common_title;
    }

    public String getAuthor() {
        return author;

    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
