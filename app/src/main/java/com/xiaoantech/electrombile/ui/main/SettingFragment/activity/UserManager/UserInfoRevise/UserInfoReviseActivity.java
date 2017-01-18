package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.UserManager.UserInfoRevise;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.databinding.ActivityCarinfoReviseBinding;
import com.xiaoantech.electrombile.databinding.ActivityUserInfoReviseBinding;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.model.CarInfoModel;
import com.xiaoantech.electrombile.ui.main.SettingFragment.activity.CarManager.CarInfoRevise.CarInfoReviseContract;
import com.xiaoantech.electrombile.ui.main.SettingFragment.activity.CarManager.CarInfoRevise.CarInfoRevisePresenter;

/**
 * Created by yangxu on 2017/1/3.
 */

public class UserInfoReviseActivity extends BaseAcitivity implements UserInfoReviseContract.View {
    private ActivityUserInfoReviseBinding mBinding;
    private UserInfoReviseContract.Presenter mPresenter;

    @Override
    protected void initBefore() {
    }

    @Override
    protected void bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info_revise);
    }

    @Override
    protected void initView() {
        mPresenter = new UserInfoRevisePresenter(this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(UserInfoReviseContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
