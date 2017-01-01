package com.communistpartyconstruction.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.communistpartyconstruction.JavaBean.PartyBuildingNews;
import com.communistpartyconstruction.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by hekesong on 2017/1/1.
 */

public class ShareAndFootprintAdapter  extends RecyclerView.Adapter<ShareAndFootprintAdapter.ViewHolder> implements View.OnClickListener{

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
    public ShareAndFootprintAdapter(Context context,List<PartyBuildingNews> list){
        this.mInflater = LayoutInflater.from(context);
        mcontext = context;
        this.list = list;
        imageLoader.init(ImageLoaderConfiguration.createDefault(mcontext));
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

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.shareandfootprint_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_title.setText(list.get(position).getTitle());
        holder.item_browse.setText(list.get(position).getBrowse());
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

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_title,item_browse,item_time;
        private ImageView item_iv;
        ViewHolder(View view){
            super(view);
            item_title = (TextView) view.findViewById(R.id.shareandfootprint_title_tv);
            item_browse = (TextView) view.findViewById(R.id.shareandfootprint_browse_tv);
            item_time = (TextView) view.findViewById(R.id.shareandfootprint_time_tv);
            item_iv = (ImageView) view.findViewById(R.id.shareandfootprint_iv);
        }
    }
}
