package com.example.administrator.fragmentpractice.View;

/**
 * Created by Administrator on 2018/6/5.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.fragmentpractice.Model.QustionData;
import com.example.administrator.fragmentpractice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    List<QustionData> list = new ArrayList<>();


    public QuestionAdapter() {
    }
    public void addData(List<QustionData> alist){
        list = alist;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView creat_at;
        ImageView gender;
        TextView description;
        ImageView imageView;
        TextView nicjer;


        public ViewHolder(View itemView) {
            super(itemView);
            nicjer = (TextView) itemView.findViewById(R.id.nicker);
            title = (TextView) itemView.findViewById(R.id.title);
            creat_at = (TextView) itemView.findViewById(R.id.jifen);
            gender = (ImageView) itemView.findViewById(R.id.gender);
            description = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.avatar);
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.question_item, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        QustionData qustionData = list.get(position);
        holder.title.setText(qustionData.getTitle());
        holder.description.setText(qustionData.getDescription());
        //holder.nicker.setText(qustionData.getNicker());
        if (qustionData.getNicker().equals("男")) {
            holder.gender.setImageResource(R.drawable.kebiao);
        } else {
            holder.gender.setImageResource(R.drawable.kebiao1);
        }
        holder.nicjer.setText(qustionData.getNicker());
        holder.creat_at.setText(qustionData.getCreated_at());
        //ImageLoader.build(context).setImagePlace(R.drawable.ic_launcher_background)
        //      .setBitmap(qustionData.getImage(),holder.imageView);
holder.imageView.setImageResource(R.drawable.kebiao1);

    }

    public int getItemCount() {
        return list.size();
    }
}