package com.communistpartyconstruction.Support;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hekesong on 2016/12/21.
 */

public class GetData {
    public static String getdata(Long time){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(time);
        String string = formatter.format(curDate);
        return string;
    }

    //判断该时间距离现在多久了
    public static String getDistanceFromNow(Long t){
        boolean isAddBefore = true ;//是否需要添加 前
        StringBuffer sb = new StringBuffer();
        long time = System.currentTimeMillis() - (t*1000);
        long mill = (long) Math.ceil(time /1000);//秒前

        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前

        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时

        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前

        if (day -7>0){
            sb.append(getdata(t));
            isAddBefore = false;
        }else if (day - 1 > 0) {
            sb.append(day + "天");
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
            isAddBefore = false;
        }
        if (isAddBefore) {
            sb.append("前");
        }
        return sb.toString();

    }

}
