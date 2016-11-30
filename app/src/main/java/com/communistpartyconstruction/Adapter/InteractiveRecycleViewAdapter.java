package com.communistpartyconstruction.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.communistpartyconstruction.R;

/**
 * Created by DerryChan on 2016/11/29 0029.
 */

public class InteractiveRecycleViewAdapter extends RecyclerView.Adapter<InteractiveRecycleViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private String[] mTitles=null;

    public InteractiveRecycleViewAdapter(Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mTitles=new String[20];
        for (int i=0;i<20;i++){
            int index=i+1;
            mTitles[i]="item"+index;
        }
    }
    @Override
    public ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.interactive_list_item,parent,false);
        //view.setBackgroundColor(Color.RED);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_tv.setText(mTitles[position]);
    }


    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item_tv;

        public ViewHolder(View view) {
            super(view);
            item_tv = (TextView) view.findViewById(R.id.interactive_list_text);
        }
    }
}
