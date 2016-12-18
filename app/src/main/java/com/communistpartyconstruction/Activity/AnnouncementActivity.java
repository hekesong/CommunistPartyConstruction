package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.PartyBuildingNewsadAdapter;
import com.communistpartyconstruction.JavaBean.PartyBuildingNews;
import com.communistpartyconstruction.R;

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
    private List<PartyBuildingNews> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        initData();
        initView();
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
        adapter = new PartyBuildingNewsadAdapter(AnnouncementActivity.this,list);
        adapter.setOnItemClickListener(new PartyBuildingNewsadAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Intent intent = new Intent();
                intent.putExtra("contenturl",data);
                intent.putExtra("title",getResources().getString(R.string.news_details));
                intent.setClass(AnnouncementActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
        recycleView.setAdapter(adapter);
        recycleView.addItemDecoration(new InteractiveRecycleViewDecoration(AnnouncementActivity.this,OrientationHelper.VERTICAL));
    }

    private void initData() {
        list = new ArrayList<>();
        PartyBuildingNews partyBuildingNews = new PartyBuildingNews();
        for (int i = 0; i < 20; i++) {
            partyBuildingNews.setTitle("微固分党校第56期入党积极分子培训班教学计划");
            partyBuildingNews.setBrowse("463次");
            partyBuildingNews.setComment("156次");
            partyBuildingNews.setTime("一天前");
            partyBuildingNews.setShare("816次");
            partyBuildingNews.setContenturl("http://www.baidu.com/");
            list.add(partyBuildingNews);
        }
    }
}
