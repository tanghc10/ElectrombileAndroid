package com.xiaoantech.electrombile.base;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by jk on 16-10-26.
 *
 * the base class of all activity
 */

public abstract class BaseAcitivity extends AppCompatActivity {
    protected ProgressDialog mProgressDialog;

    protected abstract void initBefore();

    protected abstract void initView();

    protected abstract void bindData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBefore();
        bindData();
        initView();
        mProgressDialog = new ProgressDialog(this);
    }

    public void showToast(String errorMeg) {
        Toast.makeText(this,errorMeg,Toast.LENGTH_SHORT).show();
        mProgressDialog.cancel();
    }

    public void showWaitingDialog(String dialogString) {
        try {
            mProgressDialog.setMessage(dialogString);
            mProgressDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void hideWaitingDialog() {
        mProgressDialog.cancel();
    }

}
