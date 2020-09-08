package com.bksx.mobile.common.base;

import java.util.List;

/**
 * @author :qlf
 */
public interface IBaseListView<T> extends IBaseView<T> {
    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

    /**
     * 加载完毕
     */
    void onShowNoMore();
}
