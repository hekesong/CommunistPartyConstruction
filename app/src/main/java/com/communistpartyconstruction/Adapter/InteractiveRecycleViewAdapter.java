package com.communistpartyconstruction.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
 * Created by DerryChan on 2016/11/29 0029.
 */

public class InteractiveRecycleViewAdapter extends RecyclerView.Adapter<InteractiveRecycleViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<RulesJavaBean> list;
    private Context context;
    public InteractiveRecycleViewAdapter(Context context,List<RulesJavaBean> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.interactive_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.item_tv.setText(list.get(position).getContent());
        holder.item_title.setText(list.get(position).getTitle());
        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("contenturl",list.get(position).getUrl());
                intent.putExtra("title",context.getResources().getString(R.string.membership));
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
            item_tv = (TextView) view.findViewById(R.id.interactive_list_text);
            item_title = (TextView) view.findViewById(R.id.interactive_list_title);
            body = (RelativeLayout) view.findViewById(R.id.interactive_list_body);
        }
    }
}
