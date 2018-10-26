package com.example.jsh.assignment_hh;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//This class is used to give sliding effect to the onboarding screens.
//Inspired by https://developer.android.com/training/animation/screen-slide
//https://developer.android.com/reference/android/support/v4/view/PagerAdapter

public class OnboardPagerAdapter extends PagerAdapter {
    private LayoutInflater inflate;
    private int[]layouts;
    private Context context;

    //Populate adapter
    public OnboardPagerAdapter(int[] layouts, Context context) {
        this.layouts = layouts;
        this.context = context;
    }

    //Returns number of onboarding screens available
    @Override
    public int getCount() {
        return layouts.length;
    }

    //Determines whether a page View is associated with the unique key object as returned by the instantiateItem() method
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    //Create the page for the given position.
    //The adapter is responsible for adding the view to the container given here
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(layouts[position], container, false);
        container.addView(v);
        return v;
    }

    //Remove a page for the given position.
    //The adapter is responsible for removing the view from its container
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = (View)object;
        container.removeView(v);
    }
}