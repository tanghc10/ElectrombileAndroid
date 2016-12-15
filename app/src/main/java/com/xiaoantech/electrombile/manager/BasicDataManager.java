package com.xiaoantech.electrombile.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetDataCallback;
import com.xiaoantech.electrombile.constant.LeanCloudConstant;
import com.xiaoantech.electrombile.model.CarInfoModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangxu on 2016/11/7.
 */

public class BasicDataManager {
    private static BasicDataManager  mInstance;
    private String IMEI;
    private ArrayList<String> IMEIList;
    private ArrayList<CarInfoModel> carInfoList;
    private BasicDataManager(){
        this.IMEI = "865067022385762";
    }

    public static BasicDataManager getInstance() {
        if (null == mInstance){
            mInstance = new BasicDataManager();
        }
        return mInstance;
    }

    public void fetchBasicDataIMEIList(){
        AVQuery<AVObject> query = new AVQuery<>(LeanCloudConstant.BindTable);
        query.whereEqualTo(LeanCloudConstant.User,AVUser.getCurrentUser());
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (null == e){
                    if (list.size() > 0){
                        IMEIList = new ArrayList<String>();
                        carInfoList = new ArrayList<CarInfoModel>();
                        for (int i = 0; i < list.size(); i++){
                            String IMEI = list.get(i).get(LeanCloudConstant.IMEI).toString();
                            IMEIList.add(IMEI);

                            //设置车辆信息
                            Date bindDate = list.get(i).getDate(LeanCloudConstant.CreatedAt);
                            long bindTime = bindDate.getTime()/1000;
                            CarInfoModel carInfoModel = new CarInfoModel(IMEI,bindTime);
                            carInfoList.add(carInfoModel);

                            //查询DID表中的车辆头像及车辆名称信息
                            fetchBasicDataCarName(IMEI,i);
                        }
                        LocalDataManager.getInstance().setIMEIList(IMEIList);

                    }
                }else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void fetchBasicDataCarName(String imei, final int index){
        final CarInfoModel carInfoModel = carInfoList.get(index);

        AVQuery<AVObject> query = new AVQuery<>(LeanCloudConstant.DIDTable);
        query.whereEqualTo(LeanCloudConstant.IMEI,imei);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (null == e){
                    if (list.size() > 0) {
                        AVObject object = list.get(0);
                        //设置名称
                        carInfoModel.setName(object.get(LeanCloudConstant.CarName).toString());
                        carInfoList.set(index,carInfoModel);

                        //设置照片
                        if (null != object.get(LeanCloudConstant.Image)){
                           fetchBasicDataCarImage(object.getAVFile(LeanCloudConstant.Image),index);
                        }else {
                            //TODO:User didn't set picture
                        }
                    }
                }else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fetchBasicDataCarImage(AVFile file,final int index){
        file.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, AVException e) {
                if (null == e){
                    if (null != bytes){
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        CarInfoModel carInfoModel = carInfoList.get(index);
                        carInfoModel.setCropImage(bitmap);
                        carInfoList.set(index,carInfoModel);
                    }
                }else {
                    e.printStackTrace();
                }
            }
        });
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
