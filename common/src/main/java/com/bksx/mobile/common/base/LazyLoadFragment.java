package com.bksx.mobile.common.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author :qlf
 */
public abstract class LazyLoadFragment<T extends IBasePresenter> extends BaseFragment<T>{

    private View rootView;
    protected boolean isInitView = false;
    protected boolean isVisible = false;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isInitView = true;
    }


    @Override
    public void onResume() {
        super.onResume();
        isCanLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible = true;
            isCanLoadData();
        }else {
            isVisible = false;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    private void isCanLoadData(){
        if (isInitView && isVisible){
            //加载网络请求
            prepareFetchData();
            //标志位复位
            isVisible = false;
            isInitView = false;
        }
    }

    /**
     * 加载数据
     *
     */
    protected abstract void prepareFetchData();
}
