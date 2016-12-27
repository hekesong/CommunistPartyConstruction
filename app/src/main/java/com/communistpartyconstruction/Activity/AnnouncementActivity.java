package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.PartyBuildingNewsadAdapter;
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.PartyBuildingNews;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.HttpUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hekesong on 2016/12/18.
 */

public class AnnouncementActivity extends Activity {

    private LinearLayout back;
    private RecyclerView recycleView;
    private LinearLayoutManager linearLayoutManager;
    private PartyBuildingNewsadAdapter adapter;
    private List<PartyBuildingNews> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        initView();
        new announcementAsynctask().execute();
    }

    private void initView() {
        back = (LinearLayout) findViewById(R.id.announcement_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recycleView = (RecyclerView) findViewById(R.id.announcement_list);
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(AnnouncementActivity.this);

        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycleView.setLayoutManager(linearLayoutManager);

        recycleView.addItemDecoration(new InteractiveRecycleViewDecoration(AnnouncementActivity.this,OrientationHelper.VERTICAL));
    }

    class announcementAsynctask extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            JSONObject jsonParam = new JSONObject();
            try{
                jsonParam.put("pageIndex", "0");
                jsonParam.put("pageSize", "10");
                result = HttpUtils.HttpPost(AnnouncementActivity.this, Host.getAnnouncementsList,jsonParam);
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            list = HttpUtils.getNewsList(s,AnnouncementActivity.this);
            adapter = new PartyBuildingNewsadAdapter(AnnouncementActivity.this,list);
            adapter.setOnItemClickListener(new PartyBuildingNewsadAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, String data) {
                    Intent intent = new Intent();
                    intent.putExtra("contenturl",data);
                    intent.putExtra("title",AnnouncementActivity.this.getResources().getString(R.string.news_details));
                    intent.setClass(AnnouncementActivity.this, WebViewActivity.class);
                    startActivity(intent);
                }
            });
            recycleView.setAdapter(adapter);
        }
    }
}
