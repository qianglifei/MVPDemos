package com.bksx.mobile.common.download;

import android.annotation.SuppressLint;

import org.reactivestreams.Subscriber;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author :qlf
 */
public class DownloadManager {
    private volatile static DownloadManager mInstance;
    private OkHttpClient mClient;

    private DownloadManager(){
        mClient = new OkHttpClient().newBuilder().build();
    }

    /**
     * 获取一个单例类
     * @return
     */
    public static DownloadManager getInstance(){
        if (null == mInstance){
            synchronized (DownloadManager.class){
                if (null == mInstance){
                    mInstance = new DownloadManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 开始下载
     * @param url   下载请求的接口
     * @param downloadObserver   用来回掉的接口
     */
    @SuppressLint("CheckResult")
    public void download(String url , DownloadObserver downloadObserver){
        Observable.just(url).
                //filter()
                flatMap(new Subscriber<>())
                .map(this:: getTe)
                .observeOn(AndroidSchedulers.mainThread()) // 主线程回掉
                .subscribeOn(Schedulers.io())//在子线程执行
                .subscribe((Consumer<? super Object>) downloadObserver);
    }

    /**
     *
     * 创建 DownloadEntity
     * @param url 下载路径
     *
     */
    public DownloadEntity generateDownloadInfo(String url){
        DownloadEntity entity = new  DownloadEntity(url);
        //获取文件的长度
        long contentLength = getContentLength(url);
        entity.setTotal(contentLength);
        String fileName = url.substring(url.lastIndexOf("/"));
        entity.setDownloadFileName(fileName);
        return entity;
    }

    /**
     * 获取下载文件长度
     * @param url
     * @return
     */
    private long getContentLength(String url)  {
        Request request = new Request.Builder().
                url(url).
                build();
        try{
            Response response = mClient.newCall(request).execute();
            if (response != null && response.isSuccessful()){
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength == 0 ? DownloadEntity.TOTAL_ERROR : contentLength;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return DownloadEntity.TOTAL_ERROR;
    }
}