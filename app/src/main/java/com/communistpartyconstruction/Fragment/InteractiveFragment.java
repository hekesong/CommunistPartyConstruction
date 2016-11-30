package com.communistpartyconstruction.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.communistpartyconstruction.R;

/**
 * Created by hekesong on 2016/11/15.
 */

public class InteractiveFragment extends Fragment implements View.OnClickListener{
    private View view;
    private RelativeLayout infoCheck,submitApp,rules,questionnaire,fileInfo,videoInfo;
    private RecyclerView memberList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null){
            view = inflater.inflate(R.layout.interactive_fragment,container,false);
        }
        initUI();
        return  view;
    }
    private void initUI(){
        infoCheck = (RelativeLayout) view.findViewById(R.id.interactive_infoCheck);
        submitApp = (RelativeLayout) view.findViewById(R.id.interactive_submitApplication);
        rules = (RelativeLayout) view.findViewById(R.id.interactive_rules);
        questionnaire = (RelativeLayout) view.findViewById(R.id.interactive_questionnaire);
        fileInfo = (RelativeLayout) view.findViewById(R.id.interactive_fileInfo);
        videoInfo = (RelativeLayout) view.findViewById(R.id.interactive_videoInfo);
        memberList = (RecyclerView) view.findViewById(R.id.interactive_list);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.interactive_infoCheck:
                break;
            case R.id.interactive_submitApplication:
                break;
            case R.id.interactive_rules:
                break;
            case R.id.interactive_questionnaire:
                break;
            case R.id.interactive_fileInfo:
                break;
            case R.id.interactive_videoInfo:
                break;

            default:
                break;
        }
    }
}
