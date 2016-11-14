package com.xiaoantech.electrombile.ui.main.MainFragment.Fragment;

import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.base.BaseView;

/**
 * Created by yangxu on 2016/11/5.
 */

public interface MainFragmentConstract {

    interface View extends BaseView<Presenter>{
        void showToast(String errorMeg);
        void showWaitingDialog(String dialogString);
        void showWeather(int temperature,String weather);
        void hideWaitingDialog();
        void changeFenceStatus(Boolean isOn,boolean isGet);
        void changeBattery(int battery);
        void changeItinerary(int itinerary);
    }

    interface Presenter extends BasePresenter{
        void getWeatherInfo();

        void changeFenceStatus();

        void getBattery();

        void getItinerary();
    }
}
