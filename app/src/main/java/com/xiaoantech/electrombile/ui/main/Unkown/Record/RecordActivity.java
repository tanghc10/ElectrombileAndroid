package com.xiaoantech.electrombile.ui.main.Unkown.Record;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.databinding.ActivityRecordBinding;

/**
 * Created by yangxu on 2017/3/2.
 */

public class RecordActivity extends BaseAcitivity implements RecordContract.View{
    private ActivityRecordBinding mBinding;
    private RecordContract.Presenter mPresenter;
    @Override
    protected void initBefore() {

    }

    @Override
    protected void bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_record);
    }

    @Override
    protected void initView() {
        mPresenter = new RecordPresenter(this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(RecordContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void startRecord() {
        changeButtonStatus(mBinding.btnPlay,false);
        changeButtonStatus(mBinding.btnStop,true);
        hideWaitingDialog();
        mBinding.txtTitle.setText("正录音");
        mBinding.btnPlay.setText("正录音");
    }

    @Override
    public void stopRecord() {
        changeButtonStatus(mBinding.btnStop,false);
        mBinding.txtTitle.setText("已结束");
        mBinding.btnPlay.setText("播放");
        showWaitingDialog("正在下载");
    }

    @Override
    public void changeCutDownText(String title) {
        mBinding.txtCutdown.setText(title);
    }


    public void changeButtonStatus(Button button, boolean isEnable) {
        if (!isEnable){
            button.setBackground(ContextCompat.getDrawable(this,R.drawable.corners_bg_gray));
        }else if (button.equals(mBinding.btnPlay)){
            button.setBackground(ContextCompat.getDrawable(this,R.drawable.corners_bg_green));
        }else if (button.equals(mBinding.btnStop)){
            button.setBackground(ContextCompat.getDrawable(this,R.drawable.corners_bg_orange));
        }
        button.setEnabled(isEnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unsubscribe();
    }
}

