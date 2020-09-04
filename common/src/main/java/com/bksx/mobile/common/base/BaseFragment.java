package com.bksx.mobile.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.bksx.mobile.common.R;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import okhttp3.OkHttpClient;

/**
 * @author :qlf
 */
public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView<T>{

    protected T mPresenter;

    @NonNull
    protected Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

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

    /**
     * 绑定生命周期
     * @param <X>
     * @return
     */
    @Override
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY));
    }
}
