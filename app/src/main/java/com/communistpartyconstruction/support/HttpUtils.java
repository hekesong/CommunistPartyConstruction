package com.communistpartyconstruction.Support;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.PartyBuildingNews;
import com.communistpartyconstruction.JavaBean.PartySchoolStyle;
import com.communistpartyconstruction.R;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hekesong on 2016/12/27.
 */

public class HttpUtils {
    public static String HttpGet(Context context,String url){
        String result = "";
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        HttpResponse httpResponse = null;
        try{
            httpResponse = client.execute(get);
            result = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e){

        } finally {
            client.getConnectionManager().shutdown();
        }
        return result;
    }

    public static String HttpPost(Context context, String url, JSONObject jsonParam){
        String result = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine=response.getStatusLine();//获取请求对象中的响应行对象
            int responseCode=statusLine.getStatusCode();//从状态行中获取状态码
            if (responseCode == 200){
                result = EntityUtils.toString(response.getEntity());
            } else {
                Toast.makeText(context,context.getResources().getString(R.string.server_connection_failed),Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e){
            Log.e("JsonException",e.toString());
        } finally {
            client.getConnectionManager().shutdown();
        }
        return result;
    }

    //对获取PartyBuildingNews对象数据的json解析封装
    public static List<PartyBuildingNews> getNewsList (String s,Context context){
        List<PartyBuildingNews> list = new ArrayList<>();
        if (!s.equals("")){
            JSONObject object;
            try {
                object = new JSONObject(s);
                JSONArray array = object.getJSONArray("news");
                PartyBuildingNews partyBuildingNews = new PartyBuildingNews();
                for (int i=0;i<array.length();i++){
                    JSONObject object1 = array.getJSONObject(i);
                    partyBuildingNews.setContenturl(object1.getString("link"));
                    partyBuildingNews.setShare(object1.getString("sharesNum")+context.getResources().getString(R.string.times));
                    partyBuildingNews.setBrowse(object1.getString("pv")+context.getResources().getString(R.string.times));
                    partyBuildingNews.setTitle(object1.getString("title"));
                    partyBuildingNews.setImageurl(Host.pictureUrl+object1.getString("images"));
                    partyBuildingNews.setTime(GetData.getDistanceFromNow(object1.getLong("publishTime")));
                    list.add(partyBuildingNews);
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            Toast.makeText(context,context.getResources().getString(R.string.internet_problem),Toast.LENGTH_SHORT).show();
        }

        return list;
    }

    public static List<PartySchoolStyle> getStyleList(String s,boolean isPartySchoolStyle,Context context){
        List<PartySchoolStyle> list = new ArrayList<>();
        if (!s.equals("")){
            JSONObject object;
            try {
                object = new JSONObject(s);
                JSONArray array = object.getJSONArray("partySchoolActivities");
                PartySchoolStyle partySchoolStyle = new PartySchoolStyle();
                partySchoolStyle.setIsPartySchoolStyle(isPartySchoolStyle);
                for (int i=0;i<array.length();i++){
                    JSONObject object1 = array.getJSONObject(i);
                    partySchoolStyle.setRed_title(object1.getString("category"));
                    partySchoolStyle.setCommon_title(object1.getString("title"));
                    partySchoolStyle.setAuthor(object1.getString("author"));
                    partySchoolStyle.setThe_number_of_clicks(object1.getString("clicksNum")+context.getResources().getString(R.string.times));
                    partySchoolStyle.setTime(GetData.getdata(object1.getLong("publishTime")*1000));
                    partySchoolStyle.setContenturl(object1.getString("link"));
                    list.add(partySchoolStyle);
                }
            } catch (Exception e){
            }
        } else {
            Toast.makeText(context,context.getResources().getString(R.string.internet_problem),Toast.LENGTH_SHORT).show();
        }
        return list;
    }
}
