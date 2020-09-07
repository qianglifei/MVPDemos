package com.bksx.mobile.mvpdemo.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bksx.mobile.common.base.BaseFragment;
import com.bksx.mobile.mvpdemo.R;

/**
 * @author :qlf
 */
public class HomepageFragment extends BaseFragment<INewsArticle.Presenter> {

    public HomepageFragment() {

    }

    public static HomepageFragment newInstance(){
        HomepageFragment homepageFragment = new HomepageFragment();
        return homepageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.doLoadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() throws NullPointerException {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(INewsArticle.Presenter presenter) {
        if (null == presenter) {
            mPresenter = new NewsArticlePresenter(this);
        }
    }

}
