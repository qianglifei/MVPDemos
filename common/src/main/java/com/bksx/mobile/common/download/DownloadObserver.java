package com.bksx.mobile.common.download;

import android.util.Log;

import com.uber.autodispose.android.internal.AutoDisposeAndroidUtil;

import java.util.logging.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.BlockingIgnoringReceiver;

/**
 *
 * @author :qlf
 *
 */
public class DownloadObserver implements Observer<DownloadEntity> {
    //可以用于取消注册的监听者
    private Disposable mDisposable;
    private DownloadEntity mDownloadEntity;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(DownloadEntity downloadEntity) {
        mDownloadEntity = downloadEntity;
        Log.i("TAG", "===onNext: " + mDownloadEntity.getProgress());
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        Log.i("TAG", "===onComplete: ");
    }

}
