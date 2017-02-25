package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.SettingManager.AutoLock;

import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.base.BaseView;

/**
 * Created by yangxu on 2017/2/25.
 */

public interface AutoLockContract {

    interface View extends BaseView<Presenter>{
        void changeAutoLockState(boolean isOn);
    }

    interface Presenter extends BasePresenter{
        void changeAutoLock();
    }

}
