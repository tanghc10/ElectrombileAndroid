package com.xiaoantech.electrombile.manager;

/**
 * Created by yangxu on 2016/11/7.
 */

public class BasicDataManager {
    public static BasicDataManager  mInstance;
    private String  IMEI;
    private BasicDataManager(){
        this.IMEI = "8650670223";
    }

    public static BasicDataManager getInstance() {
        if (null == mInstance){
            mInstance = new BasicDataManager();
        }
        return mInstance;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getIMEI() {
        return IMEI;
    }
}
