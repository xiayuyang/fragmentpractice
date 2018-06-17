package com.example.administrator.fragmentpractice.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.fragmentpractice.Presenter.QuestionPresenter;
import com.example.administrator.fragmentpractice.Util.API;
import com.example.administrator.fragmentpractice.Util.Application;
import com.example.administrator.fragmentpractice.Model.QustionData;
import com.example.administrator.fragmentpractice.R;
import com.example.administrator.fragmentpractice.Util.httputil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/5.
 */

public class TabFragment extends Fragment {
    private String mTitle;
List<QustionData> list;
    private RecyclerView[] mRv;
    private QuestionAdapter[] questionAdapter;
    private int type;
    private LinearLayoutManager mLayoutManager;
    private List<QustionData> allQuestions = new ArrayList<>();
    private List<QustionData> studyQuestions = new ArrayList<>();
    private List<QustionData> lifeQuestions = new ArrayList<>();
    private List<QustionData> emotionQuestions = new ArrayList<>();
    private List<QustionData> otherQuestions = new ArrayList<>();
    public static String[] title = {"全部","学习","生活","情感","其他"};
    public static String TAB_FRAGMENT = "tab_fragment";
    public void setTitle(String title) {
        this.mTitle = title;
    }
    public static TabFragment newInstance(int  type) {
        TabFragment fragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAB_FRAGMENT, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int ) getArguments().getSerializable(TAB_FRAGMENT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab,container,false);
        initView(view);
        return view;
    }
    protected void initView(View view) {
        mRv = new RecyclerView[5];
        questionAdapter = new QuestionAdapter[5];
        for (int i = 0; i < 5; i++) {
            mRv[i] = view.findViewById(R.id.recycle1);
        }
        mLayoutManager = new LinearLayoutManager(Application.getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv[type].setLayoutManager(mLayoutManager);
        switch (type) {
            case 0:
                questionAdapter[type] = new QuestionAdapter();
                QuestionPresenter questionPresenter1 = new QuestionPresenter(title[type],questionAdapter[type]);
                questionPresenter1.setData();

                break;
            case 1:
              // httputil.sendHttpRequest(API.qustion, "page=1&count=6&kind=" + title[type], new httputil.Callback() {
                //    @Override
                  //  public void onResponse(String response) throws JSONException {
                    //    list = getQuestion(response);
                      //  questionAdapter[type] = new QuestionAdapter(list);
                    //}
                //});
                questionAdapter[type] = new QuestionAdapter();
                QuestionPresenter questionPresenter2 = new QuestionPresenter(title[type],questionAdapter[type]);
                questionPresenter2.setData();


                break;
            case 2:
                questionAdapter[type] = new QuestionAdapter();
                QuestionPresenter questionPresenter3 = new QuestionPresenter(title[type],questionAdapter[type]);
                questionPresenter3.setData();



                break;
            case 3:
                questionAdapter[type] = new QuestionAdapter();
                QuestionPresenter questionPresenter4 = new QuestionPresenter(title[type],questionAdapter[type]);
                questionPresenter4.setData();



                break;
            case 4:
                questionAdapter[type] = new QuestionAdapter();
                QuestionPresenter questionPresenter5 = new QuestionPresenter(title[type],questionAdapter[type]);
                questionPresenter5.setData();



                break;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mRv[type].setAdapter(questionAdapter[type]);
    }

    }

