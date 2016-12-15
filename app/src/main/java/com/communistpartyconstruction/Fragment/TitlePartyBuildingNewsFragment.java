package com.communistpartyconstruction.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.communistpartyconstruction.Activity.WebViewActivity;
import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.PartyBuildingNewsadAdapter;
import com.communistpartyconstruction.JavaBean.PartyBuildingNews;
import com.communistpartyconstruction.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hekesong on 2016/12/15.
 */

public class TitlePartyBuildingNewsFragment extends Fragment {

    private View view;
    private RecyclerView recycleView;
    private LinearLayoutManager linearLayoutManager;
    private PartyBuildingNewsadAdapter adapter;
    private List<PartyBuildingNews> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.news_list,container,false);
        }
        initData();
        initView();
        return view;
    }

    private void initData() {
        list = new ArrayList<>();
        PartyBuildingNews partyBuildingNews = new PartyBuildingNews();
        for (int i = 0; i < 20; i++) {
            partyBuildingNews.setTitle("树立正确入党动机，发挥模范带头作用--记第54期自动化分党校课后讨论");
            partyBuildingNews.setBrowse("463次");
            partyBuildingNews.setComment("156次");
            partyBuildingNews.setTime("一天前");
            partyBuildingNews.setShare("816次");
            partyBuildingNews.setContenturl("http://www.baidu.com/");
            list.add(partyBuildingNews);
        }
    }

    private void initView() {

        recycleView = (RecyclerView) view.findViewById(R.id.news_list);
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycleView.setLayoutManager(linearLayoutManager);
        adapter = new PartyBuildingNewsadAdapter(this.getActivity(),list);
        adapter.setOnItemClickListener(new PartyBuildingNewsadAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Intent intent = new Intent();
                intent.putExtra("contenturl",data);

                intent.setClass(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });
        recycleView.setAdapter(adapter);
        recycleView.addItemDecoration(new InteractiveRecycleViewDecoration(this.getActivity(),OrientationHelper.VERTICAL));
    }
}
