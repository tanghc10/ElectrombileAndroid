package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.CarManager.CarInfoRevise;

import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.base.BaseView;
import com.xiaoantech.electrombile.model.CarInfoModel;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxu on 2017/1/3.
 */

public interface CarInfoReviseContract {
    interface View extends BaseView<Presenter>{
        void chooseBatteryType();
        void finishActivity();
    }

    interface Presenter extends BasePresenter{
        void confirmModification(CarInfoModel carInfoModel);
        void changeBatteryType();
        void changeBatteryType(int battery);
        List<Map<String,Object>> getBatteryList();
    }
}
