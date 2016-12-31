package com.communistpartyconstruction.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.communistpartyconstruction.Activity.WebViewActivity;
import com.communistpartyconstruction.JavaBean.RulesJavaBean;
import com.communistpartyconstruction.R;

import java.util.List;

/**
 * Created by DerryChan on 2016/12/5 0005.
 */

public class RulesRecycleViewAdapter extends RecyclerView.Adapter<RulesRecycleViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private String type;
    private List<RulesJavaBean> list;
    public RulesRecycleViewAdapter(Context context, String type, List<RulesJavaBean> list) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.type = type;
        this.list = list;
    }
    @Override
    public RulesRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rules_list_item, parent, false);
        RulesRecycleViewAdapter.ViewHolder viewHolder = new RulesRecycleViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RulesRecycleViewAdapter.ViewHolder holder, final int position) {
        SpannableStringBuilder builder = new SpannableStringBuilder(list.get(position ).getTitle());
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(ContextCompat.getColor(context,R.color.red));
        switch (type){
            case "0"://规章制度
                builder.setSpan(redSpan, 0, list.get(position).getTitleLength(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case "1"://文件资料
                builder.setSpan(redSpan, 0, list.get(position).getTitleLength(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case "2"://党校风采
                break;
            case "3"://理论概要
                break;
            default:
                break;
        }

        holder.item_tv.setText(list.get(position ).getContent());
        holder.item_title.setText(builder);
        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("contenturl",list.get(position).getUrl());
                switch (type){
                    case "0"://规章制度
                        intent.putExtra("title",context.getResources().getString(R.string.rules));
                        break;
                    case "1"://文件资料
                        intent.putExtra("title",context.getResources().getString(R.string.fileInfo));
                        break;
                    case "2"://党校风采
                        break;
                    case "3"://理论概要
                        break;
                    default:
                        break;
                }
                intent.setClass(context, WebViewActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_tv, item_title;
        RelativeLayout body;
        ViewHolder(View view) {
            super(view);
            item_tv = (TextView) view.findViewById(R.id.rules_list_text);
            item_title = (TextView) view.findViewById(R.id.rules_list_title);
            body = (RelativeLayout) view.findViewById(R.id.rules_list_body);
        }
    }
}
