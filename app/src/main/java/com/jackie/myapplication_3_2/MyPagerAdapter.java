package com.jackie.myapplication_3_2;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class MyPagerAdapter extends PagerAdapter {

    private List<MyImageView> myImageViews;

    public MyPagerAdapter(List<MyImageView> myImageViews) {
        this.myImageViews = myImageViews;
    }

    @Override
    public MyImageView instantiateItem(ViewGroup container, int position) {
        MyImageView myImageView = myImageViews.get(position);
        container.addView(myImageView);
        return myImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(myImageViews.get(position));
    }

    @Override
    public int getCount() {
        return myImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
