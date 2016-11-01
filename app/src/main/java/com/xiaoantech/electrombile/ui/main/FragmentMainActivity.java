package com.xiaoantech.electrombile.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.RadioButton;

import com.xiaoantech.electrombile.R;

import java.util.ArrayList;

/**
 * Created by yangxu on 2016/11/1.
 */

public class FragmentMainActivity extends FragmentActivity {
    private ViewPager   mViewPager;
    private RadioButton mainBtn,infoBtn,settingBtn;
    private ArrayList<Fragment> fragmentArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewPager();
    }

    private void initRadioBtn(){
        mainBtn = (RadioButton)findViewById(R.id.radioBtn_main);
        infoBtn = (RadioButton)findViewById(R.id.radioBtn_info);
        settingBtn = (RadioButton)findViewById(R.id.radioBtn_settings);

        mainBtn.setOnClickListener(new radioBtnListener(0));
        infoBtn.setOnClickListener(new radioBtnListener(1));
        settingBtn.setOnClickListener(new radioBtnListener(2));
    }

    public class radioBtnListener implements View.OnClickListener{
        private  int index = 0;
        public radioBtnListener(int i){
            index = i;
        }
        @Override
        public void onClick(View view){
            mViewPager.setCurrentItem(index);
        }
    }

    private void initViewPager(){
        mViewPager = (ViewPager)findViewById(R.id.viewPager_Fragment);
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragmentArrayList));
        mViewPager.setCurrentItem(0);
    }


}
