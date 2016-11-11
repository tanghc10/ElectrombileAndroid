package com.xiaoantech.electrombile.ui.main.MainFragment.Fragment;

import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.base.BaseView;

/**
 * Created by yangxu on 2016/11/5.
 */

public interface MainFragmentConstract {

    interface View extends BaseView<Presenter>{
        void showWaitingDialog(String dialogString);
        void showWeather(int temperature,String weather);
        void hideWaitingDialog();
    }

    interface Presenter extends BasePresenter{
        void getWeatherInfo();

        void changeFenceStatus();
    }
}
