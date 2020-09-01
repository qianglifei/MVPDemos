package com.bksx.mobile.common.base;

/**
 * @author :qlf
 */
public interface IBasePresenter {
    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();
}
