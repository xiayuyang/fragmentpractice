package com.example.administrator.fragmentpractice.Model;

import com.example.administrator.fragmentpractice.Util.API;
import com.example.administrator.fragmentpractice.Util.httputil;
import com.example.administrator.fragmentpractice.View.QuestionAdapter;
import com.example.administrator.fragmentpractice.View.QuestionViewListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */

public class QuestionModelInfo implements QuestionModel {


    @Override
    public void getquestion(String string,final QuestionViewListener listener) {

        httputil.sendHttpRequest(API.qustion, "page=1&count=6&kind=" + string, new httputil.Callback() {
            @Override
            public void onResponse(String response) throws JSONException {
               List<QustionData> list;
                list = getQuestion(response);
            listener.onfinish(list);
            }
        });
    }
    public List<QustionData> getQuestion(String response) throws JSONException {


        JSONObject object = null;
        JSONArray jsonArray = null;
        object = new JSONObject(response);
        List<QustionData> mlist = new ArrayList<>();
        jsonArray = object.getJSONArray("data");

        for (int i = 0; i < jsonArray.length(); i++) {
            String title = jsonArray.getJSONObject(i).getString("title");
            String description = jsonArray.getJSONObject(i).getString("description");
            String create_at = jsonArray.getJSONObject(i).getString("created_at");
            int id = jsonArray.getJSONObject(i).getInt("id");
            String nicker = jsonArray.getJSONObject(i).getString("nickname");
            String gender = jsonArray.getJSONObject(i).getString("gender");
            String image = jsonArray.getJSONObject(i).getString("photo_thumbnail_src");

            mlist.add(new QustionData(title, description, gender,create_at, id, image, nicker));
        }


        return mlist;
    }





}
