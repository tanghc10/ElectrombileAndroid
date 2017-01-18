package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.UserManager.UserInfo;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.databinding.ActivityUserInfoBinding;
import com.xiaoantech.electrombile.ui.main.SettingFragment.activity.UserManager.UserInfoRevise.UserInfoReviseActivity;

/**
 * Created by yangxu on 2016/12/14.
 */

public class UserInfoActivity extends BaseAcitivity implements UserInfoContract.View {
    private ActivityUserInfoBinding mBinding;
    private UserInfoContract.Presenter mPresenter;

    @Override
    protected void initBefore() {
    }

    @Override
    protected void bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
    }

    @Override
    protected void initView() {
        mPresenter = new UserInfoPresenter(this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(UserInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void gotoUserInfoRevise() {
        Intent intent = new Intent(UserInfoActivity.this, UserInfoReviseActivity.class);
        try {
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
