package com.communistpartyconstruction.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.communistpartyconstruction.Activity.WebViewActivity;
import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.PartyBuildingNewsadAdapter;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.FullyLinearLayoutManager;

/**
 * Created by hekesong on 2016/11/15.
 */

public class HomeFragment extends Fragment{
    private View view;
    private RecyclerView recycleView;
    private FullyLinearLayoutManager fullyLinearLayoutManager;
    private PartyBuildingNewsadAdapter adapter;
    private ScrollView scrollView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null){
                view = inflater.inflate(R.layout.home_fragment,container,false);
        }
        initView();
        return  view;
    }

    private void initView() {
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
        adapter = new PartyBuildingNewsadAdapter(this.getActivity());
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
        //解决打开APP后，直接滑到recycleView的位置，上面的view被遮挡
        scrollView = (ScrollView) view.findViewById(R.id.home_fragment_scrollview);
        scrollView.smoothScrollTo(0,20);
    }
}
