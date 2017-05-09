package com.xiaoantech.electrombile.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.application.App;
import com.xiaoantech.electrombile.utils.TimeUtil;

import java.sql.Time;

/**
 * Created by yangxu on 2016/12/6.
 */

public class CarInfoModel {

    private String  name;
    private String  IMEI;
    private long    bindTime;
    private Bitmap  cropImage;
    private String  plateNum;
    private String  frameNum;
    private String  batteryType;
    private String  brandName;
    private String  venderPhone;

    public CarInfoModel(String IMEI,long bindTime){
        this.IMEI = IMEI;
        this.name = IMEI;
        this.bindTime = bindTime;
        this.cropImage = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.othercar);
    }
    public CarInfoModel(String IMEI){
        this.IMEI = IMEI;
        this.name = IMEI;
        this.bindTime = TimeUtil.getCurrentTime();
        this.cropImage = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.othercar);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCropImage(Bitmap cropImage) {
        this.cropImage = cropImage;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public void setFrameNum(String frameNum) {
        this.frameNum = frameNum;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setVenderPhone(String venderPhone) {
        this.venderPhone = venderPhone;
    }

    public String getName() {
        if (name==null)
            return IMEI;
        return name;
    }

    public Bitmap getCropImage() {
        return cropImage;
    }

    public String getIMEI() {
        return IMEI;
    }

    public long getBindTime() {
        return bindTime;
    }

    public String getPlateNum() {
        if (plateNum == null)
            return "";
        return plateNum;
    }

    public String getFrameNum() {
        if (frameNum == null)
            return "";
        return frameNum;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public String getBrandName() {
        if (brandName == null)
            return "";
        return brandName;
    }

    public String getVenderPhone() {
        if (venderPhone == null)
            return "";
        return venderPhone;
    }
}
