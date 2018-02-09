package com.dhy.baseandroidapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dhy on 2018/1/19.
 */

public class News implements Parcelable{
    private String title;
    private String pic_url;
    private String time;

    protected News(Parcel in) {
        title = in.readString();
        pic_url = in.readString();
        time = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(pic_url);
        dest.writeString(time);
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String mPic_url) {
        pic_url = mPic_url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String mTime) {
        time = mTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }
}
