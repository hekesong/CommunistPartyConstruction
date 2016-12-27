package com.communistpartyconstruction.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.PartyBuildingNews;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.HttpUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hekesong on 2016/12/16.
 */

public class ImportantSpeechFragment extends Fragment {
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
            list = new ArrayList<>();
            new NewsAsyncTask().execute();
        }

        initView();
        return view;
    }

    private void initView() {
        recycleView = (RecyclerView) view.findViewById(R.id.news_list);
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.addItemDecoration(new InteractiveRecycleViewDecoration(this.getActivity(),OrientationHelper.VERTICAL));
    }

    //网络数据获取
    class NewsAsyncTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            JSONObject jsonParam = new JSONObject();
            try{
                jsonParam.put("pageIndex", "0");
                jsonParam.put("pageSize", "10");
                result = HttpUtils.HttpPost(getActivity(), Host.importantSpeech,jsonParam);
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            list = HttpUtils.getNewsList(s,getActivity());
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

        }
    }

}
