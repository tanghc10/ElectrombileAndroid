package com.xiaoantech.electrombile.ui.main.MainFragment.activity.Map;

/**
 * Created by yangxu on 2016/11/15.
 */

public class MapActivityPresenter implements MapActivityConstract.Presenter{
    private final static String TAG = "MapActivitypresenter";
    private MapActivityConstract.View   mMapActivity;

    protected MapActivityPresenter(MapActivityConstract.View mapActivity){
        this.mMapActivity = mapActivity;
        mMapActivity.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


}
