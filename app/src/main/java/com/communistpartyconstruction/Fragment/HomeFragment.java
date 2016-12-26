package com.communistpartyconstruction.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.communistpartyconstruction.Activity.AnnouncementActivity;
import com.communistpartyconstruction.Activity.NewsActivity;
import com.communistpartyconstruction.Activity.StytleActivity;
import com.communistpartyconstruction.Activity.WebViewActivity;
import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.PartyBuildingNewsadAdapter;
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.PartyBuildingNews;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.FullyLinearLayoutManager;
import com.communistpartyconstruction.Support.GetData;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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
 * Created by hekesong on 2016/11/15.
 */

public class HomeFragment extends Fragment{
    private View view;
    private RecyclerView recycleView;
    private FullyLinearLayoutManager fullyLinearLayoutManager;
    private PartyBuildingNewsadAdapter adapter;
    private ScrollView scrollView;
    private List<PartyBuildingNews> list;
    private LinearLayout news,style,theoretical_overview,announcement;
    private myListen myListen;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null){
                view = inflater.inflate(R.layout.home_fragment,container,false);
        }
        initView();
        myListen = new myListen();
        list = new ArrayList<PartyBuildingNews>();
        new NewsAsyncTask().execute();

        return  view;
    }

    private void initView() {
        news = (LinearLayout) view.findViewById(R.id.home_news_ll);
        news.setOnClickListener(myListen);
        style = (LinearLayout) view.findViewById(R.id.home_style_ll);
        style.setOnClickListener(myListen);
        theoretical_overview = (LinearLayout) view.findViewById(R.id.home_theoretical_overview_ll);
        theoretical_overview.setOnClickListener(myListen);
        announcement = (LinearLayout) view.findViewById(R.id.home_announcement_ll);
        announcement.setOnClickListener(myListen);
        recycleView = (RecyclerView) view.findViewById(R.id.home_list);
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);
        fullyLinearLayoutManager = new FullyLinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        fullyLinearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycleView.setLayoutManager(fullyLinearLayoutManager);

        recycleView.addItemDecoration(new InteractiveRecycleViewDecoration(this.getActivity(),OrientationHelper.VERTICAL));
        //解决打开APP后，直接滑到recycleView的位置，上面的view被遮挡
        scrollView = (ScrollView) view.findViewById(R.id.home_fragment_scrollview);
        scrollView.smoothScrollTo(0,20);
    }

    class myListen implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.home_news_ll:
                    Intent newIntent = new Intent(getActivity(), NewsActivity.class);
                    startActivity(newIntent);
                    break;
                case R.id.home_style_ll:
                    Intent styleIntent = new Intent(getActivity(), StytleActivity.class);
                    styleIntent.putExtra("type","TYPE_STYLE");
                    startActivity(styleIntent);
                    break;
                case R.id.home_theoretical_overview_ll:
                    Intent theoreticalIntent = new Intent(getActivity(), StytleActivity.class);
                    theoreticalIntent.putExtra("type","TYPE_THEORETICAL_OVERVIEW");
                    startActivity(theoreticalIntent);
                    break;
                case R.id.home_announcement_ll:
                    Intent announcementIntent = new Intent(getActivity(), AnnouncementActivity.class);
                    startActivity(announcementIntent);
                    break;
                default:
                    break;
            }
        }
    }
    //网络数据获取
    class NewsAsyncTask extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            String result = getNews();
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals("")){
                Log.e("hekesong",s);
                JSONObject object;
                try {
                    object = new JSONObject(s);
                    JSONArray array = object.getJSONArray("news");
                    PartyBuildingNews partyBuildingNews = new PartyBuildingNews();
                    for (int i=0;i<array.length();i++){
                        JSONObject object1 = array.getJSONObject(i);
                        partyBuildingNews.setContenturl(object1.getString("link"));
                        partyBuildingNews.setShare(object1.getString("sharesNum")+getResources().getString(R.string.times));
                        partyBuildingNews.setBrowse(object1.getString("pv")+getResources().getString(R.string.times));
                        partyBuildingNews.setTitle(object1.getString("title"));
                        partyBuildingNews.setImageurl(Host.pictureUrl+object1.getString("images"));
                        partyBuildingNews.setTime(GetData.getDistanceFromNow(object1.getLong("publishTime")));
                        partyBuildingNews.setComment("249次");
                        list.add(partyBuildingNews);
                    }

                    adapter = new PartyBuildingNewsadAdapter(getActivity(),list);
                    adapter.setOnItemClickListener(new PartyBuildingNewsadAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, String data) {
                            Intent intent = new Intent();
                            intent.putExtra("contenturl",data);
                            intent.putExtra("title",getActivity().getResources().getString(R.string.news_details));
                            intent.setClass(getActivity(), WebViewActivity.class);
                            startActivity(intent);
                        }
                    });
                    recycleView.setAdapter(adapter);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getActivity(),getResources().getString(R.string.internet_problem),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getNews(){
        String result = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Host.news);
        JSONObject jsonParam = new JSONObject();
        try{
            jsonParam.put("pageIndex", "0");
            jsonParam.put("pageSize", "10");
            StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e){

        }

        return result;
    }
}
