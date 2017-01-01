package com.communistpartyconstruction.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.communistpartyconstruction.Activity.InformActivity;
import com.communistpartyconstruction.Activity.RulesActivity;
import com.communistpartyconstruction.Activity.SubmitApplicationActivity;
import com.communistpartyconstruction.Activity.VideoInformationActivity;
import com.communistpartyconstruction.Activity.WebViewActivity;
import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.InteractiveRecycleViewAdapter;
import com.communistpartyconstruction.Adapter.RulesRecycleViewAdapter;
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.RulesJavaBean;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.HttpUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by hekesong on 2016/11/15.
 */

public class InteractiveFragment extends Fragment implements View.OnClickListener{
    private View view;
    private RelativeLayout infoCheck,submitApp,rules,questionnaire,fileInfo,videoInfo;
    private RecyclerView memberList;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<RulesJavaBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null){
            view = inflater.inflate(R.layout.interactive_fragment,container,false);
        }
        initUI();
        InteractiveAsyncTask task = new InteractiveAsyncTask(getActivity());
        task.execute();
        return  view;
    }
    private void initUI(){
        infoCheck = (RelativeLayout) view.findViewById(R.id.interactive_infoCheck);
        submitApp = (RelativeLayout) view.findViewById(R.id.interactive_submitApplication);
        rules = (RelativeLayout) view.findViewById(R.id.interactive_rules);
        questionnaire = (RelativeLayout) view.findViewById(R.id.interactive_questionnaire);
        fileInfo = (RelativeLayout) view.findViewById(R.id.interactive_fileInfo);
        videoInfo = (RelativeLayout) view.findViewById(R.id.interactive_videoInfo);
        infoCheck.setOnClickListener(this);
        submitApp.setOnClickListener(this);
        rules.setOnClickListener(this);
        questionnaire.setOnClickListener(this);
        fileInfo.setOnClickListener(this);
        videoInfo.setOnClickListener(this);

        memberList = (RecyclerView) view.findViewById(R.id.interactive_list);
        //设置固定大小
        memberList.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        memberList.setLayoutManager(mLayoutManager);
        //创建适配器，并且设置
//        mAdapter = new InteractiveRecycleViewAdapter(this.getActivity());
//        memberList.setAdapter(mAdapter);
        //添加分隔线
        memberList.addItemDecoration(new InteractiveRecycleViewDecoration(this.getActivity(),OrientationHelper.VERTICAL));
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.interactive_infoCheck:
                intent.setClass(this.getActivity(), InformActivity.class);
                this.getActivity().startActivity(intent);
                break;
            case R.id.interactive_submitApplication:
                intent.setClass(this.getActivity(), SubmitApplicationActivity.class);
                this.getActivity().startActivity(intent);
                break;
            case R.id.interactive_rules:
                intent.putExtra("type", "0");
                intent.setClass(this.getActivity(), RulesActivity.class);
                this.getActivity().startActivity(intent);
                break;
            case R.id.interactive_questionnaire:
                intent.putExtra("title",this.getString(R.string.questionnaire));
                intent.putExtra("contenturl","http://www.baidu.com");
                intent.setClass(this.getActivity(), WebViewActivity.class);
                this.getActivity().startActivity(intent);
                break;
            case R.id.interactive_fileInfo:
                intent.putExtra("type", "1");
                intent.setClass(this.getActivity(), RulesActivity.class);
                this.getActivity().startActivity(intent);
                break;
            case R.id.interactive_videoInfo:
                intent.setClass(this.getActivity(), VideoInformationActivity.class);
                this.getActivity().startActivity(intent);
                break;

            default:
                break;
        }
    }
    class InteractiveAsyncTask extends AsyncTask<Void,Void,String> {
        Context context;
        public InteractiveAsyncTask(Context context){
            this.context = context;
        }
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            JSONObject jsonParam = new JSONObject();
            try{
                jsonParam.put("pageIndex", "0");
                jsonParam.put("pageSize", "10");
                result = HttpUtils.HttpPost(context, Host.getLearnerActivitiesList,jsonParam);


            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("00",s);
            list = HttpUtils.getInteractiveList(s,context);
            InteractiveRecycleViewAdapter adapter = new InteractiveRecycleViewAdapter(context,list);
            memberList.setAdapter(adapter);

        }
    }
}
