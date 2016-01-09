package com.ld.tao.lhweather.http.mdel;

/**
 * Created by tao on 2016/1/9.
 */
public class BaseApiResponse<T> {
    private int errNum;
    private String errMsg;
    private T retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }
}
