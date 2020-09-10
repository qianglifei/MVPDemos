package com.bksx.mobile.common.download;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;

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
    public void download(String url , DownloadObserver downloadObserver){
        Observable.just(url).
                //filter()
                flatMap(new Subscriber<>())
    }

    /**
     * 创建 DownloadEntity
     * @param url 下载路径
     */
    public DownloadEntity generateDownloadInfo(String url){
        DownloadEntity entity = new  DownloadEntity(url);
        //获取文件的长度
        long contentLength = getContentLength(url);
    }

    /**
     * 获取下载文件长度
     * @param url
     * @return
     */
    private long getContentLength(String url) {
        OkHttpClient
    }
}
