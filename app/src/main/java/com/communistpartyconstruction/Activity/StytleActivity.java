package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.PartySchoolStyleAdapter;
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.PartySchoolStyle;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.HttpUtils;

import org.json.JSONObject;

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
    private List<PartySchoolStyle> list = new ArrayList<>();
    private TextView title;
    private boolean isPartySchoolStyle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        title = (TextView) findViewById(R.id.style_title);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("TYPE_STYLE")){
            title.setText(getResources().getString(R.string.style));
            isPartySchoolStyle = true;
        }else if(type.equals("TYPE_THEORETICAL_OVERVIEW")){
            title.setText(getResources().getString(R.string.theoretical_overview));
            isPartySchoolStyle = false;
        }

        initView();
        new styleAsynctask().execute();
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

        recycleView.addItemDecoration(new InteractiveRecycleViewDecoration(StytleActivity.this,OrientationHelper.VERTICAL));
    }

    class styleAsynctask extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            JSONObject jsonParam = new JSONObject();
            if (isPartySchoolStyle){
                try{
                    jsonParam.put("pageIndex", "0");
                    jsonParam.put("pageSize", "10");
                    result = HttpUtils.HttpPost(StytleActivity.this, Host.getPartySchoolActivitiesList,jsonParam);
                    Log.e("hekesong",isPartySchoolStyle+"");
                }catch (Exception e){
                    e.printStackTrace();
                }

            } else {
                try{
                    jsonParam.put("pageIndex", "0");
                    jsonParam.put("pageSize", "10");
                    result = HttpUtils.HttpPost(StytleActivity.this,Host.getTheoriesList,jsonParam);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            list = HttpUtils.getStyleList(s,isPartySchoolStyle,StytleActivity.this);
            Log.e("list",list.toString());
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
        }
    }

}
