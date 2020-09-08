package com.bksx.mobile.common.base;

import android.app.Activity;

import java.util.Stack;

/**
 * @author :qlf
 *
 * Activity 管理
 */
public class AppManager {
    private static Stack<Activity> mActivityStack;
    private volatile static AppManager mInstance;

    public AppManager() {

    }


    public static AppManager getAMInstance(){
        if (mInstance == null){
            synchronized (AppManager.class){
                if (mInstance == null){
                    mInstance = new AppManager();
                    mActivityStack = new Stack<>();
                }
            }
        }
        return mInstance;
    }

}
