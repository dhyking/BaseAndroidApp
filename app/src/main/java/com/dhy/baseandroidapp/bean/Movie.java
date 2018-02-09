package com.dhy.baseandroidapp.bean;

import java.io.Serializable;

/**
 * Created by dhy on 2017/12/27.
 */

public class Movie implements Serializable {


    String title;
    String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String mIcon) {
        icon = mIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }
}
