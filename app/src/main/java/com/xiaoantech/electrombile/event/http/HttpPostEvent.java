package com.xiaoantech.electrombile.event.http;

import com.xiaoantech.electrombile.manager.HttpManager;

/**
 * Created by tanghuichuan on 2017/3/2.
 */

public class HttpPostEvent {
    protected HttpManager.postType type;
    protected String resultStr;
    protected boolean isSuccess;

    public HttpPostEvent(HttpManager.postType type, String resultStr, boolean isSuccess){
        this.type = type;
        this.resultStr = resultStr;
        this.isSuccess = isSuccess;
    }

    public String getResultStr(){
        return resultStr;
    }

    public HttpManager.postType getRequestType(){
        return type;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}




















