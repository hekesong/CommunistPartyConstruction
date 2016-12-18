package com.communistpartyconstruction.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.RulesRecycleViewAdapter;
import com.communistpartyconstruction.R;

public class RulesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button goBack;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private String type = "";// 0 = 规章制度  1 = 文件资料 3 = 党校风采  4 = 理论概要

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        type = this.getIntent().getStringExtra("type");
        initUI();
    }

    private void initUI(){
        goBack = (Button) findViewById(R.id.rules_goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        switch (type){
            case "0"://规章制度
                break;
            case "1"://文件资料
                TextView title = (TextView) findViewById(R.id.rules_bar_title);
                title.setText(this.getResources().getString(R.string.fileInfo));
                break;
            case "2"://党校风采
                break;
            case "3"://理论概要
                break;
            default:
                break;
        }

        recyclerView = (RecyclerView) findViewById(R.id.rules_recycleView);
        //设置固定大小
        recyclerView.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(this);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //创建适配器，并且设置
        mAdapter = new RulesRecycleViewAdapter(this,type);
        recyclerView.setAdapter(mAdapter);
        //添加分隔线
        recyclerView.addItemDecoration(new InteractiveRecycleViewDecoration(this,OrientationHelper.VERTICAL));
    }
}
