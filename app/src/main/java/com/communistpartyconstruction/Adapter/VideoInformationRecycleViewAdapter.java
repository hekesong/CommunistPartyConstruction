package com.communistpartyconstruction.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.communistpartyconstruction.R;

/**
 * Created by DerryChan on 2016/12/8 0008.
 */

public class VideoInformationRecycleViewAdapter extends RecyclerView.Adapter<VideoInformationRecycleViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private String[] mTitles = null;
    private Context context;
    public VideoInformationRecycleViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mTitles = new String[20];
        this.context = context;
        for (int i = 0; i < 20; i++) {
            int index = i + 1;
            mTitles[i] = "视频文件" + index;
        }
    }
    @Override
    public VideoInformationRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_video_information_recycleview_item, parent, false);
        VideoInformationRecycleViewAdapter.ViewHolder viewHolder = new VideoInformationRecycleViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoInformationRecycleViewAdapter.ViewHolder holder, int position) {
        holder.item_title.setText(mTitles[position]);
        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_title;
        RelativeLayout body;
        ViewHolder(View view) {
            super(view);
            item_title = (TextView) view.findViewById(R.id.activity_video_information_cardView_text);
            body = (RelativeLayout) view.findViewById(R.id.activity_video_information_cardView_body);
        }
    }
}
