package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.CarManager.CarInfoRevise;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.constant.LeanCloudConstant;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBatteryTypeEvent;
import com.xiaoantech.electrombile.http.HttpPublishManager;
import com.xiaoantech.electrombile.manager.LocalDataManager;
import com.xiaoantech.electrombile.model.CarInfoModel;
import com.xiaoantech.electrombile.utils.ErrorCodeConvertUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxu on 2017/1/3.
 */

public class CarInfoRevisePresenter implements CarInfoReviseContract.Presenter {
    private final static String TAG = "CarInfoRevisePresenter";
    private CarInfoReviseContract.View mCarInfoReviseView;
    private int batteryType;

    protected CarInfoRevisePresenter(CarInfoReviseContract.View carInfoReviseView){
        this.mCarInfoReviseView = carInfoReviseView;
        carInfoReviseView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unsubscribe() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void changeBatteryType() {
        mCarInfoReviseView.chooseBatteryType();
    }

    @Override
    public void confirmModification(final CarInfoModel carInfoModel) {
        AVQuery avQuery = new AVQuery(LeanCloudConstant.DIDTable);
        avQuery.whereEqualTo(LeanCloudConstant.IMEI,carInfoModel.getIMEI());
        avQuery.findInBackground(new FindCallback() {
            @Override
            public void done(List list, AVException e) {
                if (e == null && list.size() > 0){
                    AVObject avObject = (AVObject)list.get(0);
                    avObject.put(LeanCloudConstant.CarName,carInfoModel.getName());
                    avObject.put(LeanCloudConstant.PlateNum,carInfoModel.getPlateNum());
                    avObject.put(LeanCloudConstant.FrameNum,carInfoModel.getFrameNum());
                    avObject.put(LeanCloudConstant.FrameNum,carInfoModel.getFrameNum());
                    avObject.put(LeanCloudConstant.VenderPhone,carInfoModel.getVenderPhone());
                    avObject.saveInBackground();
                }
            }
        });

        mCarInfoReviseView.finishActivity();
    }

    @Override
    public List<Map<String, Object>> getBatteryList() {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map;
        for (int i = 1 ; i < 8; i++){
            map = new HashMap<>();
            map.put("battery",12*i + "");
            map.put("isSelected", R.drawable.btn_unselected);
            list.add(map);
        }
        return list;
    }

    @Override
    public void changeBatteryType(int battery) {
        batteryType = battery;
        HttpPublishManager.getInstance().setBatteryType(battery);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void on(HttpPostBatteryTypeEvent event){
        if (event.getCode() == ErrorCodeConvertUtil.HTTPCodeSuccess){
            mCarInfoReviseView.showToast("设置成功");
            LocalDataManager.getInstance().setBatteryType(batteryType);
        }else {
            mCarInfoReviseView.showToast(ErrorCodeConvertUtil.getHttpErrorStrWithCode(event.getCode()));
        }
    }
}
