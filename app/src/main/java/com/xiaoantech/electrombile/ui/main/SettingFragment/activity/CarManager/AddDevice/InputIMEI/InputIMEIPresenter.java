package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.CarManager.AddDevice.InputIMEI;

/**
 * Created by yangxu on 2016/12/15.
 */

public class InputIMEIPresenter implements InputIMEIContract.Presenter{
    private final static String TAG = "InputIMEIPresenter";
    private InputIMEIContract.View mInputIMEIView;


    protected InputIMEIPresenter(InputIMEIContract.View inputIMEIView){
        this.mInputIMEIView = inputIMEIView;
        mInputIMEIView.setPresenter(this);
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void bindIMEI() {

    }
}
