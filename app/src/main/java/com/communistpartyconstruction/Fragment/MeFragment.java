package com.communistpartyconstruction.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.CircleImageView;

/**
 * Created by hekesong on 2016/11/15.
 */

public class MeFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RelativeLayout integral,history,share,message,suggest,setting;
    private CircleImageView headView;
    private TextView myName,mySchool;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.me_fragment,container,false);
        }
        initUI();
        return  view;
    }
    private void initUI(){
        integral = (RelativeLayout) view.findViewById(R.id.meFragment_integral);
        history = (RelativeLayout) view.findViewById(R.id.meFragment_history);
        share = (RelativeLayout) view.findViewById(R.id.meFragment_share);
        message = (RelativeLayout) view.findViewById(R.id.meFragment_message);
        suggest = (RelativeLayout) view.findViewById(R.id.meFragment_suggest);
        setting = (RelativeLayout) view.findViewById(R.id.meFragment_setting);
        headView = (CircleImageView) view.findViewById(R.id.meFragment_headView);
        myName = (TextView) view.findViewById(R.id.meFragment_name);
        mySchool = (TextView) view.findViewById(R.id.meFragment_school);

        integral.setOnClickListener(this);
        history.setOnClickListener(this);
        share.setOnClickListener(this);
        message.setOnClickListener(this);
        suggest.setOnClickListener(this);
        setting.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.meFragment_integral:
                break;
            case R.id.meFragment_history:
                break;
            case R.id.meFragment_share:
                break;
            case R.id.meFragment_message:
                break;
            case R.id.meFragment_suggest:
                break;
            case R.id.meFragment_setting:
                break;
            case R.id.meFragment_headView:
                break;

            default:
                break;
        }
    }
}
