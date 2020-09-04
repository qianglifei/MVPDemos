package com.bksx.mobile.mvpdemo.homepage;

import android.text.TextUtils;


import com.bksx.mobile.common.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class NewsArticlePresenter implements INewsArticle.Presenter {
    public NewsArticlePresenter(HomepageFragment homepageFragment) {
    }

    @Override
    public void doLoadData(String... category) {

    }

    @Override
    public void doLoadMoreData() {

    }

    @Override
    public void doSetAdapter(List<String> dataBeen) {

    }

    @Override
    public void doShowNoMore() {

    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {

    }
//
//    private static final String TAG = "NewsArticlePresenter";
//    private INewsArticle.View view;
//    private List<MultiNewsArticleDataBean> dataList = new ArrayList<>();
//    private String category;
//    private String time;
//    private Gson gson = new Gson();
//    private Random random = new Random();
//
//    public NewsArticlePresenter(INewsArticle.View view) {
//        this.view = view;
//        this.time = TimeUtil.getCurrentTimeStamp();
//    }
//
//    @Override
//    public void doLoadData(String... category) {
//
//        try {
//            if (this.category == null) {
//                this.category = category[0];
//            }
//        } catch (Exception e) {
//            ErrorAction.print(e);
//        }
//
//        // 释放内存
//        if (dataList.size() > 150) {
//            dataList.clear();
//        }
//
//        getRandom()
//                .subscribeOn(Schedulers.io())
//                .switchMap((Function<MultiNewsArticleBean, Observable<MultiNewsArticleDataBean>>) this::apply)
//                .filter(dataBean -> {
//                    time = dataBean.getBehot_time();
//                    if (TextUtils.isEmpty(dataBean.getSource())) {
//                        return false;
//                    }
//                    try {
//                        // 过滤头条问答新闻
//                        if (dataBean.getSource().contains("头条问答")
//                                || dataBean.getTag().contains("ad")
//                                || dataBean.getSource().contains("悟空问答")) {
//                            return false;
//                        }
//                        // 过滤头条问答新闻
//                        if (dataBean.getRead_count() == 0 || TextUtils.isEmpty(dataBean.getMedia_name())) {
//                            String title = dataBean.getTitle();
//                            if (title.lastIndexOf("？") == title.length() - 1) {
//                                return false;
//                            }
//                        }
//                    } catch (NullPointerException e) {
//                        ErrorAction.print(e);
//                    }
//                    // 过滤重复新闻(与上次刷新的数据比较)
//                    for (MultiNewsArticleDataBean bean : dataList) {
//                        if (bean.getTitle().equals(dataBean.getTitle())) {
//                            return false;
//                        }
//                    }
//                    return true;
//                })
//                .toList()
//                .map(list -> {
//                    // 过滤重复新闻(与本次刷新的数据比较,因为使用了2个请求,数据会有重复)
//                    for (int i = 0; i < list.size() - 1; i++) {
//                        for (int j = list.size() - 1; j > i; j--) {
//                            if (list.get(j).getTitle().equals(list.get(i).getTitle())) {
//                                list.remove(j);
//                            }
//                        }
//                    }
//                    return list;
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(view.bindAutoDispose())
//                .subscribe(list -> {
//                    if (null != list && list.size() > 0) {
//                        doSetAdapter(list);
//                    } else {
//                        doShowNoMore();
//                    }
//                }, throwable -> {
//                    doShowNetError();
//                    ErrorAction.print(throwable);
//                });
//    }
//
//    @Override
//    public void doLoadMoreData() {
//        doLoadData();
//    }
//
//    @Override
//    public void doSetAdapter(List<String> list) {
//        dataList.addAll(list);
//        view.onSetAdapter(dataList);
//        view.onHideLoading();
//    }
//
//    @Override
//    public void doRefresh() {
//        if (dataList.size() != 0) {
//            dataList.clear();
//            time = TimeUtil.getCurrentTimeStamp();
//        }
//        view.onShowLoading();
//        doLoadData();
//    }
//
//    @Override
//    public void doShowNetError() {
//        view.onHideLoading();
//        view.onShowNetError();
//    }
//
//    @Override
//    public void doShowNoMore() {
//        view.onHideLoading();
//        view.onShowNoMore();
//    }
//
//    private Observable<MultiNewsArticleBean> getRandom() {
//
//        int i = random.nextInt(10);
//        if (i % 2 == 0) {
//            Observable<MultiNewsArticleBean> ob1 = RetrofitFactory.getRetrofit().create(IMobileNewsApi.class)
//                    .getNewsArticle(this.category, this.time);
//            return ob1;
//        } else {
//            Observable<MultiNewsArticleBean> ob2 = RetrofitFactory.getRetrofit().create(IMobileNewsApi.class)
//                    .getNewsArticle2(this.category, this.time);
//            return ob2;
//        }
//    }
//
//    private Observable<MultiNewsArticleDataBean> apply(MultiNewsArticleBean multiNewsArticleBean) {
//        List<MultiNewsArticleDataBean> dataList = new ArrayList<>();
//        for (MultiNewsArticleBean.DataBean dataBean : multiNewsArticleBean.getData()) {
//            dataList.add(gson.fromJson(dataBean.getContent(), MultiNewsArticleDataBean.class));
//        }
//        return Observable.fromIterable(dataList);
//    }
}
