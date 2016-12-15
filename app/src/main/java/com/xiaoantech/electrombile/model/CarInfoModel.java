package com.xiaoantech.electrombile.model;

import android.graphics.Bitmap;

/**
 * Created by yangxu on 2016/12/6.
 */

public class CarInfoModel {
    private String  name;
    private String  IMEI;
    private long    bindTime;
    private Bitmap  cropImage;

    public CarInfoModel(String IMEI,long bindTime){
        this.IMEI = IMEI;
        this.bindTime = bindTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCropImage(Bitmap cropImage) {
        this.cropImage = cropImage;
    }
}
