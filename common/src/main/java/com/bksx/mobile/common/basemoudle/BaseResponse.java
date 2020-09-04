package com.bksx.mobile.common.basemoudle;

import com.google.gson.annotations.SerializedName;

/**
 * @author :qlf
 */
public class BaseResponse<T> {

    @SerializedName("returnCode")
    private int returnCode;
    @SerializedName("returnMsg")
    private String returnMsg;
    @SerializedName("returnData")
    private T returnData;
    @SerializedName("pageCount")
    private String pageCount;
    @SerializedName("rowsCount")
    private String rowsCount;
    @SerializedName("startNum")
    private String startNum;
    @SerializedName("state")
    private String state;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getReturnData() {
        return returnData;
    }

    public void setReturnData(T returnData) {
        this.returnData = returnData;
    }

    public Object getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public Object getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(String rowsCount) {
        this.rowsCount = rowsCount;
    }

    public Object getStartNum() {
        return startNum;
    }

    public void setStartNum(String startNum) {
        this.startNum = startNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "returnCode=" + returnCode +
                ", returnMsg='" + returnMsg + '\'' +
                ", returnData=" + returnData +
                ", pageCount='" + pageCount + '\'' +
                ", rowsCount='" + rowsCount + '\'' +
                ", startNum='" + startNum + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
