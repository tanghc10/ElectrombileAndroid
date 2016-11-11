package com.xiaoantech.electrombile.ui.main.MainFragment.Fragment;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.application.App;
import com.xiaoantech.electrombile.base.BaseFragment;
import com.xiaoantech.electrombile.databinding.FragmentMainBinding;

/**
 * Created by yangxu on 2016/11/3.
 */

public class MainFragment extends BaseFragment implements MainFragmentConstract.View {
    private FragmentMainBinding mBinding;
    private MainFragmentConstract.Presenter mPresenter;
    private ProgressDialog      mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        initView();
        return mBinding.getRoot();
    }

    @Override
    public void initView() {
        mPresenter = new MainFragmentPresenter(this);
        mBinding.setPresenter(mPresenter);
        mProgressDialog = new ProgressDialog(App.getContext());

    }

    @Override
    public void setPresenter(MainFragmentConstract.Presenter presenter){
        mPresenter = presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void showWaitingDialog(String dialogString) {
        mProgressDialog.setMessage(dialogString);
        mProgressDialog.show();
    }

    @Override
    public void hideWaitingDialog() {
        mProgressDialog.cancel();
    }

    @Override
    public void showWeather(int temperature, String weather) {
        mBinding.weatherImage.setText(""+weather);
        mBinding.weatherTemperature.setText(temperature);
    }
}
