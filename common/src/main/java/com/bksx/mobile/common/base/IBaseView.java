package com.bksx.mobile.common.base;

import com.uber.autodispose.AutoDisposeConverter;

/**
 * @author :qlf
 */
public interface IBaseView<T> {

    /**
     * 显示加载动画
     * @return
     */
    void onShowLoading();

    /**
     *
     * 隐藏加载动画
     *
     */
    void onHideLoading();

    /**
     *
     * 网络加载错误
     *
     */
    void onShowNetError();

    /**
     * 设置Presenter
     *
     */
    void setPresenter(T presenter);

    /**
     *
     * 绑定生命周期
     * @param <X>
     * @return
     *
     */
    <X> AutoDisposeConverter<X> bindAutoDispose();
}
