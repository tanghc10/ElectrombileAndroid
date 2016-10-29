package com.xiaoantech.electrombile.ui.login.LoginMain;


import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.Button;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.databinding.ActivityLoginmainBinding;

/**
 * Created by yangxu on 2016/10/28.
 */
public class LoginMainActivity extends BaseAcitivity implements LoginMainContract.View,View.OnClickListener{
    private ActivityLoginmainBinding mBinding;
    private LoginMainContract.Presenter mPresenter;
    private Button                     mRegister;
    @Override
    protected void initView(){
        mPresenter = new LoginMainPresenter(this);
//        mRegister = (Button) findViewById(R.id.register);
        mBinding.setPresenter(mPresenter);
//        mRegister.setOnClickListener(this);
//        mPresenter.subscribe();
    }

    protected void bindData(){
        setContentView(R.layout.activity_loginmain);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_loginmain);
    }

    @Override
    protected void initBefore() {

    }

    public void setPresenter(LoginMainContract.Presenter presenter){
        mPresenter = presenter;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.register:
//                mPresenter.register();
                break;
            default:
                break;
        }
    }
}
