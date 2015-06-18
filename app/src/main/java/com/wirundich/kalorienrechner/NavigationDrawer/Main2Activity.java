package com.wirundich.kalorienrechner.NavigationDrawer;

import android.annotation.TargetApi;


import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.wirundich.kalorienrechner.R;
import com.wirundich.kalorienrechner.dataclasses.DataBus;
import com.wirundich.kalorienrechner.fragments.AddItem;
import com.wirundich.kalorienrechner.fragments.Calendar;
import com.wirundich.kalorienrechner.fragments.DayView;
import com.wirundich.kalorienrechner.fragments.ListExpandableItems;
import com.wirundich.kalorienrechner.fragments.UserOverview;
import com.wirundich.kalorienrechner.fragments.UserSettings;

import java.util.Random;

public class Main2Activity extends FragmentActivity {
    private ViewPager mPager;
//    ActionBar ab;
    DataBus db;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DataBus.getInstance();
        db.initLoad(this);
        setContentView(R.layout.activity_main2);
        getSupportFragmentManager().beginTransaction().replace(R.id.slideuplayout, AddItem.newInstance(),AddItem.FRAG_NAME).commit();

//        actionBarSetup();
        initPager();
    }

    private void initPager() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
//        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if(positionOffset%1 ==0){
//                    ab.setSubtitle(mPagerAdapter.getPageTitle(position));
//                }
//                else if(positionOffset%1 ==0){
//                    ab.setSubtitle(random());
//                }
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    private void actionBarSetup() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            ab = getSupportActionBar();
//            ab.setTitle("Happa +");
//            ab.setSubtitle("sub-title");
//        }
//    }

//    @Override
//    public void onEditButtonPressed(boolean editMode) {
//        if(getSupportFragmentManager().findFragmentByTag("UserSettingsFrag")== null)
//            getSupportFragmentManager().beginTransaction().replace(R.id.contentframe_dayView, UserSettings.newInstance(0, 0, 0, 0, 0), "UserSettingsFrag").addToBackStack("UserSettings").commit();
//        else {
//            getSupportFragmentManager().popBackStack();
//        }
//    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "OverView";
//                    return ListExpandableItems.newInstance();

                case 1:
                    return "OverView Monthly";

                case 2:
                    return "Soething different";
//                    return UserOverview.newInstance();
            }
            return "";
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return DayView.newInstance();
//                    return ListExpandableItems.newInstance();

                case 1:
                  return Calendar.newInstance();

                case 2:
                    return ListExpandableItems.newInstance();
//                    return UserOverview.newInstance();

                case 3:
                    break;

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = 8;
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
