package com.bksx.mobile.common.download;

/**
 * @author :qlf
 */
public class DownloadEntity {
    // 获取进度失败
    public static final long TOTAL_ERROR = -1;
    // 下载路径
    private String url;
    // 总进度
    private long total;
    // 当前进度
    private long progress;
    // 下载文件名
    private String downloadFileName;

    public DownloadEntity() {
    }

    public DownloadEntity(String url) {
        this.url = url;
    }

    public static long getTotalError() {
        return TOTAL_ERROR;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }
}
