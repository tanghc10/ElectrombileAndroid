package com.xiaoantech.electrombile.ui.main.SettingFragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseFragment;
import com.xiaoantech.electrombile.databinding.FragmentSettingBinding;
import com.xiaoantech.electrombile.ui.main.SettingFragment.activity.CarManager.CarManagerActivity;

/**
 * Created by yangxu on 2016/11/3.
 */

public class SettingFragment extends BaseFragment implements SettingFragmentContract.View {
    private FragmentSettingBinding  mBinding;
    private SettingFragmentContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting,container,false);
        initView();
        return mBinding.getRoot();
    }

    @Override
    public void initView() {
        mPresenter = new SettingFragmentPresenter(this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(SettingFragmentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void gotoCarManager() {
        Intent intent = new Intent(mContext, CarManagerActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoUserManager() {

    }

    @Override
    public void gotoMapDownload() {

    }

    @Override
    public void gotoSettingManager() {

    }

    @Override
    public void gotoAboutUs() {

    }
}
