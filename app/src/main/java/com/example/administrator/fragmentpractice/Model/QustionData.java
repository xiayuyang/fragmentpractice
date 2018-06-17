package com.example.administrator.fragmentpractice.Model;

/**
 * Created by Administrator on 2018/5/25.
 */

public class QustionData {
     private String title;
    private String description;
    private String kind;
   private String PHP;
    private int answernum;
    private String disappear_at;
    private String created_at;
    private int id;
    String image;
    String nicker;

    public QustionData(String mtitle, String mdescription, String gender, String mcreated_at, int mid, String mimage, String mnicker){
        title = mtitle;
        description = mdescription;
        created_at = mcreated_at;
        nicker = mnicker;
        id = mid;
        image = mimage;
        this.gender = gender;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setPHP(String PHP) {
        this.PHP = PHP;
    }

    public void setAnswernum(int answernum) {
        this.answernum = answernum;
    }

    public void setDisappear_at(String disappear_at) {
        this.disappear_at = disappear_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNicker(String nicker) {
        this.nicker = nicker;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getKind() {
        return kind;
    }

    public String getPHP() {
        return PHP;
    }

    public int getAnswernum() {
        return answernum;
    }

    public String getDisappear_at() {
        return disappear_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getNicker() {
        return nicker;
    }

    public String getGender() {
        return gender;
    }

   String gender;

}
