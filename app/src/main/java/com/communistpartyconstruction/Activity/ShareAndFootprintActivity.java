package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.communistpartyconstruction.Adapter.Decoration.InteractiveRecycleViewDecoration;
import com.communistpartyconstruction.Adapter.ShareAndFootprintAdapter;
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.PartyBuildingNews;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.HttpUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by hekesong on 2017/1/1.
 */

public class ShareAndFootprintActivity extends Activity {
    private RecyclerView recycleView;
    private LinearLayout back;
    private LinearLayoutManager linearLayoutManager;
    private TextView title;
    private boolean isShare;
    private List<PartyBuildingNews> list;
    private ShareAndFootprintAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareandfootprint);
        title = (TextView) findViewById(R.id.shareandfootprint_title);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        if (type.equals("TYPE_SHARE")) {
            title.setText(getResources().getString(R.string.myShare));
            isShare = true;
        } else if (type.equals("TYPE_HISTORY")) {
            title.setText(getResources().getString(R.string.myHistory));
            isShare = false;
        }
        new shareAndFootprintActivityAsynctask().execute();
        initView();
    }

    private void initView() {
        back = (LinearLayout) findViewById(R.id.shareandfootprint_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recycleView = (RecyclerView) findViewById(R.id.shareandfootprint_list);
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(ShareAndFootprintActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.addItemDecoration(new InteractiveRecycleViewDecoration(ShareAndFootprintActivity.this, OrientationHelper.VERTICAL));
    }

    class shareAndFootprintActivityAsynctask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            JSONObject jsonParam = new JSONObject();
            if (isShare) {
                try {
                    jsonParam.put("pageIndex", "0");
                    jsonParam.put("pageSize", "10");
                    result = HttpUtils.HttpPost(ShareAndFootprintActivity.this, Host.share, jsonParam);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    jsonParam.put("pageIndex", "0");
                    jsonParam.put("pageSize", "10");
                    result = HttpUtils.HttpPost(ShareAndFootprintActivity.this, Host.history, jsonParam);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            list = HttpUtils.getNewsList(s, ShareAndFootprintActivity.this);
            adapter = new ShareAndFootprintAdapter(ShareAndFootprintActivity.this, list);
            adapter.setOnItemClickListener(new ShareAndFootprintAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, String data) {
                    Intent intent = new Intent();
                    intent.putExtra("contenturl", data);
                    intent.putExtra("title", getResources().getString(R.string.news_details));
                    intent.setClass(ShareAndFootprintActivity.this, WebViewActivity.class);
                    startActivity(intent);
                }
            });
            recycleView.setAdapter(adapter);
        }
    }

}
