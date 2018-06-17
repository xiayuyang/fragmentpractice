package com.example.administrator.fragmentpractice.View;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.fragmentpractice.R;


public class viewpagerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    String[] mTitle = {"全部","学习","生活","情感","其他"};
    String[] mData = new String[5];



    public viewpagerFragment() {
        // Required empty public constructor
    }


    public static viewpagerFragment newInstance(String param1, String param2) {
        viewpagerFragment fragment = new viewpagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        mTabLayout =  (TabLayout)view.findViewById(R.id.question_tab);
        mViewPager = (ViewPager)view.findViewById(R.id.mPaper);
        initView();
        return view;
    }


    private void initView() {


        mViewPager.setAdapter(new TabViewPagerAdapter(getChildFragmentManager(),mTitle));
        //将ViewPager关联到TabLayout上
        mTabLayout.setupWithViewPager(mViewPager);



        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}




