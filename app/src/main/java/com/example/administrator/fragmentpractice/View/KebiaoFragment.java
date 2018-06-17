package com.example.administrator.fragmentpractice.View;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.fragmentpractice.R;
import com.example.administrator.fragmentpractice.View.KebiaoAdapter;

/**
 * Created by Administrator on 2018/6/5.
 */

public class KebiaoFragment extends Fragment {
    ViewPager mViewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_kebiao, container, false);
String[] conten = {"di","er","san"};
        mViewPager = (ViewPager)view.findViewById(R.id.mViewPager);
  KebiaoAdapter biaoAdapter = new KebiaoAdapter(getChildFragmentManager(),conten);
  mViewPager.setAdapter(biaoAdapter);
  mViewPager.setCurrentItem(0);
        return view;
    }

}
