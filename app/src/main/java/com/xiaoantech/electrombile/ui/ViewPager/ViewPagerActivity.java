package com.xiaoantech.electrombile.ui.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaoantech.electrombile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxu on 2017/3/30.
 */

public class ViewPagerActivity extends Activity implements ViewPager.OnPageChangeListener,View.OnClickListener{

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<View> views;

    private static final int[] pictures = {R.drawable.pager_1,R.drawable.pager_2,R.drawable.pager_3};

    private ImageView[] dots;
    private int currentIndex;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        views = new ArrayList<View>();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                ,LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i=0; i < pictures.length; i++){
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(params);
            iv.setImageDrawable(getResources().getDrawable(pictures[i]));
            views.add(iv);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        initDots();
    }

    private void initDots(){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);

        dots = new ImageView[pictures.length];

        for (int i = 0;i < pictures.length; i++){
            dots[i] = (ImageView) linearLayout.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(false);
    }

    private void setCurrentView(int position){
        if (position < 0 || position >= pictures.length)
            return;

        viewPager.setCurrentItem(position);
    }

    private void setCurrentDot(int position){
        if (position < 0 || position > pictures.length - 1 || currentIndex == position) {
            return;
        }

        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 2){
            if (positionOffset > 100){

            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int arg0) {
        //设置底部小点选中状态
        setCurrentDot(arg0);
    }

    @Override
    public void onClick(View v) {
        int position = (Integer)v.getTag();
        setCurrentView(position);
        setCurrentDot(position);
    }
}
