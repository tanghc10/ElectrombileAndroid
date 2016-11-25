package com.xiaoantech.electrombile.manager;

import java.util.ArrayList;

/**
 * Created by yangxu on 2016/11/7.
 */

public class BasicDataManager {
    private static BasicDataManager  mInstance;
    private String IMEI;
    private ArrayList<String> IMEIList;
    private BasicDataManager(){
        this.IMEI = "865067022385762";
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
    public void setIMEIList(ArrayList<String> IMEIList){
        this.IMEIList = IMEIList;
    }

    public String getIMEI() {
        return IMEI;
    }
    public ArrayList<String> getIMEIList() {
        return IMEIList;
    }
}
