package com.communistpartyconstruction.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.communistpartyconstruction.JavaBean.PartyBuildingNews;
import com.communistpartyconstruction.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by hekesong on 2016/12/6.
 */

public class PartyBuildingNewsadAdapter extends RecyclerView.Adapter<PartyBuildingNewsadAdapter.ViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private List<PartyBuildingNews> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mcontext;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.logo)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }

    public PartyBuildingNewsadAdapter(Context context,List<PartyBuildingNews> list){
        this.mInflater = LayoutInflater.from(context);
        mcontext = context;
        this.list = list;
        imageLoader.init(ImageLoaderConfiguration.createDefault(mcontext));

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.partybuildingnews_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_title.setText(list.get(position).getTitle());
        holder.item_browse.setText(list.get(position).getBrowse());
        holder.item_share.setText(list.get(position).getShare());
        holder.share_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext,"分享成功",Toast.LENGTH_SHORT).show();
            }
        });
        holder.item_time.setText(list.get(position).getTime());
        holder.itemView.setTag(list.get(position).getContenturl());
//        int screenWidth = getScreenWidthAndHeight.getScreenWidth(mcontext);
//        ViewGroup.LayoutParams lp = holder.item_iv.getLayoutParams();
//        lp.width = screenWidth;
//        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        holder.item_iv.setLayoutParams(lp);
//        holder.item_iv.setMaxWidth(screenWidth);
//        holder.item_iv.setMaxHeight(screenWidth*5);
        ImageLoader.getInstance().displayImage(list.get(position).getImageurl(), holder.item_iv, options);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(view,(String)view.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_title,item_browse,item_share,item_time;
        private ImageView item_iv;
        private LinearLayout share_ll;
        ViewHolder(View view){
            super(view);
            item_title = (TextView) view.findViewById(R.id.partybuildingnews_title_tv);
            item_browse = (TextView) view.findViewById(R.id.partybuildingnews_browse_tv);
            item_share = (TextView) view.findViewById(R.id.partybuildingnews_share_tv);
            item_time = (TextView) view.findViewById(R.id.partybuildingnews_time_tv);
            item_iv = (ImageView) view.findViewById(R.id.partybuildingnews_iv);
            share_ll = (LinearLayout) view.findViewById(R.id.partybuildingnews_share_ll);
            item_iv = (ImageView) view.findViewById(R.id.partybuildingnews_iv);
        }
    }
}
