package com.xiaoantech.electrombile.ui.main.MainFragment.activity.Map;

import com.baidu.mapapi.model.LatLng;
import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.base.BaseView;
import com.xiaoantech.electrombile.constant.LayoutConstant;

/**
 * Created by yangxu on 2016/11/15.
 */

public interface MapContract {

    interface View extends BaseView<Presenter>{
        void showToast(String errorMeg);

        void showWaitingDialog(String dialogString);

        void changeGPSPoint(LatLng point);

        void changePlaceInfo(String placeInfo);

        void changeDateInfo(String timeDate);

        void changeMapType(LayoutConstant.MapType mapType);
    }


    interface Presenter extends BasePresenter{
        void refreshLocation();

        void changeMapType(int index);
    }


}
