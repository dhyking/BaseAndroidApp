package com.dhy.baseandroidapp.mvp.model;

import java.util.List;

/**
 * Created by dhy on 2017/12/27.
 */

public interface IModel<E> {

    void setData(OnLoadDataListener<E> listener);

    interface OnLoadDataListener<E>{
        void onComplete(List<E> list);
    }
}
