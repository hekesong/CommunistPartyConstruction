package com.communistpartyconstruction.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.communistpartyconstruction.JavaBean.PartySchoolStyle;
import com.communistpartyconstruction.R;

import java.util.List;

/**
 * Created by hekesong on 2016/12/18.
 */

public class PartySchoolStyleAdapter extends RecyclerView.Adapter<PartySchoolStyleAdapter.ViewHolder> implements View.OnClickListener{

    private LayoutInflater mInflater;
    private List<PartySchoolStyle> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mcontext;



    public PartySchoolStyleAdapter(Context context, List<PartySchoolStyle> list){
        this.mInflater = LayoutInflater.from(context);
        this.mcontext = context;
        this.list = list;
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.partschoolstyle_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (list.get(position).isPartySchoolStyle()){
            String text = "["+list.get(position).getRed_title()+"]"+list.get(position).getCommon_title();
            int end = list.get(position).getRed_title().length()+"[]".length();
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.setSpan(new ForegroundColorSpan(Color.RED), 0,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
            holder.item_title.setText(style);
        } else {
            holder.item_title.setText(list.get(position).getCommon_title());
        }
        holder.item_auther.setText(list.get(position).getAuthor());
        holder.item_the_number_of_clicks.setText(list.get(position).getThe_number_of_clicks());
        holder.item_time.setText(list.get(position).getTime());
        holder.itemView.setTag(list.get(position).getContenturl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(PartySchoolStyleAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(view,(String)view.getTag());
        }
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_title,item_auther,item_the_number_of_clicks,item_time;
        ViewHolder(View view){
            super(view);
            item_title = (TextView) view.findViewById(R.id.partschoolstyle_title_tv);
            item_auther = (TextView) view.findViewById(R.id.partschoolstyle_author_tv);
            item_the_number_of_clicks = (TextView) view.findViewById(R.id.partschoolstyle_click_tv);
            item_time = (TextView) view.findViewById(R.id.partschoolstyle_release_time_tv);

        }
    }
}
