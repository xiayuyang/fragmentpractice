package com.example.administrator.fragmentpractice.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.fragmentpractice.View.TabFragment;

/**
 * Created by Administrator on 2018/6/5.
 */

public class TabViewPagerAdapter extends FragmentPagerAdapter {

    String[] mTitle = new String[5];


    public TabViewPagerAdapter(FragmentManager fm,String[] title) {
        super(fm);
        mTitle = title;


    }

    //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position % mTitle.length];
        }

        @Override
        public Fragment getItem(int position) {
            //创建Fragment并返回
            TabFragment fragment = TabFragment.newInstance(position);

            return fragment;
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }
    }

