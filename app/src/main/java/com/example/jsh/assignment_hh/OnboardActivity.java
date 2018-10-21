package com.example.jsh.assignment_hh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnboardActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout layoutDot;
    private TextView[]dots;
    private int[]onBoardScreens;
    private Button skipBtn;
    private Button nextBtn;
    private OnboardPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if(!isFirstTimeStartApp()) {
            startMainActivity();
            finish();
        }*/

        setNotifBarTrans();

        setContentView(R.layout.onboard_activity);

        nextBtn = findViewById(R.id.next);
        skipBtn = findViewById(R.id.skip);
        viewPager = findViewById(R.id.viewPager);
        layoutDot = findViewById(R.id.dottedLayout);


        //When user press skip, start Main Activity
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActivity();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage < onBoardScreens.length) {
                    //move to next page
                    viewPager.setCurrentItem(currentPage);
                } else {
                    startMainActivity();
                }
            }
        });
        onBoardScreens = new int[]{R.layout.onboard0,R.layout.onboard1, R.layout.onboard2, R.layout.onboard3, R.layout.onboard4};
        pagerAdapter = new OnboardPagerAdapter(onBoardScreens,getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int page, float pageoffset, int pagepixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == onBoardScreens.length-1){
                    //LAST PAGE
                    nextBtn.setText("START");
                    skipBtn.setVisibility(View.GONE);
                }else {
                    nextBtn.setText("NEXT");
                    skipBtn.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);
    }

    private boolean isFirstTimeStartApp() {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        return ref.getBoolean("FirstTimeStartFlag", true);
    }

    private void setFirstTimeStartStatus(boolean stt) {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ref.edit();
        editor.putBoolean("FirstTimeStartFlag", stt);
        editor.commit();
    }

    private void setDotStatus(int page){
        layoutDot.removeAllViews();
        dots =new TextView[onBoardScreens.length];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutDot.addView(dots[i]);
        }
        //Set current dot active
        if(dots.length>0){
            dots[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }
    private void startMainActivity(){
        setFirstTimeStartStatus(false);
        startActivity(new Intent(OnboardActivity.this, MainActivity.class));
        finish();
    }

    private void setNotifBarTrans(){
        if (Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }
}