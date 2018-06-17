package com.example.administrator.fragmentpractice.Presenter;

import android.support.v7.widget.RecyclerView;

import com.example.administrator.fragmentpractice.Model.QuestionModel;
import com.example.administrator.fragmentpractice.Model.QuestionModelInfo;
import com.example.administrator.fragmentpractice.Model.QustionData;
import com.example.administrator.fragmentpractice.View.QuestionAdapter;
import com.example.administrator.fragmentpractice.View.QuestionViewListener;

import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */

public class QuestionPresenter {
    QuestionModel questionModel;
    String string;
   QuestionAdapter mQuestionAdapter;

    public QuestionPresenter(String s,QuestionAdapter questionAdapter) {
        questionModel = new QuestionModelInfo();
        mQuestionAdapter = questionAdapter;
        string = s;
    }

    public void setData(){
        questionModel.getquestion(string, new QuestionViewListener() {
            @Override
            public void onfinish(List<QustionData> list) {
                mQuestionAdapter.addData(list);
            }
        });

    }
}
