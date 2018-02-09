package com.dhy.baseandroidapp.mvp.view;

import com.dhy.baseandroidapp.bean.Movie;

import java.util.List;

/**
 * Created by dhy on 2017/12/28.
 */

public interface PicView extends IView {
    void showPic(List<Movie> list);
}
