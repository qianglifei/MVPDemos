package com.bksx.mobile.common.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

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


    public static AppManager getAMInstance() {
        if (mInstance == null) {
            synchronized (AppManager.class) {
                if (mInstance == null) {
                    mInstance = new AppManager();
                    mActivityStack = new Stack<>();
                }
            }
        }
        return mInstance;
    }

    /**
     * 把Activity 添加到堆栈中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取当前Activity，也就是最后一个压入堆栈的Activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        try {
            return mActivityStack.lastElement();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取当前Activity 的前一个Activity
     *
     * @return
     */
    public Activity getPreActivity() {
        int index = mActivityStack.size() - 2;
        if (index < 0) {
            return null;
        }
        Activity activity = mActivityStack.get(index);
        return activity;
    }

    /**
     * 关闭当前Activity
     */
    public void finishCurrentActivity() {
        Activity activity = mActivityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 关闭指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 删除指定的Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
        }
    }

    /**
     * 关闭指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        try {
            for (Activity activity : mActivityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭所有界面
     */
    public void finishAllActivity() {
        for (int i = 0; i < mActivityStack.size(); i++) {
            if (mActivityStack.get(i) != null) {
                mActivityStack.remove(i).finish();
            }
        }
    }

    /**
     * ？ 表示不确定的java类型。
     * T 表示java类型。
     * K V 分别代表java键值中的Key Value。
     * E 代表Element。
     * <p>
     * 返回到指定的Activity
     *
     */
    public void returnToActivity(Class<?> cls) {
        while (mActivityStack.size() != 0)
            if (mActivityStack.peek().getClass() == cls) {
                //peek() 查看栈顶元素，却不一出他
                break;
            } else {
                finishActivity(mActivityStack.peek());
            }
    }
    /**
     * 是否已经打开指定的activity
     * @param cls
     * @return
     */
    public boolean isOpenActivity(Class<?> cls) {
        if (mActivityStack != null){
            for (int i = 0, size = mActivityStack.size(); i < size; i++) {
                if (cls == mActivityStack.peek().getClass()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 退出应用程序
     *
     * @param context      上下文
     * @param isBackground 是否开开启后台运行
     */
    public void AppExit(Context context, Boolean isBackground) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
        } catch (Exception e) {

        } finally {
            // 注意，如果您有后台程序运行，请不要支持此句子
//            if (!isBackground) {
//                System.exit(0);
//            }
        }
    }
}
