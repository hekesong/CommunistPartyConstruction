package com.communistpartyconstruction.Support;

import android.app.Application;
import android.content.Context;

/**
 * Created by hekesong on 2017/1/1.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return  MyApplication.context;
    }
}
