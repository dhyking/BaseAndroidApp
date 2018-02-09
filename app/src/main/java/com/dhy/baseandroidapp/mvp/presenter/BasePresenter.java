package com.dhy.baseandroidapp.mvp.presenter;

import com.dhy.baseandroidapp.mvp.view.IView;

import java.lang.ref.WeakReference;

/**
 * Created by dhy on 2017/12/28.
 */

public class BasePresenter<T extends IView> {
    protected WeakReference<T> mWeakReference;

    public void attachView(T view) {
        mWeakReference = new WeakReference<T>(view);
    }

    public void detach(){
        mWeakReference.clear();
    }
}
