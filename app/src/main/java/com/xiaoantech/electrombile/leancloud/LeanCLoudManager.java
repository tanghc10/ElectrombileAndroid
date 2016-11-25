package com.xiaoantech.electrombile.leancloud;

/**
 * Created by yangxu on 2016/11/7.
 */

public class LeanCLoudManager {
    private static LeanCLoudManager mInstance   =   null;
    private LeanCloudDataManager    leanCloudDataManager;

    private LeanCLoudManager(){
        leanCloudDataManager = new LeanCloudDataManager();
    }

    public LeanCLoudManager getInstance(){
        if (null == mInstance){
            mInstance = new LeanCLoudManager();
        }
        return mInstance;
    }

    public void getTotalItinerary(String IMEI){

    }

    public void getIMEIList(){

    }
}
