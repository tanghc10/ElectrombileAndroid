package com.xiaoantech.electrombile.ui.login.LoginMain;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.util.DisplayMetrics;

import com.squareup.picasso.Picasso;
import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.databinding.ActivityLoginmainBinding;
import com.xiaoantech.electrombile.ui.login.Login.LoginActivity;
import com.xiaoantech.electrombile.ui.login.Register.RegisterActivity;
import com.xiaoantech.electrombile.utils.BitmapUtil;

/**
 * Created by yangxu on 2016/10/28.
 */
public class LoginMainActivity extends BaseAcitivity implements LoginMainContract.View{
    private ActivityLoginmainBinding mBinding;
    private LoginMainContract.Presenter mPresenter;
    @Override
    protected void initBefore() {

    }

    @Override
    protected void bindData(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_loginmain);
    }

    @Override
    protected void initView(){
        mPresenter = new LoginMainPresenter(this);
        mBinding.setPresenter(mPresenter);

        Picasso.with(this).load(R.drawable.login_background).noPlaceholder().into(mBinding.backgroundImage);
//        mBinding.backgroundImage.setImageBitmap(BitmapUtil.getFitSampleBitmap(getResources(),R.drawable.login_background,width,height));
    }


    public void setPresenter(LoginMainContract.Presenter presenter){
        mPresenter = presenter;
    }

    @Override
    public void gotoLogin() {
        Intent intent = new Intent(LoginMainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoRegister() {
        Intent intent = new Intent(LoginMainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}
