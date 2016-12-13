package com.communistpartyconstruction.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.PartyBuildingNewsadAdapter;
import com.communistpartyconstruction.R;

/**
 * Created by hekesong on 2016/11/15.
 */

public class HomeFragment extends Fragment{
    private View view;
    private RecyclerView recycleView;
    private LinearLayoutManager mLayoutManager;
    private PartyBuildingNewsadAdapter adapter;
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
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycleView.setLayoutManager(mLayoutManager);
        adapter = new PartyBuildingNewsadAdapter(this.getActivity());
        recycleView.setAdapter(adapter);
        recycleView.addItemDecoration(new InteractiveRecycleViewDecoration(this.getActivity(),OrientationHelper.VERTICAL));
    }
}
