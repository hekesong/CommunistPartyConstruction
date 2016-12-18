package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.PartySchoolStyleAdapter;
import com.communistpartyconstruction.JavaBean.PartySchoolStyle;
import com.communistpartyconstruction.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hekesong on 2016/12/18.
 */

public class StytleActivity extends Activity {

    private RecyclerView recycleView;
    private LinearLayout back;
    private LinearLayoutManager linearLayoutManager;
    private PartySchoolStyleAdapter adapter;
    private List<PartySchoolStyle> list;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        title = (TextView) findViewById(R.id.style_title);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("TYPE_STYLE")){
            initData1();
            title.setText(getResources().getString(R.string.style));
        }else if(type.equals("TYPE_THEORETICAL_OVERVIEW")){
            initData2();
            title.setText(getResources().getString(R.string.theoretical_overview));
        }

        initView();
    }

    private void initData1() {
        list = new ArrayList<>();
        PartySchoolStyle partySchoolStyle = new PartySchoolStyle();
        for (int i = 0; i < 20; i++) {
            partySchoolStyle.setIsPartySchoolStyle(true);
            partySchoolStyle.setCommon_title("青春演绎 红歌奋进 ——通信分党校第十二届红歌演绎会圆满落幕");
            partySchoolStyle.setTime("2016-12-18");
            partySchoolStyle.setAuthor("龚俊狼");
            partySchoolStyle.setRed_title("学校党校");
            partySchoolStyle.setThe_number_of_clicks("168次");
            partySchoolStyle.setContenturl("http://www.baidu.com/");
            list.add(partySchoolStyle);
        }
    }
    private void initData2() {
        list = new ArrayList<>();
        PartySchoolStyle partySchoolStyle = new PartySchoolStyle();
        for (int i = 0; i < 20; i++) {
            partySchoolStyle.setIsPartySchoolStyle(false);
            partySchoolStyle.setCommon_title("习近平在全国党校会议上发表重要讲话");
            partySchoolStyle.setTime("2016-12-18");
            partySchoolStyle.setAuthor("龚俊狼");
            partySchoolStyle.setRed_title("学校党校");
            partySchoolStyle.setThe_number_of_clicks("168次");
            partySchoolStyle.setContenturl("http://www.baidu.com/");
            list.add(partySchoolStyle);
        }
    }

    private void initView() {
        back = (LinearLayout) findViewById(R.id.style_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recycleView = (RecyclerView) findViewById(R.id.style_list);
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(StytleActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycleView.setLayoutManager(linearLayoutManager);
        adapter = new PartySchoolStyleAdapter(StytleActivity.this,list);
        adapter.setOnItemClickListener(new PartySchoolStyleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Intent intent = new Intent();
                intent.putExtra("contenturl",data);
                intent.putExtra("title",getResources().getString(R.string.news_details));
                intent.setClass(StytleActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
        recycleView.setAdapter(adapter);
        recycleView.addItemDecoration(new InteractiveRecycleViewDecoration(StytleActivity.this,OrientationHelper.VERTICAL));
    }
}
