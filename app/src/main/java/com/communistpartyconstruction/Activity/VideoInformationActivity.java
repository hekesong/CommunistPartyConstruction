package com.communistpartyconstruction.Activity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.communistpartyconstruction.Adapter.Decoration.VideoInformationRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.RulesRecycleViewAdapter;
import com.communistpartyconstruction.Adapter.VideoInformationRecycleViewAdapter;
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.VideoJavaBean;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.HttpUtils;

import org.json.JSONObject;

import java.util.List;

public class VideoInformationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private Button goBack;
    private List<VideoJavaBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_infomation);
        initUI();
        VideoAsyncTask task = new VideoAsyncTask(this);
        task.execute();
    }
    private void initUI(){
        goBack = (Button)findViewById(R.id.videoInformation_goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.videoInformation_recycleView);
        //设置固定大小
        recyclerView.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new GridLayoutManager(this,2);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //创建适配器，并且设置
//        mAdapter = new VideoInformationRecycleViewAdapter(this);
//        recyclerView.setAdapter(mAdapter);
        //添加分隔线
        //recyclerView.addItemDecoration(new VideoInformationRecycleViewDecoration(this));

    }
    class VideoAsyncTask extends AsyncTask<Void,Void,String> {
        Context context;
        public VideoAsyncTask(Context context){
            this.context = context;
        }
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            JSONObject jsonParam = new JSONObject();
            try{
                jsonParam.put("pageIndex", "0");
                jsonParam.put("pageSize", "10");
                result = HttpUtils.HttpPost(context, Host.getVideoDocsList,jsonParam);

            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("00",s);
            list = HttpUtils.getVideosList(s,context);
            VideoInformationRecycleViewAdapter adapter = new VideoInformationRecycleViewAdapter(context,list);
            recyclerView.setAdapter(adapter);

        }
    }
}
