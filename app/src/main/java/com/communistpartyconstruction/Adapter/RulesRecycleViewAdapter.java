package com.communistpartyconstruction.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.communistpartyconstruction.R;

/**
 * Created by DerryChan on 2016/12/5 0005.
 */

public class RulesRecycleViewAdapter extends RecyclerView.Adapter<RulesRecycleViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private String[] mTitles = null;
    private Context context;
    private String type;
    public RulesRecycleViewAdapter(Context context,String type) {
        this.mInflater = LayoutInflater.from(context);
        this.mTitles = new String[20];
        this.context = context;
        this.type = type;
        if (type.equals("0")) {
            for (int i = 0; i < 20; i++) {
                int index = i + 1;
                mTitles[i] = "[上级制度]条例" + index;
            }
        } else if (type.equals("1")){
            for (int i = 0; i < 20; i++) {
                int index = i + 1;
                mTitles[i] = "[文件]文件" + index;
            }
        }
    }
    @Override
    public RulesRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rules_list_item, parent, false);
        RulesRecycleViewAdapter.ViewHolder viewHolder = new RulesRecycleViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RulesRecycleViewAdapter.ViewHolder holder, int position) {
        SpannableStringBuilder builder = new SpannableStringBuilder(mTitles[position]);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(ContextCompat.getColor(context,R.color.red));
        if (type.equals("0")) {
            builder.setSpan(redSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (type.equals("1")){
            builder.setSpan(redSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        holder.item_tv.setText(mTitles[position]);

        holder.item_title.setText(builder);
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
