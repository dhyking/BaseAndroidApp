package com.dhy.baseandroidapp.mvp.view;

import com.dhy.baseandroidapp.bean.News;

import java.util.List;

/**
 * Created by dhy on 2018/1/19.
 */

public interface NewsView extends IView{
    void showNews(List<News> list);
    void showError();

}
