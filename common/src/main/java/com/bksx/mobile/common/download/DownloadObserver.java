package com.bksx.mobile.common.download;

import com.uber.autodispose.android.internal.AutoDisposeAndroidUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 * @author :qlf
 *
 */
public abstract class DownloadObserver implements Observer<DownloadEntity> {
    //可以用于取消注册的监听者
    protected Disposable mDisposable;
    protected DownloadEntity mDownloadEntity;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(DownloadEntity downloadEntity) {
        mDownloadEntity = downloadEntity;
    }

    @Override
    public void onError(Throwable e) {

    }
}
