package com.xiaoantech.electrombile.leancloud;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.xiaoantech.electrombile.constant.LeanCloudConstant;

import java.util.List;

import javax.security.auth.callback.Callback;

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
