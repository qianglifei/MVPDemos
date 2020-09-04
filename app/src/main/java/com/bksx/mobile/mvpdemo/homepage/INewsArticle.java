package com.bksx.mobile.mvpdemo.homepage;

import com.bksx.mobile.common.base.IBasePresenter;
import com.bksx.mobile.common.base.IBaseView;


import java.util.List;

/**
 * Created by Meiji on 2017/5/18.
 */

public interface INewsArticle {

    interface View extends IBaseView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();

        /**
         *
         * 刷新
         *
         */
        void onRefresh();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<String> dataBeen);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
