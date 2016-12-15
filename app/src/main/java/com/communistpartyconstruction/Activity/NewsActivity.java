package com.communistpartyconstruction.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.communistpartyconstruction.Adapter.NewsTebAdapter;
import com.communistpartyconstruction.Fragment.ImportantSpeechFragment;
import com.communistpartyconstruction.Fragment.KnowledgeSchoolFragment;
import com.communistpartyconstruction.Fragment.TitlePartyBuildingNewsFragment;
import com.communistpartyconstruction.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hekesong on 2016/12/15.
 */

public class NewsActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPaper;
    private LinearLayout back;

    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<String>();//页卡标题集合
    private View view;//页卡视图
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();//页卡视图集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();



    }

    private void initView() {
        mViewPaper = (ViewPager) findViewById(R.id.news_viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.news_tabs);
        back = (LinearLayout) findViewById(R.id.news_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mInflater = LayoutInflater.from(NewsActivity.this);


        //添加页卡视图

        mTitleList.add(getResources().getString(R.string.title_party_building_news));
        mTitleList.add(getResources().getString(R.string.important_speech));
        mTitleList.add(getResources().getString(R.string.knowledge_school));

        TitlePartyBuildingNewsFragment titlePartyBuildingNewsFragment = new TitlePartyBuildingNewsFragment();
        ImportantSpeechFragment importantSpeechFragment = new ImportantSpeechFragment();
        KnowledgeSchoolFragment knowledgeSchoolFragment = new KnowledgeSchoolFragment();
        mFragmentList.add(titlePartyBuildingNewsFragment);
        mFragmentList.add(importantSpeechFragment);
        mFragmentList.add(knowledgeSchoolFragment);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));

        NewsTebAdapter newstebadapter = new NewsTebAdapter(getSupportFragmentManager(),mFragmentList,mTitleList);
        mViewPaper.setAdapter(newstebadapter);
        mTabLayout.setupWithViewPager(mViewPaper);
        mTabLayout.setTabsFromPagerAdapter(newstebadapter);
    }
}
