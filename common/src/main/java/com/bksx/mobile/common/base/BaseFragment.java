package com.bksx.mobile.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author :qlf
 */
public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView<T>{

    protected T mPresenter;

    @NonNull
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View 持有Presenter 的引用
        setPresenter(mPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(attachLayoutId(), container, false);
        initEvent();
        initData();
        return view;
    }

    /**
     *
     * 初始化数据
     *
     */
    protected abstract void initData() throws NullPointerException;

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    /**
     * 绑定布局id
     * @return
     */
    protected abstract int attachLayoutId();
}
