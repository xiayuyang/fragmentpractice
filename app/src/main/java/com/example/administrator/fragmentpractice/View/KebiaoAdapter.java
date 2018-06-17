package com.example.administrator.fragmentpractice.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.fragmentpractice.View.Fragment1;

/**
 * Created by Administrator on 2018/6/5.
 */

public class KebiaoAdapter extends FragmentStatePagerAdapter{

String[] conten;

    public KebiaoAdapter(FragmentManager fm,String[] co) {
        super(fm);
        conten = co;
    }

    @Override
    public Fragment getItem(int position) {
Fragment1 fragment = new Fragment1(conten[position]);
return fragment;
    }

    @Override
    public int getCount() {
        return conten.length;
    }
}
