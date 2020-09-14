package com.bksx.mobile.common.download;

import android.app.DownloadManager;
import android.util.Log;

import com.bksx.mobile.common.util.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author :qlf
 */
public class DownloadSubscribe implements ObservableOnSubscribe<DownloadEntity> {
    private DownloadEntity mDownloadInfo;
    private OkHttpClient mOkHttpClient;

    public DownloadSubscribe() {
    }

    public DownloadSubscribe(DownloadEntity mDownloadInfo, OkHttpClient mOkHttpClient){
        this.mDownloadInfo = mDownloadInfo;
        this.mOkHttpClient = mOkHttpClient;
    }

    @Override
    public void subscribe(ObservableEmitter<DownloadEntity> emitter) throws Exception {
        String url = mDownloadInfo.getUrl();
        // 已经下载好的进度
        long currentLength = mDownloadInfo.getProgress();
        // 文件全部内容
        long contentLength = mDownloadInfo.getTotal();
        // 初始进度信息
        emitter.onNext(mDownloadInfo);
        // 发送请求
        Request request = new Request.Builder()
                //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
                .addHeader("RANGE","bytes=" + currentLength + "-" + contentLength)
                .url(url)
                .build();

        Call call = mOkHttpClient.newCall(request);
        Response response = call.execute();
        File file = new File(Constant.FILE_PATH,mDownloadInfo.getDownloadFileName());
        // 文件输入流
        InputStream inputStream = null;
        // 文件输出流
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = response.body().byteStream();
            fileOutputStream = new FileOutputStream(file,true);
            //缓冲数组2kB
            byte[] buffer = new byte[2048];
            int len;
            while ((len = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,len);
                currentLength += len;
                mDownloadInfo.setProgress(currentLength);
                emitter.onNext(mDownloadInfo);
                Log.i("TAG", "===subscribe: " + len);
            }
            // 流刷新
            fileOutputStream.flush();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            // 关闭io流
            DownloadIO.closeAll(inputStream,fileOutputStream);
        }
        //结束事件发送
        emitter.onComplete();
    }
}
