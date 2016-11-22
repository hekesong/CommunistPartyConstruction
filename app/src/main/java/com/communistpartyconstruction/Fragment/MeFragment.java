package com.communistpartyconstruction.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.communistpartyconstruction.R;

/**
 * Created by hekesong on 2016/11/15.
 */

public class MeFragment extends Fragment implements View.OnClickListener {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.me_fragment,container,false);
        }

        return  view;
    }

    @Override
    public void onClick(View view) {

    }
}
