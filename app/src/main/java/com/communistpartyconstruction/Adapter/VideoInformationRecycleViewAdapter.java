package com.communistpartyconstruction.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.communistpartyconstruction.Activity.WebViewActivity;
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.VideoJavaBean;
import com.communistpartyconstruction.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by DerryChan on 2016/12/8 0008.
 */

public class VideoInformationRecycleViewAdapter extends RecyclerView.Adapter<VideoInformationRecycleViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<VideoJavaBean> list;
    private Context context;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.logo)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    public VideoInformationRecycleViewAdapter(Context context,List<VideoJavaBean> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        imageLoader.init(ImageLoaderConfiguration.createDefault(this.context));
    }
    @Override
    public VideoInformationRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_video_information_recycleview_item, parent, false);
        VideoInformationRecycleViewAdapter.ViewHolder viewHolder = new VideoInformationRecycleViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoInformationRecycleViewAdapter.ViewHolder holder, final int position) {
        holder.item_title.setText(list.get(position).getTitle());
        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("contenturl",list.get(position).getLink());
                intent.putExtra("title",context.getResources().getString(R.string.videoInfo));
                intent.setClass(context, WebViewActivity.class);
                context.startActivity(intent);
            }
        });
        ImageLoader.getInstance().displayImage(Host.pictureUrl + list.get(position).getCover(), holder.item_iv, options);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_title;
        RelativeLayout body;
        private ImageView item_iv;
        ViewHolder(View view) {
            super(view);
            item_title = (TextView) view.findViewById(R.id.activity_video_information_cardview_text);
            body = (RelativeLayout) view.findViewById(R.id.activity_video_information_cardview_body);
            item_iv = (ImageView) view.findViewById(R.id.activity_video_information_cardview_img);
        }
    }
}
